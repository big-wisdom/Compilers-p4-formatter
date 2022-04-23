package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class ParamId implements Node {
    String id;
    boolean array;
    public ParamId(String id, boolean array) {
        this.id = id;
        this.array = array;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(id);
        if (array) builder.append("[]");
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        return MIPSResult.createVoidResult();
    }
}
