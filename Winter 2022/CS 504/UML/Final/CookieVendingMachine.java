import java.io.IOException;
import java.util.List;

public class CookieVendingMachine {
	CookieDetails cookieDetails;
	Currency currency;
	Cookie cookie;

	public List<String> machine() {
		Administrator administrator = null;
		return administrator.requestedData();
	}

	public void start(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		Administrator administrator = new Administrator(null);
		CookieBase cookieBase = new CookieBase(0, 0, 0, null, null);
		CookieContents cookieContents = new CookieContents(0, 0, 0, scannerWrapper, systemWrapper);
		cookieBase.selectCookieBase(scannerWrapper, systemWrapper);
		cookieContents.selectCookieContents(scannerWrapper, systemWrapper);
		CookieShapeFactory cookieShapeFactory = new CookieShapeFactory();
		systemWrapper.println("Please enter FILE to input from file or CONSOLE to input from console:");
		String inputFileOrConsole = scannerWrapper.nextLine();
		CookieShape cookieShape = cookieShapeFactory.create(inputFileOrConsole);
		systemWrapper.println("Please enter FILE to output from file or CONSOLE to output from console:");
		String outputFileOrConsole = scannerWrapper.nextLine();
		int coinAmount = currency.currencyAmount();
		Coin coin = new Coin(coinAmount, coinAmount, coinAmount, coinAmount);
		coin.coinValue(null);
		CoinBundle.coinBundleValue(null);
		cookie.calculateTotal();
	}

	public static void main(String[] args) throws IOException {
		CookieVendingMachine cookieVendingMachine = new CookieVendingMachine();
		cookieVendingMachine.machine();
		cookieVendingMachine.start(ScannerWrapper.getInstance(), SystemWrapper.getInstance());

	}
}
