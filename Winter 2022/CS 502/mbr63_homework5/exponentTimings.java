import java.math.BigInteger;

//import BigInteger math library
public class exponentTimings {
//an ordering of the functions from fastest to slowest.

	// Method 1: The Built In Exponent
	static BigInteger builtIn(BigInteger a, int b) {
		BigInteger x = a; // BigInteger x = 2
		BigInteger y = x.pow(b); // BigInteger y = power of x
		return y;
	}

	// Method 2: myPow1
	static BigInteger myPow1(BigInteger a, int b) { // myPow1 method
		BigInteger total = new BigInteger("1"); // BigInteger total = 1
		while (b > 0) { // while loop
			total = total.multiply(a); // total *= a
			b = b - 1; // integer b -=1
		}
		return total; // return total value
	}

	// Method 3: myPow2
	static BigInteger myPow2(BigInteger a, int b) { // myPow2 method
		if (b == 0) { // if condition to check b is 0 or not
			BigInteger one = new BigInteger("1"); // BigInteger one = 1
			return one; // return value one
		}
		if (b % 2 == 0) { // if condition to check even number
			BigInteger temp = new BigInteger("0"); // BigInteger temp = 0
			temp = myPow2(a, b / 2); // temp = myPow2(a, b/2)
			return temp.multiply(temp); // return if condition value
		} else { // else condition
			return a.multiply(myPow2(a, b - 1)); // return else condition value
		}
	}

	// Main Function
	public static void main(String args[]) throws Exception {
		System.out.println("Power of 2");
		for (int i = 0; i <= 20; i++) {
			BigInteger a = new BigInteger("2"); // Value of BigInteger is 2 which is base
			int b = i; // Value of integer b is i which is power
			System.out.println("\noutput of build in exponent\n");
			System.out.println(builtIn(a, b)); // output of build in exponent

			System.out.println("\noutput of method 1\n");
			System.out.println(myPow1(a, b)); // output of method 1

			System.out.println("\noutput of method 2\n");
			System.out.println(myPow2(a, b)); // output of method 2

		}
		System.out.println("\nPower of 5");
		for (int i = 0; i <= 20; i++) {
			BigInteger a = new BigInteger("5"); // Value of BigInteger is 2 which is base
			int b = i; // Value of integer b is i which is power
			System.out.println("\noutput of build in exponent\n");
			System.out.println(builtIn(a, b)); // output of build in exponent

			System.out.println("\noutput of method 1\n");
			System.out.println(myPow1(a, b)); // output of method 1

			System.out.println("\noutput of method 2\n");
			System.out.println(myPow2(a, b)); // output of method 2

		}

		System.out.println("\nTime in Nanoseconds of 9^x");
		for (int i = 0; i <= 14; i++) {
			BigInteger a = new BigInteger("2"); // Value of BigInteger is 2 which is base
			int b = i; // Value of integer b is i which is power
			BigInteger power = builtIn(a, b); // output of build in exponent
			int exponent = power.intValue();

			BigInteger x = new BigInteger("9"); // Value of BigInteger is 2 which is base
			int y = exponent; // Value of integer b is i which is power

			System.out.println("\noutput of build in exponent\n");
			long timeA = System.nanoTime(); // time A before function execution
			builtIn(x, y); // output of build in exponent
			long timeB = System.nanoTime(); // time B after function execution
			System.out.println(timeB - timeA); // difference of time A and B

			System.out.println("\noutput of method 1\n");
			long timeC = System.nanoTime(); // time C before function execution
			myPow1(x, y); // output of method 1
			long timeD = System.nanoTime(); // time D before function execution
			System.out.println(timeD - timeC); // difference of time C and D

			System.out.println("\noutput of method 2\n");
			long timeE = System.nanoTime(); // time E before function execution
			myPow2(x, y); // output of method 2
			long timeF = System.nanoTime(); // time F before function execution
			System.out.println(timeF - timeE); // difference of time E and F
		}

	}

}
