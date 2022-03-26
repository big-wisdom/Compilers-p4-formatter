package submit.ast;

import java.util.ArrayList;

public class CompoundStmt implements Statement, Node{
    ArrayList<Declaration> declarations;
    ArrayList<Statement> statements;

    public CompoundStmt(ArrayList<Declaration> declarations, ArrayList<Statement> statements) {
        this.declarations = declarations;
        this.statements = statements;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        if (declarations.size() != 0 || statements.size() != 0) {
            builder.append("\n"+prefix+"{\n");
            for (Declaration d: declarations) {
                d.toCminus(builder, prefix+'\t');
            }

            for (Statement s: statements) {
                s.toCminus(builder, prefix+'\t');
            }
            builder.append(prefix+"}\n");
        }
        else builder.append(prefix).append("{}\n");
    }
}
