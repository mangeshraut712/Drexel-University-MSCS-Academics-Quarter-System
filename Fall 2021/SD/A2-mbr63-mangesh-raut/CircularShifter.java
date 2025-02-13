public class CircularShifter extends Subject implements Observer {

	public void shiftLines(String lines) {

		String[] line = lines.split(" ");
		int word = 0;
		while (word < line.length) {
			String lastWord = line[line.length - 1];
			this.innerShift(line);
			line[0] = lastWord;
			this.notifyAllObservers(String.join(" ", line));
			word++;
		}
	}

	private void innerShift(String[] line) {
		for (int i = line.length - 1; i > 0; i--) {
			line[i] = line[i - 1];
		}
	}

	@Override
	public void update(String string) {
		this.shiftLines(string);

	}
}
