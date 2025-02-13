import java.util.ArrayList;
import java.util.List;

public class Managers implements Employees {
	ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper;

	public Managers(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		this.arbitraryStatisticsServiceWrapper = arbitraryStatisticsServiceWrapper;
	}

	public List<String> managersReport() {
		ArrayList<String> managersReportList = new ArrayList<String>();
		arbitraryStatisticsServiceWrapper.reportStatistics(10, 1, 9);
		return managersReportList;

	}

	@Override
	public List<String> readEmployeesData() {
		ArrayList<String> readEmployeesData = new ArrayList<String>();
		if (readEmployeesData.isEmpty()) {
			String managersReportList = null;
			readEmployeesData.add(managersReportList);
		}
		return readEmployeesData;
	}
}
