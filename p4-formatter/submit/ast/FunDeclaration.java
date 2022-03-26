package submit.ast;

import java.util.ArrayList;

public class FunDeclaration implements Declaration, Node {
    VarType type;
    String id;
    Statement statement; // TODO: implement statements
    ArrayList<Param> params; // TODO: implement parameters

    public FunDeclaration(VarType type, String id, ArrayList<Param> params, Statement statement) {
        this.type = type;
        this.id = id;
        this.params = params;
        this.statement = statement;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        if (type != null) {
            builder.append(type+ " ");
        } else {
            builder.append("void ");
        }

        builder.append(id+ " (");
        for (Param p: params) {
            p.toCminus(builder, prefix);
        }
        builder.append(")");
        statement.toCminus(builder, prefix);
    }
}
