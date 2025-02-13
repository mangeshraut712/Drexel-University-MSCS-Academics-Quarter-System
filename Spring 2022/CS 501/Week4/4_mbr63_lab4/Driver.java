import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	private ArrayList<Ingredients> ingredients;

	public void addIngredients(ArrayList<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}

	public void displayRecipe() {
		for (int i = 0; i < ingredients.size(); i++) {
			System.out.println(ingredients.get(i).toString());
		}
	}

	public double getTotalCalories() {
		double totalCalories = 0;
		for (int i = 0; i < ingredients.size(); i++) {
			totalCalories += ingredients.get(i).getCalories();
		}
		return totalCalories;
	}

	public void getIngredientsByType(String type) {
		System.out.println("Ingredients with type " + type);
		for (int i = 0; i < ingredients.size(); i++) {
			if (ingredients.get(i).getType().equals(type)) {
				System.out.println(ingredients.get(i));
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<Ingredients> ingredients = new ArrayList<>();
		Scanner keyboard = new Scanner(System.in);
		Driver driver = new Driver();

		String name = "na";
		String type = "an";
		int unit;
		double calories;
		while (true) {
			System.out.println("Enter a recipe name or DONE: ");
			name = keyboard.nextLine();
			if (name.equalsIgnoreCase("DONE")) {
				break;
			}
			System.out.println("Enter a type: ");
			type = keyboard.nextLine();
			System.out.println("Enter the unit: ");
			unit = keyboard.nextInt();
			keyboard.nextLine();
			System.out.println("Enter the calories: ");
			calories = keyboard.nextDouble();
			keyboard.nextLine();

			ingredients.add(new Ingredients(name, type, unit, calories));
		}
		driver.addIngredients(ingredients);
		driver.displayRecipe();

		System.out.println("Enter the type to get list of Ingredients by type");
		String IngredientsType = keyboard.nextLine();
		if (IngredientsType.equals("protein")) {
			driver.getIngredientsByType(IngredientsType);
		} else if (IngredientsType.equals("fat")) {
			driver.getIngredientsByType(IngredientsType);
		} else {
			driver.getIngredientsByType(IngredientsType);
		}

	}
}