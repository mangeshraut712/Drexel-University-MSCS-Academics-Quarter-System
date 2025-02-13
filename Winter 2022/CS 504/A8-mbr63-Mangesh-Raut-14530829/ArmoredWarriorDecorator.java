
public class ArmoredWarriorDecorator extends WarriorDecorator {

	ArmoredWarriorDecorator(Warrior warrior) {
		super(warrior);
		warrior.defense *= 2;
	}

}
