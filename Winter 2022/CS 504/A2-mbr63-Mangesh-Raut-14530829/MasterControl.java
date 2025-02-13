import java.io.IOException;
import java.util.List;

public class MasterControl {

	Input input = new Input();
	CircularShifter circularShifter = new CircularShifter();
	Alphabetizer alphabetizer = new Alphabetizer();
	Output output = new Output();

	public void start(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		List<String> lines = input.read(scannerWrapper, systemWrapper);
		List<String> shiftedList = circularShifter.shiftLines(lines);
		List<String> sortedList = alphabetizer.sort(shiftedList);
		output.write(sortedList, systemWrapper);
	}

	public static void main(String[] args) throws IOException {
		MasterControl masterControl = new MasterControl();
		masterControl.start(ScannerWrapper.getInstance(), SystemWrapper.getInstance());

	}
}