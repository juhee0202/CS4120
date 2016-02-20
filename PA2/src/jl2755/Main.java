package jl2755;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java_cup.runtime.Symbol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) {
		
		// parse command-line arguments 
		Options options = new Options();
		options.addOption("h", "help", false, 
				"Print a synopsis of options.");
		options.addOption("l", "lex", true, 
				"Generate output from lexical analysis.");
		options.addOption("p", "parse", true,
				"Generate output from syntactic analysis.");
		
		CommandLineParser parser = new GnuParser();
		CommandLine cmd;
		
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException exp) {
			System.err.println(exp.getMessage());
			return;
		}
				
		if (cmd.hasOption("-help")) {			
			System.out.println("Usage: xic [options] <source files>\n" +
					"where possible options include");
			
			System.out.println("  --help: Print a synopsis of options.");
			System.out.println("  --lex: Generate output from lexical analysis.");
			
		} else if (cmd.hasOption("-lex")) {
			try {
				lex(args[1]);
			} catch (FileNotFoundException e) {
				System.out.println(args[1] + " is not found.");
//				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (cmd.hasOption("-parse")) {
			System.out.println("PARSING");
			try {
				parse(args[1]);
			} catch (FileNotFoundException e) {
				System.out.println(args[1] + " is not found.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// Lex
	public static void lex(String filename) throws IOException {
		Scanner lexer = new Scanner(new FileReader(filename));
		String content = "";
		
		Symbol sym = lexer.next_token();
		while (sym.sym != 0) {
			String tokentype;
			switch (sym.sym) {
				case 8:		tokentype = "id ";
							break;
				case 10: 	tokentype = "integer ";
							break;
				case 11: 	tokentype = "character ";
							break;
				case 14: 	tokentype = "string ";
							break;
				default: 	tokentype = "";
							break;
			}
				
			String val = sym.value == null ? lexer.yytext() : sym.value.toString();
			content += sym.left + ":" + sym.right + " "+ tokentype + val + "\n";
			
			try {
				sym = lexer.next_token();
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
			File file = new File(rmExtension + ".lexed");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void parse(String filename) throws IOException {
		try {
			parser p = new parser(new Scanner(new FileReader(filename)));
			Object result = p.parse().value;
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}