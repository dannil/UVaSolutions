import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	private class Coordinate implements Comparable<Coordinate> {

		public double x;
		public double y;

		public Coordinate() {
			// TODO Auto-generated constructor stub
		}

		public Coordinate(int x, int y) {
			this();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coordinate cord) {
			// Compare coordinates on x-value

			// return (int) Math.floor(cord.x - this.x);
			return (int) ((int) cord.x - this.x);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Coordinate [x=");
			builder.append(this.x);
			builder.append(", y=");
			builder.append(this.y);
			builder.append("]");
			return builder.toString();
		}
	}

	public void run() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCases; i++) {
			List<Coordinate> coordinates = new ArrayList<Coordinate>();

			int rows = Integer.parseInt(reader.readLine());
			for (int j = 0; j < rows; j++) {
				String[] coordinateData = reader.readLine().split(" ");

				int x = Integer.parseInt(coordinateData[0]);
				int y = Integer.parseInt(coordinateData[1]);

				Coordinate coordinate = new Coordinate(x, y);
				coordinates.add(coordinate);
			}

			// Sort on coordinates x values (descending), to prepare for
			// line-sweep
			Collections.sort(coordinates);

			// System.out.println(coordinates.toString());

			double tallestHill = 0;
			double sunCoverage = 0;
			for (int j = 1; j < rows; j++) {
				// Calculate the sun coverage by comparing the current peak with
				// the previous peak
				Coordinate current = coordinates.get(j);
				Coordinate previous = coordinates.get(j - 1);

				// Only calculate sun coverage if the current hill is smaller
				// than the tallest hill (otherwise no sun coverage) and that
				// the current hill is larger than the previous (same apply as
				// for tallest hill)
				if (current.y > tallestHill && current.y > previous.y) {
					// Differences between the current and previous peak x and y
					double dx = current.x - previous.x;
					double dy = current.y - previous.y;
					// Fancy formula for calculating height
					double h = Math.sqrt(dx * dx + dy * dy);
					double sunnyOnY = current.y - tallestHill;
					double sunny = sunnyOnY * h / dy;
					sunCoverage += sunny;
				}
				// Save the tallest hill to save computation time
				tallestHill = Math.max(tallestHill, current.y);
			}

			// Convert to two decimal places floating point
			// and replace commas with dots (American notation)
			String answer = String.format("%.2f", sunCoverage);
			System.out.println(answer.replace(',', '.'));
		}

	}

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Main m = new Main();
		m.run();
	}

}
