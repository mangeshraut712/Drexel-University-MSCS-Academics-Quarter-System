public class DefensiveWarrior extends Warrior {

	private DefensiveWarrior(int level) {
		super(level);
	}

	static class Builder extends Warrior.Builder {
		public Builder(int level) {
			warrior = new DefensiveWarrior(level);
			warrior.attack = 2;
			warrior.defense = 3;
		}
	}
}