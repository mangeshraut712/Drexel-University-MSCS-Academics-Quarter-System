public abstract class Warrior {
	int level;
	int attack;
	int defense;

	Warrior(int level) {
		this.level = level;
	}

	int getLevel() {
		return level;
	}

	int getAttack() {
		return attack;
	}

	int getDefense() {
		return defense;
	}

	abstract int calculateAttack();

	abstract int calculateDefense();

	abstract double calculateBoost();

	double calculatePower() {
		return Double.valueOf(calculateAttack()) + Double.valueOf(calculateDefense()) + calculateBoost();
	}

	public static abstract class Builder {
		Warrior warrior;

		public Builder attack(int attack) {
			warrior.attack = attack;
			return this;
		}

		public Builder defense(int defense) {
			warrior.defense = defense;
			return this;
		}

		private void validate(Warrior warrior) {
			String levelMessage = "";
			String attackMessage = "";
			String defenseMessage = "";
			if (warrior.level <= 0) {
				levelMessage = "Level must be greater than 0. ";
			}
			if (warrior.attack <= 0) {
				attackMessage = "Attack must be greater than 0. ";
			}
			if (warrior.defense <= 0) {
				defenseMessage = "Defense must be greater than 0. ";
			}
			if (warrior.level <= 0 || warrior.attack <= 0 || warrior.defense <= 0) {
				throw new IllegalStateException(levelMessage + attackMessage + defenseMessage);
			}

		}

		public Warrior build() {
			validate(warrior);
			return warrior;
		}

	}
}