package jl2755.ast;

public class Literal implements Constant {
	private Long intLit;
	private String stringLit;
	private Character charLit;
	private Boolean boolLit;
	private int index;
	
	public Literal(long i) {
		intLit = i;
		index = 0;
	}
	
	public Literal(String s) {
		stringLit = s;
		index = 1;
	}
	
	public Literal(char c) {
		charLit = c;
		index = 2;
	}
	
	public Literal(boolean b) {
		boolLit = b;
		index = 3;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(this.toString());
	}
	
	@Override
	public String toString(){
		switch (index) {
			case 0: return String.valueOf(intLit);
			case 1: return stringLit;
			case 2: return String.valueOf(charLit);
			case 3: return String.valueOf(boolLit);
		}
		
	}
}
