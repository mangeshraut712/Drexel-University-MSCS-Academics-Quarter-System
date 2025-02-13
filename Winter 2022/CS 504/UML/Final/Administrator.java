import java.util.List;

public class Administrator extends CookieDecorator {

	Administrator(Cookie cookie) {
		super(cookie);
	}

	String commaDelimited, tabDelimited, otherDelimited;
	List<String> soldCookiesData;

	public List<String> requestedData() {
		return soldCookiesData;
	}

}
