package submit.ast;

public class BreakStatement implements Statement, Node {
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
        builder.append("break;\n");
    }

    @Override
    public boolean isCompound() {
        return false;
    }
}
