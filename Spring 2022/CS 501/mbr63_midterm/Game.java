public class Game {

	private String name;

	private String type;

	private Integer numberOfPlayersRequired;

	public Game() {
		name = "Mangesh";
		type = "Racing";
		numberOfPlayersRequired = 5;
	}

	public Game(String name, String type, Integer numberOfPlayersRequired) {
		this.name = name;
		this.type = type;
		this.numberOfPlayersRequired = numberOfPlayersRequired;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumberOfPlayersRequired() {
		return numberOfPlayersRequired;
	}

	public void setNumberOfPlayersRequired(Integer numberOfPlayersRequired) {
		this.numberOfPlayersRequired = numberOfPlayersRequired;
	}

	@Override
	public String toString() {
		return "Game[" + "Name: " + name + ", Type: " + type + ", NumberOfPlayersRequired: " + numberOfPlayersRequired
				+ "]";
	}
}