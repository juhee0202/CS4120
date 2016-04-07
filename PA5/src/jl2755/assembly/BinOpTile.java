package jl2755.assembly;

import edu.cornell.cs.cs4120.xic.ir.OpType;

public class BinOpTile implements Tile {
    
	/** The source operand of this binary operation. */
	private Operand src;
	/** The destination operand of this binary operation. */
	private Operand dest;
	/** The operation type. */
	private OpType op;
	
	public BinOpTile(Operand source, Operand destination, OpType opType) {
		src = source;
		dest = destination;
		op = opType;
	}
	
    public Operand getSrc() {
		return src;
	}

	public void setSrc(Operand src) {
		this.src = src;
	}

	public Operand getDest() {
		return dest;
	}

	public void setDest(Operand dest) {
		this.dest = dest;
	}

	public OpType getOp() {
		return op;
	}

	public void setOp(OpType op) {
		this.op = op;
	}

	@Override
	public Operand getRelevantOperand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCycles() {
		// TODO Auto-generated method stub
		return 0;
	}


}
