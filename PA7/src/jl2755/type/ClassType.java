package jl2755.type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import jl2755.Main;
import jl2755.SemanticErrorObject;
import jl2755.ast.*;

/** 
 * className: the string name of the class
 * fieldEnv: contains mapping of all the instance variables & their types
 * methodEnv: contains mapping of all class methods & their signatures
 */
public class ClassType implements VType{

	String className;
	HashMap<String, VType> fieldEnv;
	HashMap<String, FunType> methodEnv; 

	public ClassType(String name, HashMap<String, VType> fe, HashMap<String, FunType> me) {
		className = name;
		fieldEnv = fe;
		methodEnv = me;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public HashMap<String, VType> getFieldEnv() {
		return fieldEnv;
	}

	public void setFieldEnv(HashMap<String, VType> fieldEnv) {
		this.fieldEnv = fieldEnv;
	}
	
	public HashMap<String, FunType> getMethodEnv() {
		return methodEnv;
	}

	public void setMethodEnv(HashMap<String, FunType> methodEnv) {
		this.methodEnv = methodEnv;
	}
	
	/**
	 * @param name: name of class member (including field & method)
	 * @return the VType of the member. 
	 * Returns null if the member does not exist in the class environment
	 */
	public VType getType(String name) {
		VType fieldType = fieldEnv.get(name);
		if (fieldType != null) {
			return fieldType;
		}
		
		VType methodType = methodEnv.get(name);
		return methodType;
	}
	
	/**
	 * 
	 * @param name: name of the member
	 * @param type: type of the member that you're trying to add to class env
	 * @return: returns 1 if successfully added, otherwise 0
	 */
	public int addType(String name, VType type) {
		// add method
		if (type instanceof FunType) {
			methodEnv.put(name, (FunType) type);
		} 
		// add class field
		else if (type instanceof VarType || type instanceof ClassType) {
			fieldEnv.put(name, type);
		}
		else {
			// should raise error wherever this function was
			// called because incorrect type to add to class env
			return 0;
		}
		return 1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ClassType)) {
			return false;
		}
		
		ClassType argClassType = (ClassType) o;
		if (!argClassType.getClassName().equals(className)) {
			return false;
		}
		
		// check that all method match EXACTLY
		HashMap<String, FunType> argMethodEnv = argClassType.getMethodEnv();
		Iterator<String> it = argMethodEnv.keySet().iterator();
		while (it.hasNext()) {
			String memberName = it.next();
			FunType argFuncType = argMethodEnv.get(memberName);
			FunType thisFuncType = methodEnv.get(memberName);
			
			if (thisFuncType == null) {
				return false;
			}
			// if names match, check that method signatures match
			if (!thisFuncType.equals(argFuncType)) {
				return false;
			}
		}
		
		// if size of the hash maps are different, return false!
		if (methodEnv.size() != argMethodEnv.size()) {
			return false;
		}
		
		return true;
	
	}

}
