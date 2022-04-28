/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

/**
 *
 * @author edwajohn
 */
public class Return implements Statement {

  private final Expression expr;

  public Return(Expression expr) {
    this.expr = expr;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(prefix);
    if (expr == null) {
      builder.append("return;\n");
    } else {
      builder.append("return ");
      expr.toCminus(builder, prefix);
      builder.append(";\n");
    }
  }

  @Override
  public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
    // add return symbol info to table
    MIPSResult resultReg = expr.toMIPS(code, data, symbolTable, regAllocator);
    // add return value to stack
    code.append(String.format("sw %s %d($sp)\n", resultReg.getRegister(), symbolTable.currentOffset));
    code.append("jr $ra\n");

    return MIPSResult.createVoidResult();
  }

  @Override
  public boolean isCompound() {
    return false;
  }
}
