import java.util.Scanner;

public class Main {

	public void run() {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextDouble()) {
			double n = sc.nextDouble();
			double p = sc.nextDouble();

			// Calculates nth root of p using logarithms
			double result = Math.pow(Math.E, (Math.log(p) / n));

			// Convert to an integer and print
			System.out.println((int) Math.round(result));
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

}
