import java.io.IOException;

public class MasterControl {

	Input input;
	CircularShifter circularShifter;
	Alphabetizer alphabetizer;
	Output output;

	public void start(Pipe pipe1, Pipe pipe2, Pipe pipe3) throws IOException, InterruptedException {
		input = new Input(null, pipe1);
		circularShifter = new CircularShifter(pipe1, pipe2);
		alphabetizer = new Alphabetizer(pipe2, pipe3);
		output = new Output(pipe3, null);
		input.start();
		circularShifter.start();
		alphabetizer.start();
		output.start();

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		new MasterControl().start(new PipeList(), new PipeQueue(), new PipeList());
	}

}