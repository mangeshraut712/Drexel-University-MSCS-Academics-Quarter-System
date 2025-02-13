import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Output {
	public void write(List<String> lines) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter("kwic_output.txt");
		for (String string : lines) {
			printWriter.write(string + "\n");
		}
		printWriter.close();

	}

}