
public class TesterClass extends Employee implements Salary {

    protected double bonusRate;
    protected String type;
    protected double finalSal;
    
    

    public TesterClass(String empID, String empName, double baseSAl, double bonusRate, String type) {
        super(empID, empName, baseSAl);
        this.bonusRate = bonusRate;
        this.type = type;
        this.finalSal = getSalaryForIT();
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getSalaryForIT() {
        return baseSAl + baseSAl * bonusRate;
    }

    @Override
    public String toString() {
        return super.toString() + ", Bonus Rate: " + bonusRate + ", Type: " + type
                + ", Final Salary: " + finalSal;
    }
}
