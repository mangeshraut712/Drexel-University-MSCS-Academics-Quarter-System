import java.util.TreeSet;

public class Alphabetizer extends Filter {
	TreeSet<String> treeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

	public Alphabetizer(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
	}

	@Override
	public void filter() {

		while (inPipe.isNotEmptyOrIsNotClosed()) {
			if (inPipe.hasNext()) {

				treeSet.add(inPipe.read());
			}

		}

		for (String string : treeSet) {
			outPipe.write(string);

		}
		outPipe.close();
		this.stop();

	}

}