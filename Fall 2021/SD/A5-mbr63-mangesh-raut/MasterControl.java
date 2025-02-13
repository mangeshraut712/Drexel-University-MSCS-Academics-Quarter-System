import java.io.IOException;

public class MasterControl {

	Input input;
	CircularShifter circularShifter;
	Alphabetizer alphabetizer;
	Output output;

	public void start() throws IOException {
		Pipe pipe1 = new Pipe();
		Pipe pipe2 = new Pipe();
		Pipe pipe3 = new Pipe();
		input = new Input(null, pipe1);
		circularShifter = new CircularShifter(pipe1, pipe2);
		alphabetizer = new Alphabetizer(pipe2, pipe3);
		output = new Output(pipe3, null);
		input.start();
		circularShifter.start();
		alphabetizer.start();
		output.start();

	}

	public static void main(String[] args) throws IOException {
		MasterControl masterControl = new MasterControl();
		masterControl.start();

	}
}