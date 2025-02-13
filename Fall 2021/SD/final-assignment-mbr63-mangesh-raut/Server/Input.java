import java.util.ArrayList;

public class Input extends Subject {

	public void read(ArrayList<String> input) {
		for (String line : input) {
			this.notifyAllObservers(line);
		}

	}
}