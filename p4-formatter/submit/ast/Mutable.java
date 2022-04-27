/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolInfo;
import submit.SymbolTable;

/**
 *
 * @author edwajohn
 */
public class Mutable implements Expression, Node {

  public final String id;
  private final Expression index;

  public Mutable(String id, Expression index) {
    this.id = id;
    this.index = index;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(id);
    if (index != null) {
      builder.append("[");
      index.toCminus(builder, prefix);
      builder.append("]");
    }
  }

  @Override
  public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
    code.append(String.format("# Get %s's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later\n", id));

    //////// store in a register
    // get offset from symbol table
    SymbolInfo info = symbolTable.find(id);
    String reg = regAllocator.getT();
    code.append(String.format("li %s %d\n", reg, symbolTable.findOffset(id)));

    // add stack pointer
    code.append("# Add the stack pointer address to the offset.\n");
    code.append(String.format("add %s %s $sp\n", reg, reg));

    // return a register MIPSResult
    return MIPSResult.createRegisterResult(reg, info.type);
  }

}
