package jl2755.ast;

public class ArrayLiteral implements Constant {
	private ArrayElementList arrElemList;
	
	public ArrayLiteral(ArrayElementList l) {
		arrElemList = l;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		arrElemList.prettyPrintNode();
		tempPrinter.endList();
	}
	
	@Override
	public String toString(){
		return "";
	}
}
