import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	private int solution;

	private String first;
	private String second;
	private String third;

	private boolean[] head;
	private ArrayList<Character> all;

	// Simple method for returning the roman equivalent of a letter
	private int romanLetterToArabicNumber(char letter) {
		switch (letter) {
			case 'M':
				return 1000;
			case 'D':
				return 500;
			case 'C':
				return 100;
			case 'L':
				return 50;
			case 'X':
				return 10;
			case 'V':
				return 5;
			case 'I':
				return 1;
			default:
				return 0;
		}
	}

	// Convert the input roman sum to an arabic sum
	private int romanSumToArabicSum(String romanSum) {
		int sum = 0;

		// Normalize string
		String upperCased = romanSum.toUpperCase();
		for (int i = 0; i < upperCased.length() - 1; i++) {
			int currentLetter = romanLetterToArabicNumber(upperCased.charAt(i));
			int nextLetter = romanLetterToArabicNumber(upperCased.charAt(i + 1));

			// According to regular Roman numerals system, if the current letter
			// is smaller than the next, we subtract this from the total;
			// otherwise, we add it to the total
			if (currentLetter < nextLetter) {
				sum -= currentLetter;
			} else {
				sum += currentLetter;
			}
		}
		// Don't forget the last roman letter, since the loop misses it due to
		// indexing problems
		sum += romanLetterToArabicNumber(upperCased.charAt(romanSum.length() - 1));
		return sum;
	}

	private void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String line = reader.readLine();
		while (!line.contains("#")) {
			StringBuilder builder = new StringBuilder();
			builder.append(line.toUpperCase());

			// Replace all plus and equals signs with spaces so we can
			// split the expression into its terms. If the character
			// we're looping over isn't one of these signs, mark this
			// character as seen in the charSeen array
			boolean[] charSeen = new boolean[26];
			for (int i = 0; i < builder.length(); i++) {
				if (line.charAt(i) == '+' || line.charAt(i) == '=') {
					builder.replace(i, i + 1, " ");
				} else {
					charSeen[builder.charAt(i) - 'A'] = true;
				}
			}

			// Extract the words (roman sums)
			String[] words = builder.toString().split(" ");
			this.first = words[0];
			this.second = words[1];
			this.third = words[2];

			this.head = new boolean[128];
			this.head[this.first.charAt(0)] = true;
			this.head[this.second.charAt(0)] = true;
			this.head[this.third.charAt(0)] = true;

			this.all = new ArrayList<Character>();
			for (int i = 0; i < 26; i++) {
				if (charSeen[i]) {
					this.all.add(Character.toChars(65 + i)[0]);
				}
			}

			int[] value = new int[128];
			boolean[] used = new boolean[10];
			this.solution = 0;
			findSolution(0, value, used);

			// Calculate if the first term plus the second term actually equals
			// the third term
			if (romanSumToArabicSum(this.first) + romanSumToArabicSum(this.second) == romanSumToArabicSum(this.third)) {
				System.out.print("Correct ");
			} else {
				System.out.print("Incorrect ");
			}

			// Prints if the solution is impossible, valid or ambiguous
			switch (this.solution) {
				case 0:
					System.out.println("impossible");
					break;
				case 1:
					System.out.println("valid");
					break;
				default:
					System.out.println("ambiguous");
					break;
			}

			line = reader.readLine();
		}
	}

	// Find the correct solution (0 for impossible, 1 for valid; every other
	// result is equal to an ambiguous solution
	private void findSolution(int now, int[] value, boolean[] used) {
		// We got an ambiguous solution
		if (this.solution > 1) {
			return;
		}
		if (now == this.all.size()) {
			int n1 = 0, n2 = 0, n3 = 0;

			// Calculates the roman sum for the three terms using our value
			// array. This array contains the arabic representations of the
			// roman letters, to enable easy calculation
			for (int i = 0; i < this.first.length(); i++) {
				n1 = n1 * 10 + value[this.first.charAt(i)];
			}
			for (int i = 0; i < this.second.length(); i++) {
				n2 = n2 * 10 + value[this.second.charAt(i)];
			}
			for (int i = 0; i < this.third.length(); i++) {
				n3 = n3 * 10 + value[this.third.charAt(i)];
			}
			if (n1 + n2 == n3) {
				// The terms match up; we increment our solution. If this runs
				// more than once during the programs lifecycle, it means that
				// more than one way of correctly expressing the roman sums was
				// found, and the calculation is therefore ambiguous. This
				// satisfies this.solution > 1 at the beginning of this method
				this.solution++;
			}
			return;
		}
		boolean var = this.head[this.all.get(now)];
		int lklk = (var) ? 1 : 0;
		// if (!var) {
		// lklk = 0;
		// } else {
		// lklk = 1;
		// }

		// Try to find more solutions if we got unused values (letters) and the
		// solution is or hasn't reached 1 (valid solution)
		for (int d = lklk; d < 10 && this.solution <= 1; d++) {
			if (!used[d]) {
				value[this.all.get(now)] = d;
				used[d] = true;
				findSolution(now + 1, value, used);
				used[d] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}
}
