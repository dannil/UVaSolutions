import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	private BigInteger[] fibNumbers;

	private Main() {
		this.fibNumbers = new BigInteger[5001];
		this.fibNumbers[0] = BigInteger.ZERO;
		this.fibNumbers[1] = BigInteger.ONE;
	}

	private BigInteger fib(int n) {
		// Special cases
		if (n == 0) {
			return this.fibNumbers[0];
		}
		if (n == 1) {
			return this.fibNumbers[1];
		}

		// Check if we've already calculated the nth Fibonacci number
		// previously, saving time if there is alot of test cases
		// in exchange for data space (memoization)
		if (this.fibNumbers[n] == null) {
			this.fibNumbers[n] = fib(n - 1).add(fib(n - 2));
		}

		return this.fibNumbers[n];
	}

	private void run() {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			BigInteger nthFibNumber = fib(n);

			System.out.printf("The Fibonacci number for %d is %d\n", n, nthFibNumber);
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

}
