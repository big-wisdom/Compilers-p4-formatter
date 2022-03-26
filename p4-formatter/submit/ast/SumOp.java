package submit.ast;

public class SumOp implements Node {
    String op;
    public SumOp(String op) {
        this.op = op;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(" "+op+" ");
    }
}
