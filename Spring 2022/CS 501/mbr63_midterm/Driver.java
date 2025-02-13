import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Driver {

	private List<Game> gameList;

	public Driver(List<Game> games) {
		gameList = new ArrayList<>();
		gameList.addAll(games);
	}

	public void recommend() {
		List<Game> recommendations = new ArrayList<>();

		for (Game game : gameList) {
			if (game.getType().equalsIgnoreCase("Board")) {
				recommendations.add(game);
			}
		}

		Random random = new Random();
		System.out.println("May we recommend \n" + recommendations.get(random.nextInt(recommendations.size())));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Game> gameList = new ArrayList<>();
		while (true) {
			System.out.println("Enter Game Name: ");
			String name = scanner.nextLine();
			System.out.println("Enter Game Type: ");
			String type = scanner.nextLine();
			System.out.println("Enter minimum players required: ");
			Integer numberOfPlayers = Integer.parseInt(scanner.nextLine());
			Game game = new Game(name, type, numberOfPlayers);
			gameList.add(game);
			System.out.println("Do you want to add more games? (Y/N)");
			String choice = scanner.nextLine();
			if (choice.equalsIgnoreCase("N")) {
				Driver driver = new Driver(gameList);
				driver.recommend();
				break;
			}
		}
	}
}