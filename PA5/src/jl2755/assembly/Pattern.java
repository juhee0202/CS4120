package jl2755.assembly;

import java.util.ArrayList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRCall;
import edu.cornell.cs.cs4120.xic.ir.IRExpr;
import edu.cornell.cs.cs4120.xic.ir.IRMem;
import edu.cornell.cs.cs4120.xic.ir.IRNode;

public class Pattern {

	private IRNode rootOfSubtree;

	public Pattern(List<String> inOrder, List<String> preOrder) {
		rootOfSubtree = makeTree(inOrder, preOrder);
	}

	/**
	 * Strings should be name of IRNode instance plus a number to make
	 * each node unique
	 * 
	 * @param inOrder is the in order traversal of such a tree
	 * @param preOrder is the pre order traversal of such a tree
	 * @return the root of the tree that the in order and pre order
	 * traversals describes
	 */
	private static IRNode makeTree(List<String> inOrder, List<String> preOrder) {
		if (preOrder.size() == 0) {
			return null;
		}
		String headOfSubtree = preOrder.get(0);
		preOrder.remove(0);
		
		int indexInInOrder = inOrder.indexOf(headOfSubtree);
		
		List<String> leftSubtree = inOrder.subList(0, indexInInOrder);
		
		List<String> rightSubtree = new ArrayList<String>();
		
		if (!(indexInInOrder == inOrder.size() - 1)) {
			rightSubtree = inOrder.subList(indexInInOrder + 1, inOrder.size() - 1);
		}
		
		IRNode rootOfLeft = makeTree(leftSubtree, preOrder);
		IRNode rootOfRight = makeTree(rightSubtree, preOrder);
		
		IRNode root = stringToNode(headOfSubtree);
		root.addLeft(rootOfLeft);
		root.addRight(rootOfRight);
		
		return root;
	}
	
	/**
	 * NAMING CONVENTION:
	 * IRMem = Mem;
	 * IRCall = Call;
	 * 
	 * 
	 * @param nodeName
	 * @return
	 */
	private static IRNode stringToNode(String nodeName) {
		if (nodeName.contains("Mem")) {
			return new IRMem(null);
		}
		if (nodeName.contains("Call")) {
			IRExpr[] hue = null;
			return new IRCall(null, hue);
		}
		// TODO: Everyone finish their parts
	}
}
