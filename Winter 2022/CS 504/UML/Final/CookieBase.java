import java.util.ArrayList;

public class CookieBase implements CookieDetails {
	double regular;
	double glutenFree;
	double vegan;
	ScannerWrapper scannerWrapper;
	SystemWrapper systemWrapper;

	public CookieBase(double regular, double glutenFree, double vegan, ScannerWrapper scannerWrapper,
			SystemWrapper systemWrapper) {
		this.regular = regular;
		this.glutenFree = glutenFree;
		this.vegan = vegan;
		this.scannerWrapper = scannerWrapper;
		this.systemWrapper = systemWrapper;
	}

	public Double setCookieBaseValue() {
		regular = 1.00;
		glutenFree = 1.25;
		vegan = 1.50;
		return setCookieBaseValue();
	}

	public Double selectCookieBase(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		ArrayList<String> CookieOrder = new ArrayList<String>();
		systemWrapper.println("Select the base of the cookie");
		String cookieBase = scannerWrapper.nextLine();
		systemWrapper.println("CookieBaseValue: ");
		CookieOrder.add(cookieBase);
		Double baseValue = setCookieBaseValue();
		return baseValue;
	}

	@Override
	public String getCookieDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
