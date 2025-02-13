
public class Number implements Component {

	public int number;

	public Number(int number) {
		this.number = number;
	}

	public int getValue() {
		return number;
	}

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);

	}

	public int visit(CalculatorVisitor calculatorVisitor) {
		return number;
	}

}
