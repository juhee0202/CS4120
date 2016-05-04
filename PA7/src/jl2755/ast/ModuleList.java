package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

public class ModuleList {
	private ClassDecl classDecl;
	private FunctionDecl functionDecl;
	private GlobalDecl globalDecl;
	private ModuleList moduleList;
	
	public ModuleList(ClassDecl cd) {
		classDecl = cd;
	}
	
	public ModuleList(ClassDecl cd, ModuleList ml) {
		classDecl = cd;
		moduleList = ml;
	}
	
	public ModuleList(FunctionDecl fd) {
		functionDecl = fd;
	}
	
	public ModuleList(FunctionDecl fd, ModuleList ml) {
		functionDecl = fd;
		moduleList = ml;
	}
	
	public ModuleList(GlobalDecl gd) {
		globalDecl = gd;
	}
	
	public ModuleList(GlobalDecl gd, ModuleList ml) {
		globalDecl = gd;
		moduleList = ml;
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
		// TODO Auto-generated method stub
		
	}
}
