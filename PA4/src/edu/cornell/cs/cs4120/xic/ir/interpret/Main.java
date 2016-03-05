package edu.cornell.cs.cs4120.xic.ir.interpret;

import java.util.LinkedList;
import java.util.List;

import edu.cornell.cs.cs4120.xic.ir.IRBinOp;
import edu.cornell.cs.cs4120.xic.ir.IRBinOp.OpType;
import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRConst;
import edu.cornell.cs.cs4120.xic.ir.IRDecl;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import edu.cornell.cs.cs4120.xic.ir.IRMove;
import edu.cornell.cs.cs4120.xic.ir.IRSeq;
import edu.cornell.cs.cs4120.xic.ir.IRStmt;
import edu.cornell.cs.cs4120.xic.ir.IRTemp;

public class Main {

    public static void main(String[] args) {
        // Runs a simple arithmetic expression in the simulator
        // IR roughly corresponds to the following:
        //      a(i:int, j:int):int {
        //          return i + (2 * j);
        //      }

        String r0 = Configuration.ABSTRACT_REG_PREFIX + 0;
        String r1 = Configuration.ABSTRACT_REG_PREFIX + 1;

        List<IRStmt> stmts = new LinkedList<>();
        stmts.add(new IRDecl(r0));
        stmts.add(new IRDecl(r1));
        stmts.add(new IRMove(new IRTemp("i"), new IRTemp(r0)));
        stmts.add(new IRMove(new IRTemp("j"), new IRTemp(r1)));
        stmts.add(new IRMove(new IRTemp(Configuration.RV_NAME),
                             new IRConst(0)));
        stmts.add(new IRMove(new IRTemp(Configuration.RV_NAME),
                             new IRBinOp(OpType.ADD,
                                         new IRTemp("i"),
                                         new IRBinOp(OpType.MUL,
                                                     new IRConst(2),
                                                     new IRTemp("j")))));
        // TODO
//        stmts.add(new IRReturn());
        IRSeq seq = new IRSeq(stmts);
        IRFuncDecl f = new IRFuncDecl("a", seq, false);

        IRCompUnit compUnit = new IRCompUnit("test");
        compUnit.appendFunc(f);

        IRSimulator sim = new IRSimulator(compUnit);
        long result = sim.call("a", new long[] { 1, 9 });
        System.out.println("a(1,9) == " + result);
    }
}
