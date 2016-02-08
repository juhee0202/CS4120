package jl2755;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java_cup.runtime.Symbol;

public class PA1 {

	public static void main(String[] args) {
		
		// parse command-line arguments 
		Options options = new Options();
		options.addOption("h", "help", false, 
				"Print a synopsis of options");
		options.addOption("l", "lex", true, 
				"Generate output from lexical analysis");
		
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
			System.out.println("LEXING!\n");
			try {
				lex(args[1]);
			} catch (FileNotFoundException e) {
				System.out.println(args[1] + " is not found.");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void lex(String filename) throws IOException {
		Symbol sym;
		Scanner lexer = new Scanner(new FileReader(filename));

		for (sym = lexer.next_token(); sym.sym != 0; sym = lexer.next_token()) {
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
			System.out.println(sym.left + ":" + sym.right + " "+ tokentype + val);
//			System.out.println("val: " + sym.sym);
		}
	}
}
