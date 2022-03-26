package submit.ast;

import parser.CminusParser;

public class UnaryRelExpression implements Expression, Node {
    String bangs;
    RelExpression relExpression;
    public UnaryRelExpression(String bangs, RelExpression relExpression) {
        this.bangs = bangs;
        this.relExpression = relExpression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(bangs);
        relExpression.toCminus(builder, prefix);
    }
}
