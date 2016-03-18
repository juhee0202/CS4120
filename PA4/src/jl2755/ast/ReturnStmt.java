package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.Visitor;

/**
 * Class that encapsulates the return statement of a block
 * statement. 
 */
public class ReturnStmt {
	private ReturnList returnList;
	private int return_col;
	private int return_line;
	/**
	 * 0 if it's a procedural return, 1 if it returns something
	 */
	private int index;
	
	public ReturnStmt(int rleft, int rright) {
		returnList = null;
		index = 0;
		return_line = rleft;
		return_col = rright;
	}
	
	public ReturnStmt(ReturnList rl, int rleft, int rright) {
		returnList = rl;
		index = 1;
		return_line = rleft;
		return_col = rright;
	}
	
	public void prettyPrintNode() {
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		tempPrinter.printAtom("return");
		if (index == 1) {
			returnList.prettyPrintNode();
		}
		tempPrinter.endList();
	}
	
	public List<Expr> getListOfExpr() {
		return returnList.getListOfExpr();
	}

	public ReturnList getReturnList() {
		return returnList;
	}

	public void setReturnList(ReturnList returnList) {
		this.returnList = returnList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getReturn_col() {
		return return_col;
	}

	public void setReturn_col(int return_col) {
		this.return_col = return_col;
	}

	public int getReturn_line() {
		return return_line;
	}

	public void setReturn_line(int return_line) {
		this.return_line = return_line;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
