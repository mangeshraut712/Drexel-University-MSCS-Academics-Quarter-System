import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Alphabetizer extends Subject implements Observer {
	TreeSet<String> treeSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

	public List<String> getAlphabetizedLines() {
		return treeSetToString(treeSet);

	}

	private static List<String> treeSetToString(TreeSet<String> treeSet) {
		List<String> lines = new ArrayList<>();
		for (String line : treeSet) {
			lines.add(line);
		}
		return lines;
	}

	@Override
	public void update(String string) {
		treeSet.add(string);

	}
}