import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFromFile implements Input {
	Scanner scanner;

	@Override
	public List<String> read() {
		try {
			scanner = new Scanner(new File("kwic.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			list.add(scanner.nextLine());
		}
		scanner.close();
		return list;
	}
}
