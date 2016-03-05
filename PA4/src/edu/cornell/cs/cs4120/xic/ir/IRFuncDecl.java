package edu.cornell.cs.cs4120.xic.ir;

import java.util.Arrays;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.InsnMapsBuilder;

/** An IR function declaration */
public class IRFuncDecl extends IRNode {
    private String name;
    private IRStmt body;
    private boolean isProcedure;

    public IRFuncDecl(String name, IRStmt stmt, boolean isProcedure) {
        this.name = name;
        body = stmt;
        this.isProcedure = isProcedure;
    }

    public String name() {
        return name;
    }

    public IRStmt body() {
        return body;
    }

    @Override
    public String label() {
        return "FUNC " + name;
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRStmt stmt = (IRStmt) v.visit(this, body);

        if (stmt != body) return new IRFuncDecl(name, stmt, isProcedure);

        return this;
    }

    @Override
    public InsnMapsBuilder buildInsnMapsEnter(InsnMapsBuilder v) {
        v.addNameToCurrentIndex(name);
        return v;
    }

    @Override
    public boolean containsCalls() {
        return body.containsCalls();
    }

    @Override
    public int computeMaximumCallResults() {
        return body.computeMaximumCallResults();
    }

    // TODO
//    @Override
//    public int nodeCount() {
//        return 1 + body.nodeCount();
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRFuncDecl)) return false;
//        IRFuncDecl other = (IRFuncDecl) object;
//        return name.equals(other.name) && body.equals(other.body);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return 17 + name.hashCode() + body.treeHashCode() * 37;
//    }
//
//    public boolean isProcedure() {
//        return isProcedure;
//    }
//
//    @Override
//    public int computeMaximumCallArguments() {
//        return body.computeMaximumCallArguments();
//    }
//
//    public Copyable copy() {
//        return new IRFuncDecl(name, body, isProcedure);
//    }
//
//    public Copyable deepCopy() {
//        return new IRFuncDecl(name, (IRStmt) body.deepCopy(), isProcedure);
//    }

    @Override
    public Iterable<IRNode> children() {
        return Arrays.asList(new IRNode[] { body });
    }
}
