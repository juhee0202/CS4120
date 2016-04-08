package jl2755.visitor;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRCJump;
import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRConst;
import edu.cornell.cs.cs4120.xic.ir.IRESeq;
import edu.cornell.cs.cs4120.xic.ir.IRExp;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import edu.cornell.cs.cs4120.xic.ir.IRJump;
import edu.cornell.cs.cs4120.xic.ir.IRLabel;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRName;
import edu.cornell.cs.cs4120.xic.ir.IRNode;
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRSeq;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;

public class IRTreeEqualsVisitor implements IRTreeVisitor{

	private static boolean doesEquals(IRNode left, IRNode right) {
		if (left instanceof IRBinOp) {
			if (right instanceof IRBinOp) {
				return true;
			}
			return false;
		}
		if (left instanceof IRCall) {
			if (right instanceof IRCall) {
				return true;
			}
			return false;
		}
		if (left instanceof IRCJump) {
			if (right instanceof IRCJump) {
				return true;
			}
			return false;
		}
		if (left instanceof IRCompUnit) {
			if (right instanceof IRCompUnit) {
				return true;
			}
			return false;
		}
		if (left instanceof IRConst) {
			if (right instanceof IRConst) {
				return true;
			}
			return false;
		}
		if (left instanceof IRESeq) {
			if (right instanceof IRESeq) {
				
			}
		}
		
		return false;
	}
	
	@Override
	public void visit(IRBinOp bo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRCall call) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRCJump cj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRCompUnit cu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRConst con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRESeq eseq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRExp exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRFuncDecl fd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRJump j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRLabel l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRMem mem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRMove mov) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRName name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRReturn ret) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRSeq seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRTemp temp) {
		// TODO Auto-generated method stub
		
	}

}
