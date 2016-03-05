package edu.cornell.cs.cs4120.xic.ir;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;

/**
 * An intermediate representation for a compilation unit
 */
public class IRCompUnit extends IRNode {
    private Map<String, IRFuncDecl> functions;
    private IRSeq seq = null;
    private String name;

    public IRCompUnit(String name, Map<String, IRFuncDecl> functions,
            IRSeq seq) {
        this.functions = functions;
        this.seq = seq;
        this.name = name;
    }

    public IRCompUnit(String name, Map<String, IRFuncDecl> functions) {
        this.functions = functions;
        this.name = name;
    }

    public IRCompUnit(String name) {
        functions = new TreeMap<>();
        this.name = name;
    }

    public void appendFunc(IRFuncDecl func) {
        functions.put(func.name(), func);
    }

    public String name() {
        return name;
    }

    public Map<String, IRFuncDecl> functions() {
        return functions;
    }

    public IRFuncDecl getFunction(String name) {
        return functions.get(name);
    }

    @Override
    public String label() {
        return "COMPUNIT";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        boolean modified = false;

        Map<String, IRFuncDecl> results = new TreeMap<>();
        for (String funcName : functions.keySet()) {
            IRFuncDecl func = functions.get(funcName);
            IRFuncDecl newFunc = (IRFuncDecl) v.visit(this, func);
            if (newFunc != func) modified = true;
            results.put(newFunc.name(), newFunc);
        }

        IRSeq newSeq = seq;

        if (hasExtraSequence()) {
            newSeq = (IRSeq) v.visit(this, seq);
            if (newSeq != seq) modified = true;
        }

        if (modified) return new IRCompUnit(name, results, newSeq);

        return this;
    }

    public boolean hasExtraSequence() {
        return seq != null;
    }

    @Override
    public boolean containsCalls() {
        for (IRFuncDecl func : functions.values())
            if (func.containsCalls()) return true;
        if (hasExtraSequence()) return true;
        return false;
    }

    @Override
    public int computeMaximumCallResults() {
        int value = 0;
        for (IRFuncDecl f : functions.values())
            value = Math.max(value, f.computeMaximumCallResults());
        return value;
    }

    // TODO
//    @Override
//    public int nodeCount() {
//        int nodeCount = 0;
//        for (IRFuncDecl f : functions.values())
//            nodeCount += f.nodeCount();
//        if (seq != null) nodeCount += seq.nodeCount();
//        return nodeCount + 1;
//    }
//
//    @Override
//    public int computeMaximumCallArguments() {
//        int value = 0;
//        for (IRFuncDecl f : functions.values())
//            value = Math.max(value, f.computeMaximumCallArguments());
//        return value;
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRCompUnit)) return false;
//        IRCompUnit other = (IRCompUnit) object;
//        if (functions.size() != other.functions.size()) return false;
//        for (int i = 0; i < functions.size(); ++i)
//            if (functions.get(i).equalsTree(other.functions.get(i)))
//                return false;
//        if (seq == null && other.seq != null
//                || seq != null && other.seq == null)
//            return false;
//        return seq.equalsTree(other.seq);
//    }
//
//    @Override
//    public int treeHashCode() {
//        int code = 17;
//        for (IRFuncDecl func : functions.values())
//            code = 37 * code + func.treeHashCode();
//        if (seq != null) code = 37 * seq.treeHashCode();
//        return code;
//    }
//
//    public void appendStmt(IRStmt stmt) {
//        if (seq == null) seq = new IRSeq();
//        seq.appendStmt(stmt);
//    }
//
//    public IRSeq extraSequence() {
//        return seq;
//    }
//
//    public void setExtraSequence(IRSeq seq) {
//        this.seq = seq;
//    }
//
//    public Copyable copy() {
//        return new IRCompUnit(name,
//                              new TreeMap<String, IRFuncDecl>(functions),
//                              seq);
//    }
//
//    public Copyable deepCopy() {
//        IRSeq newSeq = null;
//        if (seq != null) newSeq = (IRSeq) seq.deepCopy();
//        Map<String, IRFuncDecl> newFuncs = new TreeMap<String, IRFuncDecl>();
//        for (String name : functions.keySet())
//            newFuncs.put(name, (IRFuncDecl) functions.get(name).deepCopy());
//        return new IRCompUnit(name, newFuncs, newSeq);
//    }

    @Override
    public Iterable<IRNode> children() {
        List<IRNode> result = new ArrayList<>(functions.size());
        result.addAll(functions.values());
        if (hasExtraSequence()) result.add(seq);
        return result;
    }
}
