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
        ArrayList<MIPSResult> results = new ArrayList<>();
        for (TermExpression t: termExpressions)
        {
            results.add(t.toMIPS(code, data, symbolTable, regAllocator));
        }

        // they should each be in a register, so I just need to do some addition
        if (results.size() > 1)
        {
            String previousResultRegister = "";
            for (int i=0; i<results.size()-1; i++)
            {
                previousResultRegister = results.get(i).getRegister();
                // do the sumop in between these two
                if (Objects.equals(sumops.get(i).op, "+"))
                    code.append(String.format("add %s %s %s\n", previousResultRegister, previousResultRegister, results.get(i+1).getRegister()));
                if (Objects.equals(sumops.get(i).op, "-"))
                    code.append(String.format("sub %s %s %s\n", previousResultRegister, previousResultRegister, results.get(i+1).getRegister()));
            }
            if (!previousResultRegister.equals(""))
                return MIPSResult.createRegisterResult(previousResultRegister, VarType.INT);
            else
                return MIPSResult.createVoidResult(); // this should never run because I check loop size before jumping in here
        } else {
            return results.get(0);
        }
    }
}
