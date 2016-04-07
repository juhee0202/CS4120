package jl2755.assembly;

public interface Tile {
    
	public Operand getRelevantOperand();
	
    public int getCycles();
    
    @Override
    public String toString();
    
}
