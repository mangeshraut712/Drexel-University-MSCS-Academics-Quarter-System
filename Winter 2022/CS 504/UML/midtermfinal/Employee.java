public class Employee {
	private String Name;
	private double salary, bonus;

	public Employee(String Name, double salary, Double bonus) {
		this.Name = Name;
		this.setSalary(salary);
		this.bonus = bonus;
	}

	public Double bonusCalculation() {
		return null;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}