package submit.ast;

import java.util.ArrayList;

public class RelExpression implements Expression, Node {
    ArrayList<SumExpression> sumExpressions;
    public RelExpression(ArrayList<SumExpression> sumExpressions) {
        this.sumExpressions = sumExpressions;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        // TODO: (sumExpression relop)*
        sumExpressions.get(0).toCminus(builder, prefix);
    }
}
