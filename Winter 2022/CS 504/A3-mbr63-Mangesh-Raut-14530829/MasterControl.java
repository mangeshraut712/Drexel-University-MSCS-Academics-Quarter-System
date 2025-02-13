import java.io.IOException;
import java.util.List;

public class MasterControl {
	List<String> lines;
	Input input;
	Output output;
	CircularShifter circularShifter = new CircularShifter();
	Alphabetizer alphabetizer = new Alphabetizer();

	public void start(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		systemWrapper.println("Please enter FILE to input from file or CONSOLE to input from console:");
		String inputFileOrConsole = scannerWrapper.nextLine();
		if (inputFileOrConsole.equals("FILE")) {
			input = new InputFromFile();
		} else if (inputFileOrConsole.equals("CONSOLE")) {
			input = new InputFromConsole(scannerWrapper, systemWrapper);
		}
		systemWrapper.println("Please enter FILE to output from file or CONSOLE to output from console:");
		String outputFileOrConsole = scannerWrapper.nextLine();
		if (outputFileOrConsole.equals("FILE")) {
			output = new OutputToFile();
		} else if (outputFileOrConsole.equals("CONSOLE")) {
			output = new OutputToConsole(systemWrapper);
		}

		lines = input.read();
		List<String> shiftedList = circularShifter.shiftLines(lines);
		List<String> sortedList = alphabetizer.sort(shiftedList);
		output.write(sortedList);
	}

	public static void main(String[] args) throws IOException {
		MasterControl masterControl = new MasterControl();
		masterControl.start(ScannerWrapper.getInstance(), SystemWrapper.getInstance());

	}
}