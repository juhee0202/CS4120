package edu.cornell.cs.cs4120.xic.ir.visit;

import edu.cornell.cs.cs4120.util.InternalCompilerError;
import edu.cornell.cs.cs4120.xic.ir.IRNode;

public class LIRVisitor extends IRVisitor {
    
	public IRNode visit(IRNode parent, IRNode n) {
        if (n == null) return null;

        /* Allow the visitor implementation to hijack traversal of n */
        IRNode overrideValue = override(parent, n);
        if (overrideValue != null) return overrideValue;

        LIRVisitor v_ = enter(parent, n);
        if (v_ == null)
            throw new InternalCompilerError("LIRVisitor.enter() returned null!");

        IRNode n_ = n.lowerChildren(v_);
        if (n_ == null)
            throw new InternalCompilerError("LIRVisitor.visitChildren() returned null!");

        n_ = leave(parent, n, n_, v_);
        if (n_ == null)
            throw new InternalCompilerError("LIRVisitor.leave() returned null!");

        return n_;
    }
	
	protected LIRVisitor enter(IRNode parent, IRNode n) {
        return this;
    }
	
	protected IRNode leave(IRNode parent, IRNode n, IRNode n_,
            LIRVisitor v_) {
        return n_;
    }
}
