package submit;

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

  public SymbolTable() {
    table = new HashMap<>();
    table.put("println", new SymbolInfo("println", null, true));
    parent = null;
    children = new ArrayList<>();
  }

  public void addSymbol(String id, SymbolInfo symbol) {
    table.put(id, symbol);
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
