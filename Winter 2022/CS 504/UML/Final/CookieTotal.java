import java.util.ArrayList;

public class CookieTotal {
	CookieContext cookieContext;

	public CookieTotal(CookieContext cookieContext) {
		this.cookieContext = cookieContext;
	}

	public Cookie totalBill(Cookie warriorOne, Cookie warriorTwo) {
		int count = 0;
		ArrayList<CookieState> states = cookieContext.getCookieState();
		for (int i = 0; i < states.size(); i++) {
			Cookie winner = states.get(i).orderedCookieDetails(warriorOne, warriorTwo);
			if (winner.equals(warriorOne))
				count++;
		}

		return count >= 3 ? warriorOne : warriorTwo;
	}
}
