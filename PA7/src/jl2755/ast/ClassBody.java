package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing a class body in a recursive manner
 *
 */
public class ClassBody {
	private GlobalDecl globalDecl;
	private ClassBody classBody;
	private FunctionDecl functionDecl;
	
	public ClassBody(GlobalDecl gd) {
		globalDecl = gd;
	}
	
	public ClassBody(GlobalDecl gd, ClassBody cb) {
		globalDecl = gd;
		classBody = cb;
	}
	
	public ClassBody(FunctionDecl fd) {
		functionDecl = fd;
	}
	
	public ClassBody(FunctionDecl fd, ClassBody cb) {
		functionDecl = fd;
		classBody = cb;
	}
	
	/**
	 * @return a list of all declarations in the class body
	 */
	public List<Decl> getAllDecls() {
		List<Decl> decls = new ArrayList<Decl>();
		if (globalDecl != null) {
			decls.add(globalDecl);
		} else if (functionDecl != null) {
			decls.add(functionDecl);
		}
		
		if (classBody != null) {
			decls.addAll(classBody.getAllDecls());
		}
		
		return decls;
	}
	
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
	
	public void prettyPrintNode() {
		// TODO
	}
}
