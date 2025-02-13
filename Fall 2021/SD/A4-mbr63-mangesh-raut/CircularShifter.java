public class CircularShifter extends Colleague {

	public CircularShifter(Mediator mediator) {
		this.mediator = mediator;
	}

	public void shiftLine(String string) {
		String[] line = string.split(" ");
		int word = 0;
		while (word < line.length) {
			String lastWord = line[line.length - 1];
			this.innerShift(line);
			line[0] = lastWord;
			mediator.shiftedLine(String.join(" ", line));
			word++;
		}
	}

	private void innerShift(String[] line) {
		for (int i = line.length - 1; i > 0; i--) {
			line[i] = line[i - 1];
		}
	}

}
