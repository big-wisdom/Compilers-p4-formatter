package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;

public class OrExpression implements Expression, Node{
    ArrayList<AndExpression> andExpressions;

    public OrExpression(ArrayList<AndExpression> andExpressions) {
        this.andExpressions = andExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        andExpressions.get(0).toCminus(builder, prefix);
        for (int i=1; i<andExpressions.size(); i++){
            builder.append(" || ");
            andExpressions.get(i).toCminus(builder, prefix);
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        for (AndExpression and: andExpressions) {
            // if true, return true
            return and.toMIPS(code, data, symbolTable, regAllocator);
        }

        // return false
        return MIPSResult.createVoidResult();
    }
}
