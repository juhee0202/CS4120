package edu.cornell.cs.cs4120.xic.ir;

import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import edu.cornell.cs.cs4120.xic.InternalCompilerError;

/**
 * An intermediate representation for a memory location
 * MEM(e)
 */
public class IRMem extends IRExpr {
    public enum MemType {
        NORMAL, IMMUTABLE
    };

    private IRExpr expr;
    private MemType memType;

    /**
     *
     * @param expr the address of this memory location
     */
    public IRMem(IRExpr expr) {
        this(expr, MemType.NORMAL);
    }

    public IRMem(IRExpr expr, MemType memType) {
        this.expr = expr;
        this.memType = memType;
    }

    public IRExpr expr() {
        return expr;
    }

    public MemType memType() {
        return memType;
    }

    @Override
    public String label() {
        switch (memType) {
        case NORMAL:
            return "MEM";
        case IMMUTABLE:
            return "MEM_I";
        default:
            throw new InternalCompilerError("Unknown mem type!");
        }
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr expr = (IRExpr) v.visit(this, this.expr);

        if (expr != this.expr) return new IRMem(expr, memType);

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
//    public int nodeCount() {
//        return 1 + expr.nodeCount();
//    }
//
//    @Override
//    public int computeMaximumCallArguments() {
//        return expr.computeMaximumCallArguments();
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRMem)) return false;
//        return ((IRMem) object).expr.equalsTree(expr);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return expr.treeHashCode();
//    }
//
//    public Copyable copy() {
//        return new IRMem(expr, memType);
//    }
//
//    public Copyable deepCopy() {
//        return new IRMem((IRExpr) expr.deepCopy(), memType);
//    }
    @Override
    public Iterable<IRNode> children() {
        return Arrays.asList(new IRNode[] { expr });
    }
}
