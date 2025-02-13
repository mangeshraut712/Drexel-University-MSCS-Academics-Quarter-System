
public class StrongWarriorDecorator extends WarriorDecorator {

	StrongWarriorDecorator(Warrior warrior) {
		super(warrior);
		warrior.attack *= 2;
	}

}
