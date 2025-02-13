
public class DefenseCombatState implements CombatState {

	@Override
	public Warrior fight(Warrior warriorOne, Warrior warriorTwo) {
		if (warriorOne.calculateDefense() > warriorTwo.calculateDefense()) {
			return warriorOne;
		} else
			return warriorTwo;
	}

}
