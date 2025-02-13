import java.util.List;

public class Corporation {

	Sorts sorts = new Sorts();
	private String name;
	private Double bonus;
	private Double salary;

	public Corporation(String name, Double bonus, Double salary) {
		this.name = name;
		this.bonus = bonus;
		this.salary = salary;
	}

	public void start(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		ArbitraryStatisticsServiceFactory arbitraryStatisticsServiceFactory = new ArbitraryStatisticsServiceFactory(
				arbitraryStatisticsServiceWrapper);

		Employees engineers = arbitraryStatisticsServiceFactory.create(name);
		List<String> employeesEngineers = null;
		List<String> bubbleSortEngineerDetails = sorts.bubbleSort(employeesEngineers);
		List<String> shellSortEngineerDetails = sorts.shellSort(employeesEngineers);
		List<String> heapSortEngineerDetails = sorts.heapSort(employeesEngineers);
		Employees managers = arbitraryStatisticsServiceFactory.create(name);
		List<String> employeesManagers = null;
		List<String> bubbleSortManagersDetails = sorts.bubbleSort(employeesManagers);
		List<String> shellSortManagersDetails = sorts.shellSort(employeesManagers);
		List<String> heapSortManagersDetails = sorts.heapSort(employeesManagers);

	}

	public static void main(String args[]) {
		Corporation corporation = new Corporation(null, null, null);
		corporation.start(ArbitraryStatisticsServiceWrapper.getInstance());
	}

}