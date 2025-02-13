import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircularShifter extends Filter {

	public CircularShifter(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
	}

	public List<String> shiftLines(String lines) {
		List<String> rotateLine = new ArrayList<String>();
		String[] line = lines.split(" ");
		int word = 0;
		while (word < line.length) {
			String lastWord = line[line.length - 1];
			this.innerShift(line);
			line[0] = lastWord;
			rotateLine.add(String.join(" ", line));
			word++;
		}
		return rotateLine;

	}

	private void innerShift(String[] line) {

		for (int i = line.length - 1; i > 0; i--) {
			line[i] = line[i - 1];
		}

	}

	private void stringListMethod() {
		List<String> stringList = shiftLines(inPipe.read());
		for (String string : stringList) {
			outPipe.write(string);
		}
	}

	@Override
	void filter() throws IOException {
		while (inPipe.isNotEmptyOrIsNotClosed()) {
			if (inPipe.hasNext())
				this.stringListMethod();

		}
		outPipe.close();
		this.stop();

	}

}