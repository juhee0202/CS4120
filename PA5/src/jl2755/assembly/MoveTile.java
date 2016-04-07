package jl2755.assembly;

public class MoveTile implements Tile {

	/** The source operand of the mov instruction. */
	private Operand src;
	/** The destination operand of the mov instruction. */
	private Operand dest;
	
	public MoveTile(Operand source, Operand destination) {
		src = source;
		dest = destination;
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
