package jl2755.visitor;

import java.util.HashMap;
import java.util.Map;

import jl2755.type.ClassType;
import jl2755.type.FunType;
import jl2755.type.VType;
import jl2755.type.VarType;

public class Environment {
	private Map<String, VarType> varMap;
	private Map<String, FunType> funMap;
	private Map<String, ClassType> classMap;
	
	public Environment() {
		varMap = new HashMap<String, VarType>();
		funMap = new HashMap<String, FunType>();
		classMap = new HashMap<String, ClassType>();
	}
	
	/**
	 * Puts (s,type) into the environment
	 * @param s
	 * @param type
	 */
	public void put(String s, VType type) {
		if (type instanceof VarType) {
			varMap.put(s,(VarType)type);
		} else if (type instanceof FunType) {
			funMap.put(s,(FunType)type);
		} else if (type instanceof ClassType) {
			classMap.put(s, (ClassType)type);
		}
	}
	
	/**
	 * @param var
	 * @return VarType of var if var is in env
	 */
	public VarType getVarType(String var) {
		return varMap.get(var);
	}
	
	/**
	 * @param fun
	 * @return FunType of fun if fun is in env
	 */
	public FunType getFunType(String fun) {
		return funMap.get(fun);
	}
	
	/**
	 * @param c
	 * @return ClassType of c(class) if c is in env
	 */
	public ClassType getClassType(String c) {
		return classMap.get(c);
	}
	
	/**
	 * Remove var from the env 
	 * @param var
	 * @return VarType of var
	 */
	public VarType removeVar(String var) {
		return varMap.remove(var);
	}
	
	/**
	 * Removes fun from the env
	 * @param fun
	 * @return FunType of fun
	 */
	public FunType removeFun(String fun) {
		return funMap.remove(fun);
	}
	
	/**
	 * Removes c(class) from the env
	 * @param c
	 * @return ClassType of c
	 */
	public ClassType removeClass(String c) {
		return classMap.remove(c);
	}
	
	/**
	 * @param var
	 * @return true if var is in the env
	 */
	public boolean containsVar(String var) {
		return varMap.containsKey(var);
	}
	
	/**
	 * @param fun
	 * @return true if fun is in the env
	 */
	public boolean containsFun(String fun) {
		return funMap.containsKey(fun);
	}
	
	/**
	 * @param c
	 * @return true if c(class) is in the env
	 */
	public boolean containsClass(String c) {
		return classMap.containsKey(c);
	}
	
}
