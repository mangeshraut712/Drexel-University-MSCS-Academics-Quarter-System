
public class InputFactory {

	ScannerWrapper scannerWrapper;
	SystemWrapper systemWrapper;

	public InputFactory(ScannerWrapper scannerWrapper, SystemWrapper systemWrapper) {
		this.scannerWrapper = scannerWrapper;
		this.systemWrapper = systemWrapper;
	}

	public Input create(String string) {
		switch (string) {
		case "FILE":
			return new InputFromFile();
		case "CONSOLE":
			return new InputFromConsole(scannerWrapper, systemWrapper);
		default:
			throw new IllegalArgumentException();

		}
	}

}
