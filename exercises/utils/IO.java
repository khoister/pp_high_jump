//
// Input/Output utilities
//

package exercises.utils;

import java.io.Console;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.IllegalFormatException;


public class IO {

	private static Console console = System.console();
	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static final void printf(String formatString, Object... args) {
		try {
			if (console != null) {
				console.printf(formatString, args);
			} else {
				System.out.print(String.format(formatString, args));
			}
		} catch (IllegalFormatException e) {
			System.err.print("Error printing...\n");
		}
	}
	
	public static final void print(String s) {
		printf("%s", s);
	}
	
	public static final String readLine(String userPrompt) throws IOException {
		String line;
		
		if (console != null) {
			line = console.readLine(userPrompt);
		} else {
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}
}

