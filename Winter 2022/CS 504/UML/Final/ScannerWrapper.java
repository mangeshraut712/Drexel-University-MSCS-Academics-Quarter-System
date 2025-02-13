import java.util.Scanner;

public class ScannerWrapper {
	private Scanner scanner;

	private ScannerWrapper() {
		scanner = new Scanner(System.in);
	}

	public static ScannerWrapper getInstance() {
		return new ScannerWrapper();
	}

	public String nextLine() {
		return scanner.nextLine();
	}

}