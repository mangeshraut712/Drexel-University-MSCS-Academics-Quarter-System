import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input extends Colleague {
	ArrayList<String> line = new ArrayList<String>();
	boolean isFinished;
	Scanner scanner;

	public Input(Mediator mediator) {
		this.mediator = mediator;
	}

	public void read() throws FileNotFoundException {

		try {
			scanner = new Scanner(new File("kwic.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			String string = scanner.nextLine();
			mediator.inputHasNextLine(string);
			line.add(string);
		}
		scanner.close();
		mediator.inputIsFinished();

	}

}