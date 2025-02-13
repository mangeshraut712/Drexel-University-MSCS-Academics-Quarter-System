import java.util.ArrayList;
import java.util.TreeSet;

public class Alphabetizer extends Subject implements Observer {
	TreeSet<String> treeSet;

	public Alphabetizer() {
		treeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	}

	@Override
	public void update(Message message) {
		ArrayList<String> list;
		if (!message.isFinished()) {
			treeSet.add(message.getString());

		} else {

			list = new ArrayList<String>(treeSet);
			for (int i = 0; i < list.size(); i++) {
				Message stringMessage = new Message(list.get(i));
				this.notifyAllObservers(stringMessage);

			}
			Message booleanMessage = new Message(true);
			this.notifyAllObservers(booleanMessage);

		}

	}

}