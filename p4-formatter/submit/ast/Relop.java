package submit.ast;

public class Relop implements Node {
    String op;
    public Relop(String op) {
        this.op = op;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(op);
    }
}
