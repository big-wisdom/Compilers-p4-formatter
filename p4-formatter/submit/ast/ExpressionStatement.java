package submit.ast;

public class ExpressionStatement implements Statement, Node {
    Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        expression.toCminus(builder, prefix);
        builder.append(";");
        // TODO: figure out newline
//        if (builder.charAt(builder.length() -1) != '\n') {
//            System.out.println(builder.subSequence(builder.length()-3, builder.length()));
//            builder.append("\n");
//        }
    }
}
