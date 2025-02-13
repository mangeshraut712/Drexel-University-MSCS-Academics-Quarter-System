import java.util.Random;
import java.util.Scanner;

public class Identity {

	public static void main(String args[]) {

		Random randGen = new Random();
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter the first name");
		String first = keyboard.nextLine();
		System.out.println("Enter the last name");
		String last = keyboard.nextLine();

		System.out.println("First name :" + first);
		System.out.println("Last name :" + last);
		int randomNumber = randGen.nextInt(900) + 100;

		String userId = "";
		char firstInitial = first.toLowerCase().charAt(0);
		char lastInitial = last.toLowerCase().charAt(0);
		userId += randomNumber;

		System.out.printf("UserId: %c%c%d", firstInitial, lastInitial, randomNumber);

	}
}