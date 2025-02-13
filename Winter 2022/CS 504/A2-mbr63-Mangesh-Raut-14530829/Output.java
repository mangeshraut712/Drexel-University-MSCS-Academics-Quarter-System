import java.util.List;

public class Output {

	public void write(List<String> lines, SystemWrapper systemWrapper) {
		for (String string : lines) {
			systemWrapper.println(string);
		}

	}

}