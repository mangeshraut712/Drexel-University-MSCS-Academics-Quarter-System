public class AggressiveWarrior extends Warrior {

	AggressiveWarrior(int level) {
		super(level);
	}

	static class Builder extends Warrior.Builder {
		public Builder(int level) {
			warrior = new AggressiveWarrior(level);
			warrior.attack = 3;
			warrior.defense = 2;
		}
	}

	@Override
	int calculateAttack() {
		return attack + (2 * level);
	}

	@Override
	int calculateDefense() {
		return defense + level;
	}

	@Override
	double calculateBoost() {
		return Double.valueOf(attack) / Double.valueOf(2);
	}

}