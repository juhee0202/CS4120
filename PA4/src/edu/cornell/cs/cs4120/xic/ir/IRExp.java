package edu.cornell.cs.cs4120.xic.ir;

import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for evaluating an expression for side effects,
 * discarding the result
 * EXP(e)
 */
public class IRExp extends IRStmt {
    private IRExpr expr;

    /**
     *
     * @param expr the expression to be evaluated and result discarded
     */
    public IRExp(IRExpr expr) {
        this.expr = expr;
    }

    public IRExpr expr() {
        return expr;
    }

    @Override
    public String label() {
        return "EXP";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr expr = (IRExpr) v.visit(this, this.expr);

        if (expr != this.expr) return new IRExp(expr);

        return this;
    }

    @Override
    public boolean containsCalls() {
        return expr.containsCalls();
    }

    @Override
    public int computeMaximumCallResults() {
        return expr.computeMaximumCallResults();
    }

    // TODO
//    @Override
//    public int computeMaximumCallArguments() {
//        return expr.computeMaximumCallArguments();
//    }
//
//    @Override
//    public int nodeCount() {
//        return expr.nodeCount() + 1;
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRExp)) return false;
//        return ((IRExp) object).expr.equalsTree(expr);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return expr.treeHashCode();
//    }
//
//    public Copyable copy() {
//        return new IRExp(expr);
//    }
//
//    public Copyable deepCopy() {
//        return new IRExp((IRExpr) expr.deepCopy());
//    }
    @Override
    public Iterable<IRNode> children() {
        return Arrays.asList(new IRNode[] { expr });
    }
}
