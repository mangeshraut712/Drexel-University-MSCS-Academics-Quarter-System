
public class Subtraction extends Component {

	Component left, right;

	public Subtraction(Component left, Component right) {
		this.left = left;
		this.right = right;
	}

	public Component getLeft() {
		return left;
	}

	public Component getRight() {
		return right;
	}

	@Override
	public Object getType() {
		return "subtraction";
	}

}
