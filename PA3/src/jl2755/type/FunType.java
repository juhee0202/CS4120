package jl2755.type;

import java.util.List;
import jl2755.ast.Type;
import jl2755.ast.FunctionCall;
import jl2755.ast.FunctionDecl;

/**
 *	Represents the function type
 *	fn params -> returns
 *
 *	0 parameter: params is a unit
 *	1 parameter: params is a VarType
 *	1< parameters: params is a TupleType
 *
 *	0 return type: returns is a unit
 *	1 return type: returns is a VarType
 *	1< return types: returns is a TupleType 
 */
public class FunType implements VType {
	private VType params;
	private VType returns;
	
	public FunType(FunctionDecl fd) {
		/* Set Params */
		List<Type> l = fd.getParamTypes();
		if (l.size() == 0) {
			params = new UnitType();
		}
		else if (l.size() == 1) {
			params = new VarType(l.get(0));
		}
		else {
			params = new TupleType();
			for (int i = 0; i < l.size(); i++) {
				VarType vt = new VarType(l.get(i));
				((TupleType) params).addToTypes(vt);
			}
		}
		
		/* Set Returns */
		List<Type> ll = fd.getReturnTypes();
		if (ll.size() == 0) {
			returns = new UnitType();
		}
		else if (ll.size() == 1) {
			returns = new VarType(l.get(0));
		}
		else {
			returns = new TupleType();
			for (int i = 0; i < ll.size(); i++) {
				VarType vt = new VarType(ll.get(i));
				((TupleType) returns).addToTypes(vt);
			}
		}
	}
	
	public VType getParams() {
		return params;
	}
	public void setParams(VType params) {
		this.params = params;
	}
	public VType getReturns() {
		return returns;
	}
	public void setReturns(VType returns) {
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
