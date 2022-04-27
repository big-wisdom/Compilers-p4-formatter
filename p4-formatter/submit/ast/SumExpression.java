package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;
import java.util.Objects;

public class SumExpression implements Expression, Node {
    ArrayList<SumOp> sumops;
    ArrayList<TermExpression> termExpressions;
    public SumExpression(ArrayList<SumOp> sumops, ArrayList<TermExpression> termExpressions) {
        this.sumops = sumops;
        this.termExpressions = termExpressions;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        termExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i< termExpressions.size(); i++) {
            sumops.get(i-1).toCminus(builder, prefix);
            termExpressions.get(i).toCminus(builder, prefix);
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        // returnReg = firstTermReg
        MIPSResult returnReg = termExpressions.get(0).toMIPS(code, data, symbolTable, regAllocator);
        // foreach term in termExpressions[1] and beyond
        for (int i=1; i<termExpressions.size(); i++)
        {
            // add this ones value into the return reg
            MIPSResult thisOnesValue = termExpressions.get(i).toMIPS(code, data, symbolTable, regAllocator);
            if (Objects.equals(sumops.get(i-1).op, "+"))
                code.append(String.format("add %s %s %s\n", returnReg.getRegister(), returnReg.getRegister(), thisOnesValue.getRegister()));
            if (Objects.equals(sumops.get(i-1).op, "-"))
                code.append(String.format("sub %s %s %s\n", returnReg.getRegister(), returnReg.getRegister(), thisOnesValue.getRegister()));
        }
        return returnReg;
    }
}
