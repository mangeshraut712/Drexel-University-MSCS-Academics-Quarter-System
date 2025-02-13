/************************
 * Cafe.java Class definition of a caffienated drink
 * 
 * @author Tammy Pirmann
 * @version 20210414
 **************************/
public class Cafe {

	private String name;
	private int caffeine;
	private double price;

	public Cafe() {
		name = "Coffee";
		caffeine = 100;
		price = 1.50;
	}

	public Cafe(String n, int c, double p) {
		name = n;
		caffeine = c;
		price = p;
	}

	public String getName() {
		return name;
	}

	public int getCaffeine() {
		return caffeine;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return (name + ", " + caffeine + "mg of caffeine at $" + price);
	}
}