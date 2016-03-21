package jl2755.visitor;

import java.math.BigInteger;
import java.util.List;

import jl2755.ast.ArrayElement;
import jl2755.ast.ArrayElementList;
import jl2755.ast.ArrayLiteral;
import jl2755.ast.AssignmentStmt;
import jl2755.ast.BinaryExpr;
import jl2755.ast.BinaryOp;
import jl2755.ast.BlockStmt;
import jl2755.ast.Expr;
import jl2755.ast.FunctionArg;
import jl2755.ast.FunctionCall;
import jl2755.ast.FunctionDecl;
import jl2755.ast.FunctionDeclList;
import jl2755.ast.Identifier;
import jl2755.ast.IfStmt;
import jl2755.ast.IndexedBrackets;
import jl2755.ast.Literal;
import jl2755.ast.Program;
import jl2755.ast.ReturnList;
import jl2755.ast.ReturnStmt;
import jl2755.ast.Stmt;
import jl2755.ast.StmtList;
import jl2755.ast.TupleDeclList;
import jl2755.ast.TupleInit;
import jl2755.ast.UnaryExpr;
import jl2755.ast.UnaryOp;
import jl2755.ast.UseId;
import jl2755.ast.VarDecl;
import jl2755.ast.VarInit;
import jl2755.ast.WhileStmt;

/**
 * This visitor's goal is to sniff out and hunt expressions
 * that may be literals. If a unary or binary expression contains
 * only literals, then it can be reduced to one constant.
 */
public class ConstantFolderVisitor implements ASTVisitor{

	/**
	 * 0 if int,
	 * 1 if bool,
	 * 2 if array,
	 * 3 if none
	 */
	private int caseIndex;
	private long tempLong;
	private Boolean tempBool;
	private ArrayLiteral tempArray;
	
	
	@Override
	public void visit(ArrayElement ae) {
		// ArrayElements always have IndexedBrackets that
		// might contain expressions
		ae.getIndexedBrackets().accept(this);
		caseIndex = 3;
	}

	@Override
	public void visit(ArrayElementList ael) {
		// ArrayElementList has expressions in
		// its list that may be foldable
		int index = ael.getIndex();
		if (index == 0 || index == 1) {
			ael.getExpr().accept(this);
			if (caseIndex == 0) {
				ael.setExpr(new Literal(tempLong + "", 0));
			}
			if (caseIndex == 1) {
				ael.setExpr(new Literal(tempBool));
			}
			if (caseIndex == 2) {
				ael.setExpr(tempArray);
			}
		}
		if (index == 1) {
			ael.getArrElemList().accept(this);
		}
	}

	@Override
	public void visit(ArrayLiteral al) {
		// Simply visit the child ArrayElementList
		al.getArrElemList().accept(this);
		caseIndex = 2;
		tempArray = al;
	}

	@Override
	public void visit(AssignmentStmt as) {
		as.getExpr().accept(this);
		if (caseIndex == 0) {
			as.setExpr(new Literal(tempLong + "", 0));
		}
		if (caseIndex == 1) {
			as.setExpr(new Literal(tempBool));
		}
		if (caseIndex == 2) {
			as.setExpr(tempArray);
		}
		if (as.getIndex() != 0) {
			as.getIndexedBrackets().accept(this);
		}
	}

	@Override
	public void visit(BinaryExpr be) {
		be.getLeftExpr().accept(this);
		if (caseIndex == 0) {
			long leftLong = tempLong;
			be.getRightExpr().accept(this);
			if (caseIndex != 0) {
				return;
			}
			long rightLong = tempLong;
			BinaryOp theOp = be.getBinaryOp();
			if (theOp == BinaryOp.PLUS) {
				tempLong = leftLong + rightLong;
			}
			else if (theOp == BinaryOp.DIVIDE) {
				if (rightLong == 0) {
					caseIndex = 3;
					return;
				}
				tempLong = leftLong/rightLong;
			}
			else if (theOp == BinaryOp.TIMES) {
				tempLong = leftLong * rightLong;
			}
			else if (theOp == BinaryOp.HIGH_MULT) {
				tempLong = BigInteger.valueOf(leftLong).multiply
						(BigInteger.valueOf(rightLong)).shiftRight(64).longValue();
			}
			else if (theOp == BinaryOp.MODULO) {
				tempLong = leftLong%rightLong;
			}
			else if (theOp == BinaryOp.MINUS) {
				tempLong = leftLong - rightLong;
			}
			else if (theOp == BinaryOp.LT) {
				caseIndex = 1;
				tempBool = leftLong < rightLong;
			}
			else if (theOp == BinaryOp.LEQ) {
				caseIndex = 1;
				tempBool = leftLong <= rightLong;
			}
			else if (theOp == BinaryOp.GEQ) {
				caseIndex = 1;
				tempBool = leftLong >= rightLong;
			}
			else if (theOp == BinaryOp.GT) {
				caseIndex = 1;
				tempBool = leftLong > rightLong;
			}
			else if (theOp == BinaryOp.EQUAL) {
				caseIndex = 1;
				tempBool = leftLong == rightLong;
			}
			else if (theOp == BinaryOp.NOT_EQUAL) {
				caseIndex = 1;
				tempBool = leftLong != rightLong;
			}
		}
		else if (caseIndex == 1) {
			boolean leftBool = tempBool;
			be.getRightExpr().accept(this);
			if (caseIndex != 1) {
				return;
			}
			boolean rightBool = tempBool;
			BinaryOp theOp = be.getBinaryOp();
			if (theOp == BinaryOp.EQUAL) {
				caseIndex = 1;
				tempBool = leftBool == rightBool;
			}
			else if (theOp == BinaryOp.NOT_EQUAL) {
				caseIndex = 1;
				tempBool = leftBool != rightBool;
			}
			else if (theOp == BinaryOp.AND) {
				caseIndex = 1;
				tempBool = leftBool && rightBool;
			}
			else if (theOp == BinaryOp.OR) {
				caseIndex = 1;
				tempBool = leftBool || rightBool;
			}
		}
		else if (caseIndex == 2) {
			ArrayLiteral leftArray = tempArray;
			be.getRightExpr().accept(this);
			if (caseIndex != 2) {
				return;
			}
			ArrayLiteral rightArray = tempArray;
			BinaryOp theOp = be.getBinaryOp();
			if (theOp == BinaryOp.PLUS) {
				tempArray = ArrayLiteral.addTwoArrays(leftArray, rightArray);
			}
		}
	}

	@Override
	public void visit(BlockStmt bs) {
		if (bs.getIndex() == 1 || bs.getIndex() == 2) {
			bs.getStmtList().accept(this);
		}
		if (bs.getIndex() == 2 || bs.getIndex() == 3) {
			bs.getReturnStmt().accept(this);
		}
	}

	@Override
	public void visit(FunctionArg fa) {
		fa.getExpr().accept(this);
		if (caseIndex == 0) {
			fa.setExpr(new Literal(tempLong + "", 0));
		}
		if (caseIndex == 1) {
			fa.setExpr(new Literal(tempBool));
		}
		if (caseIndex == 2) {
			fa.setExpr(tempArray);
		}
		if (fa.getIndex() == 1) {
			fa.getFunctionArg().accept(this);
		}
	}

	@Override
	public void visit(FunctionCall fc) {
		if (fc.getIndex() == 1) {
			fc.getFunctionArg().accept(this);
		}
		if (fc.getIndex() == 2) {
			fc.getExpr().accept(this);
			if (caseIndex == 0) {
				fc.setExpr(new Literal(tempLong + "", 0));
			}
			if (caseIndex == 1) {
				fc.setExpr(new Literal(tempBool));
			}
			if (caseIndex == 2) {
				fc.setExpr(tempArray);
			}
		}
		caseIndex = 3;
	}

	@Override
	public void visit(FunctionDecl fd) {
		fd.getBlockStmt().accept(this);
		
	}

	@Override
	public void visit(Identifier id) {
		caseIndex = 3;
	}

	@Override
	public void visit(IfStmt is) {
		is.getExpr().accept(this);
		if (caseIndex == 0) {
			is.setExpr(new Literal(tempLong + "", 0));
		}
		if (caseIndex == 1) {
			is.setExpr(new Literal(tempBool));
		}
		if (caseIndex == 2) {
			is.setExpr(tempArray);
		}
		is.getStmt1().accept(this);
		if (is.getIndex() == 1) {
			is.getStmt2().accept(this);
		}
	}
	
	@Override
	public void visit(IndexedBrackets ib) {
		ib.getExpression().accept(this);
		if (caseIndex == 0) {
			ib.setExpression(new Literal(tempLong + "", 0));
		}
		if (caseIndex == 1) {
			ib.setExpression(new Literal(tempBool));
		}
		if (caseIndex == 2) {
			ib.setExpression(tempArray);
		}
		if (ib.getIndex() == 1) {
			ib.getIndexedBrackets().accept(this);
		}
	}

	@Override
	public void visit(Literal l) {
		if (l.getIndex() == 0) {
			tempLong = Long.parseLong(l.getIntLit());
			caseIndex = 0;
			return;
		}
		if (l.getIndex() == 1) {
			tempArray = new ArrayLiteral(l.getStringLit());
			caseIndex = 2;
		}
		if (l.getIndex() == 2) {
			tempLong = l.getCharLit().charAt(0);
			caseIndex = 0;
		}
		if (l.getIndex() == 3) {
			tempBool = l.getBoolLit();
			caseIndex = 1;
		}
	}

	@Override
	public void visit(Program p) {
		List<FunctionDecl> funcs = p.getFunctionDecls();
		for (FunctionDecl fd: funcs) {
			fd.accept(this);
		}
	}
	
	@Override
	public void visit(ReturnStmt rs) {
		if (rs.getIndex() == 1) {
			List<Expr> returns = rs.getReturns();
			ReturnList rl = rs.getReturnList();
			for (Expr e: returns) {
				e.accept(this);
				if (caseIndex == 0) {
					rl.setExpr(new Literal(tempLong + "", 0));
				}
				else if (caseIndex == 1) {
					rl.setExpr(new Literal(tempBool));
				}
				else if (caseIndex == 2) {
					rl.setExpr(tempArray);
				}
			}
		}
	}

	@Override
	public void visit(Stmt s) {
		s.getNakedStmt().accept(this);
	}

	@Override
	public void visit(StmtList sl) {
		sl.getStmt().accept(this);
		if (sl.getIndex() == 1) {
			sl.getStmtList().accept(this);
		}
	}
	
	@Override
	public void visit(TupleInit ti) {
		ti.getFunctionCall().accept(this);
		if (ti.getIndex() != 0) {
			List<VarDecl> vdList = ti.getVarDecls();
			for (VarDecl vd: vdList) {
				if (vd != null) {
					vd.accept(this);
				}
			}
		}
	}

	@Override
	public void visit(UnaryExpr ue) {
		ue.getExpr().accept(this);
		if (caseIndex == 0) {
			ue.setExpr(new Literal(tempLong + "", 0));
		}
		if (caseIndex == 1) {
			ue.setExpr(new Literal(tempBool));
		}
		if (caseIndex == 2) {
			ue.setExpr(tempArray);
		}
		if (caseIndex == 0) {
			UnaryOp tempOp = ue.getUnaryOp();
			if (tempOp == UnaryOp.INT_NEG) {
				tempLong = -tempLong;
			}
		}
		else if (caseIndex == 1) {
			UnaryOp tempOp = ue.getUnaryOp();
			if (tempOp == UnaryOp.LOG_NEG) {
				tempBool = !tempBool;
			}
		}
	}

	@Override
	public void visit(UseId ui) {
		// Nothing should be done here.
	}

	@Override
	public void visit(VarDecl vd) {
		if (vd.getIndex() == 0) {
			vd.getMixedArrayType().accept(this);
		}
	}

	@Override
	public void visit(VarInit vi) {
//		vi.getVarDecl().accept(this);
		vi.getType().accept(this);
		vi.getExpr().accept(this);
		if (caseIndex == 0) {
			vi.setExpr(new Literal(tempLong + "", 0));
		}
		if (caseIndex == 1) {
			vi.setExpr(new Literal(tempBool));
		}
		if (caseIndex == 2) {
			vi.setExpr(tempArray);
		}
	}

	@Override
	public void visit(WhileStmt ws) {
		ws.getExpr().accept(this);
		if (caseIndex == 0) {
			ws.setExpr(new Literal(tempLong + "", 0));
		}
		if (caseIndex == 1) {
			ws.setExpr(new Literal(tempBool));
		}
		if (caseIndex == 2) {
			ws.setExpr(tempArray);
		}
		ws.getStmt().accept(this);
	}

}
