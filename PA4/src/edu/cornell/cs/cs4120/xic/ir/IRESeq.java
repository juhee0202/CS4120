package edu.cornell.cs.cs4120.xic.ir;

import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for an expression evaluated under side effects
 * ESEQ(stmt, expr)
 */
public class IRESeq extends IRExpr {
    private IRStmt stmt;
    private IRExpr expr;

    /**
     *
     * @param stmt IR statement to be evaluated for side effects
     * @param expr IR expression to be evaluated after {@code stmt}
     */
    public IRESeq(IRStmt stmt, IRExpr expr) {
        this.stmt = stmt;
        this.expr = expr;
    }

    public IRStmt stmt() {
        return stmt;
    }

    public IRExpr expr() {
        return expr;
    }

    @Override
    public String label() {
        return "ESEQ";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRStmt stmt = (IRStmt) v.visit(this, this.stmt);
        IRExpr expr = (IRExpr) v.visit(this, this.expr);

        if (expr != this.expr || stmt != this.stmt)
            return new IRESeq(stmt, expr);

        return this;
    }

    @Override
    public boolean containsCalls() {
        return stmt.containsCalls() || expr.containsCalls();
    }

    @Override
    public int computeMaximumCallResults() {
        int l = stmt.computeMaximumCallResults();
        int r = expr.computeMaximumCallResults();
        return Math.max(l, r);
    }

    // TODO
//    @Override
//    public int computeMaximumCallArguments() {
//        int l = stmt.computeMaximumCallArguments();
//        int r = expr.computeMaximumCallArguments();
//        return Math.max(l, r);
//    }
//
//    @Override
//    public int nodeCount() {
//        return 1 + stmt.nodeCount() + expr.nodeCount();
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRESeq)) return false;
//        IRESeq eseq = (IRESeq) object;
//        return eseq.stmt.equalsTree(stmt) && eseq.expr.equalsTree(expr);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return 17 + stmt.treeHashCode() + expr.treeHashCode() * 37;
//    }
//
//    public Copyable copy() {
//        return new IRESeq(stmt, expr);
//    }
//
//    public Copyable deepCopy() {
//        return new IRESeq((IRStmt) stmt.deepCopy(), (IRExpr) expr.deepCopy());
//    }

    @Override
    public Iterable<IRNode> children() {
        return Arrays.asList(new IRNode[] { stmt, expr });
    }
}
