package jl2755.type;
import java.util.HashMap;
import java.util.List;
import jl2755.ast.*;

public class ClassType implements VType{

	String className;
	HashMap<String, VType> classEnv;

	public ClassType(String name, HashMap<String, VType> env) {
		className = name;
		classEnv = env;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public HashMap<String, VType> getClassEnv() {
		return classEnv;
	}

	public void setClassEnv(HashMap<String, VType> classEnv) {
		this.classEnv = classEnv;
	}
}
