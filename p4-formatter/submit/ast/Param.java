package submit.ast;

public class Param implements Node {
    VarType type;
    ParamId paramId;

    public Param(VarType type, ParamId paramId) {
        this.type = type;
        this.paramId = paramId;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(type+ " ");
        paramId.toCminus(builder, prefix);
    }
}
