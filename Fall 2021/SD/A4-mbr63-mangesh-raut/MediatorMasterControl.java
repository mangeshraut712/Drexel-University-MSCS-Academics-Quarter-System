import java.io.FileNotFoundException;
import java.io.IOException;

public class MediatorMasterControl implements Mediator {
	Input input;
	CircularShifter circularShifter;
	Alphabetizer alphabetizer;
	Output output;
	Mediator mediator;

	public MediatorMasterControl() {
		input = new Input(this);
		circularShifter = new CircularShifter(this);
		alphabetizer = new Alphabetizer();
		output = new Output();

	}

	public void start() throws IOException {
		input.read();
	}

	@Override
	public void shiftedLine(String string) {
		alphabetizer.addToAlphabetizedSet(string);
	}

	@Override
	public void inputHasNextLine(String string) {
		circularShifter.shiftLine(string);

	}

	@Override
	public void inputIsFinished() throws FileNotFoundException {
		output.write(alphabetizer.getAlphabetizedLines());

	}

}
