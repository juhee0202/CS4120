package jl2755.assembly;

public class JumpTile implements Tile {

	private Operand dest;
	
	public JumpTile(Operand destination) {
		dest = destination;
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
