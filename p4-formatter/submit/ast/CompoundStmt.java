package submit.ast;

import java.util.ArrayList;

public class CompoundStmt implements Statement, Node{
    ArrayList<Statement> statements;

    public CompoundStmt(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        // TODO: varDeclaration*

        builder.append("{\n");
        for (Statement s: statements) {
            s.toCminus(builder, prefix);
        }
        builder.append("\n}\n");
    }
}
