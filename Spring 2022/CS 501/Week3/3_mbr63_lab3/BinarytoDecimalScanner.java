import java.util.Scanner;

class BinaryDecimal {
	public int binaryString(String solution) {
		int decimal = Integer.parseInt(solution, 2);
		return decimal;
	}

	public String DecimalString(int number) {
		String binary = Integer.toBinaryString(number);
		return binary;
	}
}

public class BinarytoDecimalScanner {
	public static void main(String args[]) {

		BinaryDecimal binaryDecimal = new BinaryDecimal();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("QUIZ\n");
		System.out.print("Enter 1 for Binary to Decimal. Enter 2 for Decimal to Binary.\n");
		String choice = keyboard.nextLine();
		if (choice.contains("1")) {
			System.out.print("Binary to Decimal\n");
			System.out.print("Enter Binary Number to see result in Decimal:");
			String string = keyboard.nextLine();
			int binaryResult = binaryDecimal.binaryString(string);
			System.out.printf("Your Quiz is Binary and %s is your Decimal Conversion", binaryResult);
		} else if (choice.contains("2")) {
			System.out.print("Decimal to Binary\n");
			System.out.print("Enter Decimal Number to see result in Binary:");
			int number = keyboard.nextInt();
			String decimalResult = binaryDecimal.DecimalString(number);
			System.out.printf("Your Quiz is Decimal and %s is your Binary Conversion", decimalResult);
		}
	}

}