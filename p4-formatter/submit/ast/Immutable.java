package submit.ast;

public class Immutable implements Node {
    Expression expression;
    Call call;
    Node constant;

    public Immutable(Expression expression) { this.expression = expression; }
    public Immutable(Call call) {
        this.call = call;
    }
    public Immutable(Node constant) {this.constant = constant;}

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        if (expression != null) {
            builder.append("(");
            expression.toCminus(builder, prefix);
            builder.append(")");
        } else if (call != null) {
            call.toCminus(builder, prefix);
        } else {
            constant.toCminus(builder, prefix);
        }
    }
}
