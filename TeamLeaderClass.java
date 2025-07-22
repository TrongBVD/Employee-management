
public class TeamLeaderClass extends DeveloperClass implements Salary {

    private double bonusRate;
    protected double finalSal;

    public TeamLeaderClass(String empID, String empName, double baseSAl, String teamName, String programmingLanguages, int expYear, double bonusRate) {
        super(empID, empName, baseSAl, teamName, programmingLanguages, expYear);
        this.bonusRate = bonusRate;
    }

    public TeamLeaderClass(String empID, String empName, double baseSAl, double finalSal, String teamName, String programmingLanguages, int expYear) {
        super(empID, empName, baseSAl, teamName, programmingLanguages, expYear);
        this.bonusRate = bonusRate;
        this.finalSal = getSalaryForIT();
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    @Override
    public double getSalaryForIT() {
        double devSalary = super.getSalaryForIT();
        return devSalary + devSalary * bonusRate;
    }

    @Override
    public String toString() {
        return super.toString() + ", Leader Bonus Rate: " + bonusRate
                + ", Final Salary: " + finalSal;
    }
}
