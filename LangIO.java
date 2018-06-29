


import java.io.*;

public class LangIO{
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Language lang = new Language();
		
		try { 
			if (args.length != 2) {
	            System.out.println("Usage:  java LangIO [input file pathname]" +
	                " [output file pathname]");
	            System.exit(1);
	        }
			BufferedReader input = new BufferedReader(new FileReader(args[0]));
			BufferedWriter output = new BufferedWriter(new FileWriter(args[1]));
			
			while (input.readLine() != null) {
				output.write(lang.langCheck(input.readLine()));
				}
			}
		catch (Exception e) {
            System.err.println(e.toString());
            return;
        }
		
	}
}
