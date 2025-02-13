
public class Subtraction extends Composite implements Component {

	public Subtraction(Component left, Component right) {
		super(left, right);
	}

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);

	}

}
