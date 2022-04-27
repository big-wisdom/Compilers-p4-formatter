package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolInfo;
import submit.SymbolTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Call implements Node {
    String id;
    ArrayList<Expression> expressions;
    public Call(String id, ArrayList<Expression> expressions) {
        this.id = id;
        this.expressions = expressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(id);
        builder.append("(");

        if (expressions.size() > 0) {
            expressions.get(0).toCminus(builder, prefix);
            for (int i=1; i < expressions.size(); i++) {
                builder.append(", ");
                expressions.get(i).toCminus(builder, prefix);
            }
        }

        builder.append(")");
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        if (Objects.equals(id, "println"))
        {
            code.append("# println\n");
            // evaluate parameters
            ArrayList<MIPSResult> expressionResults = new ArrayList<>();
            for (Expression exp: expressions)
            {
                expressionResults.add(exp.toMIPS(code, data, symbolTable, regAllocator));
            }
            // check what type of MIPS result the expression gave
            String value;
            if ((value = expressionResults.get(0).getAddress()) != null)
            {
                code.append(String.format("la $a0 %s\n", value));
                code.append("li $v0 4\n");
            } else if ((value = expressionResults.get(0).getRegister()) != null)
            {
                code.append(String.format("move $a0 %s\n", value));
                code.append("li $v0 1\n");
                regAllocator.clearAll();
            }
            code.append("syscall\n");
            code.append("la $a0 newline\n");
            code.append("li $v0 4\n");
            code.append("syscall\n");
        } else
        {
            code.append(String.format("# Calling function %s\n", id));

            code.append("# Save $ra to a register\n");
            String raReg = regAllocator.getT();
            code.append(String.format("move %s $ra\n", raReg));

            code.append("# Save $t0-9 registers\n");
            int spAdjust = regAllocator.saveT(code, symbolTable.size) + symbolTable.size;

            code.append("# Evaluate parameters and save to stack\n");
            ArrayList<MIPSResult> expressionResults = new ArrayList<>();
            for (Expression exp: expressions)
            {
                // TODO: add results to the stack
                expressionResults.add(exp.toMIPS(code, data, symbolTable, regAllocator));
            }

            code.append("# Update the stack pointer\n");
            code.append(String.format("add $sp $sp -%d\n", spAdjust));

            code.append("# Call the function\n");
            code.append(String.format("jal %s\n", id));

            code.append("# Restore the stack pointer\n");
            code.append(String.format("add $sp $sp %d\n", spAdjust));

            code.append("# Restore the $t0-9 registers\n");
            regAllocator.restoreT(code, symbolTable.size);

            code.append("# Restore $ra\n");
            code.append(String.format("move $ra %s\n", raReg));
        }
        return MIPSResult.createVoidResult();
    }
}
