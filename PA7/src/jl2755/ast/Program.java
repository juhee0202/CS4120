package jl2755.ast;

import java.util.List;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import jl2755.GlobalPrettyPrinter;
import jl2755.visitor.ASTVisitor;

public class Program extends XiFile {
	private UseId useId;
	private ModuleList moduleList;
	/**
	 * 0 if there are no use statements,
	 * 1 if there are use statements.
	 */
	private int index;
	
	public Program(ModuleList ml) {
		moduleList = ml;
		index = 0;
	}
	
	public Program(UseId ui, ModuleList ml) {
		useId = ui;
		moduleList = ml;
		index = 1;
	}
	
	public void prettyPrintNode(){
		CodeWriterSExpPrinter tempPrinter = GlobalPrettyPrinter.getInstance();
		tempPrinter.startList();
		if (index == 0) {
			tempPrinter.startList();
			tempPrinter.endList();
			tempPrinter.startList();
			moduleList.prettyPrintNode();
			tempPrinter.endList();
		} else {
			tempPrinter.startList();
			useId.prettyPrintNode();
			tempPrinter.endList();
			tempPrinter.startList();
			moduleList.prettyPrintNode();
			tempPrinter.endList();
		}
		tempPrinter.endList();
	}
	
	public List<Decl> getAllDecls() {
		return moduleList.getAllDecls();
	}
	
	@Override
	public String toString(){
		return "";
	}

	public UseId getUseId() {
		return useId;
	}

	public void setUseId(UseId useId) {
		this.useId = useId;
	}

	public ModuleList getModuleList() {
		return moduleList;
	}

	public void setModuleList(ModuleList ml) {
		moduleList = ml;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void accept(ASTVisitor v){
		v.visit(this);
	}

}
