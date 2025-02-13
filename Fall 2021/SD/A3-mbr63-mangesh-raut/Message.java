public class Message {
	String string;
	boolean trueFalse;

	public Message(String newString) {
		string = newString;
		trueFalse = false;
	}

	public Message(boolean newTrueFalse) {
		trueFalse = newTrueFalse;
		string = null;
	}

	public boolean isFinished() {
		return trueFalse;
	}

	public String getString() {
		if (this.string == null) {
			throw new IllegalStateException();
		}
		return this.string;
	}

}