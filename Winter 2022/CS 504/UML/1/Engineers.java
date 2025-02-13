import java.util.ArrayList;
import java.util.List;

public class Engineers extends Corporation {

	public Engineers(String name, Double salary, Double bonus) {
		super(name, salary, bonus);

	}

	public List<String> engineersReport(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		ArrayList<String> engineersReportList = new ArrayList<String>();
		arbitraryStatisticsServiceWrapper.reportStatistics(100, 10, 90);
		return engineersReportList;

	}
}
