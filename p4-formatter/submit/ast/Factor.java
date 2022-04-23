package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

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

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        return MIPSResult.createVoidResult();
    }
}
