package edu.cornell.cs.cs4120.xic.ir.visit;

import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;

public class BasicBlock {

	/** The label at the start of this basic block. */
	public IRLabel label;
	/** The list of stmts in this basic block. */
	public List<IRStmt> stmtList;
	/** Type of jump at the end of this basic block. 
	 * 	0: No jump
	 *  1: CJump
	 *  2: Jump
	 *  3: Return
	 */
	public int index;
	
	/**
	 * Creates a BasicBlock to be used in block reordering
	 * 
	 * @param list	The list of IRStmts in this basic block
	 * @param i		The type of jump at the end of this basic block
	 */
	public BasicBlock(List<IRStmt> list, int i) {
		stmtList = list;
		label = (IRLabel) list.get(0);
		index = i;
	}
	
	
}
