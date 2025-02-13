import java.util.ArrayList;
import java.util.List;

public class InputFromConsole implements Input {
	ScannerWrapper scannerWrapper;
	SystemWrapper systemWrapper;

	public InputFromConsole(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		this.scannerWrapper = scannerWrapper;
		this.systemWrapper = systemWrapper;
	}

	@Override
	public List<String> read() {
		ArrayList<String> list = new ArrayList<String>();
		systemWrapper.println("Please enter lines to add, then enter -1 to finish:");
		while (true) {
			String line = scannerWrapper.nextLine();
			if (line.contains("-1")) {
				break;
			}
			list.add(line);
		}
		return list;
	}

}
