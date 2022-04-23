package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class ExpressionStatement implements Statement, Node {
    Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
        expression.toCminus(builder, prefix);
        builder.append(";\n");
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        expression.toMIPS(code, data,symbolTable, regAllocator);
        return MIPSResult.createVoidResult();
    }

    @Override
    public boolean isCompound() {
        return false;
    }
}
