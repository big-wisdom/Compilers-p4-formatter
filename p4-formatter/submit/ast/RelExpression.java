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
        return MIPSResult.createVoidResult();
    }
}
