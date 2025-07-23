
/**
 *
 * @author Trong_DEV
 */
public class TeamLeaderClass extends DeveloperClass {

    private double bonusRate;

    public TeamLeaderClass(String empID, String empName, double baseSAl, String teamName, String programmingLanguages, int expYear, double bonusRate) {
        super(empID, empName, baseSAl, teamName, programmingLanguages, expYear);
        this.bonusRate = bonusRate;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(double br) {
        this.bonusRate = br;
    }

    @Override
    public double getSalaryForIT() {
        // Lương leader = lương dev + thưởng
        return super.getSalaryForIT() * bonusRate;
    }

    @Override
    public String toString() {
        return super.toString() + ", LeaderBonus:" + bonusRate;
    }
}
