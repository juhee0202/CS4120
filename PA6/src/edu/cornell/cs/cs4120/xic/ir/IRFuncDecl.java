package edu.cornell.cs.cs4120.xic.ir;

import java.util.List;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.InsnMapsBuilder;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

/** An IR function declaration */
public class IRFuncDecl extends IRNode {
    private String name;
    private String ABIName;
    private IRStmt body;
    private List<String> paramList;
    private int numArgs;
    private int numReturns;
    private int numSavedCalleeRegs;

    public IRFuncDecl(String name, String ABIName, IRStmt stmt) {
        this.name = name;
        this.ABIName = ABIName;
        body = stmt;
    }
    
    public IRFuncDecl(String name, IRStmt stmt) {
        this.name = name;
        body = stmt;
    }

    public String getABIName() {
        return ABIName;
    }

    public String getName() {
		return name;
	}

	public IRStmt body() {
        return body;
    }
    
    public String assemblyLabel() {
    	return "FUNC(" + ABIName.substring(1) + ")";
    }

    @Override
    public String label() {
        return "FUNC " + ABIName;
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        IRStmt stmt = (IRStmt) v.visit(this, body);

        if (stmt != body) return new IRFuncDecl(name, ABIName, stmt);

        return this;
    }

    @Override
    public <T> T aggregateChildren(AggregateVisitor<T> v) {
        T result = v.unit();
        result = v.bind(result, v.visit(body));
        return result;
    }

    @Override
    public InsnMapsBuilder buildInsnMapsEnter(InsnMapsBuilder v) {
        v.addNameToCurrentIndex(ABIName);
        v.addInsn(this);
        return v;
    }

    @Override
    public IRNode buildInsnMaps(InsnMapsBuilder v) {
        return this;
    }

    @Override
    public void printSExp(SExpPrinter p) {
        p.startList();
        p.printAtom("FUNC");
        p.printAtom(ABIName);
        body.printSExp(p);
        p.endList();
    }
    
    public void accept(IRTreeVisitor irv) {
    	irv.visit(this);
    }

	@Override
	public void addLeft(IRNode irn) {
		return;
	}

	@Override
	public void addRight(IRNode irn) {
		return;
	}

	public int getNumArgs() {
		return numArgs;
	}

	public void setNumArgs(int numArgs) {
		this.numArgs = numArgs;
	}

	public int getNumReturns() {
		return numReturns;
	}

	public void setNumReturns(int numReturns) {
		this.numReturns = numReturns;
	}

	public List<String> getParamList() {
		return paramList;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}

	public int getNumSavedCalleeRegs() {
		return numSavedCalleeRegs;
	}

	public void setNumSavedCalleeRegs(int numSavedCalleeRegs) {
		this.numSavedCalleeRegs = numSavedCalleeRegs;
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
		IRFuncDecl clone = new IRFuncDecl(name, ABIName, (IRStmt)body.copy());
		clone.setParamList(paramList);
		clone.setNumArgs(numArgs);
		clone.setNumReturns(numReturns);
		return clone;
	}
}
