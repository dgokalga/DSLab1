/* Lab 1
 *
 * Module: LangIO.java
 *
 * Author: D.G.
 *
 * This is the main driver, and the purpose of this module to read in a given input file, process each 
 * line's string and evaluate for language L, using Language.java, and write the output string
 * into a specified output file, line by line. 
 * 
 * Methods: main()
 *
 */

import java.io.*;

public class LangIO {
	/*
	 * Main method reads in input file argument, processes each line's strings for language L,
	 * writes string with appended language name to specified output file.   
	 */
	public static void main(String[] args) {
		Language lang = new Language(); // instantiate Language() class object 

		try {
			if (args.length != 2) { // if more or less than required arguments (input, output)
				System.out.println("Argument Error -> Usage: java -cp bin LangIO filename_input filename_output");
				System.exit(1);
			}
			else {
				try {
					BufferedReader input = new BufferedReader(new FileReader(args[0])); // read in input argument
					BufferedWriter output = new BufferedWriter(new FileWriter(args[1])); // write to output argument
					String line;
					output.write("Given each language: L1, L2, L3, L4, L5, L6 \n");
					output.write("The following strings were evaluated as: \n");
					output.newLine();
					while ((line = input.readLine()) != null) {
						output.write(lang.langCheck(line)); // write output string with appended language names if true
						output.newLine();
					}
					input.close();
					output.close();
				}
				catch (FileNotFoundException exception) { // file not found handling
					System.out.println("File '" + args[0] + "' was not found.");
				}
			}
		}
			 catch (Exception e) { // exception handling
				System.err.println(e.toString());
				return;
			 }
			
	}
}
