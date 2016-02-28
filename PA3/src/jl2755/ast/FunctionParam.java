package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class FunctionParam {
	private VarDecl varDecl;
	private FunctionParam functionParam;
	private int index;
	
	public FunctionParam(){
		index = 2755;
	}
	
	public FunctionParam(VarDecl vd) {
		varDecl = vd;
		index = 0;
	}
	
	public FunctionParam(VarDecl vd, FunctionParam fp) {
		varDecl = vd;
		functionParam = fp;
		index = 1;
	}
	
	public List<VarDecl> getParams() {
		List<VarDecl> l = new ArrayList<VarDecl>();
		if (index == 0) {
			l.add(varDecl);
		} else if (index == 1) {
			l.add(varDecl);
			l.addAll(functionParam.getParams());
		}
		return l;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 2755){
			return;
		}
		varDecl.prettyPrintNode();
		if (index == 1) {
			functionParam.prettyPrintNode();
		}	
	}
}
