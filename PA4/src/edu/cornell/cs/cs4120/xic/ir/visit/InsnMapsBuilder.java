package edu.cornell.cs.cs4120.xic.ir.visit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.cornell.cs.cs4120.xic.ir.IRNode;
import edu.cornell.cs.cs4120.xic.InternalCompilerError;

public class InsnMapsBuilder extends IRVisitor {
    private Map<String, Long> nameToIndex;
    private Map<Long, IRNode> indexToInsn;
    private Map<IRNode, Long> insnToIndex;
    private List<String> ctors;

    private long index;

    public InsnMapsBuilder() {
        nameToIndex = new HashMap<>();
        indexToInsn = new HashMap<>();
        insnToIndex = new HashMap<>();
        ctors = new LinkedList<>();
        index = 0;
    }

    public Map<String, Long> nameToIndex() {
        return nameToIndex;
    }

    public Map<Long, IRNode> indexToInsn() {
        return indexToInsn;
    }

    public List<String> ctors() {
        return ctors;
    }

    @Override
    protected IRVisitor enter(IRNode parent, IRNode n) {
        indexToInsn.put(index, n);
        if (insnToIndex.containsKey(n))
            throw new InternalCompilerError("Error - encountered "
                    + "duplicate node " + n.label()
                    + " in the IR tree -- go fix the generator.");
        insnToIndex.put(n, index);
        InsnMapsBuilder v = n.buildInsnMapsEnter(this);
        // TODO
//          IRNode node = n;
//          if (node instanceof IRDataSeq) {
//              IRDataSeq seq = (IRDataSeq) node;
//              int count = seq.values().size();
//              long ptr = malloc(count * Configuration.WORD_SIZE);
//              List<IRExpr> values = seq.values();
//              for (int i = 0; i < count; ++i) {
//                  if (values.get(i) instanceof IRConst)
//                      store(ptr + i * Configuration.WORD_SIZE,
//                            ((IRConst) values.get(i)).value());
//                  else if (values.get(i) instanceof IRName) {
//                      String name = ((IRName) values.get(i)).name();
//                      if (!nameToIndex.containsKey(name))
//                          throw new InternalCompilerError("Unknown name '" + name
//                                  + "'");
//                      store(ptr + i * Configuration.WORD_SIZE,
//                            nameToIndex.get(name));
//                  }
//              }
//              nameToIndex.put(seq.name(), ptr);
//          }
//          else if (node instanceof IRZData) {
//              IRZData seq = (IRZData) node;
//              int count = seq.size();
//              long ptr = malloc(count * Configuration.WORD_SIZE);
//              for (int i = 0; i < count; ++i)
//                  store(ptr + i * Configuration.WORD_SIZE, 0);
//              nameToIndex.put(seq.name(), ptr);
//          }
//          else if (node instanceof IRCtor) {
//              ctors.add(((IRCtor) node).name());
//          }
        index++;
        return v;
    }

    public void addNameToCurrentIndex(String name) {
        if (nameToIndex.containsKey(name))
            throw new InternalCompilerError("Error - encountered "
                    + "duplicate name " + name
                    + " in the IR tree -- go fix the generator.");
        nameToIndex.put(name, index);
    }
}
