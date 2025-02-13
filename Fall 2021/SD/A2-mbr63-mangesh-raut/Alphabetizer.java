import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Alphabetizer implements Observer {
	TreeSet<String> treeSet;

	public Alphabetizer() {
		treeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	}

	public List<String> getAlphabetizedLines() {
		ArrayList<String> list = new ArrayList<String>(treeSet);
		return list;
	}

	@Override
	public void update(String string) {
		treeSet.add(string);
	}
}