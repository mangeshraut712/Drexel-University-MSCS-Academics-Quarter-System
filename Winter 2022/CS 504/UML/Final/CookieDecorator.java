public class CookieDecorator extends Cookie {

	Cookie cookie;

	CookieDecorator(Cookie cookie) {
		super(cookie.cookieTotal);
		this.cookie = cookie;
	}

	@Override
	double getBase() {
		return cookie.getBase();
	}

	@Override
	double getContent() {
		return cookie.getContent();
	}

	@Override
	double getShape() {
		return cookie.getShape();
	}

	@Override
	double calculateBaseValue() {
		return cookie.calculateBaseValue();
	}

	@Override
	double calculateShapeValue() {
		return cookie.calculateShapeValue();
	}

	@Override
	double calculateContentValue() {
		return cookie.calculateContentValue();
	}

}
