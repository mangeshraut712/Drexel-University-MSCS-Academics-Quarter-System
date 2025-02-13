
public abstract class Composite {
	Component left, right;

	public Composite(Component left, Component right) {
		this.left = left;
		this.right = right;
	}

	public Component getLeft() {
		return left;
	}

	public Component getRight() {
		return right;
	}

}
