import java.math.BigInteger;

public class exponentTimings {
	public static void main(String[] args) {

		long start, finish;
		BigInteger a, b, c, number = new BigInteger("2");
		System.out.printf("|%40s|\n", "Powers of 2");
		System.out.printf("|%10s| %10s| %10s| %10s|\n", "Pow", "Built In", "myPow1", "myPow2");

		for (int i = 0; i < 21; i++) {
			a = number.pow(i);
			b = myPow1(number, i);
			c = myPow2(number, i);
			System.out.printf("|%-10s| %-10s| %-10s| %-10s|\n", "|2^(" + i + ")|\t", a.toString(), b.toString(),
					c.toString());
		}

		number = new BigInteger("5");
		System.out.println("");
		System.out.printf("|%40s|\n", "Powers of 5");
		System.out.printf("|%10s| %10s| %10s| %10s|\n", "Pow", "Built In", "myPow1", "myPow2");
		for (int i = 0; i < 21; i++) {
			a = number.pow(i);
			b = myPow1(number, i);
			c = myPow2(number, i);
			System.out.printf("|%-10s| %-10s| %-10s| %-10s|\n", "|5^(" + i + ")|\t", a.toString(), b.toString(),
					c.toString());
		}

		number = new BigInteger("9");
		Long t1, t2, t3;
		System.out.println("");
		System.out.printf("|%40s|\n", "   Time in Nanoseconds of 9^x   ");
		System.out.printf("|%10s| %10s| %10s| %10s|\n", "Pow", "Built In", "myPow1", "myPow2");
		for (int i = 1; i < 16385; i *= 2) {
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
			System.out.printf("|%-10s| %-10s| %-10s| %-10s|\n", "|9^(" + i + ")|\t", t1.toString(), t2.toString(),
					t3.toString());
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