package jl2755;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRFuncDecl;
import edu.cornell.cs.cs4120.xic.ir.interpret.IRSimulator;
import java_cup.runtime.Symbol;
import jl2755.ast.Identifier;
import jl2755.ast.Interface;
import jl2755.ast.InterfaceFunc;
import jl2755.ast.Program;
import jl2755.controlflow.ControlFlowGraph;
import jl2755.exceptions.LexicalError;
import jl2755.exceptions.SemanticError;
import jl2755.exceptions.SyntaxError;
import jl2755.optimization.CopyPropagator;
import jl2755.optimization.DeadCodeEliminator;
import jl2755.optimization.CommonSubExpElimination;
import jl2755.optimization.Optimization;
import jl2755.optimization.UnreachableCodeEliminator;
import jl2755.type.FunType;
import jl2755.type.VType;
import jl2755.visitor.ConstantFolderVisitor;
import jl2755.visitor.LIRVisitor;
import jl2755.visitor.MIRVisitor;
import jl2755.visitor.TilingVisitor;
import jl2755.visitor.TypeCheckVisitor;

@SuppressWarnings("deprecation")
public class Main {

	public static String destDPath;		// example/destPath/
	public static String destAPath;     // example/destPath/
	public static String srcPath;		// example/srcPath/
	public static String currPath;
	public static String libPath;		// example/libPath/
	public static BufferedWriter bw;
	public static java_cup.runtime.Symbol error;
	/** true if optimizations are enabled
	 *  false if optimizations are disabled 
	 */
	public static String target = "linux";

	// Optimization fields
	public static final String[] OPTS = 
		{"cf", "reg", "mc", "uce", "cse", "copy", "dce"};
	public static boolean[] enabled = new boolean[OPTS.length];
	public static final int CF = 0;
	public static final int REG = 1;
	public static final int MC = 2;
	public static final int UCE = 3;
	public static final int CSE = 4;
	public static final int COPY = 5;
	public static final int DCE = 6;
	
	public static HashMap<String, Symbol> fileToSymbol;
	public static HashMap<String, Program> fileToAST;
	public static HashMap<String, IRCompUnit> fileToIR;
	
	public static Set<String> IRPhases;
	public static boolean initialWritten = false;
	public static boolean finalWritten = false;
	public static Set<String> CFGPhases;
	
	public static CommandLine cmd;

	/* All path strings are responsible for adding their own trailing forward-slash
	 * and deleting their own leading forward-slash
	 */
	public static void main(String[] args) {
		
		// Initialize globals
		fileToSymbol = new HashMap<String, Symbol>();
		fileToAST = new HashMap<String, Program>();
		fileToIR = new HashMap<String, IRCompUnit>();
		IRPhases = new HashSet<String>();
		CFGPhases = new HashSet<String>();
		
		// Initialize command line
		Options options = optionsInit();
		CommandLineParser parser = new GnuParser();
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException exp) {
			System.err.println(exp.getMessage());
			return;
		}

		// Default paths
		srcPath = "";
		destDPath = "";
		destAPath = "";
		libPath = "";
		currPath = System.getProperty("user.dir") + "/";

		// Check path and optimization options
		try {
			optionsCheck(options);
		} catch (Exception e1) {
			if (e1.getMessage() != null) {
				System.out.println(e1.getMessage());
			}
			return;
		}

		// Get files to process
		String[] files = cmd.getArgs();

		/* LEX */
		if (cmd.hasOption("l")) {
			for (String file: files) {
				try { 
					lex(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}

		/* PARSE */
		if (cmd.hasOption("p")) {
			for (String file: files) {
				try { 
					parse(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}

		/* TYPECHECK */
		if (cmd.hasOption("t")) {
			for (String file: files) {
				try { 
					typecheck(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}
		
		/* OPTCFG */
		if (cmd.hasOption("optcfg")) {			
			for (String file: files) {
				try { 
					optcfg(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}

		/* IR GENERATION */
		if (cmd.hasOption("irgen") || cmd.hasOption("optir")) {
			for (String file: files) {
				try { 
					irgen(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}

		/* IR INTERPRET */
		if (cmd.hasOption("irrun")) {
			for (String file: files) {
				try { 
					irrun(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}

		/* ASSEMBLY */
		if (cmd.hasOption("target")) {			
			for (String file: files) {
				try { 
					assembly(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}
		
		
	}

	public static void lex(String filename) throws FileNotFoundException {
		Scanner lexer = new Scanner(new FileReader(srcPath + filename));
		String content = "";

		System.out.println("[xic] Lexing");

		Symbol symbol = null;
		try {
			symbol = lexer.next_token();
		} catch (LexicalError error) {
			error.setFilename(filename);
			content += error.getMessage();
		} catch (IOException e) {
			System.out.println("\tThere was an error in reading file " + srcPath + filename);
		}

		while (symbol != null && symbol.sym != 0) {
			String tokentype;
			switch (symbol.sym) {
			case sym.IDENTIFIER:		tokentype = "id ";
			break;
			case sym.INTEGER_LITERAL: 	tokentype = "integer ";
			break;
			case sym.CHARACTER_LITERAL: tokentype = "character ";
			break;
			case sym.STRING_LITERAL: 	tokentype = "string ";
			break;
			default: 					tokentype = "";
			break;
			}

			String val = symbol.value == null ? lexer.yytext() : symbol.value.toString();
			content += symbol.left + ":" + symbol.right + " "+ tokentype + val + "\n";

			try {
				symbol = lexer.next_token();
			} catch (LexicalError error) {
				error.setFilename(srcPath + filename);
				content += error.getLine() + ":" + error.getColumn() + " error:" + error.getDescription();
				break;
			} catch (IOException e) {
				System.out.println("\tFailed to write to output file " + srcPath + filename);
			}
		}

		// write content to output file
		try {
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}

			String rmExtension = filename.substring(0,index);

			/* strip path in front of filename.xi
			 * ex: /Users/PA6/tests/test.xi -> test/xi */
			if (!destDPath.equals("")) {
				int indexOfLastSlash = filename.lastIndexOf('/');
				if (indexOfLastSlash != -1) {
					rmExtension = rmExtension.substring(indexOfLastSlash + 1);
				}
			}

			File file = new File(destDPath + rmExtension + ".lexed");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			System.out.println("[xic] Lexing completed");
		} catch (IOException e) {	
			System.out.println("Failed to write to output file " + srcPath + filename);
		}
	}

	public static void parse(String filename) throws FileNotFoundException {
		parser p = new parser(new Scanner(new FileReader(srcPath + filename)));

		int index = filename.lastIndexOf('.');
		if (index == -1) {
			index = filename.length();
		}

		String rmExtension = filename.substring(0,index);

		/* strip path in front of filename.xi
		 * ex: /Users/PA6/tests/test.xi -> test/xi */
		if (!destDPath.equals("")) {
			int indexOfLastSlash = filename.lastIndexOf('/');
			if (indexOfLastSlash != -1) {
				rmExtension = rmExtension.substring(indexOfLastSlash + 1);
			}
		}

		String outputFileName = destDPath + rmExtension + ".parsed";

		try {		
			File file = new File(outputFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			System.out.println("[xic] Parsing");
			Symbol s = p.parse();
			
			// Update global map
			fileToSymbol.put(filename, s);

			Program result = (Program) s.value;

			new GlobalPrettyPrinter(destDPath + rmExtension + ".parsed");
			result.prettyPrintNode();
			GlobalPrettyPrinter.getInstance().flush();		
			bw.close();
			System.out.println("[xic] Parsing completed");
		} catch(LexicalError error) {
			error.setFilename(srcPath + filename);
			System.out.println("\t" + error.getMessage());
			System.out.println("[xic] Parsing failed");
		} catch(SyntaxError error) {
			error.setFilename(srcPath + filename);
			System.out.println("\t" + error.getMessage());
			System.out.println("[xic] Parsing failed");
		} catch(IOException e) {
			System.out.println("\tFailed to write to output file " + outputFileName);
			System.out.println("[xic] Parsing failed");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void typecheck(String filename) throws FileNotFoundException {
		int index = filename.lastIndexOf('.');
		if (index == -1) {
			index = filename.length();
		}
		String rmExtension = filename.substring(0,index);

		/* strip path in front of filename.xi
		 * ex: /Users/PA6/tests/test.xi -> test/xi */
		if (!destDPath.equals("")) {
			int indexOfLastSlash = filename.lastIndexOf('/');
			if (indexOfLastSlash != -1) {
				rmExtension = rmExtension.substring(indexOfLastSlash + 1);
			}
		}

		String outputFileName = destDPath + rmExtension + ".typed";

		try {
			File file = new File(outputFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			System.out.println("[xic] Typechecking");

			Symbol s;
			if (fileToSymbol.containsKey(filename)) {
				s = fileToSymbol.get(filename);
			} else {
				parser p = new parser(new Scanner(new FileReader(srcPath + filename)));
				s = p.parse();
				
				// Update global map
				fileToSymbol.put(filename, s);
			}

			/* Typecheck */
			Program result = (Program) s.value;
			TypeCheckVisitor visitor = new TypeCheckVisitor();
			result.accept(visitor);
			
			/* Constant Folding */
			if (enabled[CF]) {
				ConstantFolderVisitor constantFold = new ConstantFolderVisitor();
				result.accept(constantFold);
			}
			
			// Update global map
			fileToAST.put(filename, result);

			bw.write("Valid Xi Program");
			bw.close();
			System.out.println("[xic] Typechecking completed");

		} catch(LexicalError error) {
			error.setFilename(srcPath + filename);
			System.out.println("\t" + error.getMessage());
			System.out.println("[xic] Typechecking failed");
		} catch(SyntaxError error) {
			error.setFilename(srcPath + filename);
			System.out.println("\t" + error.getMessage());
			System.out.println("[xic] Typechecking failed");
		} catch(SemanticError error) {
			error.setFilename(srcPath + filename);
			System.out.println("\t" + error.getMessage());
			System.out.println("[xic] Typechecking failed");
		} catch(IOException e) {
			System.out.println("\tFailed to write to output file " + outputFileName);
			System.out.println("[xic] Typechecking failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static IRCompUnit irgen(String filename) throws FileNotFoundException {
		int index = filename.lastIndexOf('.');
		if (index == -1) {
			index = filename.length();
		}

		String rmExtension = filename.substring(0,index);

		/* strip path in front of filename.xi
		 * ex: /Users/PA6/tests/test.xi -> test.xi */
		if (!destDPath.equals("")) {
			int indexOfLastSlash = filename.lastIndexOf('/');
			if (indexOfLastSlash != -1) {
				rmExtension = rmExtension.substring(indexOfLastSlash + 1);
			}
		}

		String outputFileName = destDPath + rmExtension + ".ir";
//		System.out.println("srcPath+filename: " + srcPath + filename);
//		System.out.println("destDPath + rmExtension: " + outputFileName);
		try {
			File file = new File(outputFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			System.out.println("[xic] Generating intermediate code");

			Program program;
			if (fileToAST.containsKey(filename)) {
				program = fileToAST.get(filename);
			} else {
				Symbol s;
				if (fileToSymbol.containsKey(filename)) {
					s = fileToSymbol.get(filename);
				} else {
					parser p = new parser(new Scanner(new FileReader(srcPath + filename)));
					s = p.parse();
					
					// Update global map
					fileToSymbol.put(filename, s);
				}
				program = (Program) s.value;
				TypeCheckVisitor typeCheck = new TypeCheckVisitor();
				program.accept(typeCheck);
				
				/* Constant Folding */
				if (enabled[CF]) {
					ConstantFolderVisitor constantFold = new ConstantFolderVisitor();
					program.accept(constantFold);
				}
				
				// Update global map
				fileToAST.put(filename, program);
			}

			/* Translate to MIR */
			MIRVisitor mir = new MIRVisitor();
			program.accept(mir);

			// Output MIR
//			StringWriter sww = new StringWriter();
//			try (PrintWriter pw = new PrintWriter(sww);
//					SExpPrinter sp = new CodeWriterSExpPrinter(pw)) {
//				mir.program.printSExp(sp);
//			}
//			bw.write(sww.toString());
//			bw.close();

			/* Lower to LIR */
			LIRVisitor lir = new LIRVisitor();
			mir.program.accept(lir);
			IRCompUnit result = lir.program;
			
			if (IRPhases.contains("initial") && !initialWritten) {
				System.out.println("[xic] Generating initial intermediate code");
				File IRFile = new File(rmExtension + "_initial.ir");
				if (!IRFile.exists()) {
					IRFile.createNewFile();
				}
				FileWriter IRfw = new FileWriter(IRFile.getAbsoluteFile());
				BufferedWriter IRbw = new BufferedWriter(IRfw);
				StringWriter IRsw = new StringWriter();
				try (PrintWriter IRpw = new PrintWriter(IRsw);
						SExpPrinter IRsp = new CodeWriterSExpPrinter(IRpw)) {
					result.printSExp(IRsp);					
				}
				IRbw.write(IRsw.toString());
				IRbw.close();
				System.out.println("[xic] Generating initial intermediate code completed");
				initialWritten = true;
			}
			
			/* Optimize */
			result = optimize(result);
			
			// Update global map
			fileToIR.put(filename, result);

			StringWriter sw = new StringWriter();
			try (PrintWriter pw = new PrintWriter(sw);
					SExpPrinter sp = new CodeWriterSExpPrinter(pw)) {
				result.printSExp(sp);					
			}
			bw.write(sw.toString());
			bw.close();
			
			if (IRPhases.contains("final") && !finalWritten) {
				System.out.println("[xic] Generating final intermediate code");
				File IRFile2 = new File(rmExtension + "_final.ir");
				if (!IRFile2.exists()) {
					IRFile2.createNewFile();
				}
				FileWriter IRfw2 = new FileWriter(IRFile2.getAbsoluteFile());
				BufferedWriter IRbw2 = new BufferedWriter(IRfw2);
				StringWriter IRsw2 = new StringWriter();
				try (PrintWriter IRpw2 = new PrintWriter(IRsw2);
						SExpPrinter IRsp2 = new CodeWriterSExpPrinter(IRpw2)) {
					result.printSExp(IRsp2);					
				}
				IRbw2.write(IRsw2.toString());
				IRbw2.close();
				System.out.println("[xic] Generating final intermediate code completed");
				finalWritten = true;
			}
			System.out.println("[xic] Generating intermediate code completed");
			return result;

		} catch(LexicalError error) {
			error.setFilename(srcPath + filename);
			System.out.println("[xic] Generating intermediate code failed");
			System.out.println("\t" + error.getMessage());
		} catch(SyntaxError error) {
			error.setFilename(srcPath + filename);
			System.out.println("[xic] Generating intermediate code failed");
			System.out.println("\t" + error.getMessage());
		} catch(SemanticError error) {
			error.setFilename(srcPath + filename);
			System.out.println("[xic] Generating intermediate code failed");
			System.out.println("\t" + error.getMessage());
		} catch(IOException e) {
			System.out.println("[xic] Generating intermediate code failed");
			System.out.println("Failed to write to output file " + outputFileName);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void irrun(String filename) throws FileNotFoundException {
		// Generate IR code
		IRCompUnit program;
		if (fileToIR.containsKey(filename)) {
			program = fileToIR.get(filename);
		} else {
			program = irgen(filename);
			
			// Update global map
			fileToIR.put(filename, program);
		}

		// Interpret IR code
		if (program == null) {
			return;
		}
		try {
			System.out.println("[xic] Interpreting intermediate code");
			IRSimulator sim = new IRSimulator((IRCompUnit) program);
			sim.call("_Imain_paai", 0);
			System.out.println("[xic] Interpreting intermediate code completed");
		} catch (Exception e) {
			System.out.println("[xic] Interpreting intermediate code failed");
			e.printStackTrace();
		}

	}

	public static void assembly(String filename) throws FileNotFoundException {
		int index = filename.lastIndexOf('.');
		if (index == -1) {
			index = filename.length();
		}
		String rmExtension = filename.substring(0,index);

		/* strip path in front of filename.xi
		 * ex: /Users/PA6/tests/test.xi -> test/xi */
		if (!destDPath.equals("")) {
			int indexOfLastSlash = filename.lastIndexOf('/');
			if (indexOfLastSlash != -1) {
				rmExtension = rmExtension.substring(indexOfLastSlash + 1);
			}
		}

		String outputFileName = destAPath + rmExtension + ".s";

		try {
			File file = new File(outputFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			System.out.println("[xic] Generating assembly code");

			IRCompUnit result;
			if (fileToIR.containsKey(filename)) {
				result = fileToIR.get(filename);
			} else {
				Program program;
				if (fileToAST.containsKey(filename)) {
					program = fileToAST.get(filename);
				} else {
					Symbol s;
					if (fileToSymbol.containsKey(filename)) {
						s = fileToSymbol.get(filename);
					} else {
						parser p = new parser(new Scanner(new FileReader(srcPath + filename)));
						s = p.parse();
						
						// Update global map
						fileToSymbol.put(filename, s);
					}
					program = (Program) s.value;
					TypeCheckVisitor typeCheck = new TypeCheckVisitor();
					program.accept(typeCheck);
					
					/* Constant Folding */
					if (enabled[CF]) {
						ConstantFolderVisitor constantFold = new ConstantFolderVisitor();
						program.accept(constantFold);
					}
					
					// Update global map
					fileToAST.put(filename, program);
				}
				MIRVisitor mir = new MIRVisitor();
				program.accept(mir);
				LIRVisitor lir = new LIRVisitor();
				mir.program.accept(lir);
				result = lir.program;
				
				/* Optimize */
//				result = optimize(result);
				
				// Update global map
				fileToIR.put(filename, result);
			}			  

			/* Generate Assembly Code */

			TilingVisitor tv = new TilingVisitor(enabled[REG], enabled[MC]);
			String assembly = tv.parseTiles(result);

			bw.write(assembly);
			bw.close();

			System.out.println("[xic] Generating assembly code completed");

		} catch(LexicalError error) {
			error.setFilename(filename);
			System.out.println("[xic] Generating assembly code failed");
			System.out.println("\t" + error.getMessage());
		} catch(SyntaxError error) {
			error.setFilename(filename);
			System.out.println("[xic] Generating assembly code failed");
			System.out.println("\t" + error.getMessage());
		} catch(SemanticError error) {
			error.setFilename(filename);
			System.out.println("[xic] Generating assembly code failed");
			System.out.println("\t" + error.getMessage());
		} catch(IOException e) {
			System.out.println("[xic] Generating assembly code failed");
			System.out.println("\tFailed to write to output file " + outputFileName);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("[xic] Generating assembly code failed");
			e.printStackTrace();
		}
	}
	
	public static void optcfg(String filename) throws FileNotFoundException {
		int index = filename.lastIndexOf('.');
		if (index == -1) {
			index = filename.length();
		}
		String rmExtension = filename.substring(0,index);

		try {
			System.out.println("[xic] Generating control flow graph");

			IRCompUnit result;
			Program program;
			if (fileToAST.containsKey(filename)) {
				program = fileToAST.get(filename);
			} else {
				Symbol s;
				if (fileToSymbol.containsKey(filename)) {
					s = fileToSymbol.get(filename);
				} else {
					parser p = new parser(new Scanner(new FileReader(srcPath + filename)));
					s = p.parse();
					
					// Update global map
					fileToSymbol.put(filename, s);
				}
				program = (Program) s.value;
				TypeCheckVisitor typeCheck = new TypeCheckVisitor();
				program.accept(typeCheck);
				
				/* Constant Folding */
				if (enabled[CF]) {
					ConstantFolderVisitor constantFold = new ConstantFolderVisitor();
					program.accept(constantFold);
				}
				
				// Update global map
				fileToAST.put(filename, program);
			}
			
			MIRVisitor mir = new MIRVisitor();
			program.accept(mir);
			LIRVisitor lir = new LIRVisitor();
			mir.program.accept(lir);
			result = lir.program;
			
			if (CFGPhases.contains("initial")) {
				for (IRFuncDecl fd : result.functions().values()) {
					ControlFlowGraph initialCFG = new ControlFlowGraph(fd);
					File file = new File(rmExtension + "_" + fd.getABIName() + "_" + "initial.dot");
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bwCFG = new BufferedWriter(fw);
					bwCFG.write(initialCFG.dotOutput());
					bwCFG.close();
				}
			}
			
			/* Optimize */
//			result = optimize(result);
			
			// Update global map
			fileToIR.put(filename, result);	
			
			if (CFGPhases.contains("final")) {
				for (IRFuncDecl fd : result.functions().values()) {
					ControlFlowGraph finalCFG = new ControlFlowGraph(fd);
					File file = new File(rmExtension + "_" + fd.getABIName() + "_" + "final.dot");
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bwCFG = new BufferedWriter(fw);
					bwCFG.write(finalCFG.dotOutput());
					bwCFG.close();
				}
			}

			System.out.println("[xic] Generating control flow graph completed");

		} catch(LexicalError error) {
			error.setFilename(filename);
			System.out.println("[xic] Generating control flow graph failed");
			System.out.println("\t" + error.getMessage());
		} catch(SyntaxError error) {
			error.setFilename(filename);
			System.out.println("[xic] Generating control flow graph failed");
			System.out.println("\t" + error.getMessage());
		} catch(SemanticError error) {
			error.setFilename(filename);
			System.out.println("[xic] Generating control flow graph failed");
			System.out.println("\t" + error.getMessage());
		} catch(IOException e) {
			System.out.println("[xic] Generating control flow graph failed");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("[xic] Generating control flow graph failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * Handles all optimizations on the IR level.
	 * 
	 * @param node	The IRNode to optimize (should be IRCompUnit)
	 * @return	the optimized IRNode (should be IRCompUnit)
	 */
	// TODO: SSA optimizations here
	// UCE, COPY
	public static IRCompUnit optimize(IRCompUnit node) {
		boolean changed = true;
		List<Optimization> opts = new ArrayList<Optimization>();
		if (enabled[UCE]) {
			UnreachableCodeEliminator uce = new UnreachableCodeEliminator();
			opts.add(uce);
		}
		if (enabled[COPY]) {
			CopyPropagator copy = new CopyPropagator();
			opts.add(copy);
		}
		if (enabled[DCE]) {
			DeadCodeEliminator dce = new DeadCodeEliminator();
			opts.add(dce);
		}
		
		if (enabled[CSE]) {
			// TODO: CSE
			// call constructor
			// add to opts
			CommonSubExpElimination cse = new CommonSubExpElimination();
			opts.add(cse);
		}
		
		Map<String, IRFuncDecl> nameToFD = node.functions();
		for (IRFuncDecl fd : nameToFD.values()) {
			ControlFlowGraph cfg = new ControlFlowGraph(fd);
			while (changed) {
				changed = false;
				for (Optimization o : opts) {
					changed |= o.run(cfg);
				}
			}
			IRFuncDecl newFD = cfg.flattenIntoIR();
			nameToFD.put(fd.getABIName(), newFD);
		}
		
		return node;
	}


	public static void setToken(java_cup.runtime.Symbol info) {
		error = info;
	}

	/**
	 * Write the error message to the output file and throws SyntaxError,
	 * which is caught inside its parent function (parse or typecheck)
	 * 
	 * flow: parser -> handleSyntaxError -> parse/typecheck
	 * 
	 * @param msg
	 */
	public static void handleSyntaxError(String msg) {
		if (error.value == null) {
			error.value = "EOF";
		}
		msg += error.value;

		String errorMessage = error.left + ":" + error.right + " error:" + msg;

		try {
			bw.write(errorMessage);
			bw.close();	
		} catch (IOException e) {
			System.out.println("Failed to write to output file");
		}

		throw new SyntaxError(error.left, error.right, msg, error.value.toString());
	}

	/**
	 * Write the error message to the output file and throws SemanticError,
	 * which is caught in typecheck method.
	 * 
	 * @param seo
	 */
	public static void handleSemanticError(SemanticErrorObject seo) {
		try {
			bw.write(seo.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println("Failed to write to output file");
		}		
		throw new SemanticError(seo.getLineNumber(), 
				seo.getColNumber(), 
				seo.getDescription());
	}

	/**
	 * Parses the interface files used by the program we are typechecking
	 * 
	 * @param interfaceName	the name of the interface file to parse
	 * @return				a hashmap representing the function environment
	 */
	public static Map<String, VType> checkInterface(String interfaceName){
		String absPath = libPath + interfaceName + ".ixi";
		Map<String, VType> tempMap = new HashMap<String, VType>();
		try {
			ixiParser p = new ixiParser(new Scanner(new FileReader(absPath)));
			Symbol s = p.parse();

			Interface result = (Interface) s.value;
			List<InterfaceFunc> tempFuncs = result.getInterfaceFuncs();
			for (int i = 0; i < tempFuncs.size(); i++){
				if (tempMap.containsKey(tempFuncs.get(i).getIdentifier().toString())){
					Identifier id = tempFuncs.get(i).getIdentifier();
					String e = "Duplicate function declaration found";
					SemanticErrorObject seo = new SemanticErrorObject(
							id.getLineNumber(),id.getColumnNumber(),e);
					Main.handleSemanticError(seo);
				}
				tempMap.put(tempFuncs.get(i).getIdentifier().toString(),
						new FunType(tempFuncs.get(i)));
			}  
		} catch(LexicalError error) {
			error.setFilename(absPath);
			throw error;
		} catch(SyntaxError error) {
			error.setFilename(absPath);
			throw error;
		} catch (FileNotFoundException e) {
			System.out.println("\tFailed to read input file " + absPath);
		} catch (Exception e) {
			System.out.println("\t" + absPath);
			e.printStackTrace();
		}
		return tempMap;
	}

	/**
	 * Concatenates strings a and b together and returns the result
	 * 
	 * @param files	the array of file ArrayLists to be concatenated
	 * @param b	the second string to be concatenated
	 * @return	the result of concatenation
	 */
	@SafeVarargs
	public static List<String> concat(List<String>... files) {
		List<String> all = new ArrayList<String>();
		for (List<String> file: files) {
			all.addAll(file);
		}
		return all;
	}

	/**
	 * Initializes all the command-line options and args
	 * 
	 * @return	the Options object to be used in command line parsing
	 */
	private static Options optionsInit() {
		Options options = new Options();

		// help
		options.addOption("h", "help", false, "Print a synopsis of options.");

		// report opts
		options.addOption("r","report-opts", false, "Output a list of optimizations"
				+ " supported by this compiler.");

		// lex
		options.addOption("l", "lex", false, "Generate output from lexical analysis.");

		// parse
		options.addOption("p", "parse", false, "Generate output from syntactic analysis.");

		// typecheck
		options.addOption("t", "typecheck", false, "Generate output from semantic analysis. ");

		// irgen
		options.addOption(null, "irgen", false, "Generate intermediate code.");

		// irrun
		options.addOption(null, "irrun", false, "Generate and interpret intermediate code.");

		// optir
		Option optirOpt = new Option(null, "optir", true, 
				"Report the intermediate code at the specified phase of optimization");
		optirOpt.setArgs(1);
		optirOpt.setArgName("phase");
		options.addOption(optirOpt);

		// optcfg
		Option optcfgOpt = new Option(null, "optcfg", true, 
				"Report the control-flow graph at the specified phase of optimization");
		optirOpt.setArgs(1);
		optirOpt.setArgName("phase");
		options.addOption(optcfgOpt);

		// path options
		Option srcOpt = new Option("sourcepath", true, 
				"Specify where to find input source files.");
		srcOpt.setArgs(1);
		srcOpt.setArgName("path");
		options.addOption(srcOpt);

		Option libOpt = new Option("libpath", true, 
				"Specify where to find library interface files. " +
				"The default is the current directory in which xic is run. ");
		libOpt.setArgs(1);
		libOpt.setArgName("path");
		options.addOption(libOpt);

		Option destDOpt = new Option("D", true, 
				"Specify where to place generated diagnostic files.");
		destDOpt.setArgs(1);
		destDOpt.setArgName("path");
		options.addOption(destDOpt);

		Option destAOpt = new Option("d", true, 
				"Specify where to place generated assembly output files.");
		destAOpt.setArgs(1);
		destAOpt.setArgName("path");
		options.addOption(destAOpt);

		// disable all optimizations
		options.addOption("O", false, "Disable all optimizations");

		// enable optimizations
		Option test = new Option("O", true, "blah");
		test.setArgs(1);
		test.setArgName("opt");

		for (int i = 0; i < OPTS.length; i++) {
			String opt = OPTS[i];
			OptionGroup og = new OptionGroup();
			Option oE = new Option(null, "O" + opt, false, 
					"Enable only optimization <" + opt + ">.");
			og.addOption(oE);
			Option oD = new Option(null, "O-no-" + opt, false, 
					"Disable only optimization <" + opt + ">.");
			og.addOption(oD);
			options.addOptionGroup(og);
		}

		// target OS
		Option targetOSOpt = new Option("target", true, 
				"Specify the operating system for which to generate code. "
						+ "The default is linux.");
		targetOSOpt.setArgs(1);
		targetOSOpt.setOptionalArg(true);
		targetOSOpt.setArgName("OS");
		options.addOption(targetOSOpt);

		return options;
	}

	/**
	 * Checks all auxiliary options and sets global variables
	 * @throws Exception 
	 */
	private static void optionsCheck(Options opts) throws Exception {
		// print
		boolean print = false;
		String[] leftoverArgs = cmd.getArgs();
		if (cmd.hasOption("h")) {
			print = true;
		}
		if (cmd.hasOption("l") && leftoverArgs.length == 0) {
			print = true;
		}
		if (cmd.hasOption("p") && leftoverArgs.length == 0) {
			print = true;
		}
		if (cmd.hasOption("t") && leftoverArgs.length == 0) {
			print = true;
		}
		if (cmd.hasOption("irgen") && leftoverArgs.length == 0) {
			print = true;
		}
		if (cmd.hasOption("irrun") && leftoverArgs.length == 0) {
			print = true;
		}
		if (cmd.hasOption("target") && leftoverArgs.length == 0) {
			print = true;
		}
		if (cmd.hasOption("optir") && leftoverArgs.length == 0) {
			print = true;
		}
		if (cmd.hasOption("optcfg") && leftoverArgs.length == 0) {
			print = true;
		}
		if (print) {
			HelpFormatter format = new HelpFormatter();
			format.setOptionComparator(null);
			format.setWidth(80);
			format.printHelp("xic [options] <source files>", opts);
			throw new Exception();
		}

		// report ops
		if (cmd.hasOption("r")) {
			for (int i = 0; i < OPTS.length; i++) {
				System.out.println(OPTS[i]);
			}
			throw new Exception();
		}

		// source path
		if (cmd.hasOption("sourcepath")) {
			String src = cmd.getOptionValue("sourcepath");
			if (src != null && src.length() > 0) {
				if (src.charAt(src.length()-1) != '/') {
					src += "/";
				}
				srcPath = src;
			}
		}

		// library path
		if (cmd.hasOption("libpath")) {
			String lib = cmd.getOptionValue("libpath");
			if (lib != null && lib.length() > 0) {
				if (lib.charAt(lib.length()-1) != '/') {
					lib += "/";
				}
				libPath = lib;
			}
		}

		// destination diagnostic path
		if (cmd.hasOption("D")) {
			String destD = cmd.getOptionValue("D");
			if (destD != null && destD.length() > 0) {
				if (destD.charAt(destD.length()-1) != '/') {
					destD += "/";
				}
				destDPath = destD;
			}
		}

		// destination assembly path
		if (cmd.hasOption("d")) {
			String destA = cmd.getOptionValue("d");
			if (destA != null && destA.length() > 0) {
				if (destA.charAt(destA.length()-1) != '/') {
					destA += "/";
				}
				destAPath = destA;
			}
		}

		// target OS
		if (cmd.hasOption("target")) {
			String OS = cmd.getOptionValue("target");
			if (OS != null) {
				target = OS;
			}
			if (!target.equals("linux") && !target.equals("windows") 
					&& !target.equals("macos")) {
				throw new Exception("Invalid argument for option -target: " + OS);
			}
		}

		// optir
		if (cmd.hasOption("optir")) {
			IRPhases.addAll(Arrays.asList(cmd.getOptionValues("optir")));
			for (String phase : IRPhases) {
				if (!phase.equals("initial") && !phase.equals("final")) {
					throw new Exception("Invalid argument for option -optir: " + phase);
				}
			}
		}

		// optcfg
		if (cmd.hasOption("optcfg")) {
			CFGPhases.addAll(Arrays.asList(cmd.getOptionValues("optcfg")));
			for (String phase : CFGPhases) {
				if (!phase.equals("initial") && !phase.equals("final")) {
					throw new Exception("Invalid argument for option -optir: " + phase);
				}
			}
		}
		
		// optimizations
		boolean enables = false;
		boolean initialized = false;
		if (cmd.hasOption("O")) {
			Arrays.fill(enabled, false);
			enables = true;
			initialized = true;
		} else {
			Arrays.fill(enabled, true);
		}
		for (int i = 0; i < OPTS.length; i++) {
			String opt = OPTS[i];
			if (cmd.hasOption("O" + opt)) {
				enabled[i] = true;
				if (initialized && !enables) {
					throw new Exception("Cannot simultaneously enable "
							+ "and disable optimizations");
				}
				enables = true;
			} else if (cmd.hasOption("O-no-" + opt)) {
				enabled[i] = false;
				if (initialized && enables) {
					throw new Exception();
				}
				enables = false;
			}
		}
	}
	
}