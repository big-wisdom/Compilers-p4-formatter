package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;

public class AndExpression implements Expression, Node {
    ArrayList<UnaryRelExpression> unaryRelExpressions;
    public AndExpression(ArrayList<UnaryRelExpression> unaryRelExpressions) {
        this.unaryRelExpressions = unaryRelExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        // TODO: (unaryRelExpression '&&')*
        unaryRelExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i<unaryRelExpressions.size(); i++) {
            builder.append(" && ");
            unaryRelExpressions.get(i).toCminus(builder, prefix);
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        for (UnaryRelExpression u : unaryRelExpressions)
        {
            // TODO: I will need to combine MIPSResults
            return u.toMIPS(code, data, symbolTable, regAllocator);
        }
        return MIPSResult.createVoidResult();
    }
}
