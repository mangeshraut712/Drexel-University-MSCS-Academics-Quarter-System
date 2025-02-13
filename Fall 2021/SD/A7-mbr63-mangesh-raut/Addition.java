
public class Addition extends Component {
	Component left, right;

	public Addition(Component left, Component right) {
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
		return "addition";
	}

}