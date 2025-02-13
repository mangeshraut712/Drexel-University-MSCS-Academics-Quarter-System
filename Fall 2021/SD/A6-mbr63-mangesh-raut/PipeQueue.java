import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PipeQueue extends Pipe {
	Queue<String> queue;

	public PipeQueue() {
		queue = new ConcurrentLinkedQueue<>();
		closed = false;
	}

	@Override
	void write(String string) {
		queue.offer(string);
	}

	@Override
	String read() {
		return queue.poll();
	}

	@Override
	boolean isNotEmptyOrIsNotClosed() {
		return !queue.isEmpty() || !closed;
	}

	@Override
	boolean hasNext() {
		return !queue.isEmpty();
	}

}
