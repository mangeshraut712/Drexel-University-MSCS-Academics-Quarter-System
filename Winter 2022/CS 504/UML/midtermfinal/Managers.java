public class Managers extends Employee {

	public Managers(String Name, double salary, Double bonus) {
		super(Name, salary, bonus);
	}

	@Override
	public Double bonusCalculation() {
		Double managersBonus = getSalary() + 1000;
		return managersBonus;
	}
}
