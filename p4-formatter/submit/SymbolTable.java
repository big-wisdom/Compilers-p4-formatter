package submit;

import submit.ast.VarType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Code formatter project
 * CS 4481
 */
/**
 *
 */
public class SymbolTable {

  private final HashMap<String, SymbolInfo> table;
  private SymbolTable parent;
  private final List<SymbolTable> children;
  private int size = 0;
  private int currentOffset = 0;

  public SymbolTable() {
    table = new HashMap<>();
    table.put("println", new SymbolInfo("println", null, true, currentOffset));
    parent = null;
    children = new ArrayList<>();
  }

  // add symbol to the table and update offset and size data members
  public void addSymbol(String id, VarType type, boolean function) {
    int typeSize = type == null ? 0 : VarType.typeSize(type.toString());
    currentOffset -= typeSize;
    table.put(id, new SymbolInfo(id, type, function, currentOffset));
    size += typeSize;
  }

  /**
   * Returns null if no symbol with that id is in this symbol table or an
   * ancestor table.
   *
   * @param id
   * @return
   */
  public SymbolInfo find(String id) {
    if (table.containsKey(id)) {
      return table.get(id);
    }
    if (parent != null) {
      return parent.find(id);
    }
    return null;
  }

  /**
   * Returns the new child.
   *
   * @return
   */
  public SymbolTable createChild() {
    SymbolTable child = new SymbolTable();
    children.add(child);
    child.parent = this;
    return child;
  }

  // TODO: method to compute the size of activation record
  int label = 0;
  public String getUniqueLabel() {
    return "datalabel" + label;
  }

  public SymbolTable getParent() {
    return parent;
  }

  public ArrayList<String> getKeys()
  {
    ArrayList<String> keys = new ArrayList<>();
    for (String s: table.keySet())
    {
      keys.add(s);
    }
    return keys;
  }
}
