import java.util.ArrayList;
import java.util.List;

public class Managers extends Corporation {
	public Managers(String name, Double salary, Double bonus) {
		super(name, salary, bonus);
	}

	public List<String> managersReport(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		ArrayList<String> managersReportList = new ArrayList<String>();
		arbitraryStatisticsServiceWrapper.reportStatistics(10, 1, 9);
		return managersReportList;

	}
}
