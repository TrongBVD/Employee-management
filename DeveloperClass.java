/**
 *
 * @author Trong_DEV
 */
public class DeveloperClass extends Employee implements Salary {

    private String teamName;
    private String programmingLanguages;
    private int expYear;

    public DeveloperClass(String empID, String empName, double baseSAl, String teamName, String programmingLanguages, int expYear) {
        super(empID, empName, baseSAl);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String name) {
        this.teamName = name;
    }

    public String getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(String pl) {
        this.programmingLanguages = pl;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int exp) {
        this.expYear = exp;
    }

    @Override
    public double getSalaryForIT() {
        return getBaseSAl() + expYear * 100;
    }

    @Override
    public String toString() {
        return super.toString() + ", Team:" + teamName + ", Languages:" + programmingLanguages + ", ExpYear:" + expYear;
    }
}
