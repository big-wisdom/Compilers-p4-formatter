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
        String trueLabel = SymbolTable.getUniqueLabel();
        String falseLabel = SymbolTable.getUniqueLabel();
        code.append(String.format("%s:\n", trueLabel));

        MIPSResult condition = simpleExpression.toMIPS(code, data, symbolTable, regAllocator);
        code.append(String.format("bne %s $zero %s\n", condition.getRegister(), falseLabel));

        statement.toMIPS(code, data, symbolTable, regAllocator);

        code.append(String.format("j %s\n", trueLabel));
        code.append(String.format("%s:\n", falseLabel));

        return MIPSResult.createVoidResult();
    }

    @Override
    public boolean isCompound() {
        return false;
    }
}
