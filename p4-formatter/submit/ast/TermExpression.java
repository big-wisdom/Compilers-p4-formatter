package submit.ast;

import java.util.ArrayList;

public class TermExpression implements Expression, Node {
    ArrayList<Mulop> mulops;
    ArrayList<UnaryExpression> unaryExpressions;

    public TermExpression(ArrayList<Mulop> mulops, ArrayList<UnaryExpression> unaryExpressions) {
        this.mulops = mulops;
        this.unaryExpressions = unaryExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        unaryExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i< unaryExpressions.size(); i++) {
            mulops.get(i-1).toCminus(builder, prefix);
            unaryExpressions.get(i).toCminus(builder, prefix);
        }
    }
}
