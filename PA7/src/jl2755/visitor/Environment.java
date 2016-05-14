package jl2755.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	 * Puts (s,type) into the varMap/funMap
	 * @param s
	 * @param type
	 */
	public void put(String s, VType type) {
		if (type instanceof FunType) {
			funMap.put(s,(FunType)type);
		} else if (type instanceof VarType) {
			varMap.put(s,(VarType) type);
		}
	}
	
	/**
	 * Puts (className,classType) into the classMap
	 * @param className
	 * @param classType
	 */
	public void putClass(String className, ClassType classType) {
		classMap.put(className, classType);
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
	public VType removeVar(String var) {
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
	
	/**
	 * Merge env into this
	 * @param env
	 */
	public void putAll(Environment env) {
		varMap.putAll(env.varMap);
		funMap.putAll(env.funMap);
		classMap.putAll(env.classMap);
	}
	
	public Set<ClassType> getClassTypes() {
		return (Set<ClassType>) classMap.values();
	}
	
	public Set<FunType> getFunTypes() {
		return (Set<FunType>) funMap.values();
	}
}
