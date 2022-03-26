package submit.ast;

import java.util.ArrayList;

public class AndExpression implements Expression, Node {
    ArrayList<UnaryRelExpression> unaryRelExpressions;
    public AndExpression(ArrayList<UnaryRelExpression> unaryRelExpressions) {
        this.unaryRelExpressions = unaryRelExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        // TODO: (unaryRelExpression '&&')*
        unaryRelExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i<unaryRelExpressions.size(); i++) {
            builder.append("&&");
            unaryRelExpressions.get(i).toCminus(builder, prefix);
        }
    }
}
