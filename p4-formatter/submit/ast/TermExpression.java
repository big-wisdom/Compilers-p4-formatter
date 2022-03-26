package submit.ast;

import java.util.ArrayList;

public class TermExpression implements Expression, Node {
    ArrayList<UnaryExpression> unaryExpressions;
    public TermExpression(ArrayList<UnaryExpression> unaryExpressions) {
        this.unaryExpressions = unaryExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        // TODO: (unary mulop)*
        unaryExpressions.get(0).toCminus(builder, prefix);
    }
}
