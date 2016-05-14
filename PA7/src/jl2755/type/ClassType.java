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
	 * @param name: name of class member (including field & method)
	 * @return the VType of the member. 
	 * Returns null if the member does not exist in the class environment
	 */
	public VType getFieldType(String name) {
		// TODO
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
