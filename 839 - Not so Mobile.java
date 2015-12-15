import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private BufferedReader reader;

	public static void main(String args[]) throws Exception {
		Main m = new Main();
		m.run();
	}

	public void run() throws NumberFormatException, IOException {
		this.reader = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.parseInt(this.reader.readLine());

		this.reader.readLine();

		for (int i = 0; i < testCases; i++) {
			if (weight(this.reader.readLine()) != -1) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

			if (i != testCases - 1) {
				System.out.println();
				this.reader.readLine();
			}
		}
	}

	private int weight(String line) throws IOException {
		int left = 0;
		int right = 0;

		String[] rowContent = line.split(" ");

		// If the input is 0, it means we got a sub-mobile hanging underneath
		// the left sub-mobile. Calculate the weight for that sub-mobile.
		// Every other number means that there isn't anything underneath, and
		// this is our left weight.
		if (Integer.parseInt(rowContent[0]) == 0) {
			left = weight(this.reader.readLine());
		} else {
			left = Integer.parseInt(rowContent[0]);
		}

		// If the input is 0, it means we got a sub-mobile hanging underneath
		// the right sub-mobile. Calculate the weight for that sub-mobile.
		// Every other number means that there isn't anything underneath, and
		// this is our right weight.
		if (Integer.parseInt(rowContent[2]) == 0) {
			right = weight(this.reader.readLine());
		} else {
			right = Integer.parseInt(rowContent[2]);
		}

		// If the left or right weight are -1, we don't have an equilibrium
		if (left == -1 || right == -1) {
			return -1;
		}

		// Equilibrium on this sub-mobile if the weight on the left and right
		// side are equal; add the left and right weight to get the total weight
		// for this sub-mobile
		if (left * Integer.parseInt(rowContent[1]) == Integer.parseInt(rowContent[3]) * right) {
			return left + right;
		}

		// No equilibrium
		return -1;
	}
}
