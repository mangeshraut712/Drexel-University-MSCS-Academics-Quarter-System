import java.util.Scanner;

public class Calorie {
	public static void main(String args[]) {
		double height_in_meters;
		double weight_in_kg;
		double walking_velocity_in_ms;
		double WEIGHT_MODIFIER = 0.035;
		double HEIGHT_MODIFIER = 0.029;
		double Calories_burned_per_minute;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter the calories to burn = ");
			double burn_calories = scanner.nextDouble();
			System.out.println("Enter the height = ");
			height_in_meters = scanner.nextDouble();
			System.out.println("Enter the weight = ");
			weight_in_kg = scanner.nextDouble();
			System.out.println("walking_velocity_in_meter/seconds = ");
			walking_velocity_in_ms = scanner.nextDouble();
			double walking_velocity_square = Math.pow(walking_velocity_in_ms, 2);
			Calories_burned_per_minute = (WEIGHT_MODIFIER * weight_in_kg)
					+ ((walking_velocity_square) / height_in_meters) * HEIGHT_MODIFIER * weight_in_kg;
			System.out.println("Calories_burned_per_minute =" + Calories_burned_per_minute
					+ " Calories_to_burned_in_Minutes =" + (int) Math.ceil(burn_calories / Calories_burned_per_minute));
		}
	}
}
