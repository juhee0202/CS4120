package edu.cornell.cs.cs4120.xic.ir;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

/**
 * An intermediate representation for evaluating an expression for side effects,
 * discarding the result
 * EXP(e)
 */
public class IRExp extends IRStmt {
    private IRExpr expr;

    /**
     *
     * @param expr the expression to be evaluated and result discarded
     */
    public IRExp(IRExpr expr) {
        this.expr = expr;
    }

    public IRExpr expr() {
        return expr;
    }

    @Override
    public String label() {
        return "EXP";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr expr = (IRExpr) v.visit(this, this.expr);

        if (expr != this.expr) return new IRExp(expr);

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
        p.printAtom("EXP");
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

	@Override
	public IRNode copy() {
		return new IRExp((IRExpr)expr.copy());
	}
}
