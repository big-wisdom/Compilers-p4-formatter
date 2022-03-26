package submit.ast;

public class Mulop implements Node{
    String op;
    public Mulop(String op) {
        this.op = op;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(" "+op+" ");
    }
}
