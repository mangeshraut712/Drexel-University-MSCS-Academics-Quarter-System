
public class ArbitraryStatisticsServiceFactory {
	ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper;

	public ArbitraryStatisticsServiceFactory(ArbitraryStatisticsServiceWrapper arbitraryStatisticsServiceWrapper) {
		this.arbitraryStatisticsServiceWrapper = arbitraryStatisticsServiceWrapper;
	}

	public Employees create(String string) {
		switch (string) {
		case "EngineerData":
			return new Engineers(arbitraryStatisticsServiceWrapper);
		case "ManagerData":
			return new Managers(arbitraryStatisticsServiceWrapper);
		default:
			throw new IllegalArgumentException();

		}
	}

}
