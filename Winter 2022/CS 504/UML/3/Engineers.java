import java.util.ArrayList;
import java.util.List;

public class Engineers implements Employees {
	ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper;

	public Engineers(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		this.arbitraryStatisticsServiceWrapper = arbitraryStatisticsServiceWrapper;
	}

	public List<String> engineersReport() {
		ArrayList<String> engineersReportList = new ArrayList<String>();
		arbitraryStatisticsServiceWrapper.reportStatistics(100, 10, 90);
		return engineersReportList;

	}

	@Override
	public List<String> readEmployeesData() {
		ArrayList<String> readEmployeesData = new ArrayList<String>();
		if (readEmployeesData.isEmpty()) {
			String engineersReportList = null;
			readEmployeesData.add(engineersReportList);
		}
		return readEmployeesData;
	}
}
