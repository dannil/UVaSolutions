import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static class Point implements Comparable<Point> {

		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point point) {
			// return (this.x < point.x) ? 1 : 0;
			return (int) ((int) point.x - this.x);
		}

		public static double distance(Point a, Point b) {
			// Returns the distance between two points by calculating the
			// resulting right triangle between the points.

			// Square root computation to figure out the hypotenuse is performed
			// after the loop in run() to save computation time

			// Source: http://www.purplemath.com/modules/distform.htm
			return Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Point [x=");
			builder.append(this.x);
			builder.append(", y=");
			builder.append(this.y);
			builder.append("]");
			return builder.toString();
		}

	}

	public void run() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int numberOfPoints = Integer.parseInt(reader.readLine());
		while (numberOfPoints != 0) {

			// System.out.println("Points: " + numberOfPoints);
			List<Point> points = new ArrayList<Point>();

			for (int i = 0; i < numberOfPoints; i++) {
				String[] rowData = reader.readLine().split(" ");

				// System.out.println(Arrays.toString(rowData));

				double x = Double.parseDouble(rowData[0]);
				double y = Double.parseDouble(rowData[1]);

				Point p = new Point(x, y);
				points.add(p);
			}

			double minimumDistance = Double.MAX_VALUE;

			// Compare the current point (p1) to all the points to the right of
			// it on the x-axis.
			for (int i = 0; i < numberOfPoints; i++) {
				Point p1 = points.get(i);

				for (int j = i + 1; j < numberOfPoints; j++) {
					Point p2 = points.get(j);
					double square = Math.pow(p2.x - p1.x, 2);

					// Is the current x-distance smaller than the saved minimum
					// distance?
					if (square < minimumDistance) {
						// Compare the distance for p1 and p2 (both x and y) and
						// save it to minimumDistance
						// if it's smaller than the current minimumDistance
						double distance = Point.distance(p1, p2);
						minimumDistance = Math.min(distance, minimumDistance);
					}
				}

			}

			minimumDistance = Math.sqrt(minimumDistance);
			// System.out.println("Distance: " + minimumDistance);

			// Check if the distance is between 10000 (infinity) and 0 (no
			// distance at all)
			if (minimumDistance > 10000 || Math.abs(minimumDistance - 10000) < 0) {
				System.out.println("INFINITY");
			} else {
				System.out.println(String.format("%.4f", minimumDistance).replace(',', '.'));
			}

			numberOfPoints = Integer.parseInt(reader.readLine());
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Main m = new Main();
		m.run();
	}

}
