import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CircularShifter extends Subject implements Observer {
	List<String> rotateLine = new ArrayList<>();

	public void shiftLine(String line) {

		rotateLine.add(line);
		List<String> lineToList = new LinkedList<>(Arrays.asList(line.split(" ")));
		List<String> lineToShift = new ArrayList<>(lineToList);
		List<String> lineShifted = this.shiftLine(lineToList);
		this.shiftingLines(rotateLine, lineToShift, lineShifted);

	}

	private void shiftingLines(List<String> lines, List<String> lineToShift, List<String> lineShifted) {

		this.notifyAllObservers(listToString(lineToShift));
		while (!this.shiftingChecker(lineToShift, lineShifted)) {
			lines.add(this.listToString(lineShifted));
			this.notifyAllObservers(listToString(lineShifted));
			rotateLine = this.shiftLine(lineShifted);
		}
	}

	private boolean shiftingChecker(List<String> lineToShift, List<String> lineShifted) {
		if (lineToShift.equals(lineShifted))
			return true;
		return false;

	}

	private List<String> shiftLine(List<String> lineToShift) {
		String last = lineToShift.get(lineToShift.size() - 1);
		lineToShift.add(0, last);
		lineToShift.remove(lineToShift.size() - 1);
		return lineToShift;
	}

	private String listToString(List<String> lineShifted) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < lineShifted.size(); i++) {
			if (i != lineShifted.size() - 1) {
				stringBuffer.append(lineShifted.get(i));
				stringBuffer.append(" ");
			} else
				stringBuffer.append(lineShifted.get(i));
		}
		return stringBuffer.toString();
	}

	@Override
	public void update(String string) {
		shiftLine(string);

	}

}