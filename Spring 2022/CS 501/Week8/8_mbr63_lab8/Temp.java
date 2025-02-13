import java.util.Arrays;
import java.util.Scanner;

public class Temp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the size of the temperature array : ");
		int size = sc.nextInt();
		int[] temps = new int[size];
		float sum = 0, new_sum = 0;
		int[] new_temps;
		int start_index = 0;
		int end_index = 0;
		int index = 0;
		try {
			System.out.println("Enter the temperatures in the array : ");
			for (int i = 0; i < size; i++) {
				temps[i] = sc.nextInt();
			}

			Arrays.sort(temps);
			System.out.println("Minimum value is " + temps[0]);
			System.out.println("Maximum value is " + temps[size - 1]);
			System.out.println("Range is " + (temps[size - 1] - temps[0]));
			for (int i = 0; i < size; i++) {
				sum = sum + temps[i];
			}
			System.out.println("Average is " + sum / size);

			System.out.println("Enter the start value of the array : ");
			int start = sc.nextInt();
			System.out.println("Enter the end value of the array : ");
			int end = sc.nextInt();

			for (int i = 0; i < temps.length; i++) {
				if (temps[i] == start) {
					start_index = i;
				} else if (temps[i] == end) {
					end_index = i;
				}
			}
			int new_size = end_index - start_index + 1;
			new_temps = new int[new_size];

			for (int k = start_index; k <= end_index; k++) {
				new_temps[index] = temps[k];
				index = index + 1;
				System.out.println(temps[k]);
			}

			System.out.println("Minimum value is " + new_temps[0]);
			System.out.println("Maximum value is " + new_temps[new_temps.length - 1]);
			System.out.println("Range is " + (new_temps[new_temps.length - 1] - new_temps[0]));
			for (int i = 0; i < new_temps.length; i++) {
				new_sum = new_sum + new_temps[i];
			}
			System.out.println("Average is " + new_sum / new_temps.length);
		} catch (Exception e) {
			System.out.println("\nException caught");
		}

	}

}
