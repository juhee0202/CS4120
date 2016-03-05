package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.xic.ir.visit.InsnMapsBuilder;

/**
 * An intermediate representation for naming a memory address
 */
public class IRLabel extends IRStmt {
    private String name;

    /**
     *
     * @param name name of this memory address
     */
    public IRLabel(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String label() {
        return "LABEL(" + name + ")";
    }

    @Override
    public InsnMapsBuilder buildInsnMapsEnter(InsnMapsBuilder v) {
        v.addNameToCurrentIndex(name);
        return v;
    }

    @Override
    public boolean containsCalls() {
        return false;
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
//        return 1;
//    }
//
//    @Override
//    public boolean equalsTree(Object object) {
//        if (!(object instanceof IRLabel)) return false;
//        return ((IRLabel) object).name.equals(name);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return name.hashCode();
//    }
//
//    public Copyable copy() {
//        return new IRLabel(name);
//    }
//
//    public Copyable deepCopy() {
//        return new IRLabel(name);
//    }
}
