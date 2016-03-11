package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Class that is a recursive box for
 * holding use statements.
 */
public class UseId {
	private Identifier identifier;
	private UseId useId;
	private boolean hasSemicolon;
	/**
	 * 0 for base case, 1 for recursive case
	 */
	private int index;
	
	public UseId(Identifier s, boolean b) {
		identifier = s;
		setHasSemicolon(b);
		index = 0;
	}
	
	public UseId(Identifier s, UseId ui, boolean b) {
		identifier = s;
		useId = ui;
		setHasSemicolon(b);
		index = 1;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("use");
		tempPrinter.printAtom(identifier.toString());
		tempPrinter.endList();
		if (index == 1) {
			useId.prettyPrintNode();
		}
	}

	/**
	 * @return the hasSemicolon
	 */
	public boolean isHasSemicolon() {
		return hasSemicolon;
	}

	/**
	 * @param hasSemicolon the hasSemicolon to set
	 */
	public void setHasSemicolon(boolean hasSemicolon) {
		this.hasSemicolon = hasSemicolon;
	}	
	
	public void accept(Visitor v){
		v.visit(this);
	}
	
	/**
	 * @return a List of all the Files that are imported by
	 * this xi file
	 */
	public List<String> getUseFiles() {
		List<String> tempList = new ArrayList<String>();
		if (index == 0){
			tempList.add(identifier.toString());
		}
		if (index == 1){
			tempList.add(identifier.toString());
			tempList.addAll(useId.getUseFiles());
		}
		return tempList;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
}
