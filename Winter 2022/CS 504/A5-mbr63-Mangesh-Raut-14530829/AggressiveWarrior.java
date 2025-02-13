public class AggressiveWarrior extends Warrior {

	private AggressiveWarrior(int level) {
		super(level);
	}

	static class Builder extends Warrior.Builder {
		public Builder(int level) {
			warrior = new AggressiveWarrior(level);
			warrior.attack = 3;
			warrior.defense = 2;
		}
	}

}