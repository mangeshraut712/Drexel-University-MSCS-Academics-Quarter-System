public class WarriorDecorator extends Warrior {

	Warrior warrior;

	WarriorDecorator(Warrior warrior) {
		super(warrior.level);
		this.warrior = warrior;
	}

	@Override
	int getAttack() {
		return warrior.getAttack();
	}

	@Override
	int getDefense() {
		return warrior.getDefense();
	}

	@Override
	int calculateAttack() {
		return warrior.calculateAttack();
	}

	@Override
	int calculateDefense() {
		return warrior.calculateDefense();
	}

	@Override
	double calculateBoost() {
		return warrior.calculateBoost();
	}

}
