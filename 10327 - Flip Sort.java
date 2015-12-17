import java.util.Scanner;

public class Main {

	private int mergeSort(int[] array) {
		int[] temp = new int[array.length];
		return mergeSort(array, temp, 0, array.length - 1);
	}

	// Merge sort which counts the number of inversions
	private int mergeSort(int[] array, int[] temp, int left, int right) {
		int inversionCount = 0;
		if (right > left) {
			int mid = (right + left) / 2;

			inversionCount += mergeSort(array, temp, left, mid);
			inversionCount += mergeSort(array, temp, mid + 1, right);

			inversionCount += merge(array, temp, left, mid + 1, right);
		}
		return inversionCount;
	}

	// Merges two arrays and returns the inversion count
	private int merge(int[] array, int[] temp, int left, int mid, int right) {
		int i = left;
		int j = mid;
		int k = left;

		int inversionCount = 0;

		while ((i <= mid - 1) && (j <= right)) {
			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			} else {
				temp[k++] = array[j++];

				inversionCount = inversionCount + (mid - i);
			}
		}

		while (i <= mid - 1) {
			temp[k++] = array[i++];
		}
		while (j <= right) {
			temp[k++] = array[j++];
		}
		for (i = left; i <= right; i++) {
			array[i] = temp[i];
		}

		return inversionCount;
	}

	private void run() {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextInt()) {
			int numbers = sc.nextInt();

			int[] arr = new int[numbers];
			for (int i = 0; i < numbers; i++) {
				arr[i] = sc.nextInt();
			}

			System.out.println("Minimum exchange operations : " + mergeSort(arr));
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

}
