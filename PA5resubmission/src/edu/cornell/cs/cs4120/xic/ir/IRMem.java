package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.InternalCompilerError;
import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

/**
 * An intermediate representation for a memory location
 * MEM(e)
 */
public class IRMem extends IRExpr {
    public enum MemType {
        NORMAL, IMMUTABLE;

        @Override
        public String toString() {
            switch (this) {
            case NORMAL:
                return "MEM";
            case IMMUTABLE:
                return "MEM_I";
            }
            throw new InternalCompilerError("Unknown mem type!");
        }
    };

    private IRExpr expr;
    private MemType memType;

    /**
     *
     * @param expr the address of this memory location
     */
    public IRMem(IRExpr expr) {
        this(expr, MemType.NORMAL);
    }

    public IRMem(IRExpr expr, MemType memType) {
        this.expr = expr;
        this.memType = memType;
    }

    public IRExpr expr() {
        return expr;
    }

    public MemType memType() {
        return memType;
    }

    @Override
    public String label() {
        return memType.toString();
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr expr = (IRExpr) v.visit(this, this.expr);

        if (expr != this.expr) return new IRMem(expr, memType);

        return this;
    }

    @Override
    public <T> T aggregateChildren(AggregateVisitor<T> v) {
        T result = v.unit();
        result = v.bind(result, v.visit(expr));
        return result;
    }

    @Override
    public void printSExp(SExpPrinter p) {
        p.startList();
        p.printAtom(memType.toString());
        expr.printSExp(p);
        p.endList();
    }
    
    public void accept(IRTreeVisitor irv) {
    	irv.visit(this);
    }

	@Override
	public void addLeft(IRNode irn) {
		expr = (IRExpr) irn;
	}

	@Override
	public void addRight(IRNode irn) {
		return;
	}

	@Override
	public ChildType getLeftChildEnumType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLeftChildEnumType(ChildType argEnum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChildType getRightChildEnumType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRightChildEnumType(ChildType argEnum) {
		// TODO Auto-generated method stub
		
	}
}
