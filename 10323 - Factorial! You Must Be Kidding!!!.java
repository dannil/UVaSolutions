import java.util.Scanner;

public class Main {

	private long[] factorials;

	private Main() {
		this.factorials = new long[100001];
		this.factorials[0] = 1;
	}

	private long fac(int n) {
		// System.out.println("N is " + n);

		if (n == 0) {
			return this.factorials[0];
		}

		if (this.factorials[n] == 0) {
			this.factorials[n] = n * fac(n - 1);
		}

		// System.out.println("Returning from where N is " + n);

		return this.factorials[n];

	}

	private void run() {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextInt()) {
			int n = sc.nextInt();

			if (n >= 0) {
				if (n >= 14) {
					System.out.println("Overflow!");
				} else if (n >= 8 && n <= 13) {
					System.out.println(fac(n));
				} else if (n >= 0 && n <= 7) {
					System.out.println("Underflow!");
				}
			} else {
				if (n % 2 == 0) {
					System.out.println("Underflow!");
				} else {
					System.out.println("Overflow!");
				}
			}
		}

	}

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

}
