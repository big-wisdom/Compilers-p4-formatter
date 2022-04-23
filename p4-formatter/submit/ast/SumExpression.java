package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;

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
        for (TermExpression t: termExpressions)
        {
            // TODO: this will soon need to sum the values and return the result
            return t.toMIPS(code, data, symbolTable, regAllocator);
        }
        return MIPSResult.createVoidResult();
    }
}
