package submit.ast;

import java.util.ArrayList;

public class IfStatement implements Node, Statement {
    SimpleExpression simpleExpression;
    ArrayList<Statement> statements;
    public IfStatement(SimpleExpression simpleExpression, ArrayList<Statement> statements) {
        this.simpleExpression = simpleExpression;
        this.statements = statements;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        builder.append("if (");
        simpleExpression.toCminus(builder, prefix);
        builder.append(")\n");
        statements.get(0).toCminus(builder, prefix);
        if (statements.size() > 1) {
            builder.append(prefix + "else\n");
            statements.get(1).toCminus(builder, prefix);
        }
    }
}
