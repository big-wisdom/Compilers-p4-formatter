package submit.ast;

import java.util.ArrayList;

public class SumExpression implements Expression, Node {
    ArrayList<TermExpression> termExpressions;
    public SumExpression(ArrayList<TermExpression> termExpressions) {
        this.termExpressions = termExpressions;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        // TODO: (sumExpression relop)*
        termExpressions.get(0).toCminus(builder, prefix);
    }
}
