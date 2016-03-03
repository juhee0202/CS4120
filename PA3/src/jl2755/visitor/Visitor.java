package jl2755.visitor;

import jl2755.ast.*;

public interface Visitor {
	public void visit(ArrayElement ae);
	public void visit(ArrayElementList ael);
	public void visit(ArrayLiteral al);
	public void visit(AssignmentStmt as);
	public void visit(BinaryExpr be);
	public void visit(BlockStmt bs);
	public void visit(EmptyArrayType et);
	public void visit(FunctionArg fa);
	public void visit(FunctionCall fc);
	public void visit(FunctionDecl fd);
	public void visit(FunctionDeclList fdl);
	public void visit(Identifier i);
	public void visit(IfStmt is);
	public void visit(Literal l);
	public void visit(MixedArrayType mat);
	public void visit(PrimitiveType pt);
	public void visit(Program p);
	public void visit(PureContentArrayType pca);
	public void visit(ReturnList rl);
	public void visit(ReturnStmt rs);
	public void visit(ReturnType rt);
	public void visit(Stmt s);
	public void visit(StmtList sl);
	public void visit(TupleDeclList tdl);
	public void visit(TupleInit ti);
	public void visit(TypeList tl);
	public void visit(UnaryExpr ue);
	public void visit(UseId ui);
	public void visit(VarDecl vd);
	public void visit(VarInit vi);
	public void visit(WhileStmt ws);
}
