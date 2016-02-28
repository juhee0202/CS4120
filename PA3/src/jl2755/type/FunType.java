package jl2755.type;

import java.util.List;

import jl2755.ast.FunctionDecl;
import jl2755.ast.VarDecl;

public class FunType implements VType {
	// TODO: revisit
	private List<VarType> params;
	private List<VarType> returns;
	
	public FunType(FunctionDecl fd) {
		List<VarDecl> l = fd.getParams();
		for (int i = 0; i < l.size(); i++) {
			VarDecl vd = l.get(i);
			VarType vt = new VarType(vd.getType());
			params.add(vt);
		}
		
		List<jl2755.ast.Type> ll = fd.getReturnTypes();
		for (int i = 0; i < ll.size(); i++) {
			VarType vt = new VarType(ll.get(i));
			returns.add(vt);
		}
	}
	
	public List<VarType> getParams() {
		return params;
	}
	public void setParams(List<VarType> params) {
		this.params = params;
	}
	public List<VarType> getReturns() {
		return returns;
	}
	public void setReturns(List<VarType> returns) {
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
