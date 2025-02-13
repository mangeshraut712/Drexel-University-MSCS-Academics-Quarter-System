
public class SortingFactory {
	public SortingFactory() {

	}

	public Sorting create(String string) {
		switch (string) {
		case "BubbleSort":
			return new BubbleSort();
		case "ShellSort":
			return new ShellSort();
		case "HeapSort":
			return new HeapSort();
		default:
			throw new IllegalArgumentException();

		}
	}

}
