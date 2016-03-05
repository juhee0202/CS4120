package edu.cornell.cs.cs4120.xic.ir;

import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for a transfer of control
 */
public class IRJump extends IRStmt {
    private IRExpr target;

    /**
     *
     * @param expr the destination of the jump
     */
    public IRJump(IRExpr expr) {
        target = expr;
    }

    public IRExpr target() {
        return target;
    }

    @Override
    public String label() {
        return "JUMP";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr expr = (IRExpr) v.visit(this, target);

        if (expr != target) return new IRJump(expr);

        return this;
    }

    @Override
    public boolean containsCalls() {
        return target.containsCalls();
    }

    @Override
    public int computeMaximumCallResults() {
        return 0;
    }

    // TODO
//    @Override
//    public int computeMaximumCallArguments() {
//        return 0;
//    }
//
//    @Override
//    public int nodeCount() {
//        return 1 + target.nodeCount();
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRJump)) return false;
//        IRJump other = (IRJump) object;
//        return other.target.equalsTree(target);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return target.treeHashCode();
//    }
//
//    public Copyable copy() {
//        return new IRJump(target);
//    }
//
//    public Copyable deepCopy() {
//        return new IRJump((IRExpr) target.deepCopy());
//    }

    @Override
    public Iterable<IRNode> children() {
        return Arrays.asList(new IRNode[] { target });
    }
};
