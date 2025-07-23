
/**
 *
 * @author Trong_DEV
 */
import java.io.Serializable;

public abstract class Employee implements Serializable {

    private String empID;
    private String empName;
    private double baseSAl;

    public Employee(String empID, String empName, double baseSAl) {
        this.empID = empID;
        this.empName = empName;
        this.baseSAl = baseSAl;
    }

    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String name) {
        this.empName = name;
    }

    public double getBaseSAl() {
        return baseSAl;
    }

    public void setBaseSAl(double sal) {
        this.baseSAl = sal;
    }

    @Override
    public String toString() {
        return "ID:" + empID + ", Name:" + empName + ", BaseSalary:" + baseSAl;
    }
}
