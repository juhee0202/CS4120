package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import jl2755.visitor.IRTreeVisitor;

/**
 * An intermediate representation for a binary operation
 * OP(left, right)
 */
public class IRBinOp extends IRExpr {

    private OpType type;
    private IRExpr left, right;

    public IRBinOp(OpType type, IRExpr left, IRExpr right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    public OpType opType() {
        return type;
    }

    public IRExpr left() {
        return left;
    }

    public IRExpr right() {
        return right;
    }

    @Override
    public String label() {
        return type.toString();
    }
    
    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr left = (IRExpr) v.visit(this, this.left);
        IRExpr right = (IRExpr) v.visit(this, this.right);

        if (left != this.left || right != this.right)
            return new IRBinOp(type, left, right);

        return this;
    }

    @Override
    public <T> T aggregateChildren(AggregateVisitor<T> v) {
        T result = v.unit();
        result = v.bind(result, v.visit(left));
        result = v.bind(result, v.visit(right));
        return result;
    }

    @Override
    public void printSExp(SExpPrinter p) {
        p.startList();
        p.printAtom(type.toString());
        if (left == null) {
        	p.printAtom("null");
        } else {
            left.printSExp(p);
        }
        if (right == null) {
        	p.printAtom("null");
        } else {
            right.printSExp(p);
        }
        p.endList();
    }
    
    public void accept(IRTreeVisitor irv) {
    	irv.visit(this);
    }

	@Override
	public void addLeft(IRNode irn) {
		left = (IRExpr) irn;
	}

	@Override
	public void addRight(IRNode irn) {
		right = (IRExpr) irn;
	}
}
