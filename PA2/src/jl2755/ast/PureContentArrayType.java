package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class PureContentArrayType {
	private PrimitiveType primitiveType;
	private MixedBrackets mixedBrackets;
	
	public PureContentArrayType(PrimitiveType argPrim,
								MixedBrackets argBra){
		primitiveType = argPrim;
		mixedBrackets = argBra;
	}
	
	public PrimitiveType getPrimitiveType(){
		return primitiveType;
	}
	
	public List<Expr> getBracketsWithContent(){
		return mixedBrackets.getContent();
	}
	
	public void prettyPrintToNode(){
		List<Expr> list = new ArrayList<Expr>();
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		list = mixedBrackets.getContent();
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
