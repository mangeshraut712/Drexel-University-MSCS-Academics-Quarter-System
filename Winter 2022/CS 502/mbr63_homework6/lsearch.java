import java.util.ArrayList;
import java.util.Scanner;

public class lsearch {
	public static void main(String args[]) {
		String alphabet[] = { " ", "!", ".", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String letter = "";
		ArrayList<String> list = new ArrayList<String>();
		int counter = 0;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Are you thinking of the letter" + "'" + alphabet[counter] + "'" + "?");
			letter = scanner.nextLine();
			if (letter.equals("y")) {
				if (alphabet[counter].equals("!")) {
					break;
				} else {
					list.add(alphabet[counter]);
					counter = 0;
					continue;
				}
			}
			if (counter == alphabet.length - 1) {
				counter = 0;
			} else {
				counter++;
			}

		}
		String result = String.join("", list);
		System.out.println("You typed:\n" + result);
	}
}
