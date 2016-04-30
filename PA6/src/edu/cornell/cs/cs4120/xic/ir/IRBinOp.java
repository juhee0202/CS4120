package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

/**
 * An intermediate representation for a binary operation
 * OP(left, right)
 */
public class IRBinOp extends IRExpr {

    private OpType type;
    private IRExpr left, right;
    private ChildType leftType, rightType;
    
    /**
     * 0:	add or sub
     * 1:	mul
     * 2:	add with 1 add child
     * 3:	add with 1 mul child
     * 4:	add with 1 add child and 1 mul child
     * 5:	add with 1 add child and 1 mul grandchild
     * 6:	other (non-interesting binop)
     */
    private int index;

    public IRBinOp(OpType type, IRExpr left, IRExpr right) {
        this(type, left, right, 0);
        computeIndex();
    }
    
    /**
     * Computes the corresponding index of this IRBinOp and sets the index field.
     */
    private void computeIndex() {
		
		
	}

	public IRBinOp(OpType type, IRExpr left, IRExpr right, int index) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.index = index;
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

	@Override
	public ChildType getLeftChildEnumType() {
		return leftType;
	}

	@Override
	public void setLeftChildEnumType(ChildType argEnum) {
		leftType = argEnum;
	}

	@Override
	public ChildType getRightChildEnumType() {
		return rightType;
	}

	@Override
	public void setRightChildEnumType(ChildType argEnum) {
		rightType = argEnum;
	}

	@Override
	public IRNode copy() {
		// TODO Auto-generated method stub
		return new IRBinOp(type, (IRExpr)left.copy(), (IRExpr)right.copy());
	}

	@Override
	public int hashCode() {
		return left.hashCode() + right.hashCode() + type.hashCode();
	}
}
