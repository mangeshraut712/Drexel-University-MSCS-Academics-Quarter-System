
public class TraditionalCombatState implements CombatState {

	@Override
	public Warrior fight(Warrior warriorOne, Warrior warriorTwo) {
		if (warriorOne.calculateAttack() > warriorTwo.calculateDefense()) {
			return warriorOne;
		} else
			return warriorTwo;
	}

}
