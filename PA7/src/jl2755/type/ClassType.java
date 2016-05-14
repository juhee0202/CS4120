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
	String superClassName;
	HashMap<String, VType> fieldEnv;
	HashMap<String, FunType> methodEnv; 

	public ClassType(InterfaceClassDecl intClassDecl) {
		className = intClassDecl.getClassName().getTheValue();
		superClassName = intClassDecl.getSuperclassName().getTheValue();
		fieldEnv = null;
		methodEnv = new HashMap<String, FunType>();
		List<InterfaceFunc> methods = intClassDecl.getMethods();
		for (InterfaceFunc method: methods) {
			methodEnv.put(method.getIdentifier().toString(), new FunType(method));
		}
	}
	
	public ClassType(ClassDecl classDecl) {
		className = classDecl.getClassName().getTheValue();
		superClassName = classDecl.getSuperclassName().getTheValue();
		fieldEnv = new HashMap<String, VType>();
		methodEnv = new HashMap<String, FunType>();
		
		List<GlobalDecl> fields = classDecl.getFields();
		List<FunctionDecl> methods = classDecl.getMethods();
		
		for (GlobalDecl field: fields) {
			String fieldName;
			VType fieldType;
			
			switch (field.getType()) {
			case VAR_DECL:
				VarDecl varDecl = field.getVarDecl();
				fieldName = varDecl.getIdentifier().toString();
				fieldType = new VarType(varDecl);
				fieldEnv.put(fieldName, fieldType);
				break;
			case VAR_INIT:
				// this should actually just be a duplicate add.. could get rid of it
				VarInit varInit = field.getVarInit();
				fieldName = varInit.getId().toString();
				fieldType = new VarType(varInit.getVarDecl());
				fieldEnv.put(fieldName, fieldType);
				break;
			case SHORT_TUPLE_DECL:
				ShortTupleDecl tupleDecl = field.getShortTupleDecl();
				for (Identifier id: tupleDecl.getAllIdentifiers()) {
					fieldName = id.getTheValue();
					fieldType = new VarType(tupleDecl.getType());
					fieldEnv.put(fieldName, fieldType);
				}
				break;
			case TUPLE_INIT:
				TupleInit tupleInit = field.getTupleInit();
				break;
			default:
				break;
			}
			
		}
		
		for (FunctionDecl method: methods) {
			methodEnv.put(method.getIdentifier().toString(), new FunType(method));
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSuperClassName() {
		return superClassName;
	}
	
	public void setSuperClassName(String superName) {
		superClassName = superName;
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
	 * @param name: name of class field
	 * @return the VType of the field. 
	 * Returns null if the field does not exist in the class environment
	 */
	public VType getFieldType(String name) {
		// TODO
		VType fieldType = fieldEnv.get(name);
		return fieldType;
	}
	
	/**
	 * 
	 * @param name: name of the field
	 * @param type: type of the field that you're trying to add to class env
	 * @return: returns 1 if successfully added, otherwise 0
	 */
	public int addFieldType(String name, VType type) {
		if (type instanceof VarType || type instanceof ClassType) {
			fieldEnv.put(name,  (FunType) type);
			return 0;
		}
		else {
			return 1;
		}
	}
	
	/**
	 * @param name: name of class field
	 * @return the VType of the field. 
	 * Returns null if the field does not exist in the class environment
	 */
	public FunType getMethodType(String name) {
		FunType methodType = methodEnv.get(name);
		return methodType;
	}
	
	/**
	 * 
	 * @param name: name of the method
	 * @param type: type of the method that you're trying to add to class env
	 * @return: returns 1 if successfully added, otherwise 0
	 */
	public int addMethodType(String name, FunType type) {
		methodEnv.put(name,  type);
		return 0;
	}
	
	/**
	 * 
	 * @param argClassType
	 * @return returns true if 
	 * 		1) class names are the same 
	 * 		2) same superclass
	 * 		3) all method signatures match exactly
	 * 		4) number of methods are equal
	 */
	public boolean compareClassSignatures(ClassType argClassType) {
		
		// if class names are different, return false
		if (!argClassType.getClassName().equals(className)) {
			return false;
		}
		
		// if they extend different superclasses, return false
		String argSuperClassName = argClassType.getSuperClassName();
		if (!argSuperClassName.equals(superClassName)) {
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
