package edu.cornell.cs.cs4120.xic.ir;

/**
 * An intermediate representation for a temporary register
 * TEMP(name)
 */
public class IRTemp extends IRExpr {
    private String name;

    /**
     *
     * @param name name of this temporary register
     */
    public IRTemp(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String label() {
        return "TEMP(" + name + ")";
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
//        if (!(object instanceof IRTemp)) return false;
//        return ((IRTemp) object).name.equals(name);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return name.hashCode();
//    }
//
//    public void setName(String n) {
//        name = n;
//    }
//
//    public Copyable copy() {
//        return new IRTemp(name);
//    }
//
//    public Copyable deepCopy() {
//        return new IRTemp(name);
//    }
}
