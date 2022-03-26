package submit.ast;

public class SimpleExpression implements Expression, Node{
    OrExpression orExpression;
    public SimpleExpression(OrExpression orExpression) {
        this.orExpression = orExpression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        orExpression.toCminus(builder, prefix);
    }
}
