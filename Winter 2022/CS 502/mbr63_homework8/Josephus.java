import java.util.Scanner;

// Java code for Josephus Problem

public class Josephus {

	public static void Josephus(int n, int m) {
		Queue queue = new Queue();
		for (int i = 0; i < n; i++) {
			queue.enqueue(i);
		}
		while (!queue.empty()) {
			// The eliminated position counted from 1.
			for (int i = 1; i <= m; i++) {
				int eliminatedPosition = queue.dequeue();

				if (i == m) {
					System.out.print(eliminatedPosition + " ");
					break;
				}
				queue.enqueue(eliminatedPosition);
			}
		}
	}

	// Driver code
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Number of People (N):");
		int n = scanner.nextInt();
		System.out.println("Enter Person to Eliminate (M):");
		int m = scanner.nextInt();
		System.out.println("Order Eliminated: ");
		Josephus(n, m);

	}
}
