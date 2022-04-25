/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author edwajohn
 */
public enum VarType {

  INT("int"), BOOL("bool"), CHAR("char");

  private final String value;

  private VarType(String value) {
    this.value = value;
  }

  public static VarType fromString(String s) {
    for (VarType vt : VarType.values()) {
      if (vt.value.equals(s)) {
        return vt;
      }
    }
    throw new RuntimeException("Illegal string in VarType.fromString()");
  }

  public static int typeSize(String s)
  {
    return 4; // TODO: What are their sizes?
  }

  @Override
  public String toString() {
    return value;
  }

}
