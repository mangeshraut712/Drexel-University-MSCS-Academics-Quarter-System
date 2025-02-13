
public class PowerCombatState implements CombatState {

	@Override
	public Warrior fight(Warrior warriorOne, Warrior warriorTwo) {
		if (warriorOne.calculatePower() > warriorTwo.calculatePower()) {
			return warriorOne;
		} else
			return warriorTwo;
	}

}
