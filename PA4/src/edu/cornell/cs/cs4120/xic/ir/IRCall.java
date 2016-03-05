package edu.cornell.cs.cs4120.xic.ir;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for a function call
 * CALL(e_target, e_1, ..., e_n)
 */
public class IRCall extends IRExpr {
    private IRExpr target;
    private List<IRExpr> args;
    int returnSize;

    /**
     *
     * @args target address of the code for this function call
     * @args args arguments of this function call
     * @args returnSize TODO
     */
    public IRCall(IRExpr target, List<IRExpr> args, int returnSize) {
        this.target = target;
        this.args = args;
        this.returnSize = returnSize;
    }

//    public IRCall(IRExpr target, int returnSize) {
//        this(target, Collections.emptyList(), returnSize);
//    }

    public IRExpr target() {
        return target;
    }

    public List<IRExpr> args() {
        return args;
    }

    public int returnSize() {
        return returnSize;
    }

    public boolean isToProcedure() {
        return returnSize == 0;
    }

    @Override
    public String label() {
        return "CALL";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        boolean modified = false;

        IRExpr target = (IRExpr) v.visit(this, this.target);
        if (target != this.target) modified = true;

        List<IRExpr> results = new ArrayList<>(args.size());
        for (IRExpr args : args) {
            IRExpr newExpr = (IRExpr) v.visit(this, args);
            if (newExpr != args) modified = true;
            results.add(newExpr);
        }

        if (modified) return new IRCall(target, results, returnSize);

        return this;
    }

    @Override
    public boolean containsCalls() {
        return true;
    }

    @Override
    public int computeMaximumCallResults() {
        return Math.max(target.computeMaximumCallResults(), returnSize);
    }

    // TODO
//    @Override
//    public int nodeCount() {
//        int nodeCount = 0;
//        for (IRExpr e : args)
//            nodeCount += e.nodeCount();
//        return 1 + target.nodeCount() + nodeCount;
//    }
//
//    @Override
//    public int computeMaximumCallArguments() {
//        return Math.max(target.computeMaximumCallArguments(), args.size());
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRCall)) return false;
//        IRCall other = (IRCall) object;
//        if (!other.target.equalsTree(target)) return false;
//        if (other.args.size() != args.size()) return false;
//        for (int i = 0; i < args.size(); ++i)
//            if (!other.args.get(i).equalsTree(args.get(i))) return false;
//        return true;
//    }
//
//    @Override
//    public int treeHashCode() {
//        int code = 17;
//        code = 37 * code + target.hashCode();
//        for (IRExpr expr : args)
//            code = 37 * code + expr.treeHashCode();
//        return code;
//    }

//    public Copyable copy() {
//        return new IRCall(target, new ArrayList<IRExpr>(args), returnSize);
//    }
//
//    public Copyable deepCopy() {
//        List<IRExpr> exprs = new ArrayList<IRExpr>();
//        for (IRExpr e : args)
//            exprs.add((IRExpr) e.deepCopy());
//        return new IRCall((IRExpr) target.deepCopy(), exprs, returnSize);
//    }

    @Override
    public Iterable<IRNode> children() {
        ArrayList<IRNode> result = new ArrayList<>(args.size());
        result.add(target);
        result.addAll(args);
        return result;
    }
}
