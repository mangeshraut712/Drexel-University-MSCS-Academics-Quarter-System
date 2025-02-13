
public class AttackCombatState implements CombatState {

	@Override
	public Warrior fight(Warrior warriorOne, Warrior warriorTwo) {
		if (warriorOne.calculateAttack() > warriorTwo.calculateAttack()) {
			return warriorOne;
		} else {
			return warriorTwo;
		}
	}

}
