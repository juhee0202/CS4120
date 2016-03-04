package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

public class Literal implements Constant {
	private Long intLit;
	private int intLit_col;
	private int intLit_line;
	private String stringLit;
	private int stringLit_col;
	private int stringLit_line;
	private Character charLit;
	private int charLit_col;
	private int charLit_line;
	private Boolean boolLit;
	private int boolLit_col;
	private int boolLit_line;
	/**
	 * 0 for int literal,
	 * 1 for string literal,
	 * 2 for character literal,
	 * 3 for boolean literal
	 */
	private int index;
	
	public Literal(long i, int ileft, int iright) {
		intLit = i;
		intLit_line = ileft;
		intLit_col = iright;
		index = 0;
	}
	
	public Literal(String s, int sleft, int sright) {
		stringLit = s;
		stringLit_line = sleft;
		stringLit_col = sright;
		index = 1;
	}
	
	public Literal(char c, int cleft, int cright) {
		charLit = c;
		charLit_line = cleft;
		charLit_col = cright;
		index = 2;
	}
	
	public Literal(boolean b, int bleft, int bright) {
		boolLit = b;
		boolLit_line = bleft;
		boolLit_col = bright;
		index = 3;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(this.toString());
	}
	
	public Long getIntLit() {
		return intLit;
	}

	public void setIntLit(Long intLit) {
		this.intLit = intLit;
	}

	public String getStringLit() {
		return stringLit;
	}

	public void setStringLit(String stringLit) {
		this.stringLit = stringLit;
	}

	public Character getCharLit() {
		return charLit;
	}

	public void setCharLit(Character charLit) {
		this.charLit = charLit;
	}

	public Boolean getBoolLit() {
		return boolLit;
	}

	public void setBoolLit(Boolean boolLit) {
		this.boolLit = boolLit;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIntLit_col() {
		return intLit_col;
	}

	public void setIntLit_col(int intLit_col) {
		this.intLit_col = intLit_col;
	}

	public int getIntLit_line() {
		return intLit_line;
	}

	public void setIntLit_line(int intLit_line) {
		this.intLit_line = intLit_line;
	}

	public int getStringLit_col() {
		return stringLit_col;
	}

	public void setStringLit_col(int stringLit_col) {
		this.stringLit_col = stringLit_col;
	}

	public int getStringLit_line() {
		return stringLit_line;
	}

	public void setStringLit_line(int stringLit_line) {
		this.stringLit_line = stringLit_line;
	}

	public int getCharLit_col() {
		return charLit_col;
	}

	public void setCharLit_col(int charLit_col) {
		this.charLit_col = charLit_col;
	}

	public int getCharLit_line() {
		return charLit_line;
	}

	public void setCharLit_line(int charLit_line) {
		this.charLit_line = charLit_line;
	}

	public int getBoolLit_col() {
		return boolLit_col;
	}

	public void setBoolLit_col(int boolLit_col) {
		this.boolLit_col = boolLit_col;
	}

	public int getBoolLit_line() {
		return boolLit_line;
	}

	public void setBoolLit_line(int boolLit_line) {
		this.boolLit_line = boolLit_line;
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
	
	public void accept(Visitor v){
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		if (index == 0) {
			return intLit_col;
		}
		if (index == 1) {
			return stringLit_col;
		}
		if (index == 2) {
			return charLit_col;
		}
		if (index == 3) {
			return boolLit_col;
		}
		return 0;
	}

	@Override
	public int getLineNumber() {
		if (index == 0) {
			return intLit_line;
		}
		if (index == 1) {
			return stringLit_line;
		}
		if (index == 2) {
			return charLit_line;
		}
		if (index == 3) {
			return boolLit_line;
		}
		return 0;
	}
}
