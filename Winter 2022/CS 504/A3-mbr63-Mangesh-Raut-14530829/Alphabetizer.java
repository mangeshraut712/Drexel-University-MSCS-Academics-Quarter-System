import java.util.ArrayList;
import java.util.List;

public class Alphabetizer {

	public List<String> sort(List<String> lines) {
		int count = lines.size();
		String[] arrayLines = putLinesInArray(lines, count);
		bubbleSort(arrayLines, count);
		List<String> sortedLines = new ArrayList<String>();
		for (int i = 0; i <= count - 1; i++) {
			sortedLines.add(arrayLines[i]);
		}

		return sortedLines;
	}

	public String[] putLinesInArray(List<String> lines, int count) {
		int index = 0;
		String arrayLines[] = new String[count];
		for (String line : lines) {
			arrayLines[index] = line;
			index++;
		}
		return arrayLines;
	}

	public void bubbleSort(String arrayLines[], int count) {
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (arrayLines[i].compareToIgnoreCase(arrayLines[j]) > 0) {
					String swapString = arrayLines[i];
					arrayLines[i] = arrayLines[j];
					arrayLines[j] = swapString;
				}
			}
		}
	}
}
