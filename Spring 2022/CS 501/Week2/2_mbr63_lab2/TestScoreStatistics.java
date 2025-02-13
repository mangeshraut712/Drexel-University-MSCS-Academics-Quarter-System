import java.util.ArrayList;
import java.util.Scanner;

public class TestScoreStatistics {
	public static void main(String[] args) {
		ArrayList<Double> grades = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		double grd;
		double sum = 0;
		for (int i = 0; i < 3; i++) {
			System.out.print("Enter a grade: ");
			grd = scan.nextFloat();
			grades.add(grd);
		}
		double min = Math.min(grades.get(0), grades.get(1));
		double min1 = Math.min(grades.get(2), min);

		double max = Math.max(grades.get(0), grades.get(1));
		double max1 = Math.max(grades.get(2), max);

		double range = range = max1 - min1;
		double average = (grades.get(0) + grades.get(1) + grades.get(2)) / 3;
		System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.print("\nAverage: " + average);
		System.out.print("\nMin: " + min1);
		System.out.print("\nMax: " + max1);
		System.out.print("\nRange: " + range);
		System.out.print("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

}