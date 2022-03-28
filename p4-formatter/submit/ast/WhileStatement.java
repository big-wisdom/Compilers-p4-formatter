package submit.ast;

public class WhileStatement implements Statement, Node {
    SimpleExpression simpleExpression;
    Statement statement;

    public WhileStatement(SimpleExpression simpleExpression, Statement statement) {
        this.simpleExpression = simpleExpression;
        this.statement = statement;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        builder.append("while(" );
        simpleExpression.toCminus(builder, prefix+"\t");
        builder.append(")\n");
        statement.toCminus(builder, prefix);
    }
}
