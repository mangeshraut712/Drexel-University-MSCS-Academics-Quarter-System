import java.util.ArrayList;

public class CombatContext {
	ArrayList<CombatState> states = new ArrayList<CombatState>();

	public CombatContext() {
		states.add(new AttackCombatState());
		states.add(new DefenseCombatState());
		states.add(new InverseCombatState());
		states.add(new PowerCombatState());
		states.add(new TraditionalCombatState());
	}

	public ArrayList<CombatState> getCombateState() {
		return states;
	}

}
