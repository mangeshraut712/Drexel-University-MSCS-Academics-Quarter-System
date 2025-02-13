import java.util.Random;

public class RandomArray {
	Random rd = new Random();

	public void zeroandoneArray(int[] array0to1) {
		int total1 = 0;
		for (int i = 0; i < array0to1.length; i++) {
			array0to1[i] = rd.nextInt(1, 3) - 1;
			total1 += array0to1[i];
			System.out.println(array0to1[i]);
		}

		int avg1 = total1 / array0to1.length;
		System.out.printf("Average of first array: %d", avg1);
	}

	public void diceArray(int[] dicearray) {
		int total2 = 0;
		for (int i = 0; i < dicearray.length; i++) {
			dicearray[i] = rd.nextInt(1, 7);
			total2 += dicearray[i];
			System.out.println(dicearray[i]);
		}
		int avg2 = total2 / dicearray.length;
		System.out.printf("Average of second array: %d", avg2);
	}

	public int[] twodiceArray(int[][] twodicearray) {
		int[] sum = new int[100];
		for (int row = 0; row < twodicearray.length; row++) {
			for (int col = 0; col < 2; col++) {
				twodicearray[row][col] = rd.nextInt(1, 7);

			}
			sum[row] = twodicearray[row][0] + twodicearray[row][1];

		}
		int total3 = 0;
		for (int i = 0; i < sum.length; i++) {
			total3 += sum[i];
			System.out.println(sum[i]);
		}
		int avg3 = total3 / sum.length;
		System.out.printf("Average of third array: %d\n", avg3);
		return sum;
	}

	public void histogram(int[] sum) {
		int[] tally = new int[11];
		// print star histogram
		for (int sumtally : sum) {
			tally[sumtally - 2]++;
		}
		for (int i = 0; i < tally.length; i++) {
			// counts[i] stores how many students scored i on the test,
			// so print a star (counts[i]) times
			System.out.print((i + 2) + ": ");
			for (int j = 0; j < tally[i]; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
