import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Alphabetizer {
	public List<String> sort(List<String> lines) {

		TreeSet<String> treeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		treeSet.addAll(lines);
		ArrayList<String> list = new ArrayList<String>(treeSet);
		return list;

	}
}
