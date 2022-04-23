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
        return MIPSResult.createVoidResult();
    }

    @Override
    public boolean isCompound() {
        return false;
    }
}
