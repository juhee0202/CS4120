package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class PureContentArrayType {
	private PrimitiveType primitiveType;
	private BracketsWithContent bracketsWithContent;
	
	public PureContentArrayType(PrimitiveType argPrim,
								BracketsWithContent argBra){
		primitiveType = argPrim;
		bracketsWithContent = argBra;
	}
	
	public PrimitiveType getPrimitiveType(){
		return primitiveType;
	}
	
	public List<Expr> getBracketsWithContent(){
		return bracketsWithContent.getContent();
	}
	
	public void prettyPrintToNode(){
		List<Expr> list = new ArrayList<Expr>();
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		list = bracketsWithContent.getContent();
		for (int i = 0; i < list.size(); i++) {
			tempPrinter.startList();
			tempPrinter.printAtom("[]");
		}
		for (int i = list.size()-1; i >= 0; i--) {
			list.get(i).prettyPrintNode();
			tempPrinter.endList();
		}
	}
}
