import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Output implements Observer {

	PrintWriter printWriter;

	public Output() {
		try {
			printWriter = new PrintWriter("kwic_output.txt");
		} catch (FileNotFoundException exception) {

		}

	}

	@Override
	public void update(Message message) {

		if (!message.isFinished()) {
			printWriter.write(message.getString() + "\n");
		} else {
			printWriter.close();
		}
	}
}