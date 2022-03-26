package submit.ast;

public class Factor implements Node {
    Immutable immutable;
    Mutable mutable;
    public Factor(Immutable immutable) {
        this.immutable = immutable;
    }

    public Factor(Mutable mutable) {
        this.mutable = mutable;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        if (immutable != null)
            immutable.toCminus(builder, prefix);
        else
            mutable.toCminus(builder, prefix);
    }
}
