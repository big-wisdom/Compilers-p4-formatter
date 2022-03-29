package submit.ast;

import java.util.ArrayList;

public class UnaryExpression implements Expression, Node {
    ArrayList<String> unaryOps;
    Factor factor;
    public UnaryExpression(ArrayList<String> unaryOps, Factor factor) {
        this.unaryOps = unaryOps;
        this.factor = factor;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        for (String op: unaryOps) builder.append(op);
        factor.toCminus(builder, prefix);
    }
}
