package submit.ast;

public class BreakStatement implements Statement, Node {
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("break");
    }
}