package submit.ast;

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
        builder.append(prefix);

        mutable.toCminus(builder, prefix);
        builder.append(" "+ operator);
        if (expression != null) {
            builder.append(" ");
            expression.toCminus(builder, prefix);
        }
    }
}
