import java.util.Scanner;

public class Paint {

	public static void main(String args[]) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter the height = ");
			double height = scanner.nextDouble();
			System.out.println("Enter the width = ");
			double width = scanner.nextDouble();
			double area_per_quarts = 100;
			double area = width * height;
			double quarts = area / area_per_quarts;
			int roundQuarts = (int) Math.ceil(quarts);
			int gallon = roundQuarts / 4;
			System.out.println("Gallon = " + gallon + " Quarts = " + roundQuarts % 4);
		}
	}
}