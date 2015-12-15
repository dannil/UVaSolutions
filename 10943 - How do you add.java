import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	int[][] numbers;

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}

	public Main() {
		this.numbers = new int[101][101];

		// Fill the numbers array with -1 (placeholder) to see if we haven't
		// calculated this cell yet
		for (int i = 0; i < this.numbers.length; i++) {
			for (int j = 0; j < this.numbers.length; j++) {
				this.numbers[i][j] = -1;
			}
		}
	}

	public int divideIntoParts(int n, int k) {
		if (k <= 1) {
			// If k is smaller or equal to 1, return 1 (we reached the end)
			return 1;
		} else if (this.numbers[n][k] != -1) {
			// If numbers[n][k] isn't -1, it means that we've already calculated
			// that cell, so return it
			return this.numbers[n][k];
		}

		int i;
		this.numbers[n][k] = 0;
		for (i = 0; i <= n; i++) {
			// Calculate all the combinations by divide-and-conquer into smaller
			// k-parts. When that calculation is complete, we increment i by 1
			// (to get all the combinations where N starts with i + 1) and
			// repeat the k-part
			this.numbers[n][k] = (this.numbers[n][k] + divideIntoParts(i, k - 1)) % 1000000;
		}
		// Return the result of inputting N and K
		return this.numbers[n][k];
	}

	public void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] rowContents = reader.readLine().split(" ");

			int N = Integer.parseInt(rowContents[0]);
			int K = Integer.parseInt(rowContents[1]);

			// End case
			if (N == 0 && K == 0) {
				break;
			}

			// Print the result
			System.out.println(divideIntoParts(N, K));
		}
	}

}
