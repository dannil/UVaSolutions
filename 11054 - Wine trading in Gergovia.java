import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		long[] bottles;
		int numberOfHouses = -1;

		while (true) {
			numberOfHouses = Integer.parseInt(reader.readLine());
			if (numberOfHouses == 0) {
				break;
			}

			bottles = new long[1000001];

			String[] rowContents = reader.readLine().split(" ");
			for (int i = 0; i < numberOfHouses; i++) {
				bottles[i] = Long.parseLong(rowContents[i]);
			}

			long result = 0;
			// Loop through all the houses and calculate the work needed
			for (int i = 1; i < numberOfHouses; i++) {
				// The work needed for the previous house
				long previousHouse = bottles[i - 1];

				// Add the needed work for the previous house to the current
				// house
				bottles[i] += bottles[i - 1];

				// Increment the result (total work) with the work for the
				// previous house
				result += Math.abs(previousHouse);
			}

			// Print result
			System.out.println(result);
		}
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}
}
