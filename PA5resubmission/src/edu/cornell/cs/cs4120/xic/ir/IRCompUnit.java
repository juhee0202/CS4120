package edu.cornell.cs.cs4120.xic.ir;

import java.util.LinkedHashMap;
import java.util.Map;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import jl2755.assembly.ChildType;
import jl2755.visitor.IRTreeVisitor;

/**
 * An intermediate representation for a compilation unit
 */
public class IRCompUnit extends IRNode {
    private String name;
    private Map<String, IRFuncDecl> functions;

    public IRCompUnit(String name) {
        this.name = name;
        functions = new LinkedHashMap<>();
    }

    public IRCompUnit(String name, Map<String, IRFuncDecl> functions) {
        this.name = name;
        this.functions = functions;
    }

    public void appendFunc(IRFuncDecl func) {
        functions.put(func.name(), func);
    }

    public String name() {
        return name;
    }

    public Map<String, IRFuncDecl> functions() {
        return functions;
    }

    public IRFuncDecl getFunction(String name) {
        return functions.get(name);
    }

    @Override
    public String label() {
        return "COMPUNIT";
    }

    @Override
    public IRNode visitChildren(IRVisitor v) {
        boolean modified = false;

        Map<String, IRFuncDecl> results = new LinkedHashMap<>();
        for (IRFuncDecl func : functions.values()) {
            IRFuncDecl newFunc = (IRFuncDecl) v.visit(this, func);
            if (newFunc != func) modified = true;
            results.put(newFunc.name(), newFunc);
        }

        if (modified) return new IRCompUnit(name, results);

        return this;
    }

    @Override
    public <T> T aggregateChildren(AggregateVisitor<T> v) {
        T result = v.unit();
        for (IRFuncDecl func : functions.values())
            result = v.bind(result, v.visit(func));
        return result;
    }

    @Override
    public void printSExp(SExpPrinter p) {
        p.startList();
        p.printAtom("COMPUNIT");
        p.printAtom(name);
        for (IRFuncDecl func : functions.values())
            func.printSExp(p);
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
