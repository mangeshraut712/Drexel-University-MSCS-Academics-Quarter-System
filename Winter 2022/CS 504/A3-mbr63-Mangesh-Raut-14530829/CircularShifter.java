import java.util.ArrayList;
import java.util.List;

public class CircularShifter {

	public List<String> shiftLines(List<String> lines) {
		List<String> rotateLine = new ArrayList<String>();
		for (String string : lines) {
			String[] line = string.split(" ");
			this.addShiftedLine(line, rotateLine);
		}
		return rotateLine;
	}

	private void addShiftedLine(String[] line, List<String> rotateLine) {
		int word = 0;
		while (word < line.length) {
			String lastWord = line[line.length - 1];
			this.innerShift(line);
			line[0] = lastWord;
			rotateLine.add(String.join(" ", line));
			word++;
		}

	}

	private void innerShift(String[] line) {

		for (int index = line.length - 1; index > 0; index--) {
			line[index] = line[index - 1];
		}

	}

}