package submit.ast;

public class ParamId implements Node {
    String id;
    boolean array;
    public ParamId(String id, boolean array) {
        this.id = id;
        this.array = array;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(id);
        if (array) builder.append("[]");
    }
}
