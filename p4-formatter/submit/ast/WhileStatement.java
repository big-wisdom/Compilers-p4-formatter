package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class WhileStatement implements Statement, Node {
    SimpleExpression simpleExpression;
    Statement statement;

    public WhileStatement(SimpleExpression simpleExpression, Statement statement) {
        this.simpleExpression = simpleExpression;
        this.statement = statement;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        builder.append("while(" );
        simpleExpression.toCminus(builder, prefix+"\t");
        builder.append(")\n");
        statement.toCminus(builder, statement.isCompound()? prefix: prefix+" ");
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        return MIPSResult.createVoidResult();
    }

    @Override
    public boolean isCompound() {
        return false;
    }
}
