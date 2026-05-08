import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Employees {

    private static final String FILE_NAME = "employee.txt";

    private final Scanner sc = new Scanner(System.in);

    private final File file = new File(FILE_NAME);

    // Display Single Employee
    public void displayEmployee(String name,
                                int empId,
                                String department,
                                double salary) {

        System.out.println("_____________________________________________________________________________________________");

        System.out.printf(
                "| %-20s | %-20s | %-20s | %-20s |%n",
                "NAME",
                "EMPLOYEE ID",
                "DEPARTMENT",
                "SALARY"
        );

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.printf(
                "| %-20s | %-20d | %-20s | %-20.2f |%n",
                name,
                empId,
                department,
                salary
        );

        System.out.println("---------------------------------------------------------------------------------------------");
    }

    // Add Employee
    public void addEmployee() throws IOException {

        FileWriter writer = new FileWriter(FILE_NAME, true);

        System.out.print("ENTER EMPLOYEE NAME : ");
        String name = sc.nextLine();

        System.out.print("ENTER EMPLOYEE ID : ");
        int empId = Integer.parseInt(sc.nextLine());

        System.out.print("ENTER DEPARTMENT : ");
        String department = sc.nextLine();

        System.out.print("ENTER SALARY : ");
        double salary = Double.parseDouble(sc.nextLine());

        writer.write(name + "," + empId + "," +
                department + "," + salary + "\n");

        writer.close();

        System.out.println("\nEMPLOYEE ADDED SUCCESSFULLY!\n");

        displayEmployee(name, empId, department, salary);
    }

    // Display Single Employee By ID
    public void displayEmployeeById(int empId)
            throws FileNotFoundException {

        Scanner fileReader = new Scanner(file);

        boolean found = false;

        while (fileReader.hasNextLine()) {

            String line = fileReader.nextLine();

            String[] data = line.split(",");

            if (data.length < 4) {
                continue;
            }

            int id = Integer.parseInt(data[1]);

            if (id == empId) {

                displayEmployee(
                        data[0],
                        id,
                        data[2],
                        Double.parseDouble(data[3])
                );

                found = true;

                break;
            }
        }

        if (!found) {
            System.out.println("EMPLOYEE NOT FOUND!");
        }

        fileReader.close();
    }

    // Display All Employees
    public void displayAllEmployees()
            throws FileNotFoundException {

        Scanner fileReader = new Scanner(file);

        System.out.println("_____________________________________________________________________________________________");

        System.out.printf(
                "| %-20s | %-20s | %-20s | %-20s |%n",
                "NAME",
                "EMPLOYEE ID",
                "DEPARTMENT",
                "SALARY"
        );

        System.out.println("---------------------------------------------------------------------------------------------");

        while (fileReader.hasNextLine()) {

            String line = fileReader.nextLine();

            String[] data = line.split(",");

            if (data.length < 4) {
                continue;
            }

            System.out.printf(
                    "| %-20s | %-20s | %-20s | %-20s |%n",
                    data[0],
                    data[1],
                    data[2],
                    data[3]
            );

            System.out.println("---------------------------------------------------------------------------------------------");
        }

        fileReader.close();
    }

    // Remove Employee
    public void removeEmployee(int empId)
            throws IOException {

        Scanner fileReader = new Scanner(file);

        File tempFile = new File("temp.txt");

        FileWriter writer = new FileWriter(tempFile);

        boolean found = false;

        while (fileReader.hasNextLine()) {

            String line = fileReader.nextLine();

            String[] data = line.split(",");

            if (data.length < 4) {
                continue;
            }

            int id = Integer.parseInt(data[1]);

            if (id == empId) {

                found = true;

                System.out.println("\nREMOVED EMPLOYEE:\n");

                displayEmployee(
                        data[0],
                        id,
                        data[2],
                        Double.parseDouble(data[3])
                );

                continue;
            }

            writer.write(line + "\n");
        }

        fileReader.close();

        writer.close();

        file.delete();

        tempFile.renameTo(file);

        if (found) {
            System.out.println("\nEMPLOYEE DELETED SUCCESSFULLY!");
        } else {
            System.out.println("\nEMPLOYEE NOT FOUND!");
        }
    }

    // Main Method
    public static void main(String[] args)
            throws IOException {

        Employees obj = new Employees();

        Scanner sc = new Scanner(System.in);

        System.out.println("\n========== EMPLOYEE MANAGEMENT SYSTEM ==========\n");

        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Display Single Employee");
        System.out.println("4. Display All Employees");
        System.out.println("5. Exit");

        System.out.print("\nENTER YOUR CHOICE : ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:

                obj.addEmployee();

                break;

            case 2:

                System.out.print("ENTER EMPLOYEE ID : ");

                int removeId = sc.nextInt();

                obj.removeEmployee(removeId);

                break;

            case 3:

                System.out.print("ENTER EMPLOYEE ID : ");

                int searchId = sc.nextInt();

                obj.displayEmployeeById(searchId);

                break;

            case 4:

                obj.displayAllEmployees();

                break;

            case 5:

                System.out.println("EXITING PROGRAM...");

                break;

            default:

                System.out.println("INVALID CHOICE!");
        }

        sc.close();
    }
}