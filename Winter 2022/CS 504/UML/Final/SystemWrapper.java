public class SystemWrapper {

	private SystemWrapper() {
	}

	public static SystemWrapper getInstance() {
		return new SystemWrapper();
	}

	public void println(String string) {
		System.out.println(string);
	}

}
