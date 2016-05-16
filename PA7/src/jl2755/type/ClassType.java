package jl2755.type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import jl2755.Main;
import jl2755.SemanticErrorObject;
import jl2755.ast.*;
import jl2755.visitor.Environment;

/** 
 * - className: the string name of the class
 * - fieldEnv: contains mapping of all the instance variables & their types
 * - methodEnv: contains mapping of all class methods & their signatures (limited to this class's view)
 * - orderedFields: ordered list of fields ONLY within this class
 * - orderedMethods: ordered list of methods ONLY within this class
 * 		Note that ordered fields and ordered methods are filled in LATER in one sweep after
 * 		all the class hierarchy has been determined.
 */
public class ClassType implements VType{

	String className;
	String superClassName;
	HashMap<String, VarType> fieldEnv;
	HashMap<String, FunType> methodEnv;		//contains methods of ONLY this class (no subclass or superclass)
	List<String> orderedFields;
	List<String> orderedMethods;

	public ClassType(InterfaceClassDecl intClassDecl) {
		className = intClassDecl.getClassName().getTheValue();
		Identifier sc = intClassDecl.getSuperclassName();
		if (sc != null) {
			superClassName = sc.getTheValue();
		} else {
			superClassName = null;
		}
		
		fieldEnv = new HashMap<String, VarType>();		// gets filled in if xi file declares this class
		methodEnv = new HashMap<String, FunType>();		
		orderedFields = new ArrayList<String>(); 		// gets filled in if xi file declares this class
		orderedMethods = new ArrayList<String>();
		
		List<InterfaceFunc> methods = intClassDecl.getMethods();
		for (InterfaceFunc method: methods) {
			methodEnv.put(method.getIdentifier().toString(), new FunType(method));
			orderedMethods.add(method.getIdentifier().toString());
		}

	}
	
	public ClassType(ClassDecl classDecl) {
		className = classDecl.getClassName().getTheValue();
		Identifier sc = classDecl.getSuperclassName();
		if (sc != null) {
			superClassName = sc.getTheValue();
		} else {
			superClassName = null;
		}

		fieldEnv = new HashMap<String, VarType>();		// gets filled in if xi file declares this class
		methodEnv = new HashMap<String, FunType>();		
		orderedFields = new ArrayList<String>(); 		// gets filled in if xi file declares this class
		orderedMethods = new ArrayList<String>();
		
		List<FieldDecl> fields = classDecl.getFields();
		List<FunctionDecl> methods = classDecl.getMethods();
		
		for (FieldDecl field: fields) {
			String fieldName;
			VarType fieldType;
			
			switch (field.getType()) {
			case VAR_DECL:
				VarDecl varDecl = field.getVarDecl();
				fieldName = varDecl.getIdentifier().toString();
				fieldType = new VarType(varDecl);
				fieldEnv.put(fieldName, fieldType);
				orderedFields.add(fieldName);
				break;
			case SHORT_TUPLE_DECL:
				ShortTupleDecl tupleDecl = field.getShortTupleDecl();
				for (Identifier id: tupleDecl.getAllIdentifiers()) {
					fieldName = id.getTheValue();
					fieldType = new VarType(tupleDecl.getType());
					fieldEnv.put(fieldName, fieldType);
					orderedFields.add(fieldName);
				}
				break;
			default:
				break;
			}
			
		}
		
		for (FunctionDecl method: methods) {
			String methodName = method.getIdentifier().getTheValue();
			methodEnv.put(methodName, new FunType(method));
			orderedMethods.add(methodName);
		}
		
		orderedFields = new ArrayList<String>();
		orderedMethods = new ArrayList<String>();
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
	
	public HashMap<String, VarType> getFieldEnv() {
		return fieldEnv;
	}

	public void setFieldEnv(HashMap<String, VarType> fieldEnv) {
		this.fieldEnv = fieldEnv;
	}
	
	public HashMap<String, FunType> getMethodEnv() {
		return methodEnv;
	}

	public void setMethodEnv(HashMap<String, FunType> methodEnv) {
		this.methodEnv = methodEnv;
	}
	
	public List<String> getOrderedFields() {
		return orderedFields;
	}

	/**
	 * Precondition: fieldEnv and orderedFields is currently empty because
	 * the class was declared in an interface. The function setOrderedFields
	 * takes in a class decl, and fills the fieldEnv and orderedFields
	 * using the instance variables found in the class decl in the xi file. 
	 */
	public void addFieldsToClassType(ClassDecl cd) {
		List<FieldDecl> fields = cd.getFields();
		
		for (FieldDecl field: fields) {
			String fieldName;
			VarType fieldType;
			
			switch (field.getType()) {
			case VAR_DECL:
				VarDecl varDecl = field.getVarDecl();
				fieldName = varDecl.getIdentifier().toString();
				fieldType = new VarType(varDecl);
				fieldEnv.put(fieldName, fieldType);
				orderedFields.add(fieldName);
				break;
			case SHORT_TUPLE_DECL:
				ShortTupleDecl tupleDecl = field.getShortTupleDecl();
				for (Identifier id: tupleDecl.getAllIdentifiers()) {
					fieldName = id.getTheValue();
					fieldType = new VarType(tupleDecl.getType());
					fieldEnv.put(fieldName, fieldType);
					orderedFields.add(fieldName);
				}
				break;
			default:
				break;
			}
		}
	}

	public List<String> getOrderedMethods() {
		return orderedMethods;
	}

	/**
	 * @param name: name of class field
	 * @return the VType of the field. 
	 * Returns null if the field does not exist in the class environment
	 */
	public VarType getFieldType(String name) {
		// TODO
		VarType fieldType = fieldEnv.get(name);
		return fieldType;
	}
	
	/**
	 * 
	 * @param name: name of the field
	 * @param type: type of the field that you're trying to add to class env
	 * @return: returns 1 if successfully added, otherwise 0
	 */
	public int addFieldType(String name, VType type) {
		if (type instanceof VarType) {
			fieldEnv.put(name,  (VarType) type);
			return 1;
		}
		else {
			return 0;
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
	 * @param methodName
	 * @return true if methodName is in methodEnv
	 */
	public boolean containsMethod(String methodName) {
		return methodEnv.containsKey(methodName);
	}
	
	/**
	 * @param fieldName
	 * @return true if fieldName is in fieldEnv
	 */
	public boolean containsField(String fieldName) {
		return fieldEnv.containsKey(fieldName);
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
		
		if (argSuperClassName == null) {
			if (superClassName != null) {
				return false;
			}
		}
		else {
			if (superClassName != null) {
				if (!argSuperClassName.equals(superClassName)) {
					return false;
				}
			}
			else {
				return false;
			}
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
	
	public void replaceAll(EmptyClassType ect, ClassType ct) {
		for (FunType funType : methodEnv.values()) {
			funType.replaceAll(ect, ct);
		}
	}

	/**
	 * Checks if the method signatures are the same in super classes
	 */
	public void checkSupers(Environment env) {
		List<ClassType> superClasses = getSuperClasses(env);
		for (ClassType superClass : superClasses) {
			HashMap<String, FunType> superMethodEnv = superClass.methodEnv;
			for (Entry<String, FunType> f1 : methodEnv.entrySet()) {
				for (Entry<String, FunType> f2 : superMethodEnv.entrySet()) {
					if (f1.getKey().equals(f2.getKey()) &&
							!f1.getValue().equals(f2.getValue())) {
						String e = "Function signature does not match super class";
						Main.handleSemanticError(new SemanticErrorObject(1,1,e));
					}
				}
			}
		}
	}
	
	private List<ClassType> getSuperClasses(Environment env) {
		List<ClassType> superclasses = new ArrayList<ClassType>();
		return getSuperClasses(superclasses, env);
	}
	
	/**
	 * 
	 * @param currentClass: name of the class to get super classes of
	 * @return list of superclasses in order of most immediate superclass to
	 * highest superclass
	 */
	private List<ClassType> getSuperClasses(List<ClassType> superclasses, Environment env) {
		assert(env.containsClass(className));
		if (superClassName != null) {
			ClassType superClassType = env.getClassType(superClassName);
			superclasses.add(superClassType);
			superClassType.getSuperClasses(superclasses, env);
		}
		return superclasses;
	}
	
	@Override
	public boolean canDot() {
		return true;
	}


	@Override
	public boolean singleReturn() {
		return true;
	}
		
	@Override
	public boolean equals(Object o) {
		if (o instanceof NullType) {
			return true;
		}
		if (!(o instanceof ClassType)) {
			return false;
		}
		ClassType ct = (ClassType) o;
		return ct.getClassName().equals(className);
	}
	
	/**
	 * precondition: class hierarchy has all been predetermined
	 * @return list of dispatch fields in the order of superclass to current class
	 */
	public List<String> getDispatchFields(Environment env) {
		List<String> allFields = new ArrayList<String>();
		List<ClassType> superclasses = getSuperClasses(env);
		for (int i = superclasses.size()-1; i >= 0; i--) {
			List<String> classFields = superclasses.get(i).getOrderedFields();
			allFields.addAll(classFields);
		}
		return allFields;
	}
	
	/**
	 * precondition: class hierarchy has all been predetermined
	 * @return list of dispatch methods in the order of superclass to current class
	 */
	public List<String> getDispatchMethods(Environment env) {
		List<String> allMethods = new ArrayList<String>();
		List<ClassType> superclasses = getSuperClasses(env);
		for (int i = superclasses.size()-1; i >= 0; i--) {
			List<String> classFields = superclasses.get(i).getOrderedMethods();
			allMethods.addAll(classFields);
		}
		return allMethods;
	}
	
}
