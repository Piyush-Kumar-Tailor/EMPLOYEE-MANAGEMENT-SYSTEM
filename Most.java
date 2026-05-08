import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Most {

    Scanner sc = new Scanner(System.in);
    File file = new File("employee.txt");

    public void display(String name,int emp_id,String dept, double salary,File file) throws FileNotFoundException {


        
        System.out.println("_____________________________________________________________________________________________");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s |\n", "NAME", "EMPLOYEE ID", "DEPARTMENT", "SALARY");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s |\n", name, emp_id, dept, salary);
        System.out.println("---------------------------------------------------------------------------------------------");
    }


    public void add_emp() throws IOException {
        File file = new File("employee.txt");

        FileWriter writer = new FileWriter("employee.txt",true);
        System.out.print("ENTER EMPLOYEE NAME : ");
        String name = sc.nextLine();

        System.out.print("ENTER EMPLOYEE ID : ");
        int emp_id = Integer.parseInt(sc.nextLine());

        System.out.print("ENTER DEPARTMENT : ");
        String dept = sc.nextLine();

        System.out.print("ENTER SALARY : ");
        double salary = Double.parseDouble(sc.nextLine());

        writer.write(name+","+emp_id+","+dept+","+salary+"\n");
        
        System.out.println("EMPLOYEE IS ADDED SUCCESSFULLY !");
        display(name,emp_id,dept,salary,file);
        writer.close();
    }

    public void display_emp(int emp_id) throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            String[] st = str.split(",");
            if(Integer.parseInt(st[1]) == emp_id){
                display(st[0], Integer.parseInt(st[1]), st[2], Double.parseDouble(st[3]), file);
                break;
            }
        }
        sc.close();
    }

    public void display_emp_management() throws FileNotFoundException {


        Scanner sc = new Scanner(file);
        System.out.println("_____________________________________________________________________________________________");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s |\n", "NAME", "EMPLOYEE ID", "DEPARTMENT", "SALARY");
        System.out.println("---------------------------------------------------------------------------------------------");
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            String[] st = str.split(",");
            System.out.printf("| %-20s | %-20s | %-20s | %-20s |\n", st[0], st[1], st[2], st[3]);
            System.out.println("---------------------------------------------------------------------------------------------");
        }
            
        
        sc.close();

        
        
    }

    public void rmv_emp(int emp_id) throws IOException {

        Scanner sc = new Scanner(file);
        File tempfile = new File("temp.txt");
        FileWriter writer = new FileWriter("temp.txt",true);
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            String[] st = str.split(",");
            if(Integer.parseInt(st[1]) == emp_id){
                display(st[0], Integer.parseInt(st[1]), st[2], Double.parseDouble(st[3]), file);
                continue;
            }
            writer.write(str+"\n");
        }
        sc.close();
        writer.close();
        file.delete();
        tempfile.renameTo(file);
        
        System.out.println("EMPLOYEE IS DELETED SUCCESSFULLY !");

    }

    

    public static void main(String args[]) throws IOException {

        System.out.println("WELCOME INTO EMPLOYEE MANAGEMENT SYSTEM...");
        System.out.println("SELECT CHOICE : \n1. Add Employee \n2. Remove Employee \n3. Print Single Employee Detail \n4. Print Entire Employee Data \n");

        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER CHOICE : ");
        int ch = sc.nextInt();
        int emp_id;
        Most obj = new Most();

        switch (ch) {
            case 1:
                obj.add_emp();
                break;
            case 2:
                System.out.print("ENTER EMPLOYEE ID : ");
                emp_id = sc.nextInt();
                obj.rmv_emp(emp_id);
                break;
            case 3:
                System.out.print("ENTER EMPLOYEE ID : ");
                emp_id = sc.nextInt();
                obj.display_emp(emp_id);
                break;
            case 4:
                obj.display_emp_management();
                break;
            default:
                System.out.println("ENTER VALID CHOICE !");
                break;
        }

        sc.close();

    }
}
