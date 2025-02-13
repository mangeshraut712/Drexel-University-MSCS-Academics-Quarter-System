import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/************************************
 * TestPrep.java Runs a little game involving caffeine
 * 
 * @author Tammy Pirmann
 * @version 20210414
 ********************************/

public class TestPrep {
	public static void main(String args[]) {
		Scanner keyboard = new Scanner(System.in);
		Random randGen = new Random();
		ArrayList<Cafe> drinks = new ArrayList<Cafe>();

		System.out.println("It's time to get ready for midterms!");
		System.out.println("You know you have to be alert for study sessions.");
		System.out.println("Caffeinated beverages from your favorite cafe will work.");
		System.out.println("We all like different drinks, so get ready to enter yours.");
		System.out.println("You will need the name, the mg of caffeine and the price.");
		System.out.println("When you are done, enter DONE then 0 then 0.");

		String name = "na";
		int caffeine;
		double price;
		while (true) {
			System.out.println("Enter a drink name or DONE: ");
			name = keyboard.nextLine();
			if (name.equalsIgnoreCase("DONE")) {
				break;
			}
			System.out.println("Enter the caffeine in mg: ");
			caffeine = keyboard.nextInt();
			System.out.println("Enter the price: ");
			price = keyboard.nextDouble();
			keyboard.nextLine();
			drinks.add(new Cafe(name, caffeine, price));
		}
		int max = drinks.size();
		System.out.println(max);

		System.out.println("The human body can handle about 500 mg of caffeine before bad things start to happen.");
		System.out.println("You will now play chicken with your drink choices. Try to stay alert without going over.");
		System.out.println("You can DRINK or be DONE");
		System.out.println("The drink will be chosen at random from the ones you entered");

		int totalCaffeine = 0;
		ArrayList<Cafe> tally = new ArrayList<Cafe>();
		String choice = "DRINK";
		while (!choice.equalsIgnoreCase("DONE") && totalCaffeine <= 500) {
			System.out.printf("You have had %dmg of caffeine so far\n", totalCaffeine);
			System.out.println("You can DRINK or be DONE!");
			choice = keyboard.nextLine();
			Cafe bev = drinks.get(randGen.nextInt(max));
			totalCaffeine += bev.getCaffeine();
			tally.add(bev);
		}

		if (totalCaffeine > 500) {
			System.out.println("You lose! You get the jitters and can't remember anything you studied today!");
		} else if (totalCaffeine > 100) {
			System.out.println("You win! You stay alert for the whole study session!");
		} else {
			System.out.println("What's this? It's like you didn't play! You fell asleep on your book.");
		}

		System.out.println("You drank: " + tally);
		for (Cafe bev : tally) {
			System.out.println(bev);
		}
		double totalCost = 0.0;
		for (int i = 0; i < tally.size(); i++) {
			totalCost += tally.get(i).getPrice();
		}
		System.out.println("Total Cost: $" + totalCost);

	}
}