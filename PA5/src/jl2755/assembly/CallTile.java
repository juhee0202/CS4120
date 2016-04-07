package jl2755.assembly;

public class CallTile implements Tile {

	private Constant dest;
	
	public CallTile(Constant destination) {
		dest = destination;
	}
	
    public Constant getDest() {
		return dest;
	}

	public void setDest(Constant dest) {
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
