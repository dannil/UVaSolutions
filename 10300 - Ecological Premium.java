import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCases; i++) {
			int numberOfFarmers = Integer.parseInt(br.readLine());

			int sum = 0;

			for (int j = 0; j < numberOfFarmers; j++) {

				String[] values = br.readLine().split(" ");

				// Calculating the animals per square meter and multiplying with
				// the number of animals cancel each other out, so we only need
				// to calculate the land area multiplied by the environment
				// friendliness
				//
				// Formally:
				// (land area / number of animals) * environment
				// friendliness * number of animals
				// =
				// land area * environment friendliness
				sum += Integer.parseInt(values[0]) * Integer.parseInt(values[2]);
			}

			System.out.println(sum);
		}

	}
}
