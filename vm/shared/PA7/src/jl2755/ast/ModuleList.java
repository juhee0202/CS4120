package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class ModuleList {
	private ClassDecl classDecl;
	private FunctionDecl functionDecl;
	private GlobalDecl globalDecl;
	private ModuleList moduleList;
	int index;
	
	public ModuleList(ClassDecl cd) {
		classDecl = cd;
		index = 0;
	}
	
	public ModuleList(ClassDecl cd, ModuleList ml) {
		classDecl = cd;
		moduleList = ml;
		index = 1;
	}
	
	public ModuleList(FunctionDecl fd) {
		functionDecl = fd;
		index = 2;
	}
	
	public ModuleList(FunctionDecl fd, ModuleList ml) {
		functionDecl = fd;
		moduleList = ml;
		index = 3;
	}
	
	public ModuleList(GlobalDecl gd) {
		globalDecl = gd;
		index = 4;
	}
	
	public ModuleList(GlobalDecl gd, ModuleList ml) {
		globalDecl = gd;
		moduleList = ml;
		index = 5;
	}
	
	/**
	 * @return a list of all declaration in the module
	 */
	public List<Decl> getAllDecls() {
		List<Decl> decls = new ArrayList<Decl>();
		if (classDecl != null) {
			decls.add(classDecl);
		} else if (functionDecl != null) {
			decls.add(functionDecl);
		} else {
			decls.add(globalDecl);
		}
		
		if (moduleList != null) {
			decls.addAll(moduleList.getAllDecls());
		}
		
		return decls;
	}

	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		
		tempPrinter.startList();
		switch (index) {
		case 0:
		case 1:
			classDecl.prettyPrintNode();
			break;
		case 2:
		case 3:
			functionDecl.prettyPrintNode();
			break;
		case 4:
		case 5:
			globalDecl.prettyPrintNode();
			break;
		}
		tempPrinter.endList();
		if (index%2 == 1) {
			moduleList.prettyPrintNode();
		}
	}
}
