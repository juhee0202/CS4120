package edu.cornell.cs.cs4120.xic.ir;

import java.util.HashMap;
import java.util.Map;

import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.visit.AggregateVisitor;
import edu.cornell.cs.cs4120.xic.ir.visit.IRVisitor;
import jl2755.assembly.ChildType;
import jl2755.assembly.Instruction.Operation;
import jl2755.assembly.Tile;
import jl2755.visitor.IRTreeVisitor;

/**
 * An intermediate representation for a binary operation
 * OP(left, right)
 */
public class IRBinOp extends IRExpr {

    private OpType type;
    private IRExpr left, right;
    private ChildType leftType, rightType;
    private static Map<IRBinOp, Tile> nodeToTile;
    
    /**
     *  0:	add		(const + reg)
     *  1:	add		(reg + reg)
     *  2:	mul		(const * reg)
     *  3:	add with 1 add child	(const + (reg + reg))
     *  4:	add with 1 add child	(reg + (const + reg))
     *  5:	add with 1 mul child	(const + (const * reg))
     *  6:	add with 1 mul child	(reg + (const * reg))
     *  7:	add with 1 add child and 1 mul grandchild	(const + (reg + (reg * const)))
     *  8:	add with 1 add child and 1 mul grandchild	(reg + (const + (reg * const)))
     *  9:	add with 1 add child and 1 mul child	((const + reg) + (reg * const))
     * -1:	other (non-interesting binop)
     */
    private int index;

    public IRBinOp(OpType type, IRExpr left, IRExpr right) {
        this(type, left, right, -1);
        computeIndex();
    }
    
    /**
     * Computes the corresponding index of this IRBinOp and sets the index field.
     * Sets index to the most complex tile that can be made from this IRBinOp.
     */
    private void computeIndex() {
    	int operands = computeChildrenTypes();
    	if (type == OpType.ADD) {
    		if (left instanceof IRBinOp && right instanceof IRBinOp) {
    			IRBinOp leftNode = (IRBinOp) left;
    			IRBinOp rightNode = (IRBinOp) right;
    			if (leftNode.index == 0 && rightNode.index == 2) {
    				index = 9;
    				return;
    			} else if (leftNode.index == 2 && rightNode.index == 0) {
    				index = 9;
    				return;
    			}
    		}
    		if (left instanceof IRBinOp) {
    			IRBinOp childNode = (IRBinOp) left;
    			if (childNode.index == 5 && operands == 1) {
    				index = 8;
    				return;
    			} else if (childNode.index == 6 && operands == 1) {
    				index = 7;
    				return;
    			} else if (childNode.index == 2) {
    				if (operands == 1) {
    					index = 5;
    					return;
    				} else if (operands == 2) {
    					index = 6;
    					return;
    				}
    			} else if (childNode.index == 0 && operands == 2) {
    				index = 4;
    				return;
    			} else if (childNode.index == 1 && operands == 1) {
    				index = 3;
    				return;
    			}
    		}
    		if (right instanceof IRBinOp) {
    			IRBinOp childNode = (IRBinOp) left;
    			if (childNode.index == 5 && operands == 1) {
    				index = 8;
    				return;
    			} else if (childNode.index == 6 && operands == 1) {
    				index = 7;
    				return;
    			} else if (childNode.index == 2) {
    				if (operands == 1) {
    					index = 5;
    					return;
    				} else if (operands == 2) {
    					index = 6;
    					return;
    				}
    			} else if (childNode.index == 0 && operands == 2) {
    				index = 4;
    				return;
    			} else if (childNode.index == 1 && operands == 1) {
    				index = 3;
    				return;
    			}
    		}
    		if (operands == 1) {
    			index = 0;
    		} else if (operands == 2) {
    			index = 1;
    		} else {
    			index = -1;
    		}
    	} else if (type == OpType.MUL) {
    		if (operands == 1) {
    			index = 2;
    		} else {
    			index = -1;
    		}
    	} else {
    		index = -1;
    	}
	}
    
    /**
     * Computes the types of the children of this IRBinOp
     * 
     * @return	0: const, const
     * 			1: const, reg/binop
     * 			2: reg/binop, reg/binop
     * 		   -1: other
     */
    private int computeChildrenTypes() {
    	if (left instanceof IRConst) {
    		if (right instanceof IRConst) {
    			return 0;
    		} else if (right instanceof IRTemp || right instanceof IRBinOp) {
    			return 1;
    		}
    	} else if (left instanceof IRTemp) {
    		if (right instanceof IRConst) {
    			return 1;
    		} else if (right instanceof IRTemp || right instanceof IRBinOp) {
    			return 2;
    		}
    	} else if (left instanceof IRBinOp) {
    		if (right instanceof IRConst) {
    			return 1;
    		} else if (right instanceof IRTemp || right instanceof IRBinOp) {
    			return 2;
    		}
    	}
    	return -1;
    }

	public IRBinOp(OpType type, IRExpr left, IRExpr right, int index) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.index = index;
        if (nodeToTile == null) {
        	nodeToTile = new HashMap<IRBinOp, Tile>();
        }
    }

    public OpType opType() {
        return type;
    }

    public IRExpr left() {
        return left;
    }

    public IRExpr right() {
        return right;
    }

    @Override
    public String label() {
        return type.toString();
    }
    
    public int getIndex() {
		return index;
	}

	public static Map<IRBinOp, Tile> getNodeToTile() {
		return nodeToTile;
	}

	@Override
    public IRNode visitChildren(IRVisitor v) {
        IRExpr left = (IRExpr) v.visit(this, this.left);
        IRExpr right = (IRExpr) v.visit(this, this.right);

        if (left != this.left || right != this.right)
            return new IRBinOp(type, left, right);

        return this;
    }

    @Override
    public <T> T aggregateChildren(AggregateVisitor<T> v) {
        T result = v.unit();
        result = v.bind(result, v.visit(left));
        result = v.bind(result, v.visit(right));
        return result;
    }

    @Override
    public void printSExp(SExpPrinter p) {
        p.startList();
        p.printAtom(type.toString());
        if (left == null) {
        	p.printAtom("null");
        } else {
            left.printSExp(p);
        }
        if (right == null) {
        	p.printAtom("null");
        } else {
            right.printSExp(p);
        }
        p.endList();
    }
    
    public void accept(IRTreeVisitor irv) {
    	irv.visit(this);
    }

	@Override
	public void addLeft(IRNode irn) {
		left = (IRExpr) irn;
	}

	@Override
	public void addRight(IRNode irn) {
		right = (IRExpr) irn;
	}

	@Override
	public ChildType getLeftChildEnumType() {
		return leftType;
	}

	@Override
	public void setLeftChildEnumType(ChildType argEnum) {
		leftType = argEnum;
	}

	@Override
	public ChildType getRightChildEnumType() {
		return rightType;
	}

	@Override
	public void setRightChildEnumType(ChildType argEnum) {
		rightType = argEnum;
	}

	@Override
	public IRNode copy() {
		// TODO Auto-generated method stub
		return new IRBinOp(type, (IRExpr)left.copy(), (IRExpr)right.copy());
	}
}
