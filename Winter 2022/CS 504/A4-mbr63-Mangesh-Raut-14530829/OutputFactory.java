
public class OutputFactory {

	SystemWrapper systemWrapper;

	public OutputFactory(SystemWrapper systemWrapper) {
		this.systemWrapper = systemWrapper;
	}

	public Output create(String string) {

		OutputType outputType = OutputType.valueOf(string);

		return outputType.getInstance(systemWrapper);

	}

}
