package edu.cornell.cs.cs4120.xic.ir;

/**
 * An intermediate representation for a 64-bit integer constant.
 * CONST(n)
 */
public class IRConst extends IRExpr {
    private long value;

    /**
     *
     * @param value value of this constant
     */
    public IRConst(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String label() {
        return "CONST(" + value + ")";
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
//        if (!(object instanceof IRConst)) return false;
//        return ((IRConst) object).value == value;
//    }
//
//    @Override
//    public int treeHashCode() {
//        return (int) value;
//    }
//
//    public Copyable copy() {
//        return new IRConst(value);
//    }
//
//    public Copyable deepCopy() {
//        return new IRConst(value);
//    }
}
