import java.io.FileNotFoundException;

public interface Mediator {

	public void shiftedLine(String string);

	public void inputHasNextLine(String string);

	public void inputIsFinished() throws FileNotFoundException;

}
