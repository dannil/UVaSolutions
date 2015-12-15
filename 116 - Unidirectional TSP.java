import java.util.Scanner;

public class Main {
	private int m, n;
	private int[][] grid;
	private int[][] dp;
	private int[][] paths;

	private void run() {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int min, start, check;
		while (in.hasNext()) {

			// Initialize variables
			this.m = in.nextInt();
			this.n = in.nextInt();
			this.grid = new int[this.m][this.n];
			this.dp = new int[this.m][this.n];
			this.paths = new int[this.m][this.n];
			for (int i = 0; i < this.m; i++) {
				for (int j = 0; j < this.n; j++) {
					this.grid[i][j] = in.nextInt();
				}
			}

			// Find shortest path
			min = getPath(0, 0);
			start = 0;
			for (int i = 1; i < this.m; i++) {
				check = getPath(i, 0);
				if (check < min) {
					// If the checked path is shorter than our current shortest
					// path, set new shortest path and the start index.
					min = check;
					start = i;
				}
			}

			// Print data
			for (int i = 0; i < this.n; i++) {
				out.append(start + 1);
				// For every loop, print the current cell's weight
				start = this.paths[start][i];
				if (i < this.n - 1) {
					out.append(" ");
				}
			}
			// Print the shortest path
			out.append("\n");
			out.append(min + "\n");
		}
		System.out.print(out);
	}

	private int getPath(int i, int j) {
		// We reached the end
		if (j == this.n) {
			return 0;
		}
		// Cell already calculated; return value
		if (this.dp[i][j] != 0) {
			return this.dp[i][j];
		}

		// Get all the travel direction's values (weights)
		int down = (i + 1) % this.m;
		int beside = i;
		int up = (i + this.m - 1) % this.m;

		int min, index, check;

		// Get weight for the next cell under the current
		min = getPath(down, j + 1);
		index = down;

		// Get weight for the next cell beside the current. If the weight is
		// smaller than the minimum OR if the checked distance is equal to the
		// minimum distance and the weight to the right is smaller than the
		// index (our current travel direction), the path to the right is less
		// expensive.
		check = getPath(beside, j + 1);
		if (check < min || (check == min && beside < index)) {
			min = check;
			index = beside;
		}

		// Get weight for the next cell over the current. If the weight is
		// smaller than the minimum OR if the checked distance is equal to the
		// minimum distance and the weight to over is smaller than the
		// index (our current travel direction), the path up is less
		// expensive.
		check = getPath(up, j + 1);
		if (check < min || (check == min && up < index)) {
			min = check;
			index = up;
		}

		this.paths[i][j] = index;
		return this.dp[i][j] = this.grid[i][j] + min;
	}

	public static void main(String[] args) throws Exception {
		Main m = new Main();
		m.run();
	}
}