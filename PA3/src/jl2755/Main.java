package jl2755;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java_cup.runtime.Symbol;
import jl2755.ast.Interface;
import jl2755.ast.InterfaceFunc;
import jl2755.ast.Program;
import jl2755.type.FunType;
import jl2755.type.VType;
import jl2755.visitor.TypeCheckVisitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {

	public static String destPath;		// example/destPath/
	public static String srcPath;		// example/srcPath/
	public static String currPath;
	public static String libPath;		// example/libPath/
	public static BufferedWriter bw;
	public static java_cup.runtime.Symbol error;
	
	public static void main(String[] args) {
		
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
		
		CommandLineParser parser = new GnuParser();
		CommandLine cmd;
		srcPath = "";
		destPath = "";
		libPath = "";
		currPath = System.getProperty("user.dir") + "/";
		
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException exp) {
			System.err.println(exp.getMessage());
			return;
		}
				
		if ((cmd.getOptions().length == 0 && cmd.getArgs().length == 0) ||
			 cmd.hasOption("-help")) {
			
			System.out.println("Usage: xic [options] <source files>\n" +
					"where possible options include");
			System.out.println("  --help: Print a synopsis of options.");
			System.out.println("  --lex: Generate output from lexical analysis.");
			System.out.println("  --parse: Generate output from syntactic analysis.");
			System.out.println("  -sourcepath: " +
					"Specify where to find input source files.");
			System.out.println("  -libpath: " +
					"Specify where to find " +
					"library interface files. The default " +
					"is the current directory in which xic is run. ");
			System.out.println("  -D: " +
					"Specify where to place generated diagnostic files.");
			return;
		}
		
		if (cmd.hasOption("sourcepath")) {
			String src = cmd.getOptionValue("sourcepath");
			if (src != null) {
				if (src.charAt(0) == '/') {
					src = src.substring(1, src.length());
				}
				if (src.charAt(src.length()-1) != '/') {
					src += "/";
				}
				srcPath = src;
			}
		}

		if (cmd.hasOption("libpath")) {
			String lib = cmd.getOptionValue("libpath");
			if (lib != null) {
				if (lib.charAt(0) == '/') {
					lib = lib.substring(1, lib.length());
				}
				if (lib.charAt(lib.length()-1) != '/') {
					lib += "/";
				}
				libPath = lib;
			}
		}
		
		if (cmd.hasOption("D")) {
			String dest = cmd.getOptionValue("D");
			if (dest != null) {
				if (dest.charAt(0) == '/') {
					dest = dest.substring(1, dest.length());
				}
				if (dest.charAt(dest.length()-1) != '/') {
					dest += "/";
				}
				destPath = dest;
			}
		}
		String[] lexFiles = cmd.getOptionValues("l");;
		String[] parseFiles = cmd.getOptionValues("p");
		String[] typecheckFiles = cmd.getOptionValues("t");
		String[] leftoverFiles = cmd.getArgs();

		if (cmd.hasOption("-lex")) {
			String[] files = null;
			if (lexFiles == null) {
				files = concat(parseFiles, typecheckFiles);
				files = concat(files, leftoverFiles);
			}
			else {
				files = lexFiles;
			}
						
			if (files == null || files.length == 0) {
				System.out.println("Missing argument for option: --lex");
				return;
			}

			for (int i = 0; i < files.length; i++) {
				try { 
					lex(srcPath + files[i]);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + files[1] + " is not found.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Missing argument for option: --lex");
				}
			}
		}
		
		if (cmd.hasOption("-parse")) {
			String[] files = null;
			if (parseFiles == null) {
				files = concat(lexFiles, typecheckFiles);
				files = concat(files, leftoverFiles);
			}
			else {
				files = parseFiles;
			}
						
			if (files == null || files.length == 0) {
				System.out.println("Missing argument for option: --parse");
				return;
			}

			for (int i = 0; i < files.length; i++) {
				try { 
					parse(srcPath + files[i]);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + files[1] + " is not found.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Missing argument for option: --lex");
				}
			}
		}

		if (cmd.hasOption("-typecheck")) {
			String[] files = null;
			if (typecheckFiles == null) {
				files = concat(parseFiles, lexFiles);
				files = concat(files, leftoverFiles);
			}
			else {
				files = typecheckFiles;
			}
			if (files == null || files.length == 0) {
				System.out.println("Missing argument for option: --typecheck");
				return;
			}

			for (int i = 0; i < files.length; i++) {
				try { 
					typecheck(srcPath + files[i]);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + files[1] + " is not found.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Missing argument for option: --typecheck");
				}
			}
		}
	}
	
	public static void lex(String filename) throws IOException {
		Scanner lexer = new Scanner(new FileReader(filename));
		String content = "";
		
		System.out.println("[xic] Lexing");
		
		Symbol symbol = null;
		try {
			symbol = lexer.next_token();
		} catch (RuntimeException ex) {
			content += ex.getMessage();
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
			}
			catch (RuntimeException ex) {
				content += ex.getMessage();
				break;
			}
		}
		
		// write content to output file
		try {
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}
			
			String rmExtension = filename.substring(0,index);
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
//			e.printStackTrace();
		}
	}
	
	public static void parse(String filename) throws IOException {
		try {
			parser p = new parser(new Scanner(new FileReader(filename)));
		
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}
			
			String rmExtension = filename.substring(0,index);
			File file = new File(rmExtension + ".parsed");
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
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	public static void typecheck(String filename) throws IOException {
		try {
			System.out.println(filename);
			int index = filename.lastIndexOf('.');
			if (index == -1) {
				index = filename.length();
			}
			
			String rmExtension = filename.substring(0,index);
			File file = new File(rmExtension + ".typed");
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
			TypeCheckVisitor visitor = new TypeCheckVisitor(result);
			visitor.visit(result);
			
			bw.write("Valid Xi Program");
			bw.close();
			System.out.println("[xic] Typechecking completed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void setToken(java_cup.runtime.Symbol info) {
		error = info;
	}
	
	public static void handleError(String msg) 
			throws RuntimeException, IOException {
		
		String errorMessage = error.left + ":" + error.right + 
				" error:" + msg + error.value;
		bw.write(errorMessage);
		bw.close();
		System.out.println("Syntax error beginning at " + errorMessage);
		throw new RuntimeException("[xic] Parsing Failed.");
	}
	
	public static void handleSemanticError(SemanticErrorObject seo) {
		// TODO: implement
		try {
			bw.write(seo.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Semantic error beginning at " + 
				seo.getLineNumber() + ":" + seo.getColNumber() + ": "
				+ seo.getDescription());
		throw new RuntimeException("[xic] Typecheck Failed.");
	}
	
	public static Map<String, VType> checkInterface(String interfaceName){
		String absPath = libPath + interfaceName + ".ixi";
		Map<String, VType> tempMap = new HashMap<String, VType>();
		try {
			parser p = new parser(new Scanner(new FileReader(absPath)));
//			p.setScanner(new Scanner(new FileReader(filename)));
			Symbol s = p.parse();
			Interface result = (Interface) s.value;
			List<InterfaceFunc> tempFuncs = result.getInterfaceFuncs();
			for (int i = 0; i < tempFuncs.size(); i++){
				// TODO: CHECK IF INTERFACE CAN HAVE 2 METHODS WITH SAME SIGNATURE
				if (tempMap.containsKey(tempFuncs.get(i).getIdentifier().toString())){
					// TODO: ERROR AHNDLING ASSUMING METHOD WAS ALREADY DECLARED
				}
				tempMap.put(tempFuncs.get(i).getIdentifier().toString(),
						new FunType(tempFuncs.get(i)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempMap;
	}
	
	public static String[] concat(String[] a, String[] b) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		int aLen = a.length;
		int bLen = b.length;
	   String[] c= new String[aLen+bLen];
	   System.arraycopy(a, 0, c, 0, aLen);
	   System.arraycopy(b, 0, c, aLen, bLen);
	   return c;
	}
}