import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while ((line = br.readLine()) != null) {
			// System.out.println(currentLine);

			String lineAsBinary = "";

			// Convert the line to characters
			char[] lineAsChars = line.toCharArray();
			for (int i = 0; i < lineAsChars.length; i++) {
				if (lineAsChars[i] == ' ') {
					// If the character is a space ( ), it translates to a 0
					lineAsBinary += "0";
				} else if (lineAsChars[i] == 'o') {
					// If the character is a o, it translates to a 1
					lineAsBinary += "1";
				}
			}

			if (lineAsBinary != "") {
				// If the line resulted in 0's and 1's (binary of course),
				// convert the current line to a character
				Character c = new Character((char) Integer.parseInt(lineAsBinary, 2));
				System.out.print(c);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}
}
