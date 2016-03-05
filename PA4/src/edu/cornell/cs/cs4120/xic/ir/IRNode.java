package edu.cornell.cs.cs4120.xic.ir;

import java.util.Collections;

import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.InsnMapsBuilder;

/**
 * An intermediate representation
 */
public abstract class IRNode {

    public IRNode visitChildren(IRVisitor v) {
        return this;
    }

    public InsnMapsBuilder buildInsnMapsEnter(InsnMapsBuilder v) {
        return v;
    }

    public abstract String label();

    /**
     *  Return an empty list by default
     */
    public Iterable<IRNode> children() {
        return Collections.emptyList();
    }

    public abstract boolean containsCalls();

    /** Max number of results of any call in the subtree. */
    public abstract int computeMaximumCallResults();

    // TODO
//    /** Helper function for IR tree comparisons.
//     *  In a separate function to preserve equals()/hasCode() semantics
//     */
//    public abstract boolean equalsTree(Object object);
//
//    public abstract int treeHashCode();
//
//    /** Count the total number of IR nodes in the subtree rooted here. */
//    public abstract int nodeCount();
//
//    /** Max number of arguments of any call in the subtree. */
//    public abstract int computeMaximumCallArguments();
//
//	/**
//	 * Create a deep copy (as opposed to copy(), which makes a shallow one)
//	 */
//	public abstract Copyable deepCopy();
//
//	public String toString() {
//		return IRPrinter.IRToString(this);
//	}
}
