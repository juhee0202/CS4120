package edu.cornell.cs.cs4120.xic.ir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.CheckCanonicalIRVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

/**
 * An intermediate representation for a function call
 * CALL(e_target, e_1, ..., e_n)
 */
public class IRCall extends IRExpr {
    private IRExpr target;
    private List<IRExpr> args;
    private int numReturns;
    private boolean[] returnBoolList; 
    private int num8ByteSpace;

    /**
     *
     * @param target address of the code for this function call
     * @param args arguments of this function call
     */
    public IRCall(IRExpr target, IRExpr... args) {
        this(target, Arrays.asList(args));
    }

    /**
     *
     * @param target address of the code for this function call
     * @param args arguments of this function call
     */
    public IRCall(IRExpr target, List<IRExpr> args) {
        this.target = target;
        this.args = args;
    }

    public IRExpr target() {
        return target;
    }

    public List<IRExpr> args() {
        return args;
    }

    @Override
    public String label() {
        return "CALL";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        boolean modified = false;

        IRExpr target = (IRExpr) v.visit(this, this.target);
        if (target != this.target) modified = true;

        List<IRExpr> results = new ArrayList<>(args.size());
        for (IRExpr arg : args) {
            IRExpr newExpr = (IRExpr) v.visit(this, arg);
            if (newExpr != arg) modified = true;
            results.add(newExpr);
        }

        if (modified) return new IRCall(target, results);

        return this;
    }

    @Override
    public <T> T aggregateChildren(AggregateVisitor<T> v) {
        T result = v.unit();
        result = v.bind(result, v.visit(target));
        for (IRExpr arg : args)
            result = v.bind(result, v.visit(arg));
        return result;
    }

    @Override
    public boolean isCanonical(CheckCanonicalIRVisitor v) {
        return !v.inExpr();
    }

    @Override
    public void printSExp(SExpPrinter p) {
        p.startList();
        p.printAtom("CALL");
        target.printSExp(p);
        for (IRExpr arg : args)
            arg.printSExp(p);
        p.endList();
    }
    
    public void accept(IRTreeVisitor irv) {
    	irv.visit(this);
    }

	@Override
	public void addLeft(IRNode irn) {
		target = (IRExpr) irn;
	}

	@Override
	public void addRight(IRNode irn) {
		return;
	}

	public int getNumReturns() {
		return numReturns;
	}

	public void setNumReturns(int numReturns) {
		this.numReturns = numReturns;
		// set [true,...,true] 
		returnBoolList = new boolean[numReturns];
		for (int i = 0; i < numReturns; i++) {
			returnBoolList[i] = true;
		}
	}
	
	public boolean[] getReturnBoolList() {
		return returnBoolList;
	}
	
	public void setReturnBoolList(boolean[] rbl) {
		returnBoolList = rbl;
	}

	public int getNum8ByteSpace() {
		return num8ByteSpace;
	}

	public void setNum8ByteSpace(int num8ByteSpace) {
		this.num8ByteSpace = num8ByteSpace;
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
		List<IRExpr> cloneArgs = new ArrayList<IRExpr>();
		for (int i = 0; i < args.size(); i++) {
			cloneArgs.add((IRExpr) args.get(i).copy());
		}
		IRCall clone = new IRCall((IRExpr)target.copy(), cloneArgs);
		clone.setNumReturns(numReturns);
		clone.setNum8ByteSpace(num8ByteSpace);
		clone.setReturnBoolList(returnBoolList);
		return clone;
	}
}
