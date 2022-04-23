package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class SimpleExpression implements Expression, Node{
    OrExpression orExpression;
    public SimpleExpression(OrExpression orExpression) {
        this.orExpression = orExpression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        orExpression.toCminus(builder, prefix);
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        return MIPSResult.createVoidResult();
    }
}
