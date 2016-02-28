package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

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
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public String toString(){
		String ret = "";
		
		switch (index) {
			case 0: ret = String.valueOf(intLit); break;
			case 1: ret = "\"" + stringLit + "\""; break;
			case 2: ret = "\'" + String.valueOf(charLit) + "\'"; break;
			case 3: ret = String.valueOf(boolLit); break;
		}
		
		return ret;
	}
}
