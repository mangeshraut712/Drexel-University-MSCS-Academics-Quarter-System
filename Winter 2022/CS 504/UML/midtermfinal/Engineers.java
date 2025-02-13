public class Engineers extends Employee {

	public Engineers(String Name, double salary, Double bonus) {
		super(Name, salary, bonus);
	}

	@Override
	public Double bonusCalculation() {
		Double engineersBonus = getSalary() + 100;
		return engineersBonus;
	}
}
