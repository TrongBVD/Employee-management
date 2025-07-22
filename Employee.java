
public abstract class Employee implements IEmployee, java.io.Serializable{

    private static final long serialVersionUID = 1L;
    protected final String empID;
    protected String empName;
    protected double baseSAl;
    
    public void callInterfaceMethod(Salary s) {
        s.getSalaryForIT();
    }

    public Employee(String empID, String empName, double baseSAl) {
        this.empID = empID.trim().toLowerCase();
        this.empName = empName;
        this.baseSAl = baseSAl;
    }

    @Override
    public String getEmpID() {
        return empID;
    }

    @Override
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getBaseSAl() {
        return baseSAl;
    }

    public void setBaseSAl(double baseSAl) {
        this.baseSAl = baseSAl;
    }
    
    

    @Override
    public String toString() {
        return "ID: " + empID + ", Name: " + empName + ", Base Salary: " + baseSAl;
    }
}
