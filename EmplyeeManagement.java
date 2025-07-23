
/**
 *
 * @author Trong_DEV
 */
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class EmplyeeManagement {

    private static final Scanner sc = new Scanner(System.in);
    protected static ArrayList<Employee> employeeList = new ArrayList<>();
    protected static Set<String> empIDSet = new HashSet<>();
    protected static Map<String, TeamLeaderClass> teamLeaderMap = new HashMap<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMainMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "0":
                    buildDataStructure();
                    break;
                case "1":
                    showEmployeeList();
                    break;
                case "2":
                    addEmployee();
                    break;
                case "3":
                    updateEmployee();
                    break;
                case "4":
                    searchEmployeeMenu();
                    break;
                case "5":
                    saveBoth();
                    break;
                case "6":
                    System.out.println("Reading data from file...");
                    readDataFromDatFile();
                    readDataFromTextFile();
                    break;
                case "7":
                    sortEmployee();
                    break;
                case "8":
                    if (!(employeeList.isEmpty())) {
                        System.out.println("You have not save the list yet, do you want to save it? (Yes/no)");
                        String input = sc.nextLine();
                        if (input.equalsIgnoreCase("Yes")) {
                            saveBoth();
                        } else if (input.equalsIgnoreCase("No")) {
                            exit = true;
                            System.out.println("Goodbye!");
                            continue;
                        }
                    }
                    System.out.println("Exiting program. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }
        sc.close();
    }

    private static void showMainMenu() {
        System.out.println("\n=== Employee Management Program ===");
        System.out.println("0. Build the data structure");
        System.out.println("1. Show the Employee list");
        System.out.println("2. Add Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Search Employee");
        System.out.println("5. Store data to file (dat and text)");
        System.out.println("6. Read data from file (dat and text)");
        System.out.println("7. Sort Employee");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }

    // Function 0: Build the data structure
    private static void buildDataStructure() {
        employeeList.clear();
        empIDSet.clear();
        teamLeaderMap.clear();

        System.out.println("Building data structure (no data input here).");

        List<Employee> testList = Arrays.asList(
                new DeveloperClass("dev01", "Dev A", 1000, "JavaTeam", "Java", 5),
                new TesterClass("test01", "Test B", 900, 1.1, "Manual"),
                new TeamLeaderClass("lead01", "Lead C", 1500, "JavaTeam", "Java", 8, 1.2)
        );

        for (Employee emp : testList) {
            System.out.print(emp.toString());
            if (emp instanceof Salary) {
                double salary = ((Salary) emp).getSalaryForIT();
                System.out.println(" | Salary: " + salary);
            } else {
                System.out.println(" | No Salary info");
            }
        }

        System.out.println("Structure OK. You may add data in other functions.");
    }

    private static String normalizeLanguages(String input) {
        String[] langs = input.split(",");
        List<String> result = new ArrayList<>();
        for (String lang : langs) {
            lang = lang.trim();
            if (!lang.isEmpty()) {
                result.add(lang.substring(0, 1).toUpperCase() + lang.substring(1));
            }
        }
        return String.join(", ", result);
    }

    protected static void addEmployee() {
        System.out.println("Add Employee:");
        System.out.println("1. Add Developer");
        System.out.println("2. Add Tester");
        System.out.println("3. Add Team Leader");
        System.out.print("Your choice: ");
        String choice = sc.nextLine();
        String empID = getUniqueID();
        if (empID == null) {
            return;
        }
        System.out.print("Enter name: ");
        String empName = sc.nextLine();
        double baseSal = getPositiveDouble("Enter base salary: ");

        switch (choice) {
            case "1":
                addDev(empID, empName, baseSal);
                break;
            case "2":
                addTester(empID, empName, baseSal);
                break;
            case "3":
                addLead(empID, empName, baseSal);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static String getUniqueID() {
        System.out.print("Enter ID: ");
        String empID = sc.nextLine().trim().toLowerCase();
        if (EmplyeeManagement.isDuplicateID(empID)) {
            System.out.println("Employee ID " + empID + " already exists. Cannot add.");
            return null;
        }
        return empID;
    }

    private static double getPositiveDouble(String prompt) {
        double value = -1;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(sc.nextLine());
                if (value < 0) {
                    System.out.println("Value must be >= 0. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Try again.");
            }
        }
        return value;
    }

    private static int getPositiveInt(String prompt) {
        int value = -1;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value < 0) {
                    System.out.println("Value must be >= 0. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Try again.");
            }
        }
        return value;
    }

    private static double getDoubleInRange(String prompt, double min, double max) {
        double value = -1;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(sc.nextLine());
                if (value < min || value > max) {
                    System.out.println("Value must be between " + min + " and " + max + ". Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Try again.");
            }
        }
        return value;
    }

    private static void addDev(String empID, String empName, double baseSal) {
        System.out.print("Enter team name: ");
        String teamName = sc.nextLine();
        System.out.print("Enter programming languages (comma-separated): ");
        String programmingLanguages = normalizeLanguages(sc.nextLine());
        int expYear = getPositiveInt("Enter years of experience: ");
        DeveloperClass dev = new DeveloperClass(empID, empName, baseSal, teamName, programmingLanguages, expYear);
        employeeList.add(dev);
        empIDSet.add(empID);
        System.out.println("Developer added successfully!");
    }

    private static void addTester(String empID, String empName, double baseSal) {
        double bonus = getDoubleInRange("Enter bonus rate (>= 1): ", 1, Double.MAX_VALUE);
        System.out.print("Enter tester type (Manual/Automation): ");
        String testerType = sc.nextLine();
        TesterClass tester = new TesterClass(empID, empName, baseSal, bonus, testerType);
        employeeList.add(tester);
        empIDSet.add(empID);
        System.out.println("Tester added successfully!");
    }

    private static void addLead(String empID, String empName, double baseSal) {
        System.out.print("Enter team name: ");
        String teamName = sc.nextLine().trim().toLowerCase();
        if (teamLeaderMap.containsKey(teamName)) {
            System.out.println("This team already has a leader. Only one leader per team is allowed.");
            return;
        }
        System.out.print("Enter programming languages (comma-separated): ");
        String programmingLanguages = normalizeLanguages(sc.nextLine());
        int expYear = getPositiveInt("Enter years of experience: ");
        double bonusRate = getDoubleInRange("Enter bonus rate (>= 1): ", 1, Double.MAX_VALUE);
        TeamLeaderClass lead = new TeamLeaderClass(empID, empName, baseSal, teamName, programmingLanguages, expYear, bonusRate);
        employeeList.add(lead);
        empIDSet.add(empID);
        teamLeaderMap.put(teamName, lead);
        System.out.println("Team Leader added successfully!");
    }

    private static void showEmployeeList() {
        if (employeeList.isEmpty()) {
            System.out.println("Employee list is empty.");
            return;
        }
        System.out.println("=== Employee List ===");
        for (Employee employee : employeeList) {
            String type = employee.getClass().getSimpleName().replace("Class", "");
            String salaryStr = (employee instanceof Salary)
                    ? ", Salary: " + ((Salary) employee).getSalaryForIT()
                    : "";
            System.out.println("[" + type + "] " + employee + salaryStr);
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        String id = sc.nextLine().trim().toLowerCase();
        Employee emp = null;
        for (Employee e : employeeList) {
            if (e.getEmpID().equals(id)) {
                emp = e;
                break;
            }
        }
        if (emp == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }
        System.out.println("Current info: " + emp);
        System.out.print("Enter new name (leave blank to skip): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            emp.setEmpName(newName);
        }
        System.out.print("Enter new salary (-1 to skip): ");
        double newSal = -1;
        try {
            newSal = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            newSal = -1;
        }
        if (newSal >= 0) {
            emp.setBaseSAl(newSal);
        }

        if (emp instanceof DeveloperClass && !(emp instanceof TeamLeaderClass)) {
            updateDeveloper((DeveloperClass) emp);
        } else if (emp instanceof TesterClass) {
            updateTester((TesterClass) emp);
        } else if (emp instanceof TeamLeaderClass) {
            updateTeamLeader((TeamLeaderClass) emp);
        }
        System.out.println("Update successful!");
    }

    private static void updateDeveloper(DeveloperClass dev) {
        System.out.print("Enter new team name (leave blank to skip): ");
        String team = sc.nextLine();
        if (!team.isEmpty()) {
            dev.setTeamName(team);
        }

        System.out.print("Enter new programming languages (leave blank to skip): ");
        String lang = normalizeLanguages(sc.nextLine());
        if (!lang.isEmpty()) {
            dev.setProgrammingLanguages(lang);
        }

        System.out.print("Enter new experience years (-1 to skip): ");
        int exp = -1;
        try {
            exp = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
        }
        if (exp >= 0) {
            dev.setExpYear(exp);
        }
    }

    private static void updateTester(TesterClass tester) {
        System.out.print("Enter new bonus rate (-1 to skip): ");
        double bonus = -1;
        try {
            bonus = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
        }
        if (bonus >= 0) {
            tester.setBonusRate(bonus);
        }

        System.out.print("Enter new tester type (leave blank to skip): ");
        String type = sc.nextLine();
        if (!type.isEmpty()) {
            tester.setType(type);
        }
    }

    private static void updateTeamLeader(TeamLeaderClass lead) {
        String oldTeam = lead.getTeamName().trim().toLowerCase();
        System.out.print("Enter new team name (leave blank to skip): ");
        String team = sc.nextLine().trim().toLowerCase();
        if (!team.isEmpty()) {
            if (!team.equals(oldTeam) && teamLeaderMap.containsKey(team)) {
                System.out.println("This team already has a leader.");
                return;
            }
            lead.setTeamName(team);
            teamLeaderMap.remove(oldTeam);
            teamLeaderMap.put(team, lead);
        }
        System.out.print("Enter new programming languages (leave blank to skip): ");
        String lang = normalizeLanguages(sc.nextLine());
        if (!lang.isEmpty()) {
            lead.setProgrammingLanguages(lang);
        }

        System.out.print("Enter new experience years (-1 to skip): ");
        int exp = -1;
        try {
            exp = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
        }
        if (exp >= 0) {
            lead.setExpYear(exp);
        }

        System.out.print("Enter new bonus rate (-1 to skip): ");
        double bonusRate = -1;
        try {
            bonusRate = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
        }
        if (bonusRate >= 0) {
            lead.setBonusRate(bonusRate);
        }
    }

    private static void searchEmployeeMenu() {
        System.out.println("\nSearch Employee menu:");
        System.out.println("1. By ID");
        System.out.println("2. By name");
        System.out.println("3. By salary (descending, from file)");
        System.out.println("4. By programming languages");
        System.out.print("Choose search option: ");

        String subChoice = sc.nextLine();
        switch (subChoice) {
            case "1":
                searchByID();
                break;
            case "2":
                searchByName();
                break;
            case "3":
                sortEmployeeBySalaryDescFromTextFile();
                break;
            case "4":
                searchByProgrammingLanguages2();
                break;
            default:
                System.out.println("Invalid search option!");
        }
    }

    private static void searchByID() {
        System.out.print("Enter employee ID to search: ");
        String id = sc.nextLine().trim().toLowerCase();
        if (id.isEmpty()) {
            System.out.println("No ID entered.");
            return;
        }
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/Employees.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String lower = line.toLowerCase();
                int idx = line.indexOf("ID:");
                if (idx == -1) {
                    continue;
                }
                int start = idx + "ID:".length();
                int end = lower.indexOf(',', start);
                String IDPart;
                if (end != -1) {
                    IDPart = lower.substring(start, end);
                } else {
                    IDPart = lower.substring(start);
                }

                IDPart = IDPart.trim();

                if (IDPart.contains(id)) {
                    System.out.println(line);
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading text file: " + e.getMessage());
            return;
        }
        if (!found) {
            System.out.println("No matching ID found.");
        }
    }

    private static void searchByName() {
        System.out.print("Enter name or part of name to search: ");
        String input = sc.nextLine()
                .toLowerCase()
                .trim();
        if (input.isEmpty()) {
            System.out.println("No name entered.");
            return;
        }
        String[] searchWords = input.split("\\s+");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("src/Employees.txt"),
                        StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String lower = line.toLowerCase();
                int idxName = lower.indexOf("name:");
                if (idxName == -1) {
                    continue;
                }
                int start = idxName + "name:".length();
                int end = lower.indexOf(',', start);
                String namePart;
                if (end != -1) {
                    namePart = lower.substring(start, end);
                } else {
                    namePart = lower.substring(start);
                }
                namePart = namePart.trim();

                boolean allMatch = true;
                for (String w : searchWords) {
                    if (!namePart.contains(w)) {
                        allMatch = false;
                        break;
                    }
                }

                if (allMatch) {
                    System.out.println(line);
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading text file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("No matching name found.");
        }
    }

    private static void sortEmployeeBySalaryDescFromTextFile() {
        List<String> lines = new ArrayList<>();
        List<EmployeeSalaryRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/Employees.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);

                double salary = 0;
                int idx = line.toLowerCase().indexOf("salary:");
                if (idx != -1) {
                    int start = idx + "salary:".length();
                    String salaryPart = line.substring(start).trim();
                    int endIdx = salaryPart.indexOf(',');
                    if (endIdx != -1) {
                        salaryPart = salaryPart.substring(0, endIdx).trim();
                    }
                    try {
                        salary = Double.parseDouble(salaryPart);
                    } catch (NumberFormatException e) {
                        salary = 0;
                    }
                }
                records.add(new EmployeeSalaryRecord(line, salary));
            }
        } catch (IOException e) {
            System.out.println("Error reading text file: " + e.getMessage());
            return;
        }

        if (records.isEmpty()) {
            System.out.println("No employee data found in text file.");
            return;
        }

        records.sort((r1, r2) -> Double.compare(r2.salary, r1.salary));

        System.out.println("Employees sorted by salary (descending) from text file:");
        for (EmployeeSalaryRecord rec : records) {
            System.out.println(rec.line);
        }
    }

    private static class EmployeeSalaryRecord {

        String line;
        double salary;

        EmployeeSalaryRecord(String line, double salary) {
            this.line = line;
            this.salary = salary;
        }
    }

    private static void searchByProgrammingLanguages2() {
        System.out.print("Enter programming language to search: ");
        String lang = sc.nextLine().trim();
        if (lang.isEmpty()) {
            System.out.println("No language entered.");
            return;
        }
        lang = lang.substring(0, 1).toUpperCase() + lang.substring(1).toLowerCase();

        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/Employees.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int idx = line.toLowerCase().indexOf("languages:");
                if (idx != -1) {
                    String langs = line.substring(idx + "Languages:".length()).trim();
                    String[] tokens = langs.split(",");
                    for (int i = 0; i < tokens.length; i++) {
                        if (tokens[i].trim().contains(lang)) {
                            System.out.println(line);
                            found = true;
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading text file: " + e.getMessage());
            return;
        }
        if (!found) {
            System.out.println("No employee found using language: " + lang);
        }
    }

    private static void storeDataToDatFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/Employees.dat"))) {
            objectOutputStream.writeObject(employeeList);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void storeDataToTextFile() {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream("src/Employees.txt"), StandardCharsets.UTF_8))) {
            for (Employee emp : employeeList) {
                writer.println(emp.toString());
            }
            System.out.println("Text file saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving text file: " + e.getMessage());
        }
    }

    private static void saveBoth() {
        if (employeeList.isEmpty()) {
            System.out.println("Nothing to save.");
            return;
        }
        storeDataToDatFile();
        storeDataToTextFile();
    }

    private static void readDataFromDatFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("src/Employees.dat"))) {
            employeeList = (ArrayList<Employee>) inputStream.readObject();
            empIDSet.clear();
            teamLeaderMap.clear();
            System.out.println("Data loaded successfully! " + employeeList.size() + " employees loaded.");
            for (Employee emp : employeeList) {
                empIDSet.add(emp.getEmpID());
                if (emp instanceof TeamLeaderClass) {
                    String team = ((TeamLeaderClass) emp).getTeamName().trim().toLowerCase();
                    teamLeaderMap.put(team, (TeamLeaderClass) emp);
                }
                if (emp instanceof Salary) {
                    System.out.println(emp.toString() + ", Salary: " + ((Salary) emp).getSalaryForIT());
                } else {
                    System.out.println(emp.toString());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist. Please save data before reading.");
        } catch (Exception ex) {
            System.out.println("Error reading data: " + ex.getMessage());
        }
    }

    private static void readDataFromTextFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/Employees.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read from text file: " + line);
            }
            System.out.println("Text file read successfully!");
        } catch (IOException e) {
            System.out.println("Error reading text file: " + e.getMessage());
        }
    }

    private static void sortEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("Employee list is empty.");
            return;
        }
        System.out.println("Sort employees by:");
        System.out.println("1. Name (ascending)");
        System.out.println("2. Salary (descending)");
        System.out.print("Choose: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1":
                employeeList.sort(Comparator.comparing(Employee::getEmpName, String.CASE_INSENSITIVE_ORDER));
                System.out.println("Sorted by name.");
                showEmployeeList();
                break;
            case "2":
                sortEmployeeBySalaryDesc();
                break;
            default:
                System.out.println("Invalid sort option.");
        }
    }

    private static void sortEmployeeBySalaryDesc() {
        if (employeeList.isEmpty()) {
            System.out.println("Employee list is empty.");
            return;
        }
        employeeList.sort((e1, e2) -> {
            double sal1 = (e1 instanceof Salary) ? ((Salary) e1).getSalaryForIT() : 0;
            double sal2 = (e2 instanceof Salary) ? ((Salary) e2).getSalaryForIT() : 0;
            return Double.compare(sal2, sal1); // Descending
        });
        System.out.println("Employees sorted by salary (descending):");
        showEmployeeList();
    }

    public static boolean isDuplicateID(String empID) {
        return empIDSet.contains(empID.trim().toLowerCase());
    }
}
