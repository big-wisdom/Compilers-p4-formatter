package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolInfo;
import submit.SymbolTable;

import java.util.ArrayList;

public class CompoundStmt implements Statement, Node{
    ArrayList<Declaration> declarations;
    ArrayList<Statement> statements;

    public CompoundStmt(ArrayList<Declaration> declarations, ArrayList<Statement> statements) {
        this.declarations = declarations;
        this.statements = statements;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        if (declarations.size() != 0 || statements.size() != 0) {
            builder.append(prefix+"{\n");
            for (Declaration d: declarations) {
                d.toCminus(builder, prefix+"  ");
            }

            for (Statement s: statements) {
                s.toCminus(builder, prefix+"  ");
            }
            builder.append(prefix+"}\n");
        }
        else builder.append(prefix).append("{\n"+prefix+"}\n");
    }

    @Override
    public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
        code.append("# TODO: Entering a new scope.\n");
        code.append("# TODO?: Symbols in symbol table:\n");
        for (String key: symbolTable.getKeys())
        {
            SymbolInfo symbol = symbolTable.find(key);
            code.append("# " + symbol.toString() + "\n");
        }

        for (Declaration d: declarations) {
            d.toMIPS(code, data, symbolTable, regAllocator);
        }

        for (Statement s: statements) {
            s.toMIPS(code, data, symbolTable, regAllocator);
        }

        code.append("# TODO: Exiting scope.\n");
        return MIPSResult.createVoidResult();
    }

    @Override
    public boolean isCompound() {
        return true;
    }
}
