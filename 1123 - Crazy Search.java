import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	private void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCases; i++) {
			reader.readLine();

			String[] input = reader.readLine().split(" ");

			int N = Integer.parseInt(input[0]);
			// int NC = Integer.parseInt(input[1]);

			String stringToCheck = reader.readLine();

			Set<String> set = new HashSet<String>(N);

			for (int j = 0; j <= stringToCheck.length() - N; j++) {
				String subbed = stringToCheck.substring(j, j + N);
				set.add(subbed);
			}

			if (i != testCases - 1) {
				System.out.println(set.size());
			} else {
				System.out.print(set.size());
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}

}
