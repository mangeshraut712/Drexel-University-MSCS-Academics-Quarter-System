import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Output extends Filter {
	PrintWriter printWriter;

	public Output(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
		try {
			printWriter = new PrintWriter("kwic_output.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	void filter() {

		while (inPipe.isNotEmptyOrIsNotClosed()) {
			if (inPipe.hasNext()) {
				printWriter.write(inPipe.read() + "\n");

			}

		}
		printWriter.close();
		this.stop();

	}
}