package jl2755.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;

public class BasicBlock {

	/** The label at the start of this basic block. */
	public String label;
	/** The list of stmts in this basic block. */
	public List<IRStmt> stmtList;
	/** Type of jump at the end of this basic block. 
	 *  1: Jump
	 *  2: CJump
	 *  3: Return
	 */
	public int index;
	/**
	 * The label of the block right after this one in
	 * the original LIR tree.
	 */
	public BasicBlock nextBlock1;
	public BasicBlock nextBlock2;
	/** Whether this basic block is marked. */
	public boolean marked;
	/** The predecessors of this basic block. */
	public List<BasicBlock> preds;
	
	public BasicBlock(IRLabel argLabel) {
		label = argLabel.name();
		stmtList = new ArrayList<IRStmt>();
		stmtList.add(argLabel);
		index = -1;
		nextBlock1 = null;
		nextBlock2 = null;
		marked = false;
		preds = new ArrayList<BasicBlock>();
	}
	
	/**
	 * Creates a BasicBlock to be used in block reordering
	 * 
	 * @param list	The list of IRStmts in this basic block
	 * @param i		The type of jump at the end of this basic block
	 */
	public BasicBlock(List<IRStmt> list, int i) {
		stmtList = list;
		label = ((IRLabel) list.get(0)).name();
		index = i;
	}
	
	public void addIRStmt(IRStmt stmt) {
		stmtList.add(stmt);
	}
	
	public IRStmt getLastStmt() {
		return stmtList.get(stmtList.size() - 1);
	}
	
	public void setLastStmt(IRStmt s) {
		stmtList.set(stmtList.size() - 1, s);
	}
	
	public void removeLastStmt() {
		stmtList.remove(stmtList.size() - 1);
	}
	
	public boolean hasUnmarkedSucc() {
		boolean result = false;
		if (nextBlock1 != null) {
			result |= !nextBlock1.marked;
			if (nextBlock2 != null) {
				result |= !nextBlock2.marked;
			}
		}
		
		return result;
	}
	
}
