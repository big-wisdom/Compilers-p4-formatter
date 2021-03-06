package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;

public class RelExpression implements Expression, Node {
    ArrayList<Relop> relops;
    ArrayList<SumExpression> sumExpressions;
    public RelExpression(ArrayList<Relop> relops, ArrayList<SumExpression> sumExpressions) {
        this.relops = relops;
        this.sumExpressions = sumExpressions;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        sumExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i< sumExpressions.size(); i++) {
            relops.get(i-1).toCminus(builder, prefix);
            sumExpressions.get(i).toCminus(builder, prefix);
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        MIPSResult resultReg = sumExpressions.get(0).toMIPS(code, data, symbolTable, regAllocator);
        MIPSResult currentReg;
        for (int i=1; i<sumExpressions.size(); i++)
        {
            currentReg = sumExpressions.get(i).toMIPS(code, data, symbolTable, regAllocator);
            // all will return zero in the register if the condition is true;
            if (relops.get(i-1).op.equals("<"))
            {
                code.append(String.format("slt %s %s %s\n", resultReg.getRegister(), resultReg.getRegister(), currentReg.getRegister()));
                code.append(String.format("subi %s %s 1\n", resultReg.getRegister(), resultReg.getRegister()));
            }
            else if (relops.get(i-1).op.equals(">"))
            {
                // just switch the two from last time
                code.append(String.format("slt %s %s %s\n", resultReg.getRegister(), currentReg.getRegister(), resultReg.getRegister()));
                code.append(String.format("subi %s %s 1\n", resultReg.getRegister(), resultReg.getRegister()));
            }
            else if (relops.get(i-1).op.equals("<="))
            {
                code.append(String.format("slt %s %s %s\n", resultReg.getRegister(), currentReg.getRegister(), resultReg.getRegister()));
            }
            else if (relops.get(i-1).op.equals(">="))
            {
                code.append(String.format("slt %s %s %s\n", resultReg.getRegister(), resultReg.getRegister(), currentReg.getRegister()));
            }
            else if (relops.get(i-1).op.equals("=="))
            {
                code.append(String.format("sub %s %s %s\n", resultReg.getRegister(), resultReg.getRegister(), currentReg.getRegister()));
            }
            regAllocator.clear(currentReg.getRegister());
        }
        return resultReg;
    }
}
