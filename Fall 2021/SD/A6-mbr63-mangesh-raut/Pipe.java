public abstract class Pipe {
	boolean closed;

	abstract void write(String string);

	abstract String read();

	abstract boolean isNotEmptyOrIsNotClosed();

	abstract boolean hasNext();

	void close() {
		closed = true;
	}
}
