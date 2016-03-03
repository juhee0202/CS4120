package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class Program extends XiFile {
	private UseId useId;
	private FunctionDeclList functionDeclList;
	private int index;
	
	public Program(FunctionDeclList fdl) {
		functionDeclList = fdl;
		index = 0;
	}
	
	public Program(UseId ui, FunctionDeclList fdl) {
		useId = ui;
		functionDeclList = fdl;
		index = 1;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		if (index == 0) {
			tempPrinter.startList();
			tempPrinter.endList();
			tempPrinter.startList();
			functionDeclList.prettyPrintNode();
			tempPrinter.endList();
		} else {
			tempPrinter.startList();
			useId.prettyPrintNode();
			tempPrinter.endList();
			tempPrinter.startList();
			functionDeclList.prettyPrintNode();
			tempPrinter.endList();
		}
		tempPrinter.endList();
	}
	
	@Override
	public String toString(){
		return "";
	}

	public UseId getUseId() {
		return useId;
	}

	public void setUseId(UseId useId) {
		this.useId = useId;
	}

	public FunctionDeclList getFunctionDeclList() {
		return functionDeclList;
	}

	public void setFunctionDeclList(FunctionDeclList functionDeclList) {
		this.functionDeclList = functionDeclList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
