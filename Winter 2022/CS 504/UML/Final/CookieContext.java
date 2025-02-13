import java.util.ArrayList;

public class CookieContext {
	ArrayList<CookieState> states = new ArrayList<CookieState>();

	public CookieContext() {
		states.add(new CookieDistributed());
		states.add(new WaitingForDeposit());
		states.add(new CurrencyRefund());
	}

	public ArrayList<CookieState> getCookieState() {
		return states;
	}
}
