import java.io.IOException;

public class Corporation {

	Employee employee = new Employee("Apple", 100000.00, 1000.00);
	Engineers engineer = new Engineers("Gowtham", 10000.00, 1000.00);
	Managers manager = new Managers("Mangesh", 50000.00, 2500.00);

	public void start(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		SortingFactory sortingFactory = new SortingFactory();
		Double engineerSalary = employee.bonusCalculation();
		String employeeSalaryData = null;
		Sorting sorting = sortingFactory.create(employeeSalaryData);
		Double managerSalary = manager.bonusCalculation();
		arbitraryStatisticsServiceWrapper.reportStatistics(1000, 100, 1000);
	}

	public static void main(String[] args) throws IOException {
		Corporation corporation = new Corporation();
		corporation.start(ArbitraryStatisticsServiceWrapper.getInstance());
	}

}
