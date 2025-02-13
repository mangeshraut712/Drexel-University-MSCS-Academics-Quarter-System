import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class OutputToFile implements Output {
	PrintWriter printWriter;

	@Override
	public void write(List<String> lines) {
		try {
			printWriter = new PrintWriter("kwic_output.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (String string : lines) {
			printWriter.write(string + "\n");
		}
		printWriter.close();
	}

}
