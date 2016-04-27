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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.cornell.cs.cs4120.util.CodeWriterSExpPrinter;
import edu.cornell.cs.cs4120.util.SExpPrinter;
import edu.cornell.cs.cs4120.xic.ir.IRCompUnit;
import edu.cornell.cs.cs4120.xic.ir.IRNode;
import edu.cornell.cs.cs4120.xic.ir.interpret.IRSimulator;
import java_cup.runtime.Symbol;
import jl2755.ast.Identifier;
import jl2755.ast.Interface;
import jl2755.ast.InterfaceFunc;
import jl2755.ast.Program;
import jl2755.exceptions.LexicalError;
import jl2755.exceptions.SemanticError;
import jl2755.exceptions.SyntaxError;
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
	public static boolean optimize = true;
	public static String target = "linux";

	public static final int NUM_OPTS = 6;
	public static final String[] OPTS = 
		{"cf", "reg", "mc", "uce", "cse", "temp"};

	/* All path strings are responsible for adding their own trailing forward-slash
	 * and deleting their own leading forward-slash
	 */
	public static void main(String[] args) {
		// Initialize command line
		Options options = optionsInit();
		CommandLineParser parser = new GnuParser();
		CommandLine cmd;
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
		//        System.out.println("currPath: " + currPath);

		// Check path and optimization options
		try {
			optionsCheck(cmd, options);
		} catch (Exception e1) {
			if (e1.getMessage() != null) {
				System.out.println(e1.getMessage());
			}
			return;
		}

		// Get files to process
//		String[] lexArgs = cmd.getOptionValues("l");
//		String[] parseArgs = cmd.getOptionValues("p");
//		String[] typecheckArgs = cmd.getOptionValues("t");
//		String[] irgenArgs = cmd.getOptionValues("irgen");
//		String[] irrunArgs = cmd.getOptionValues("irrun");
//		String[] leftoverArgs = cmd.getArgs();
//
//		if (lexArgs == null) {
//			lexArgs = new String[0];
//		} 
//		if (parseArgs == null) {
//			parseArgs = new String[0];
//		} 
//		if (typecheckArgs == null) {
//			typecheckArgs = new String[0];
//		} 
//		if (irgenArgs == null) {
//			irgenArgs = new String[0];
//		} 
//		if (irrunArgs == null) {
//			irrunArgs = new String[0];
//		} 
//		if (leftoverArgs == null) {
//			leftoverArgs = new String[0];
//		} 
//
//		List<String> lexFiles = new ArrayList<String>(Arrays.asList(lexArgs));; 
//		List<String> parseFiles = new ArrayList<String>(Arrays.asList(parseArgs));
//		List<String> typecheckFiles = new ArrayList<String>(Arrays.asList(typecheckArgs));
//		List<String> irgenFiles = new ArrayList<String>(Arrays.asList(irgenArgs));
//		List<String> irrunFiles = new ArrayList<String>(Arrays.asList(irrunArgs));
//		List<String> leftoverFiles = new ArrayList<String>(Arrays.asList(leftoverArgs));
//		List<String> files;
		
		String[] files = cmd.getArgs();

		/* LEX */
		if (cmd.hasOption("l")) {
//			if (lexFiles.size() == 0) {
//				files = concat(parseFiles, 
//						typecheckFiles, 
//						irgenFiles, 
//						irrunFiles, 
//						leftoverFiles);
//			} else {
//				files = lexFiles;
//			}			
//			if (files.length == 0) {
//				System.out.println("Missing argument for xic");
//				return;
//			}

			for (String file: files) {
				try { 
					lex(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/* PARSE */
		if (cmd.hasOption("p")) {
//			if (parseFiles.size() == 0) {
//				files = concat(lexFiles, 
//						typecheckFiles, 
//						irgenFiles, 
//						irrunFiles, 
//						leftoverFiles);
//			} else {
//				files = parseFiles;
//			}
//			if (files.length == 0) {
//				System.out.println("Missing argument for xic");
//				return;
//			}

			for (String file: files) {
				try { 
					parse(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/* TYPECHECK */
		if (cmd.hasOption("t")) {
//			if (typecheckFiles.size() == 0) {
//				files = concat(lexFiles, 
//						parseFiles, 
//						irgenFiles, 
//						irrunFiles, 
//						leftoverFiles);
//			} else {
//				files = typecheckFiles;
//			}
//			if (files.length == 0) {
//				System.out.println("Missing argument for xic");
//				return;
//			}

			for (String file: files) {
				try { 
					typecheck(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/* IR GENERATION */
		if (cmd.hasOption("irgen") || cmd.hasOption("optir")) {
//			if (irgenFiles.size() == 0) {
//				files = concat(lexFiles, 
//						parseFiles, 
//						typecheckFiles, 
//						irrunFiles, 
//						leftoverFiles);
//			} else {
//				files = irgenFiles;
//			}
//			if (files.length == 0) {
//				System.out.println("Missing argument xic");
//				return;
//			}

			for (String file: files) {
				try { 
					irgen(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/* IR INTERPRET */
		if (cmd.hasOption("irrun")) {
//			if (irrunFiles.size() == 0) {
//				files = concat(lexFiles, 
//						parseFiles, 
//						typecheckFiles, 
//						irgenFiles, 
//						leftoverFiles);
//			} else {
//				files = irrunFiles;
//			}
//			if (files.length == 0) {
//				System.out.println("Missing argument for xic");
//				return;
//			}

			for (String file: files) {
				try { 
					irrun(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/* ASSEMBLY */
		if (cmd.hasOption("target")) {
//			files = concat(lexFiles, 
//					parseFiles, 
//					typecheckFiles, 
//					irgenFiles, 
//					irrunFiles,
//					leftoverFiles);
//			if (files.length == 0) {
//				System.out.println("Missing argument for xic");
//				return;
//			}
			
			for (String file: files) {
				try { 
					assembly(file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					e.printStackTrace();
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
					rmExtension = filename.substring(indexOfLastSlash + 1);
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
				rmExtension = filename.substring(indexOfLastSlash + 1);
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
				rmExtension = filename.substring(indexOfLastSlash + 1);
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

			/* typecheck */
			parser p = new parser(new Scanner(new FileReader(srcPath + filename)));
			Symbol s = p.parse();

			Program result = (Program) s.value;
			TypeCheckVisitor visitor = new TypeCheckVisitor();
			result.accept(visitor);

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

	public static IRNode irgen(String filename) throws FileNotFoundException {
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
				rmExtension = filename.substring(indexOfLastSlash + 1);
			}
		}

		String outputFileName = destDPath + rmExtension + ".ir";
		System.out.println("srcPath+filename: " + srcPath + filename);
		System.out.println("destDPath + rmExtension: " + outputFileName);
		try {
			File file = new File(outputFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			System.out.println("[xic] Generating intermediate code");

			parser p = new parser(new Scanner(new FileReader(srcPath + filename)));
			Symbol s = p.parse();
			Program program = (Program) s.value;
			TypeCheckVisitor typeCheck = new TypeCheckVisitor();
			program.accept(typeCheck);

			/* Constant Folding */
			if (optimize) {
				ConstantFolderVisitor constantFold = new ConstantFolderVisitor();
				program.accept(constantFold);
			}

			/* Translate to MIR */
			MIRVisitor mir = new MIRVisitor();
			program.accept(mir);

			// Output MIR
			//            			StringWriter sww = new StringWriter();
			//            	        try (PrintWriter pw = new PrintWriter(sww);
			//            		             SExpPrinter sp = new CodeWriterSExpPrinter(pw)) {
			//            				mir.program.printSExp(sp);
			//            		        }
			//            	        bw.write(sww.toString());

			/* Lower to LIR */
			LIRVisitor lir = new LIRVisitor();
			mir.program.accept(lir);

			StringWriter sw = new StringWriter();
			try (PrintWriter pw = new PrintWriter(sw);
					SExpPrinter sp = new CodeWriterSExpPrinter(pw)) {
				lir.program.printSExp(sp);					
			}
			bw.write(sw.toString());
			bw.close();
			System.out.println("[xic] Generating intermediate code completed");
			return lir.program;

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
		IRNode program = irgen(filename);

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
				rmExtension = filename.substring(indexOfLastSlash + 1);
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

			parser p = new parser(new Scanner(new FileReader(srcPath + filename)));
			Symbol s = p.parse();
			Program program = (Program) s.value;
			TypeCheckVisitor typeCheck = new TypeCheckVisitor();
			program.accept(typeCheck);

			if (optimize) {
				ConstantFolderVisitor constantFold = new ConstantFolderVisitor();
				program.accept(constantFold);
			}

			MIRVisitor mir = new MIRVisitor();
			program.accept(mir);
			LIRVisitor lir = new LIRVisitor();
			mir.program.accept(lir);

			//            StringWriter sw = new StringWriter();
			//            try (PrintWriter pw = new PrintWriter(sw);
			//                    SExpPrinter sp = new CodeWriterSExpPrinter(pw)) {
			//                lir.program.printSExp(sp);
			//            }
			//            bw.write(sw.toString());
			////            bw.close();
			//            

			/* Generate Assembly Code */

			TilingVisitor tv = new TilingVisitor();
			String assembly = tv.parseTiles(lir.program);

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
			//            e.printStackTrace();
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

		for (int i = 0; i < NUM_OPTS; i++) {
			String opt = OPTS[i];
			options.addOption(null, "O" + opt, false, 
					"Enable optimization <" + opt + ">.");
			options.addOption(null, "O-no-" + opt, false, 
					"Disable optimization <" + opt + ">.");
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
	private static void optionsCheck(CommandLine cmd, Options opts) throws Exception {
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

		// optimization
		if (cmd.hasOption("O")) {
			optimize = false;
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
			String phase = cmd.getOptionValue("optir");
			if (!phase.equals("initial") && !phase.equals("final")) {
				throw new Exception("Invalid argument for option -optir: " + phase);
			}
		}

		// optcfg
		if (cmd.hasOption("optcfg")) {
			String phase = cmd.getOptionValue("optcfg");
			if (!phase.equals("initial") && !phase.equals("final")) {
				throw new Exception("Invalid argument for option -optcfg: " + phase);
			}
		}
	}



}