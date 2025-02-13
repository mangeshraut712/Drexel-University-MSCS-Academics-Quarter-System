import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input extends Filter {
	public Input(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
	}

	@Override
	void filter() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("kwic.txt"));
		while (scanner.hasNextLine()) {
			String string = scanner.nextLine();
			outPipe.write(string);
		}
		scanner.close();
		outPipe.close();
		this.stop();

	}
}
