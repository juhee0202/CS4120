package edu.cornell.cs.cs4120.xic.ir;

/**
 * Fake declaration node, which is used to "anchor" registers
 * that are used to pass arguments to functions
 */
public class IRDecl extends IRStmt {
    private String name;

    public IRDecl(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String label() {
        return "DECL_REG(" + name + ")";
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
//        if (!(object instanceof IRDecl)) return false;
//        return ((IRDecl) object).name.equals(name);
//    }
//
//    @Override
//    public int treeHashCode() {
//        return name.hashCode();
//    }
//
//    public Copyable copy() {
//        return new IRDecl(name);
//    }
//
//    public Copyable deepCopy() {
//        return new IRDecl(name);
//    }
}
