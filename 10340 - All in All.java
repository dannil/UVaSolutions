import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] rowContents = line.split(" ");

			String firstString = rowContents[0];
			String secondString = rowContents[1];

			boolean isSubSequence = true;
			int index = 0;

			// Loop through the first string
			for (int i = 0; i < firstString.length(); i++) {
				char c = firstString.charAt(i);

				// If the index hasn't reached the end of the second string AND
				// the current char of the second string isn't equal to the char
				// in the first string (they're equal), increment the index
				while (index < secondString.length() && secondString.charAt(index) != c) {
					index++;
				}

				// If the index is equal to the second strings length, this
				// means that we looped through the second string and couldn't
				// find a sub sequence which our variable c is part of
				if (index == secondString.length()) {
					isSubSequence = false;
					break;
				}

				// Increment the index
				index++;
			}

			if (isSubSequence) {
				System.out.printf("%s\n", "Yes");
			} else {
				System.out.printf("%s\n", "No");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}
}
