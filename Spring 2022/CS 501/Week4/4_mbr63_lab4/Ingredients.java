public class Ingredients {

	private String name;
	private String type;
	private int unit;
	private double calories;

	public Ingredients() {
		name = "cereals";
		type = "protein";
		unit = 1;
		calories = 100;
	}

	public Ingredients(String n, String t, int u, double c) {
		name = n;
		type = t;
		unit = u;
		calories = c;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getUnit() {
		return unit;
	}

	public double getCalories() {
		return calories;
	}

	@Override
	public String toString() {
		return "Name: " + name + ",Type: " + type + ",units: " + unit + ",calories: " + calories;
	}

}