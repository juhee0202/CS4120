package jl2755.visitor;

import jl2755.ast.*;

public interface ASTVisitor {
	public void visit(ArrayElement ae);
	public void visit(ArrayElementList ael);
	public void visit(ArrayLiteral al);
	public void visit(AssignmentStmt as);
	public void visit(BinaryExpr be);
	public void visit(BlockStmt bs);
	public void visit(Break br);
	public void visit(ClassBody cb);
	public void visit(ClassDecl cd);
	public void visit(Continue cn);
	public void visit(DotableExpr de);
	public void visit(FunctionArg fa);
	public void visit(FunctionCall fc);
	public void visit(FunctionDecl fd);
	public void visit(GlobalDecl gd);
	public void visit(Identifier id);
	public void visit(IfStmt is);
	public void visit(IndexedBrackets ib);
	public void visit(Literal l);
	public void visit(Null n);
	public void visit(Program p);
	public void visit(ReturnStmt rs);
	public void visit(ShortTupleDecl std);
	public void visit(Stmt s);
	public void visit(StmtList sl);
	public void visit(TupleInit ti);
	public void visit(UnaryExpr ue);
	public void visit(UseId ui);
	public void visit(VarDecl vd);
	public void visit(VarInit vi);
	public void visit(WhileStmt ws);
}
