package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class SumOp implements Node {
    public String op;
    public SumOp(String op) {
        this.op = op;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(" "+op+" ");
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        return MIPSResult.createVoidResult();
    }
}
