import java.util.ArrayList;

public class Combat {
	CombatContext combatContext;

	public Combat(CombatContext combatContext) {
		this.combatContext = combatContext;
	}

	public Warrior duel(Warrior warriorOne, Warrior warriorTwo) {
		int count = 0;
		ArrayList<CombatState> states = combatContext.getCombateState();
		for (int i = 0; i < states.size(); i++) {
			Warrior winner = states.get(i).fight(warriorOne, warriorTwo);
			if (winner.equals(warriorOne))
				count++;
		}

		return count >= 3 ? warriorOne : warriorTwo;
	}
}
