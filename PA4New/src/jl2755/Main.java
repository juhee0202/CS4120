package jl2755;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import edu.cornell.cs.cs4120.xic.ir.visit.LIRVisitor;
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
import jl2755.visitor.MIRVisitor;
import jl2755.visitor.TypeCheckVisitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@SuppressWarnings("deprecation")
public class Main {

	public static String destPath;		// example/destPath/
	public static String srcPath;		// example/srcPath/
	public static String currPath;
	public static String libPath;		// example/libPath/
	public static BufferedWriter bw;
	public static java_cup.runtime.Symbol error;
	/** true if optimizations are enabled
	 *  false if optimizations are disabled 
	 */
	public static boolean optimize = true;
	
	
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
			
		if (cmd.hasOption("-help") ||
				(cmd.getOptions().length == 0 && cmd.getArgs().length == 0)) {
			HelpFormatter format = new HelpFormatter();
			format.printHelp("xic", options);
			return;
		}
		
		// Default paths
		srcPath = "";
		destPath = "";
		libPath = "";
		currPath = System.getProperty("user.dir") + "/";
		
		
		// Check path and optimization options
		optionsCheck(cmd);
		
		// Get files to process
		String[] lexArgs = cmd.getOptionValues("l");
		String[] parseArgs = cmd.getOptionValues("p");
		String[] typecheckArgs = cmd.getOptionValues("t");
		String[] irgenArgs = cmd.getOptionValues("irgen");
		String[] irrunArgs = cmd.getOptionValues("irrun");
		String[] leftoverArgs = cmd.getArgs();
		
		if (lexArgs == null) {
			lexArgs = new String[0];
		} 
		if (parseArgs == null) {
			parseArgs = new String[0];
		} 
		if (typecheckArgs == null) {
			typecheckArgs = new String[0];
		} 
		if (irgenArgs == null) {
			irgenArgs = new String[0];
		} 
		if (irrunArgs == null) {
			irrunArgs = new String[0];
		} 
		if (leftoverArgs == null) {
			leftoverArgs = new String[0];
		} 
		
		List<String> lexFiles = new ArrayList<String>(Arrays.asList(lexArgs));; 
		List<String> parseFiles = new ArrayList<String>(Arrays.asList(parseArgs));
		List<String> typecheckFiles = new ArrayList<String>(Arrays.asList(typecheckArgs));
		List<String> irgenFiles = new ArrayList<String>(Arrays.asList(irgenArgs));
		List<String> irrunFiles = new ArrayList<String>(Arrays.asList(irrunArgs));
		List<String> leftoverFiles = new ArrayList<String>(Arrays.asList(leftoverArgs));
		List<String> files;
		
		/* LEX */
		if (cmd.hasOption("-lex")) {
			if (lexFiles.size() == 0) {
				files = concat(parseFiles, 
						   typecheckFiles, 
						   irgenFiles, 
						   irrunFiles, 
						   leftoverFiles);
			} else {
				files = lexFiles;
			}			
			if (files.size() == 0) {
				System.out.println("Missing argument for option: --lex");
				return;
			}

			for (String file: files) {
				try { 
					lex(srcPath + file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					System.out.println("Missing argument for option: --lex");
				}
			}
		}
		
		/* PARSE */
		if (cmd.hasOption("-parse")) {
			if (parseFiles.size() == 0) {
				files = concat(lexFiles, 
						   typecheckFiles, 
						   irgenFiles, 
						   irrunFiles, 
						   leftoverFiles);
			} else {
				files = parseFiles;
			}
			if (files.size() == 0) {
				System.out.println("Missing argument for option: --parse");
				return;
			}

			for (String file: files) {
				try { 
					parse(srcPath + file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					System.out.println("Missing argument for option: --lex");
				}
			}
		}

		/* TYPECHECK */
		if (cmd.hasOption("-typecheck")) {
			if (typecheckFiles.size() == 0) {
				files = concat(lexFiles, 
						   parseFiles, 
						   irgenFiles, 
						   irrunFiles, 
						   leftoverFiles);
			} else {
				files = typecheckFiles;
			}
			if (files.size() == 0) {
				System.out.println("Missing argument for option: --typecheck");
				return;
			}

			for (String file: files) {
				try { 
					typecheck(srcPath + file);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + file + " is not found.");
				} catch (Exception e) {
					System.out.println("Missing argument for option: --typecheck");
				}
			}
		}
		
		/* IR GENERATION */
		if (cmd.hasOption("-irgen")) {
			if (irgenFiles.size() == 0) {
				files = concat(lexFiles, 
						   parseFiles, 
						   typecheckFiles, 
						   irrunFiles, 
						   leftoverFiles);
			} else {
				files = irgenFiles;
			}
			if (files.size() == 0) {
				System.out.println("Missing argument for option: --irgen");
				return;
			}

			for (String file: files) {
				try { 
					irgen(file);
				} catch (FileNotFoundException e) {
					System.out.println(file + " is not found.");
				} catch (Exception e) {
					System.out.println("Missing argument for option: --irgen");
				}
			}
		}
		
		/* IR INTERPRET */
		if (cmd.hasOption("-irrun")) {
			if (irrunFiles.size() == 0) {
				files = concat(lexFiles, 
						   parseFiles, 
						   typecheckFiles, 
						   irgenFiles, 
						   leftoverFiles);
			} else {
				files = irrunFiles;
			}
			if (files.size() == 0) {
				System.out.println("Missing argument for option: --irrun");
				return;
			}

			for (String file: files) {
				try { 
					irrun(file);
				} catch (FileNotFoundException e) {
					System.out.println(file + " is not found.");
				} catch (Exception e) {
					System.out.println("Missing argument for option: --irrun");
				}
			}
		}
		
	}

	public static void lex(String filename) throws FileNotFoundException {
		Scanner lexer = new Scanner(new FileReader(filename));
		String content = "";
		
		System.out.println("[xic] Lexing");
		
		Symbol symbol = null;
		try {
			symbol = lexer.next_token();
		} catch (LexicalError error) {
			content += error.getMessage();
		} catch (IOException e) {
			// TODO: not too sure what kind of IOException would happen here (there are more in this function)
			e.printStackTrace();
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
//				content += error.getMessage();
				content += error.getLine() + ":" + error.getColumn() + " error:" + error.getDescription();
				break;
			} catch (IOException e) {
				System.out.println("Failed to write to output file");
			}
		}
		
		// write content to output file
		try {
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}
			
			String rmExtension = filename.substring(0,index);
			
			if (!destPath.equals("")) {
				int indexOfLastSlash = filename.lastIndexOf('/');
				if (indexOfLastSlash != -1) {
					rmExtension = filename.substring(indexOfLastSlash + 1);
				}
			}
			
			File file = new File(destPath + rmExtension + ".lexed");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			System.out.println("[xic] Lexing completed");
		} catch (IOException e) {	
			System.out.println("Failed to write to output file");
		}
	}
	
	public static void parse(String filename) throws FileNotFoundException {
		parser p = new parser(new Scanner(new FileReader(filename)));
		try {		
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}
			
			String rmExtension = filename.substring(0,index);
			
			if (!destPath.equals("")) {
				int indexOfLastSlash = filename.lastIndexOf('/');
				if (indexOfLastSlash != -1) {
					rmExtension = filename.substring(indexOfLastSlash + 1);
				}
			}
			
			File file = new File(destPath + rmExtension + ".parsed");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			System.out.println("[xic] Parsing");
			Symbol s = p.parse();
			Program result = (Program) s.value;
		
			new GlobalPrettyPrinter(rmExtension + ".parsed");
			result.prettyPrintNode();
			GlobalPrettyPrinter.getInstance().flush();		
			bw.close();
			System.out.println("[xic] Parsing completed");
		} catch(LexicalError error) {
			System.out.println(error.getMessage());
		} catch(SyntaxError error) {
			System.out.println(error.getMessage());
		} catch(IOException e) {
			System.out.println("Failed to write to output file");
		} catch(Exception e) {
			// TODO: not sure what kind of exceptions would happen here
			e.printStackTrace();
		}
	}
	
	public static void typecheck(String filename) throws FileNotFoundException {
		try {
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}
			
			
			
			String rmExtension = filename.substring(0,index);
			
			if (!destPath.equals("")) {
				int indexOfLastSlash = filename.lastIndexOf('/');
				if (indexOfLastSlash != -1) {
					rmExtension = filename.substring(indexOfLastSlash + 1);
				}
			}
			
			File file = new File(destPath + rmExtension + ".typed");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			System.out.println("[xic] Typechecking");
			
			/* typecheck */
			parser p = new parser(new Scanner(new FileReader(filename)));
			Symbol s = p.parse();
			Program result = (Program) s.value;
			TypeCheckVisitor visitor = new TypeCheckVisitor();
			result.accept(visitor);
			
			bw.write("Valid Xi Program");
			bw.close();
			System.out.println("[xic] Typechecking completed");
			
		} catch(LexicalError error) {
			System.out.println(error.getMessage());
		} catch(SyntaxError error) {
			System.out.println(error.getMessage());
		} catch(SemanticError error) {
			System.out.println(error.getMessage());
		} catch(IOException e) {
			System.out.println("Failed to write to output file " + filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static IRNode irgen(String filename) throws FileNotFoundException {
		try {
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}
			
			String rmExtension = filename.substring(0,index);
			
			if (!destPath.equals("")) {
				int indexOfLastSlash = filename.lastIndexOf('/');
				if (indexOfLastSlash != -1) {
					rmExtension = filename.substring(indexOfLastSlash + 1);
				}
			}
			
			File file = new File(destPath + rmExtension + ".ir");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			System.out.println("[xic] Generating intermediate code");
			
			parser p = new parser(new Scanner(new FileReader(filename)));
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
//			StringWriter sww = new StringWriter();
//	        try (PrintWriter pw = new PrintWriter(sww);
//		             SExpPrinter sp = new CodeWriterSExpPrinter(pw)) {
//				mir.program.printSExp(sp);
//		        }
//	        bw.write(sww.toString());
	       
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
//			StringWriter sw2 = new StringWriter();
//			try (PrintWriter pw2 = new PrintWriter(sw2);
//					SExpPrinter sp2 = new CodeWriterSExpPrinter(pw2)){
//				lir.program.printSExp(sp2);
//			}
//			FileWriter fw2 = new FileWriter("C:/Users/Jonathan/Desktop/Files/CS4120/vmstuff/shared/CS4120/PA4/IROutput.txt");
//			BufferedWriter bw2 = new BufferedWriter(fw2);
//			bw2.write(sw2.toString());
//			bw2.close();
			System.out.println("[xic] Generating intermediate code completed");
			return lir.program;
			
		} catch(LexicalError error) {
			System.out.println(error.getMessage());
		} catch(SyntaxError error) {
			System.out.println(error.getMessage());
		} catch(SemanticError error) {
			System.out.println(error.getMessage());
		} catch(IOException e) {
			System.out.println("Failed to write to output file " + filename);
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
		
		String errorMessage = error.left + ":" + error.right + " error:" + msg + error.value;
		try {
			bw.write(errorMessage);
			bw.close();	
		} catch (IOException e) {
			System.out.println("Failed to write to output file");
		}
				
		throw new SyntaxError(error.left, error.right, msg);
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
		Symbol s;
		try {
			parser p = new parser(new Scanner(new FileReader(absPath)));
			s = p.parse();
		} catch (FileNotFoundException e) {
			System.out.println("Failed to read input file " + absPath);
			return tempMap;
		} catch (Exception e) {
			e.printStackTrace();
			return tempMap;
		}

		// if the interface file is syntactically invalid
		if (s.value instanceof Program) {
			try {
				bw.write("1:1 error: Interface file is invalid");
				bw.close();
			} catch (IOException e) {
				System.out.println("Failed to write to output file");
			}
			throw new SyntaxError(1,1, "Interface file is invalid"); 
		}
		
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
		
		// lex
		Option lexOpt = new Option("l", "lex", false, 
				"Generate output from lexical analysis.");
		lexOpt.setArgs(Option.UNLIMITED_VALUES);
		lexOpt.setOptionalArg(true);
		options.addOption(lexOpt);
		
		// parse
		Option parseOpt = new Option("p", "parse", false, 
				"Generate output from syntactic analysis.");
		parseOpt.setArgs(Option.UNLIMITED_VALUES);
		parseOpt.setOptionalArg(true);
		options.addOption(parseOpt);
		
		// typecheck
		Option typecheckOpt = new Option("t", "typecheck", false, 
				"Generate output from semantic analysis. ");
		typecheckOpt.setArgs(Option.UNLIMITED_VALUES);
		typecheckOpt.setOptionalArg(true);
		options.addOption(typecheckOpt);
		
		// irgen
		Option irgenOpt = new Option("irgen", false, "Generate intermediate code.");
		irgenOpt.setArgs(Option.UNLIMITED_VALUES);
		irgenOpt.setOptionalArg(true);
		options.addOption(irgenOpt);
		
		// irrun
		Option irrunOpt = new Option("irrun", false, 
				"Generate and interpret intermediate code.");
		irrunOpt.setArgs(Option.UNLIMITED_VALUES);
		irrunOpt.setOptionalArg(true);
		options.addOption(irrunOpt);
		
		// path options
		Option srcOpt = new Option("sourcepath", true, 
				"Specify where to find input source files.");
		srcOpt.setArgs(1);
		options.addOption(srcOpt);
		
		Option libOpt = new Option("libpath", true, 
				"Specify where to find library interface files. " +
				"The default is the current directory in which xic is run. ");
		libOpt.setArgs(1);
		options.addOption(libOpt);
		
		Option destOpt = new Option ("D", true, 
				"Specify where to place generated diagnostic files.");
		destOpt.setArgs(1);
		options.addOption(destOpt);

		// optimization
		options.addOption("O", false, 
				"If specified, optimizations such as constant" +
				" folding will not be performed.");
		
		return options;
	}
	
	/**
	 * Checks all auxiliary the options and sets global variables
	 */
	private static void optionsCheck(CommandLine cmd) {
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
                destPath = destD;
            }
        }

        // optimization
        if (cmd.hasOption("O")) {
            optimize = false;
        }
	}
	
}