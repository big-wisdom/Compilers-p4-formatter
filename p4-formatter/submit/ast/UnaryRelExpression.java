package submit.ast;

import parser.CminusParser;
import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class UnaryRelExpression implements Expression, Node {
    String bangs;
    RelExpression relExpression;
    public UnaryRelExpression(String bangs, RelExpression relExpression) {
        this.bangs = bangs;
        this.relExpression = relExpression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(bangs);
        relExpression.toCminus(builder, prefix);
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        return relExpression.toMIPS(code, data, symbolTable, regAllocator);
    }
}
