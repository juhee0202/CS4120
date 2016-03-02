package jl2755.type;

import java.util.List;
import jl2755.ast.Type;
import jl2755.ast.FunctionDecl;

public class FunType implements VType {
	// TODO: revisit
	private TupleType params;
	private TupleType returns;
	
	public FunType(FunctionDecl fd) {
		List<Type> l = fd.getParams();
		for (int i = 0; i < l.size(); i++) {
			VarType vt = new VarType(l.get(i));
			params.addToVarTypes(vt);
		}
		
		List<jl2755.ast.Type> ll = fd.getReturnTypes();
		for (int i = 0; i < ll.size(); i++) {
			VarType vt = new VarType(ll.get(i));
			returns.addToVarTypes(vt);
		}
	}
	
	public TupleType getParams() {
		return params;
	}
	public void setParams(TupleType params) {
		this.params = params;
	}
	public TupleType getReturns() {
		return returns;
	}
	public void setReturns(TupleType returns) {
		this.returns = returns;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FunType)) {
			return false;
		}
		FunType vt = (FunType)o;
		return (this.getParams()).equals(vt.getParams()) 
				&& (this.getReturns()).equals(vt.getReturns());
	}
}
