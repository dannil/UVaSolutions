import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private class Corner {

		public int x;
		public int y;

		public Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public void run() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int numberOfCorners = Integer.parseInt(reader.readLine());
		while (numberOfCorners != 0) {
			Corner[] corners = new Corner[55];
			for (int i = 0; i < numberOfCorners; i++) {
				String[] cornerData = reader.readLine().split(" ");

				int x = Integer.parseInt(cornerData[0]);
				int y = Integer.parseInt(cornerData[1]);

				Corner c = new Corner(x, y);
				corners[i] = c;
			}

			corners[numberOfCorners] = corners[0];
			corners[numberOfCorners + 1] = corners[1];

			int i;

			// Does the first three corners do a right-hand or left-hand turn?
			// That decides if we should go left or right
			if (turn(corners[0], corners[1], corners[2]) >= 0) {
				// Yay, a left turn!
				for (i = 1; i < numberOfCorners; i++) {
					// Is it a right turn? If it is, we got a critical spot
					if (turn(corners[i], corners[i + 1], corners[i + 2]) < 0) {
						break;
					}
				}
			} else {
				// Yay, a right turn!
				for (i = 1; i < numberOfCorners; i++) {
					// Is it a left turn? If it is, we got a critical spot
					if (turn(corners[i], corners[i + 1], corners[i + 2]) > 0) {
						break;
					}
				}
			}

			// If the variable i isn't equal to the number of corners, it means
			// that the above loops (depending if the first three corners form a
			// right-hand or left-hand turn) did in fact break, which means we
			// detected a right-hand turn after a left-hand turn (or vice
			// versa). Drawing this, it's easy to see we now got a critical spot
			if (i != numberOfCorners) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}

			numberOfCorners = Integer.parseInt(reader.readLine());
		}
	}

	private int turn(Corner c1, Corner c2, Corner c3) {
		// Calculates the turn depending on the x and y
		int leftOrRight = (c1.y - c2.y) * (c3.x - c2.x) - (c1.x - c2.x) * (c3.y - c2.y);

		if (leftOrRight < 0) {
			// Right turn
			return -1;
		}
		if (leftOrRight > 0) {
			// Left turn
			return 1;
		}

		// No turn
		return 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Main m = new Main();
		m.run();
	}

}
