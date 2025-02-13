import java.util.List;

public class Corporation {

	Sorts sorts = new Sorts();
	Engineers engineers = new Engineers(null, null, null);
	Managers managers = new Managers(null, null, null);
	private String name;
	private Double bonus;
	private Double salary;

	public Corporation(String name, Double bonus, Double salary) {
		this.name = name;
		this.bonus = bonus;
		this.salary = salary;
	}

	public void start(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		List<String> engineerDetails = engineers.engineersReport(arbitraryStatisticsServiceWrapper);
		List<String> bubbleSortEngineerDetails = sorts.bubbleSort(engineerDetails);
		List<String> shellSortEngineerDetails = sorts.shellSort(engineerDetails);
		List<String> heapSortEngineerDetails = sorts.heapSort(engineerDetails);

		List<String> managersDetails = managers.managersReport(arbitraryStatisticsServiceWrapper);
		List<String> bubbleSortManagersDetails = sorts.bubbleSort(managersDetails);
		List<String> shellSortManagersDetails = sorts.shellSort(managersDetails);
		List<String> heapSortManagersDetails = sorts.heapSort(managersDetails);

	}

	public static void main(String args[]) {
		Corporation corporation = new Corporation(null, null, null);
		corporation.start(ArbitraryStatisticsServiceWrapper.getInstance());
	}

}