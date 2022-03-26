package submit.ast;

import parser.CminusParser;

public class UnaryRelExpression implements Expression, Node {
    RelExpression relExpression;
    public UnaryRelExpression(RelExpression relExpression) {
        this.relExpression = relExpression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        // TODO: BANG*
        relExpression.toCminus(builder, prefix);
    }
}
