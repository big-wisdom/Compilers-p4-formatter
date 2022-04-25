package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

public class AssignmentExpression implements Node, Expression {
    Mutable mutable;
    String operator;
    Expression expression;
    public AssignmentExpression(Mutable mutable, String operator, Expression expression) {
        this.mutable = mutable;
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        mutable.toCminus(builder, prefix);
        if (expression != null) {
            builder.append(" "+ operator);
            builder.append(" ");
            expression.toCminus(builder, prefix);
        } else {
            builder.append(operator);
        }
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        // compute register of the mutable
        MIPSResult mutableResult = mutable.toMIPS(code, data, symbolTable, regAllocator);
        String mReg = mutableResult.getRegister();

        // add stack pointer
        code.append("# Add the stack pointer address to the offset.\n");
        code.append(String.format("add %s %s $sp\n", mReg, mReg));

        // compute the right hand side
        code.append("# Compute rhs for assignment =\n");
        MIPSResult expResult = expression.toMIPS(code, data, symbolTable, regAllocator);
        String expReg = expResult.getRegister();

        // store the new value at the offset in the mutable register
        code.append("# complete assignment statement with store\n");
        code.append(String.format("sw %s 0(%s)\n", expReg, mReg));

        regAllocator.clearAll();
        return MIPSResult.createVoidResult();
    }
}
