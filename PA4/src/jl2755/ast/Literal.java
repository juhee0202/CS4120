package jl2755.ast;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

/**
 * Class that encapsulates raw literals strings, characters,
 * integers, and booleans.
 */
public class Literal implements Constant {
	private String intLit;
	private int col;
	private int line;
	private String stringLit;
	private String charLit;
	private Boolean boolLit;
	/**
	 * 0 for int literal,
	 * 1 for string literal,
	 * 2 for character literal,
	 * 3 for boolean literal
	 */
	private int index;
	
	/**
	 * Constructor for either INT_LITERAL or STRING_LITERAL or CHAR_LITERAL
	 */
	public Literal(String s, int i, int left, int right) {
		index = i;
		line = left;
		col = right;
		if (index == 0) {
			intLit = s;
		} else if (index == 1) {
			stringLit = s;
		} else {
			charLit = s;
		}
	}
	
	public Literal(boolean b, int bleft, int bright) {
		boolLit = b;
		line = bleft;
		col = bright;
		index = 3;
	}
	
	public Literal(String s) {
		index = 0;
		intLit = s;
	}
	
	public Literal(String s, int i) {
		index = i;
		if (index == 0) {
			intLit = s;
		}
		else if (index == 1) {
			stringLit = s;
		}
		else {
			charLit = s;
		}
	}
	
	public Literal(boolean b) {
		boolLit = b;
		index = 3;
	}
	
	public String getIntLit() {
		return intLit;
	}

	public void setIntLit(String intLit) {
		this.intLit = intLit;
	}

	public String getStringLit() {
		return collapseEscapes(stringLit);
	}

	public void setStringLit(String stringLit) {
		this.stringLit = stringLit;
	}

	public String getCharLit() {
		return charLit;
	}

	public void setCharLit(String charLit) {
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

	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.printAtom(this.toString());
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
	
	public void accept(ASTVisitor v){
		v.visit(this);
	}

	@Override
	public int getColumnNumber() {
		return col;
	}

	@Override
	public int getLineNumber() {
		return line;
	}
	
	private String collapseEscapes(String arg) {
		String newString = "";
		for (int i = 0; i < arg.length(); i++) {
			
			if (!(arg.charAt(i) == '\\')) {
				newString += arg.charAt(i);
			}
			else {
				char nextChar = arg.charAt(i+1);
				if (nextChar == 'n') {
					newString += '\n';
				}
				else if (nextChar == '\'') {
					newString += '\'';
				}
				else if (nextChar == '\\') {
					newString += '\\';
				}
				else if (nextChar == '\"') {
					newString += '\"';
				}
				i++;
			}
		}
		
		return newString;
	}
}
