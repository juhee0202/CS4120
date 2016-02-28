package jl2755.ast;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;

public class MixedArrayType {
	
	private PrimitiveType p_type;
	private MixedBrackets mixedBrackets;
	private Brackets brackets;
	private MixedArrayType arrayType;
	private PureContentArrayType pureArray;
	private int index;
	
	public MixedArrayType(PrimitiveType p_type, Brackets brackets) {
		this.p_type = p_type;
		this.brackets = brackets;
		index = 0;
	}
	
	public MixedArrayType(PrimitiveType p_type, MixedBrackets mb) {
		this.p_type = p_type;
		this.mixedBrackets = mb;
		index = 1;
	}
	
	public void prettyPrintNode() {
		int n = 0;
		List<Expr> list = new ArrayList<Expr>();
		
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		if (index == 0) {
			n = brackets.getNumBrackets();
			for (int i = 0; i < n; i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			p_type.prettyPrintNode();
			for (int i = 0; i < n; i++) {
				tempPrinter.endList();
			}
		} else if (index == 1) {
			list = mixedBrackets.getContent();
			for (int i = 0; i < list.size(); i++) {
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			for (int i = 0; i < mixedBrackets.getNumBrackets(); i++){
				tempPrinter.startList();
				tempPrinter.printAtom("[]");
			}
			p_type.prettyPrintNode();
			for (int i = 0; i < mixedBrackets.getNumBrackets(); i++){
				tempPrinter.endList();
			}
			for (int i = list.size()-1; i >= 0; i--){
				list.get(i).prettyPrintNode();
				tempPrinter.endList();
			}
		}
	}
}