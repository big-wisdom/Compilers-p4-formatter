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
        if (immutable != null)
            return immutable.toMIPS(code, data, symbolTable, regAllocator);
        else
        {
            MIPSResult result = mutable.toMIPS(code, data, symbolTable, regAllocator);
            code.append(String.format("# Load the value of %s\n", mutable.id));
            String newRegister = regAllocator.getT();
            code.append(String.format("lw %s 0(%s)\n", newRegister, result.getRegister()));
            // release the register of the offput
            regAllocator.clear(result.getRegister());
            return MIPSResult.createRegisterResult(newRegister, result.getType());
        }
    }
}
