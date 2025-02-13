import java.util.ArrayList;


public abstract class Corporation implements ArbitraryStatisticsService{
        String name;
        Double salary, bonus;
    public Corporation(String name, Double salary, Double bonus) {
        this.name = name;
        this.salary = salary;
        this.bonus = bonus;
    }
    public ArrayList bubbleSort(){
    return new ArrayList();
    }
    public ArrayList heapSort(){
        return new ArrayList();

    }
    public ArrayList ShellSort(){
        return new ArrayList();
    }
    public void reportStatistics(double amountOfPeople, double minSalary, double maxSalary){

    }

    public abstract Double calculateBonus();

}
