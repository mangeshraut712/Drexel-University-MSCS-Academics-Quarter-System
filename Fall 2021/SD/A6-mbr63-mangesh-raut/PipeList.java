import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PipeList extends Pipe {
	List<String> list = Collections.synchronizedList(new ArrayList<>());

	public PipeList() {
		closed = false;
	}

	@Override
	void write(String string) {
		list.add(string);
	}

	@Override
	String read() {
		String readString = list.get(0);
		list.remove(0);
		return readString;
	}

	@Override
	boolean isNotEmptyOrIsNotClosed() {
		return !list.isEmpty() || !closed;
	}

	@Override
	boolean hasNext() {
		return !list.isEmpty();
	}

}
