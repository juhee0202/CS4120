package jl2755;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java_cup.runtime.Symbol;
import jl2755.ast.Program;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {

	public static String destPath;
	public static String srcPath;
	public static BufferedWriter bw;
	public static java_cup.runtime.Symbol error;
	
	public static void main(String[] args) {
		
		// add command-line arguments 
		Options options = new Options();
		options.addOption("h", "help", false, 
				"Print a synopsis of options.");
		Option lexOpt = new Option("l", "lex", false, 
				"Generate output from lexical analysis.");
		lexOpt.setArgs(Option.UNLIMITED_VALUES);
		lexOpt.	setOptionalArg(true);
		options.addOption(lexOpt);
		Option parseOpt = new Option("p", "parse", false, 
				"Generate output from syntactic analysis.");
		parseOpt.setArgs(Option.UNLIMITED_VALUES);
		parseOpt.setOptionalArg(true);
		options.addOption(parseOpt);
		options.addOption("sourcepath", true, 
				"Specify where to find input source files. ");
		options.addOption("D", true, " Specify where to place generated diagnostic files.");
		
		CommandLineParser parser = new GnuParser();
		CommandLine cmd;
		srcPath = "";
		destPath = "";
		
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException exp) {
			System.err.println(exp.getMessage());
			return;
		}
				
		if (cmd.hasOption("sourcepath")) {
			srcPath = cmd.getOptionValue("sourcepath")+"/";
		}

		if (cmd.hasOption("D")) {
			destPath = cmd.getOptionValue("D")+"/";
		}

		if (cmd.hasOption("-help")) {			
			System.out.println("Usage: xic [options] <source files>\n" +
					"where possible options include");
			
			System.out.println("  --help: Print a synopsis of options.");
			System.out.println("  --lex: Generate output from lexical analysis.");
			System.out.println("  --parse: Generate output from syntactic analysis.");
			System.out.println("  -sourcepath: Specify where to find input source files.");
			System.out.println("  -D: Specify where to place generated diagnostic files.");
			
		} 
		
		if (cmd.hasOption("-lex")) {
			String[] files = cmd.getOptionValues("l");
			if (files == null && cmd.hasOption("-parse")) {
				files = cmd.getOptionValues("p");
			}
			
			for (int i = 0; i < files.length; i++) {
				try { 
					lex(srcPath + files[i]);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + files[1] + " is not found.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (cmd.hasOption("-parse")) {
			String[] files = cmd.getOptionValues("p");
			if (files == null && cmd.hasOption("-parse")) {
				files = cmd.getOptionValues("l");
			}
			
			for (int i = 0; i < files.length; i++) {
				try { 
					parse(srcPath + files[i]);
				} catch (FileNotFoundException e) {
					System.out.println(srcPath + files[1] + " is not found.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	// Lex
	public static void lex(String filename) throws IOException {
		Scanner lexer = new Scanner(new FileReader(filename));
		String content = "";
		
		System.out.println("[xic] Lexing");
		
		Symbol symbol = lexer.next_token();
		while (symbol.sym != 0) {
			String tokentype;
			switch (symbol.sym) {
					case sym.IDENTIFIER:		tokentype = "id ";
											break;
				case sym.INTEGER_LITERAL: 	tokentype = "integer ";
											break;
				case sym.CHARACTER_LITERAL: 	tokentype = "character ";
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
				content += lexer.origLine + ":" + lexer.origCol + 
						" error: Illegal input \"" + lexer.yytext() + "\"";
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
			e.printStackTrace();
		}
	}
	
	public static void parse(String filename) throws IOException {
		try {
			parser p = new parser(new Scanner(new FileReader(filename)));
//			p.setScanner(new Scanner(new FileReader(filename)));
		
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
			System.out.println("[xic] Parsing completed");
		} catch (Exception e) {
			return;
		}
	}
	
	public static void setToken(java_cup.runtime.Symbol info) {
		error = info;
	}
	
	public static void handleError(String msg) 
			throws RuntimeException, IOException {
		String errorMessage = error.left + ":" + error.right + 
				" error:" + msg + error.value;
//		System.out.println(errorMessage);
		bw.write(errorMessage);
		bw.close();
		throw new RuntimeException("[xic] Parsing Failed.");
	}
	
	public static void handleSemanticError(SemanticErrorObject seo) {
		// TODO impelment
	}
}