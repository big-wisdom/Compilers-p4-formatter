package submit.ast;

public class ExpressionStatement implements Statement, Node {
    Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        expression.toCminus(builder, prefix);
        builder.append(";");
    }
}
