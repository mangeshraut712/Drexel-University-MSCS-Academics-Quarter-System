import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
	public List<String> read() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("kwic.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			list.add(scanner.nextLine());
		}
		scanner.close();
		return list;
	}
}
