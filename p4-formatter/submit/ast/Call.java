package submit.ast;

import java.util.ArrayList;

public class Call implements Node {
    String id;
    ArrayList<Expression> expressions;
    public Call(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        // TODO: (expression ',')*
        // also why expresion?
        builder.append(id);
        builder.append("(");
        expressions.get(0).toCminus(builder, prefix);
        builder.append(")");
    }
}
