package edu.cornell.cs.cs4120.xic.ir;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for a sequence of statements
 * SEQ(s1,...,sn)
 */
public class IRSeq extends IRStmt {
    private List<IRStmt> stmts;

    /**
     *
     * @param stmts the sequence of statements
     */
    public IRSeq(List<IRStmt> stmts) {
        this.stmts = stmts;
    }

    public List<IRStmt> stmts() {
        return stmts;
    }

    @Override
    public String label() {
        return "SEQ";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        boolean modified = false;

        List<IRStmt> results = new ArrayList<>(stmts.size());
        for (IRStmt stmt : stmts) {
            IRStmt newStmt = (IRStmt) v.visit(this, stmt);
            if (newStmt != stmt) modified = true;
            results.add(newStmt);
        }

        if (modified) return new IRSeq(results);

        return this;
    }

    @Override
    public boolean containsCalls() {
        for (IRStmt stmt : stmts)
            if (stmt.containsCalls()) return true;
        return false;
    }

    @Override
    public int computeMaximumCallResults() {
        int value = 0;
        for (IRStmt s : stmts)
            value = Math.max(value, s.computeMaximumCallResults());
        return value;
    }

    // TODO
//    @Override
//    public int nodeCount() {
//        int nodeCount = 0;
//        for (IRNode n : stmts)
//            nodeCount += n.nodeCount();
//        return nodeCount + 1;
//    }
//
//    @Override
//    public int computeMaximumCallArguments() {
//        int value = 0;
//        for (IRStmt s : stmts)
//            value = Math.max(value, s.computeMaximumCallArguments());
//        return value;
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRSeq)) return false;
//        IRSeq other = (IRSeq) object;
//        if (other.stmts.size() != stmts.size()) return false;
//
//        for (int i = 0; i < stmts.size(); ++i)
//            if (!stmts.get(i).equalsTree(other.stmts.get(i))) return false;
//        return true;
//    }
//
//    @Override
//    public int treeHashCode() {
//        int code = 17;
//        for (IRStmt stmt : stmts)
//            code = 37 * code + stmt.treeHashCode();
//        return code;
//    }
//
//    public void appendStmt(IRStmt stmt) {
//        if (stmt != null) {
//            /* Flatten nested SEQs here already */
//            if (stmt instanceof IRSeq)
//                stmts.addAll(((IRSeq) stmt).stmts());
//            else stmts.add(stmt);
//        }
//    }
//
//    public Copyable copy() {
//        return new IRSeq(new ArrayList<IRStmt>(stmts));
//    }
//
//    public Copyable deepCopy() {
//        List<IRStmt> stmtsCopy = new ArrayList<IRStmt>();
//        for (IRStmt s : stmts)
//            stmtsCopy.add((IRStmt) s.deepCopy());
//        return new IRSeq(stmtsCopy);
//    }

    @Override
    public Iterable<IRNode> children() {
        ArrayList<IRNode> result = new ArrayList<>(stmts.size());
        result.addAll(stmts);
        return result;
    }
}
