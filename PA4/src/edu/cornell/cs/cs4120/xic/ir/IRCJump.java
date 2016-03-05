package edu.cornell.cs.cs4120.xic.ir;

import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for a conditional transfer of control
 * CJUMP(expr, trueLabel, falseLabel)
 */
public class IRCJump extends IRStmt {
    private IRExpr expr;
    private String trueLabel, falseLabel;

    /**
     *
     * @param expr the condition for the jump
     * @param trueLabel the destination of the jump if {@code expr} evaluates
     *          to true
     * @param falseLabel the destination of the jump if {@code expr} evaluates
     *          to false
     */
    public IRCJump(IRExpr expr, String trueLabel, String falseLabel) {
        this.expr = expr;
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    public IRExpr expr() {
        return expr;
    }

    public String trueLabel() {
        return trueLabel;
    }

    public String falseLabel() {
        return falseLabel;
    }

    @Override
    public String label() {
        return "CJUMP";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr expr = (IRExpr) v.visit(this, this.expr);

        if (expr != this.expr) return new IRCJump(expr, trueLabel, falseLabel);

        return this;
    }

    @Override
    public boolean containsCalls() {
        return expr.containsCalls();
    }

    @Override
    public int computeMaximumCallResults() {
        return 0;
    }

    @Override
    public Iterable<IRNode> children() {
        return Arrays.asList(new IRNode[] { expr });
    }

    // TODO
//    @Override
//    public int nodeCount() {
//        return 1 + expr.nodeCount();
//    }
//
//    @Override
//    public int computeMaximumCallArguments() {
//        return 0;
//    }
//
//    public boolean hasTrueLabel() {
//        return trueLabel != null;
//    }
//
//    public boolean hasFalseLabel() {
//        return falseLabel != null;
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRCJump)) return false;
//        IRCJump other = (IRCJump) object;
//        if (!other.expr.equalsTree(expr)) return false;
//        if (hasTrueLabel() && !other.hasTrueLabel()
//                || !hasTrueLabel() && other.hasTrueLabel())
//            return false;
//        if (hasFalseLabel() && !other.hasFalseLabel()
//                || !hasFalseLabel() && other.hasFalseLabel())
//            return false;
//        if (hasTrueLabel() && !other.trueLabel.equals(trueLabel)) return false;
//        if (hasFalseLabel() && !other.falseLabel.equals(falseLabel))
//            return false;
//        return true;
//    }
//
//    @Override
//    public int treeHashCode() {
//        return 17 + expr.treeHashCode() + trueLabel.hashCode() * 37
//                + falseLabel.hashCode() * 37 * 37;
//    }
//
//    public Copyable copy() {
//        return new IRCJump(expr, trueLabel, falseLabel);
//    }
//
//    public Copyable deepCopy() {
//        return new IRCJump((IRExpr) expr.deepCopy(), trueLabel, falseLabel);
//    }
}
