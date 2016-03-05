package edu.cornell.cs.cs4120.xic.ir;

/**
 * An intermediate representation for named memory address
 * NAME(n)
 */
public class IRName extends IRExpr {
    private String name;

    /**
     *
     * @param name name of this memory address
     */
    public IRName(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String label() {
        return "NAME(" + name + ")";
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
//        if (!(object instanceof IRName)) return false;
//        return ((IRName) object).name.equals(name);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return name.hashCode();
//    }
//
//    public Copyable copy() {
//        return new IRName(name);
//    }
//
//    public Copyable deepCopy() {
//        return new IRName(name);
//    }
}
