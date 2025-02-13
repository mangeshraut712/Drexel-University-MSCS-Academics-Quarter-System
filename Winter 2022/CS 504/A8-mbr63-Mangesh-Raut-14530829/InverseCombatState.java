
public class InverseCombatState implements CombatState {

	@Override
	public Warrior fight(Warrior warriorOne, Warrior warriorTwo) {
		if (warriorOne.calculateDefense() >= warriorTwo.calculateAttack()) {
			return warriorOne;
		} else
			return warriorTwo;
	}

}
