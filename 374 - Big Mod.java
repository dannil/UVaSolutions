import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	private BigInteger bigMod(BigInteger b, BigInteger p, BigInteger m) {
		return b.modPow(p, m);
	}

	// Calculates modular exponentiation in a divide-and-conquer fashion
	// private long bigMod(long b, long p, long m) {
	// if (p == 0) {
	// return 1L;
	// }
	// if (p % 2 == 0) {
	// return (bigMod(b, p / 2, m) * bigMod(b, p / 2, m)) % m;
	// }
	// return (bigMod(b, p - 1, m) * (b % m)) % m;
	// }

	private void run() throws IOException {
		// long res = bigMod(875898931, 93086444, 10494);
		// System.out.println("RES: " + res);

		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			BigInteger b = s.nextBigInteger();
			BigInteger p = s.nextBigInteger();
			BigInteger m = s.nextBigInteger();

			System.out.println(bigMod(b, p, m));
		}

		// while (s.hasNextLong()) {
		// long b = s.nextLong();
		// long p = s.nextLong();
		// long m = s.nextLong();
		//
		// System.out.println(bigMod(b, p, m));
		// }
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}

}
