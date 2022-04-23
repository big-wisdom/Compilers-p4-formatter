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
        // update the stack pointer
        code.append("# Update the stack pointer\n");

        ArrayList<MIPSResult> expressionResults = new ArrayList<>();
        for (Expression exp: expressions)
        {
            expressionResults.add(exp.toMIPS(code, data, symbolTable, regAllocator));
        }

        if (Objects.equals(id, "println"))
        {
            code.append("# println\n");
            code.append(String.format("la $a0 %s\n", expressionResults.get(0).getAddress()));
            code.append("li $v0 4\n");
            code.append("syscall\n");
            code.append("la $a0 newline\n");
            code.append("li $v0 4\n");
            code.append("syscall\n");
        }

        // return stack pointer

        return MIPSResult.createVoidResult();
    }
}
