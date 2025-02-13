
public class Number extends Component {

	public int number;

	public Number(int number) {
		this.number = number;
	}

	public int getValue() {
		return number;
	}

	@Override
	public Object getType() {
		return "number";
	}

}
