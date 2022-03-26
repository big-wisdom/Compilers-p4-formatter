package submit.ast;

public class UnaryExpression implements Expression, Node {
    Factor factor;
    public UnaryExpression(Factor factor) {
        this.factor = factor;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        // TODO: unaryop*
        factor.toCminus(builder, prefix);
    }
}
