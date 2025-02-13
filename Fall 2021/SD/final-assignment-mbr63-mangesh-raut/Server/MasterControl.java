import java.util.ArrayList;
import java.util.List;

public class MasterControl {

	public List<String> start(ArrayList<String> clientInput) {
		Input input = new Input();
		CircularShifter circularShifter = new CircularShifter();
		Alphabetizer alphabetizer = new Alphabetizer();

		input.attach(circularShifter);
		circularShifter.attach(alphabetizer);
		input.read(clientInput);
		List<String> lines = alphabetizer.getAlphabetizedLines();
		return lines;

	}
}
