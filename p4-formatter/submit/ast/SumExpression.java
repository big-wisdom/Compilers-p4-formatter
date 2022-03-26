package submit.ast;

import java.util.ArrayList;

public class SumExpression implements Expression, Node {
    ArrayList<SumOp> sumops;
    ArrayList<TermExpression> termExpressions;
    public SumExpression(ArrayList<SumOp> sumops, ArrayList<TermExpression> termExpressions) {
        this.sumops = sumops;
        this.termExpressions = termExpressions;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        termExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i< termExpressions.size(); i++) {
            sumops.get(i-1).toCminus(builder, prefix);
            termExpressions.get(i).toCminus(builder, prefix);
        }
    }
}
