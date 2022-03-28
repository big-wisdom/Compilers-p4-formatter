package submit.ast;

import java.util.ArrayList;

public class Call implements Node {
    String id;
    ArrayList<Expression> expressions;
    public Call(String id, ArrayList<Expression> expressions) {
        this.id = id;
        this.expressions = expressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(id);
        builder.append("(");

        if (expressions.size() > 0) {
            expressions.get(0).toCminus(builder, prefix);
            for (int i=1; i < expressions.size(); i++) {
                builder.append(", ");
                expressions.get(i).toCminus(builder, prefix);
            }
        }

        builder.append(")");
    }
}
