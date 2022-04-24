package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;
import java.util.Objects;

public class TermExpression implements Expression, Node {
    ArrayList<Mulop> mulops;
    ArrayList<UnaryExpression> unaryExpressions;

    public TermExpression(ArrayList<Mulop> mulops, ArrayList<UnaryExpression> unaryExpressions) {
        this.mulops = mulops;
        this.unaryExpressions = unaryExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        unaryExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i< unaryExpressions.size(); i++) {
            mulops.get(i-1).toCminus(builder, prefix);
            unaryExpressions.get(i).toCminus(builder, prefix);
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        ArrayList<MIPSResult> results = new ArrayList<>();
        for (UnaryExpression u: unaryExpressions)
        {
            results.add(u.toMIPS(code, data, symbolTable, regAllocator));
        }

        // they should each be in a register, so I just need to do some multiplication and addition
        if (results.size() > 1)
        {
            String previousResultRegister = "";
            for (int i=0; i<results.size()-1; i++)
            {
                previousResultRegister = results.get(i).getRegister();
                // do the sumop in between these two
                if (Objects.equals(mulops.get(i).op, "*"))
                {
                    code.append(String.format("mult %s %s\n", previousResultRegister, results.get(i+1).getRegister()));
                }
                if (Objects.equals(mulops.get(i).op, "/"))
                {
                    code.append(String.format("div %s %s\n", previousResultRegister, results.get(i+1).getRegister()));
                }
                code.append(String.format("mflo %s\n", previousResultRegister));
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
