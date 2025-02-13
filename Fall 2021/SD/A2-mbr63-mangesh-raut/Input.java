import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input extends Subject {

	public void read() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("kwic.txt"));
		while (scanner.hasNextLine()) {
			this.notifyAllObservers(scanner.nextLine());
		}
		scanner.close();
	}
}
