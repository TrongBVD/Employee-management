
/**
 *
 * @author Trong_DEV
 */
public class TesterClass extends Employee implements Salary {

    private double bonusRate;
    private String type;

    public TesterClass(String empID, String empName, double baseSAl, double bonusRate, String type) {
        super(empID, empName, baseSAl);
        this.bonusRate = bonusRate;
        this.type = type;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(double br) {
        this.bonusRate = br;
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        this.type = t;
    }

    @Override
    public double getSalaryForIT() {
        return getBaseSAl() * bonusRate;
    }

    @Override
    public String toString() {
        return super.toString() + ", BonusRate:" + bonusRate + ", Type:" + type;
    }
}
