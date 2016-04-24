package edu.cornell.cs.cs4120.xic.ir.visit;

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
import edu.cornell.cs.cs4120.xic.ir.IRReturn;
import edu.cornell.cs.cs4120.xic.ir.IRSeq;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;

public interface IRTreeVisitor {
	public void visit(IRBinOp bo);
	public void visit(IRCall call);
	public void visit(IRCJump cj);
	public void visit(IRCompUnit cu);
	public void visit(IRConst con);
	public void visit(IRESeq eseq);
	public void visit(IRExp exp);
	public void visit(IRFuncDecl fd);
	public void visit(IRJump j);
	public void visit(IRLabel l);
	public void visit(IRMem mem);
	public void visit(IRMove mov);
	public void visit(IRName name);
	public void visit(IRReturn ret);
	public void visit(IRSeq seq);
	public void visit(IRTemp temp);
}
