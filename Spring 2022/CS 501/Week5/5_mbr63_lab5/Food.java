import java.util.ArrayList;

public class Food {
	ArrayList<String> items = new ArrayList<String>();
	ArrayList<String> food = new ArrayList<String>();
	ArrayList<String> nonfood = new ArrayList<String>();

	public ArrayList<String> getItems() {
		items.add("Milk");
		items.add("Paper Towels");
		items.add("Oreos");
		items.add("Doritos");
		items.add("Scissors");
		items.add("Yogurt");
		items.add("Canned beans");
		items.add("Lighter");
		return items;
	}

	public ArrayList<String> getfooditems() {
		food.add("Milk");
		food.add("Oreos");
		food.add("Doritos");
		food.add("Yogurt");
		food.add("Canned beans");
		return food;
	}

	public ArrayList<String> getnonfooditems() {
		nonfood.add("Paper Towels");
		nonfood.add("Scissors");
		nonfood.add("Lighter");
		return nonfood;
	}
}
