import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class exponentTimings {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		ArrayList Number = new ArrayList();
		long start, finish;
		System.out.println("Enter the number to find the exponent");
		int intnumber = keyboard.nextInt();
		BigInteger number = BigInteger.valueOf(intnumber);
		BigInteger a, b, c;
		Number.add(number);
		int maxNumber = -1;
		while (maxNumber < 0) {
			// make sure that the user inputs a number >= 0
			System.out.println("Enter the number to find the max exponent");
			maxNumber = keyboard.nextInt();
		}

		System.out.printf("%40s\n", "Powers of " + number + "");
		System.out.printf("%7s %25s %15s %15s\n", "Power", "Built In", "myPower1", "myPower2");
		for (int i = 0; i <= maxNumber; i++) {
			a = number.pow(i);
			b = myPow1(number, i);
			c = myPow2(number, i);
			System.out.printf("|%-15s| %-15s| %-15s| %-15s|\n", "|" + number + "^(" + i + ")|\t", a.toString(),
					b.toString(), c.toString());
		}

		Long t1, t2, t3;
		System.out.println("");
		System.out.printf("%40s\n", "   Time in Nanoseconds of Power" + number);
		System.out.printf("%7s %25s %15s %15s\n", "Power", "Built In", "myPower1", "myPower2");
		for (int i = 0; i <= maxNumber; i++) {
			start = System.nanoTime();
			number.pow(i);
			finish = System.nanoTime();
			t1 = finish - start;

			start = System.nanoTime();
			myPow1(number, i);
			finish = System.nanoTime();
			t2 = finish - start;

			start = System.nanoTime();
			myPow2(number, i);
			finish = System.nanoTime();
			t3 = finish - start;
			System.out.printf("|%-15s| %-15s| %-15s| %-15s|\n", "|" + number + "^(" + i + ")|\t", t1.toString(),
					t2.toString(), t3.toString());
		}
		System.out.println(
				"\nWe use " + Number.size() + " Numbers as BigInteger datatype to calculate the powers of " + number);
	}

	public static BigInteger myPow1(BigInteger a, int b) {
		BigInteger total = new BigInteger("1");
		while (b > 0) {
			total = total.multiply(a);
			b -= 1;
		}
		return total;
	}

	public static BigInteger myPow2(BigInteger a, int b) {
		if (b == 0) {
			return new BigInteger("1");
		}
		if (b % 2 == 0) {
			BigInteger temp = myPow2(a, b / 2);
			return temp.multiply(temp);
		} else {
			return myPow2(a, b - 1).multiply(a);
		}
	}

}