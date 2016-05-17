package jl2755;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;

public class GlobalPrettyPrinter {
	private static CodeWriterSExpPrinter thePrinter;
	
	public GlobalPrettyPrinter(String argFilePath){
		try {
			thePrinter = new CodeWriterSExpPrinter(new FileOutputStream(argFilePath));
		} catch (FileNotFoundException e) {
			System.out.println("Messed up in GlobalPrettyPrinter constructor 1");
			e.printStackTrace();
		}
	}
	
	public static CodeWriterSExpPrinter getInstance(){
		return thePrinter;
	}
}
