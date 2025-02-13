
public class CookieShapeFactory {

	public CookieShape create(String choice) {
		switch (choice) {
		case "CIRCLE":
			return new Circle();
		case "STAR":
			return new Star();
		case "HEART":
			return new Heart();
		default:
			throw new IllegalArgumentException();
		}
	}

}
