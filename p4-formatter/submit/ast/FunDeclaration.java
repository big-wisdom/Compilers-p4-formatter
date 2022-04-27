package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.ArrayList;

public class FunDeclaration implements Declaration, Node {
    VarType type;
    String id;
    Statement statement;
    ArrayList<Param> params;

    public FunDeclaration(VarType type, String id, ArrayList<Param> params, Statement statement) {
        this.type = type;
        this.id = id;
        this.params = params;
        this.statement = statement;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("\n"+prefix);

        if (type != null) {
            builder.append(type+ " ");
        } else {
            builder.append("void ");
        }

        builder.append(id+ "(");
        if (params.size() != 0) {
            params.get(0).toCminus(builder, prefix);
            for (int i=1; i< params.size(); i++) {
                builder.append(", ");
                params.get(i).toCminus(builder, prefix);
            }
        }
        builder.append(")\n");
        statement.toCminus(builder, prefix);
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        code.append("\n# code for "+id+"\n").append(id).append(":\n");
        MIPSResult result = statement.toMIPS(code, data, symbolTable, regAllocator);
        if (!id.equals("main"))
            code.append("jr $ra\n");
        regAllocator.clearAll();
        return result;
    }

    @Override
    public boolean isCompound() {
        return false;
    }
}
