/*
 * Code formatter project
 * CS 4481
 */
package submit;

import submit.ast.VarType;

/**
 *
 * @author edwajohn
 */
public class SymbolInfo {

  private final String id;
  // In the case of a function, type is the return type
  public final VarType type;
  private final boolean function;
  public int size;
  public int offset;

  public SymbolInfo(String id, VarType type, boolean function, int offset) {
    this.id = id;
    this.type = type;
    this.function = function;
    this.size = type == null ? 0 : VarType.typeSize(type.toString());
    this.offset = offset;
  }

  @Override
  public String toString() {
    return "<" + id + ", " + type + '>';
  }

}
