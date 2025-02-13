import java.util.Random;

class BinaryDecimal {

	public String DecimalString(int secondDecimalNumber) {
		return Integer.toBinaryString(secondDecimalNumber);
	}
}

public class BinaryToDec {
	public static void main(String args[]) {
		BinaryDecimal binaryDecimal = new BinaryDecimal();
		Random random = new Random();
		int firstDecimalNumber = random.nextInt(1000) + 1;
		System.out.printf("%s is your Decimal Random Number\n", firstDecimalNumber);

		int secondDecimalNumber = random.nextInt(1000) + 1;
		String binary = binaryDecimal.DecimalString(secondDecimalNumber);
		System.out.printf("Decimal to binary conversion of %d = %s", secondDecimalNumber, binary);

	}

}