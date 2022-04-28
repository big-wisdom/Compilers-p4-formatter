package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;

public class IfStatement implements Node, Statement {
    SimpleExpression simpleExpression;
    ArrayList<Statement> statements;
    public IfStatement(SimpleExpression simpleExpression, ArrayList<Statement> statements) {
        this.simpleExpression = simpleExpression;
        this.statements = statements;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);

        builder.append("if (");
        simpleExpression.toCminus(builder, prefix);
        builder.append(")\n");
        statements.get(0).toCminus(builder, statements.get(0).isCompound() ? prefix : prefix+" ");
        if (statements.size() > 1) {
            builder.append(prefix + "else\n");
            statements.get(1).toCminus(builder, statements.get(1).isCompound() ? prefix : prefix+" ");
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        MIPSResult condition = simpleExpression.toMIPS(code, data, symbolTable, regAllocator);

        String labelFalse = SymbolTable.getUniqueLabel();
        String labelTrue = SymbolTable.getUniqueLabel();
        code.append(String.format("bne %s $zero %s\n", condition.getRegister(), labelFalse));
        MIPSResult s1 = statements.get(0).toMIPS(code, data, symbolTable, regAllocator);

        code.append(String.format("j %s\n", labelTrue));
        code.append(String.format("%s:\n", labelFalse));
        MIPSResult s2;
        if (statements.size() > 1)
            s2 = statements.get(1).toMIPS(code, data, symbolTable, regAllocator);
        code.append(labelTrue+":\n");
        return MIPSResult.createVoidResult();
    }

    @Override
    public boolean isCompound() {
        return false;
    }
}
