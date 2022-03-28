package submit;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import parser.CminusBaseVisitor;
import parser.CminusParser;
import submit.ast.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ASTVisitor extends CminusBaseVisitor<Node> {
    private final Logger LOGGER;
    private SymbolTable symbolTable;

    public ASTVisitor(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    private VarType getVarType(CminusParser.TypeSpecifierContext ctx) {
        final String t = ctx.getText();
        return (t.equals("int")) ? VarType.INT : (t.equals("bool")) ? VarType.BOOL : VarType.CHAR;
    }

    @Override public Node visitProgram(CminusParser.ProgramContext ctx) {
        symbolTable = new SymbolTable();
        List<Declaration> decls = new ArrayList<>();
        for (CminusParser.DeclarationContext d : ctx.declaration()) {
            decls.add((Declaration) visitDeclaration(d));
        }

        return new Program(decls);
    }

    @Override public Node visitVarDeclaration(CminusParser.VarDeclarationContext ctx) {
        VarType type = getVarType(ctx.typeSpecifier());
        List<String> ids = new ArrayList<>();
        List<Integer> arraySizes = new ArrayList<>();
        for (CminusParser.VarDeclIdContext v : ctx.varDeclId()) {
            String id = v.ID().getText();
            ids.add(id);
            symbolTable.addSymbol(id, new SymbolInfo(id, type, false));
            if (v.NUMCONST() != null) {
                arraySizes.add(Integer.parseInt(v.NUMCONST().getText()));
            } else {
                arraySizes.add(-1);
            }
        }
        final boolean isStatic = false;
        return new VarDeclaration(type, ids, arraySizes, isStatic);
    }

    @Override public Node visitReturnStmt(CminusParser.ReturnStmtContext ctx) {
        if (ctx.expression() != null) {
            return new Return((Expression) visitExpression(ctx.expression()));
        }
        return new Return(null);
    }

    @Override public Node visitConstant(CminusParser.ConstantContext ctx) {
        final Node node;
        if (ctx.NUMCONST() != null) {
            node = new NumConstant(Integer.parseInt(ctx.NUMCONST().getText()));
        } else if (ctx.CHARCONST() != null) {
            node = new CharConstant(ctx.CHARCONST().getText().charAt(0));
        } else if (ctx.STRINGCONST() != null) {
            node = new StringConstant(ctx.STRINGCONST().getText());
        } else {
            node = new BoolConstant(ctx.getText().equals("true"));
        }
        return node;
    }

    // TODO Uncomment and implement whatever methods make sense
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitDeclaration(CminusParser.DeclarationContext ctx) { return visitChildren(ctx); }

//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public T visitVarDeclId(CminusParser.VarDeclIdContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitFunDeclaration(CminusParser.FunDeclarationContext ctx) {

        // I will need the return type
        VarType type = getVarType(ctx.typeSpecifier());
        // ID
        String id = ctx.ID().getText();

        // create entry for this function then new table for this function
        symbolTable.addSymbol(id, new SymbolInfo(id, type, true));
        symbolTable = symbolTable.createChild();

        // list of params
        ArrayList<Param> params = new ArrayList<>();
        for (CminusParser.ParamContext p: ctx.param()) {
            params.add((Param) visitParam(p)); // entries for the new symbol table will be made here
        }
        // statement
        Statement statement = (Statement) visitStatement(ctx.statement());
        symbolTable = symbolTable.getParent(); // return the scope before this declaration
        return new FunDeclaration(type, id, params, statement);
    }
//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public T visitTypeSpecifier(CminusParser.TypeSpecifierContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitParam(CminusParser.ParamContext ctx) {
        VarType type = getVarType(ctx.typeSpecifier());

        // create new symbol table entry
        symbolTable.addSymbol(ctx.paramId().ID().getText(), new SymbolInfo(ctx.paramId().ID().getText(), type, false));

        ParamId paramId = (ParamId) visitParamId(ctx.paramId());
        return new Param(type, paramId);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitParamId(CminusParser.ParamIdContext ctx) {
        String id = ctx.ID().getText();
        boolean array = false;
        if (ctx.getChildCount() > 1) // TODO: I can't tell how to actually check if this is an array type from the context
            array = true;
        return new ParamId(id, array);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitStatement(CminusParser.StatementContext ctx) {
        Node node;
        if (ctx.expressionStmt() != null) {
            node = visitExpressionStmt(ctx.expressionStmt());
        } else if (ctx.compoundStmt() != null)
            node = visitCompoundStmt(ctx.compoundStmt());
        else if (ctx.ifStmt() != null)
            node = visitIfStmt(ctx.ifStmt());
        else if (ctx.whileStmt() != null)
            node = visitWhileStmt(ctx.whileStmt()); // TODO: implement the other types
        else if (ctx.returnStmt() != null)
            node = visitReturnStmt(ctx.returnStmt());
        else
            node = visitBreakStmt(ctx.breakStmt());

        return node;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitCompoundStmt(CminusParser.CompoundStmtContext ctx) {
        // create a new scope
        symbolTable = symbolTable.createChild();

        ArrayList<Declaration> declarations = new ArrayList<>();
        for (CminusParser.VarDeclarationContext d: ctx.varDeclaration()) {
            declarations.add((VarDeclaration) visitVarDeclaration(d));
        }

        ArrayList<Statement> statements = new ArrayList<>();
        for (CminusParser.StatementContext s: ctx.statement()) {
            statements.add((Statement) visitStatement(s));
        }

        // return to parent scope
        symbolTable = symbolTable.getParent();
        return new CompoundStmt(declarations, statements);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitExpressionStmt(CminusParser.ExpressionStmtContext ctx) {
        Expression expression = (Expression) visitExpression(ctx.expression());
        return new ExpressionStatement(expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitIfStmt(CminusParser.IfStmtContext ctx) {
        SimpleExpression simpleExpression = (SimpleExpression) visitSimpleExpression(ctx.simpleExpression());
        ArrayList<Statement> statements = new ArrayList<>();
        for (CminusParser.StatementContext s: ctx.statement()) {
            statements.add((Statement) visitStatement(s));
        }
        return new IfStatement(simpleExpression, statements);
    }
//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public T visitWhileStmt(CminusParser.WhileStmtContext ctx) { return visitChildren(ctx); }
//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public T visitBreakStmt(CminusParser.BreakStmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitExpression(CminusParser.ExpressionContext ctx) {
        // System.out.println(ctx.getText());
        if (ctx.children.size() == 1){
            return (SimpleExpression) visitSimpleExpression(ctx.simpleExpression());
        } else {
            Mutable mutable = null;
            String operator;
            Expression expression = null;

            mutable = (Mutable) visitMutable(ctx.mutable());
            operator = ctx.children.get(1).getText();
            if (ctx.children.size() == 3) {
                expression = (Expression) visitExpression(ctx.expression());
            }
            return new AssignmentExpression(mutable, operator, expression);
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitSimpleExpression(CminusParser.SimpleExpressionContext ctx) {
        OrExpression orExpression = (OrExpression) visitOrExpression(ctx.orExpression());
        return new SimpleExpression(orExpression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitOrExpression(CminusParser.OrExpressionContext ctx) {
        ArrayList<AndExpression> andExpressions = new ArrayList<>();
        for (CminusParser.AndExpressionContext a: ctx.andExpression()) {
            andExpressions.add((AndExpression) visitAndExpression(a));
        }
        return new OrExpression(andExpressions);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitAndExpression(CminusParser.AndExpressionContext ctx) {
        ArrayList<UnaryRelExpression> unaryRelExpressions = new ArrayList<>();
        for (CminusParser.UnaryRelExpressionContext u: ctx.unaryRelExpression()) {
            unaryRelExpressions.add((UnaryRelExpression) visitUnaryRelExpression(u));
        }
        return new AndExpression(unaryRelExpressions);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitUnaryRelExpression(CminusParser.UnaryRelExpressionContext ctx) {
        String bangs = "";
        for (int i=0; i<ctx.BANG().size(); i++) bangs += "!";
        return new UnaryRelExpression(bangs, (RelExpression) visitRelExpression(ctx.relExpression()));
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitRelExpression(CminusParser.RelExpressionContext ctx) {
        ArrayList<Relop> relops = new ArrayList<>();
        for (CminusParser.RelopContext r: ctx.relop()) {
            relops.add((Relop) visitRelop(r));
        }

        ArrayList<SumExpression> sumExpressions = new ArrayList<>();
        for (CminusParser.SumExpressionContext s: ctx.sumExpression()) {
            sumExpressions.add((SumExpression) visitSumExpression(s));
        }
        return new RelExpression(relops, sumExpressions);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitRelop(CminusParser.RelopContext ctx) {
        return new Relop(ctx.getText());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitSumExpression(CminusParser.SumExpressionContext ctx) {
        ArrayList<SumOp> sumOps = new ArrayList<>();
        for (CminusParser.SumopContext r: ctx.sumop()) {
            sumOps.add((SumOp) visitSumop(r));
        }

        ArrayList<TermExpression> termExpressions = new ArrayList<>();
        for (CminusParser.TermExpressionContext t: ctx.termExpression()){
            termExpressions.add((TermExpression) visitTermExpression(t));
        }
        return new SumExpression(sumOps, termExpressions);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitSumop(CminusParser.SumopContext ctx) {
        return new SumOp(ctx.getText());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitTermExpression(CminusParser.TermExpressionContext ctx) {
        ArrayList<Mulop> mulops = new ArrayList<>();
        for (CminusParser.MulopContext r: ctx.mulop()) {
            mulops.add((Mulop) visitMulop(r));
        }

        ArrayList<UnaryExpression> unaryExpressions = new ArrayList<>();
        for (CminusParser.UnaryExpressionContext u: ctx.unaryExpression()) {
            unaryExpressions.add((UnaryExpression) visitUnaryExpression(u));
        }
        return new TermExpression(mulops, unaryExpressions);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitMulop(CminusParser.MulopContext ctx) {
        return new Mulop(ctx.getText());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitUnaryExpression(CminusParser.UnaryExpressionContext ctx) {
        Factor factor = (Factor) visitFactor(ctx.factor());
        return new UnaryExpression(factor);
    }
//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public T visitUnaryop(CminusParser.UnaryopContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitFactor(CminusParser.FactorContext ctx) {
        CminusParser.ImmutableContext ic = ctx.immutable();
        CminusParser.MutableContext m = ctx.mutable();
        if (ic != null){
            Immutable immutable = (Immutable) visitImmutable(ic);
            return new Factor(immutable);
        } else {
            Mutable mutable = (Mutable) visitMutable(m);
            return new Factor(mutable);
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitMutable(CminusParser.MutableContext ctx) {
        String id = ctx.ID().getText();

        // check the symbol table for this mutable
        if (symbolTable.find(id) == null) {
            LOGGER.warning("Undefined symbol on line "+ ctx.getStart().getLine() + ": " + id);
        }

        Expression index = null;
        if (ctx.expression() != null)
            index = (Expression) visitExpression(ctx.expression());

        return new Mutable(id, index);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitImmutable(CminusParser.ImmutableContext ctx) {
        CminusParser.CallContext c = ctx.call();
        CminusParser.ExpressionContext e = ctx.expression();
        CminusParser.ConstantContext co = ctx.constant();
        if (c != null)
            return new Immutable((Call) visitCall(ctx.call()));
        else if (e != null)
            return new Immutable((Expression) visitExpression(e));
        else
            return new Immutable(visitConstant(co));
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitCall(CminusParser.CallContext ctx) {
        String id = ctx.ID().getText();

        // check the current and parent scopes for a function with that name
        SymbolInfo entry = symbolTable.find(id);
        if (entry == null) {
            LOGGER.warning("Undefined symbol on line "+ ctx.getStart().getLine() + ": " + id);
        }

        ArrayList<Expression> expressions = new ArrayList<>();
        for (CminusParser.ExpressionContext e: ctx.expression()) {
            expressions.add((Expression) visitExpression(e));
        }
        return new Call(id, expressions);
    }

}
