import java.io.IOException;
import java.util.List;

public class MasterControl {

	Input input = new Input();
	CircularShifter circularShifter = new CircularShifter();
	Alphabetizer alphabetizer = new Alphabetizer();
	Output output = new Output();

	public void start() throws IOException {
		List<String> lines = input.read();
		List<String> shiftedList = circularShifter.shiftLines(lines);
		List<String> sortedList = alphabetizer.sort(shiftedList);
		output.write(sortedList);
	}

	public static void main(String[] args) throws IOException {
		MasterControl masterControl = new MasterControl();
		masterControl.start();

	}
}