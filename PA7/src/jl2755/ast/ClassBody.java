package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing a class body in a recursive manner
 *
 */
public class ClassBody {
	private FieldDecl fieldDecl;
	private ClassBody classBody;
	private FunctionDecl functionDecl;
	private int index;
	
	public ClassBody(FieldDecl fd) {
		fieldDecl = fd;
		index = 0;
	}

	public ClassBody() {
		index = 4;
	}
	
	public ClassBody(FieldDecl fd, ClassBody cb) {
		fieldDecl = fd;
		classBody = cb;
		index = 1;
	}
	
	public ClassBody(FunctionDecl fd) {
		functionDecl = fd;
		index = 2;
	}
	
	public ClassBody(FunctionDecl fd, ClassBody cb) {
		functionDecl = fd;
		classBody = cb;
		index = 3;
	}
	
	/**
	 * @return a list of all declarations in the class body
	 */
	public List<Decl> getAllDecls() {
		List<Decl> decls = new ArrayList<Decl>();
		if (fieldDecl != null) {
			decls.add(fieldDecl);
		} else if (functionDecl != null) {
			decls.add(functionDecl);
		}
		
		if (classBody != null) {
			decls.addAll(classBody.getAllDecls());
		}
		
		return decls;
	}
	
	public List<FunctionDecl> getMethods() {
		List<FunctionDecl> methods = new ArrayList<FunctionDecl>();
		if (functionDecl != null) {
			methods.add(functionDecl);
		}
		if (classBody != null) {
			methods.addAll(classBody.getMethods());
		}
		return methods;
	}
	
	public List<FieldDecl> getFields() {
		List<FieldDecl> fields = new ArrayList<FieldDecl>();
		if (fieldDecl != null) {
			fields.add(fieldDecl);
		}
		if (classBody != null) {
			fields.addAll(classBody.getFields());
		}
		return fields;
	}
	
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		
		tempPrinter.startList();
		switch (index) {
		case 0:
		case 1:
			fieldDecl.prettyPrintNode();
			break;
		case 2:
		case 3:
			functionDecl.prettyPrintNode();
			break;
		}
		tempPrinter.endList();
		if (index%2 == 1) {
			classBody.prettyPrintNode();
		}
	}
}
