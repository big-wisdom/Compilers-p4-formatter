package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;

public class UnaryExpression implements Expression, Node {
    ArrayList<String> unaryOps;
    Factor factor;
    public UnaryExpression(ArrayList<String> unaryOps, Factor factor) {
        this.unaryOps = unaryOps;
        this.factor = factor;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        for (String op: unaryOps) builder.append(op);
        factor.toCminus(builder, prefix);
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        MIPSResult factorReg = factor.toMIPS(code, data, symbolTable, regAllocator);
        // TODO: implement * and ? operators
        for (String op: unaryOps) {
            if (op.equals("-"))
            {
                code.append(String.format("sub %s $zero %s\n", factorReg.getRegister(), factorReg.getRegister()));
            }
        }
        return factorReg;
    }
}
