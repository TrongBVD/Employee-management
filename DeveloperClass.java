
public class DeveloperClass extends Employee implements Salary {

    protected String teamName;
    protected String programmingLanguages;
    protected int expYear;
    protected double finalSal;

    public DeveloperClass(String empID, String empName, double baseSAl, String teamName, String programmingLanguages, int expYear) {
        super(empID, empName, baseSAl);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;
    }

    public DeveloperClass(String empID, String empName, double baseSAl,
            double finalSal, String teamName, String programmingLanguages, int expYear) {
        super(empID, empName, baseSAl);
        this.finalSal = finalSal;
        this.teamName = teamName.trim().toLowerCase();
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;
        this.finalSal = getSalaryForIT();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName.trim().toLowerCase();
    }

    public String getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(String programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public double getSalaryForIT() {
        if (expYear > 10) {
            return baseSAl + expYear * 2000000;
        } else if (expYear > 3) {
            return baseSAl + expYear * 1000000;
        } else {
            return baseSAl;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Team: " + teamName
                + ", Languages: " + programmingLanguages
                + ", Experience: " + expYear + " years"
                + ", Final Salary: " + finalSal;
    }
}
