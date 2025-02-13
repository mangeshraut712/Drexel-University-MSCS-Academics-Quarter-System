import java.io.IOException;
import java.util.List;

public class MasterControl {
	List<String> lines;
	CircularShifter circularShifter = new CircularShifter();
	Alphabetizer alphabetizer = new Alphabetizer();

	public void start(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		InputFactory inputFactory = new InputFactory(scannerWrapper, systemWrapper);
		OutputFactory outputFactory = new OutputFactory(systemWrapper);
		systemWrapper.println("Please enter FILE to input from file or CONSOLE to input from console:");
		String inputFileOrConsole = scannerWrapper.nextLine();
		Input input = inputFactory.create(inputFileOrConsole);
		systemWrapper.println("Please enter FILE to output from file or CONSOLE to output from console:");
		String outputFileOrConsole = scannerWrapper.nextLine();
		Output output = outputFactory.create(outputFileOrConsole);
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