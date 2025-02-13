import java.util.ArrayList;
import java.util.List;

public class Input {

	public List<String> read(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
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