/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import org.antlr.v4.runtime.Parser;
import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.logging.Logger;

/**
 *
 * @author edwajohn
 */
public class StringConstant implements Expression {

  private final String value;

  public StringConstant(String value) {
    this.value = value;
  }

  public void toCminus(StringBuilder builder, final String prefix) {
    builder.append("\"").append(value).append("\"");
  }

  @Override
  public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
    return MIPSResult.createAddressResult(symbolTable.getUniqueLabel(), VarType.CHAR);
  }

}
