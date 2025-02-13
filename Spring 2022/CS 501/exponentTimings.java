import java.math.BigInteger;

public class exponentTimings {
	public static void main(String[] args) {

		long start, finish;
		BigInteger a, b, c, number = new BigInteger("2");
		System.out.printf("%40s\n", "Powers of 2");
		System.out.printf("%7s %25s %15s %15s\n", "Power", "Built In", "myPower1", "myPower2");

		for (int i = 0; i < 21; i++) {
			a = number.pow(i);
			b = myPow1(number, i);
			c = myPow2(number, i);
			System.out.printf("|%-15s| %-15s| %-15s| %-15s|\n", "|2^(" + i + ")|\t", a.toString(), b.toString(),
					c.toString());
		}

		number = new BigInteger("5");
		System.out.println("");
		System.out.printf("\n\n%40s\n", "Powers of 5");
		System.out.printf("%7s %25s %15s %15s\n", "Power", "Built In", "myPower1", "myPower2");
		for (int i = 0; i < 21; i++) {
			a = number.pow(i);
			b = myPow1(number, i);
			c = myPow2(number, i);
			System.out.printf("|%-15s| %-15s| %-15s| %-15s|\n", "|5^(" + i + ")|\t", a.toString(), b.toString(),
					c.toString());
		}

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