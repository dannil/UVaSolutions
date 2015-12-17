import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= testCases; i++) {
			String[] dimensions = br.readLine().split(" ");

			int length = Integer.parseInt(dimensions[0]);
			int width = Integer.parseInt(dimensions[1]);
			int height = Integer.parseInt(dimensions[2]);

			String result = "";
			if (length > 20 || width > 20 || height > 20) {
				result = "bad";
			} else {
				result = "good";
			}
			System.out.printf("Case %s: %s\n", i, result);
		}
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}

}
