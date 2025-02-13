import java.util.ArrayList;

public class CookieContents implements CookieDetails {
	double raisins;
	double walnuts;
	double chocolateChips;
	int darkPercentage;
	ScannerWrapper scannerWrapper;
	SystemWrapper systemWrapper;

	public CookieContents(double raisins, double walnuts, double chocolateChips, ScannerWrapper scannerWrapper,
			SystemWrapper systemWrapper) {
		this.raisins = raisins;
		this.walnuts = walnuts;
		this.chocolateChips = chocolateChips;
		this.scannerWrapper = scannerWrapper;
		this.systemWrapper = systemWrapper;
	}

	public Double setCookieCookieContents() {
		raisins = 0.50;
		walnuts = 0.50;
		chocolateChips = 0.25;
		return setCookieCookieContents();
	}

	public Double selectCookieContents(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		ArrayList<String> CookieOrder = new ArrayList<String>();
		systemWrapper.println("Select the contents of the cookie");
		String cookieContents = scannerWrapper.nextLine();
		systemWrapper.println("CookieBaseValue: ");
		CookieOrder.add(cookieContents);
		Double contentValue = setCookieCookieContents();
		return contentValue;
	}

	@Override
	public String getCookieDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
