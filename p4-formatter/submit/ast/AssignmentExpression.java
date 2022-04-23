package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class AssignmentExpression implements Node, Expression {
    Mutable mutable;
    String operator;
    Expression expression;
    public AssignmentExpression(Mutable mutable, String operator, Expression expression) {
        this.mutable = mutable;
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        mutable.toCminus(builder, prefix);
        if (expression != null) {
            builder.append(" "+ operator);
            builder.append(" ");
            expression.toCminus(builder, prefix);
        } else {
            builder.append(operator);
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        return MIPSResult.createVoidResult();
    }
}
