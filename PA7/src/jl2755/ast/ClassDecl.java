package jl2755.ast;

import jl2755.visitor.ASTVisitor;

/**
 * 
 * Class representing a class declaration
 * ex) class A {...}
 * ex) class A extends B {...}
 *
 */
public class ClassDecl implements Decl {
	private Identifier className;
	private Identifier superclassName;
	private ClassBody classBody;

	public ClassDecl(Identifier cName, ClassBody cBody) {
		className = cName;
		classBody = cBody;
	}
	
	public ClassDecl(Identifier cName, Identifier scName, ClassBody cBody) {
		className = cName;
		superclassName = scName;
		classBody = cBody;
	}
	
	public Identifier getClassName() {
		return className;
	}
	
	public Identifier getSuperclassName() {
		return superclassName;
	}
	
	public ClassBody getClassBody() {
		return classBody;
	}
	
	/**
	 * @return true if this class extends some superclass
	 */
	public boolean hasExtends() {
		return superclassName != null;
	}
	
	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
	
	@Override
	public void prettyPrintNode() {
		// TODO
	}
}
