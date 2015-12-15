import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numberOfPillars = -1;
		int set = 0;

		while (numberOfPillars != 0) {
			set++;

			int totalBricks = 0;

			numberOfPillars = Integer.parseInt(br.readLine());
			if (numberOfPillars != 0) {
				String[] pillarsAsStrings = br.readLine().split(" ");

				int[] pillars = new int[pillarsAsStrings.length];

				for (int i = 0; i < pillarsAsStrings.length; i++) {
					pillars[i] = Integer.parseInt(pillarsAsStrings[i]);
					totalBricks += pillars[i];
				}

				int totalMoves = 0;

				// Calculate the average stack of bricks
				int averageBricksInAStack = totalBricks / numberOfPillars;

				// Loop through all our stacks
				for (int j = 0; j < pillars.length; j++) {
					if (averageBricksInAStack < pillars[j]) {
						// If there's more bricks in our current stack than the
						// average, it means we need to move bricks. The number
						// of moves is the result of comparing the number of
						// bricks in our current stack to what it should be (the
						// average). The result is the number of moves that
						// need's to be performed.

						totalMoves += pillars[j] - averageBricksInAStack;
					}
				}

				System.out.println("Set #" + set);
				System.out.println("The minimum number of moves is " + totalMoves + ".");
				System.out.println();
			}
		}

	}
}
