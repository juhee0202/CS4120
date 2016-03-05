package edu.cornell.cs.cs4120.xic.ir;

import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for a move statement
 * MOVE(target, expr)
 */
public class IRMove extends IRStmt {
    private IRExpr target;
    private IRExpr expr;

    /**
     *
     * @param target the destination of this move
     * @param expr the expression whose value is to be moved
     */
    public IRMove(IRExpr target, IRExpr expr) {
        this.target = target;
        this.expr = expr;
    }

    public IRExpr target() {
        return target;
    }

    public IRExpr expr() {
        return expr;
    }

    @Override
    public String label() {
        return "MOVE";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr target = (IRExpr) v.visit(this, this.target);
        IRExpr expr = (IRExpr) v.visit(this, this.expr);

        if (target != this.target || expr != this.expr)
            return new IRMove(target, expr);

        return this;
    }

    @Override
    public boolean containsCalls() {
        return target.containsCalls() || expr.containsCalls();
    }

    @Override
    public int computeMaximumCallResults() {
        int l = target.computeMaximumCallResults();
        int r = expr.computeMaximumCallResults();
        return Math.max(l, r);
    }

    // TODO
//    @Override
//    public int nodeCount() {
//        return 1 + target.nodeCount() + expr.nodeCount();
//    }
//
//    @Override
//    public int computeMaximumCallArguments() {
//        int l = target.computeMaximumCallArguments();
//        int r = expr.computeMaximumCallArguments();
//        return Math.max(l, r);
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRMove)) return false;
//        IRMove other = (IRMove) object;
//        return other.expr.equalsTree(expr) && other.target.equalsTree(target);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return 17 + target.treeHashCode() + expr.treeHashCode() * 37;
//    }
//
//    public Copyable copy() {
//        return new IRMove(target, expr);
//    }
//
//    public Copyable deepCopy() {
//        return new IRMove((IRExpr) target.deepCopy(), (IRExpr) expr.deepCopy());
//    }

    @Override
    public Iterable<IRNode> children() {
        return Arrays.asList(new IRNode[] { target, expr });
    }
}
