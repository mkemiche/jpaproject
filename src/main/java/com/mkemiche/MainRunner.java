package com.mkemiche;

import com.mkemiche.manager.StaffManager;
import com.mkemiche.models.Employee;
import com.mkemiche.services.EmployeeService;
import com.mkemiche.services.StaffService;
import java.util.*;

/**
 * @author mkemiche
 * @created 27/04/2021
 */
public class MainRunner {
    final static EmployeeService es = new EmployeeService();
    final static StaffService ss = new StaffService();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        while(true){
            menu();
            int choice = sc.nextInt();
            switch (choice){
                case 1: 
                    MainRunner.createEmployee();
                    break;
                case 2:
                    MainRunner.updateEmployee();
                    break;
                case 3:
                    MainRunner.findEmployeeById();
                    break;
                case 4:
                    MainRunner.removeEmployee();
                case 5:
                    MainRunner.displayEmployees();
                    break;
                case 6:
                    MainRunner.createStaff();
                case 7:
                    MainRunner.updateStaff();
                    break;
                case 8:
                    MainRunner.removeStaff();
                    break;
                case 9:
                    MainRunner.displayStaff();
                    break;
                case 10:
                    MainRunner.getEmployeesBySalary();
                    break;
                case 99:
                    System.out.println("Good Bye !!");
                    System.exit(0);
                default:
                    System.out.println("try again !!");
                    break;
            }
        }


    }

    private static void getEmployeesBySalary() {
        System.out.println("Enter salary: ");
        double salary = sc.nextDouble();
        List<Employee> employeeList = es.searchBySalary(salary);
        employeeList.sort(Employee::compareTo);
        for(Employee e: employeeList){
            System.out.println(e);
        }
    }

    private static void displayStaff() {
        List<Object> objList = ss.getAllStaff();
        for(Object o: objList){
            System.out.println(o);
        }
    }

    private static void removeStaff() {
        int id = getId();
        Object staff = ss.getStaffById(id);
        ss.updateStaff(staff);
    }

    private static int getId() {
        System.out.println("Enter ID: ");
        return sc.nextInt();
    }

    private static void updateStaff() {
        int id = getId();
        Object staff = ss.getStaffById(id);
        ss.updateStaff(staff);

    }

    private static void createStaff() {
        Object staff = getObjectStaff();
        ss.createStaff(staff);
    }

    public static Object getObjectStaff() {
        System.out.println("Enter Staff name: ");
        String name = sc.next();
        System.out.println("Enter Teacher qualification: (Optional)");
        String qualification = sc.next();
        System.out.println("Enter Teacher subject expertise: (required if qualification filled)");
        String subExpert = sc.next();
        System.out.println("Enter non teacher staff area experience: (Optional)");
        String areaExp = sc.next();
        Object staff = null;
        if(qualification != null){
            staff = StaffManager.staffManager(name,qualification,subExpert);
        }else {
            staff = StaffManager.staffManager(name,areaExp);
        }
        return staff;
    }

    private static void removeEmployee() {
        int id = getId();
        es.removeEmployee(id);
    }

    private static void displayEmployees() {
        List<Employee> employeeList = es.getAllEmployees();
        employeeList.sort(Employee::compareTo);
        for(Employee e: employeeList){
            System.out.println(e);
        }
    }

    private static void findEmployeeById() {
        int id = getId();
        es.getEmployeeById(id);
    }

    private static void updateEmployee() {
        int id = getId();
        es.updateEmployee(id);
    }

    private static void createEmployee() {
        Employee emp = getEmployee();
        es.createEmployee(emp);
        System.out.println("employee created : "+ emp);
    }

    public static Employee getEmployee() {
        System.out.println("Enter employee name: ");
        String name = sc.next();
        System.out.println("Enter salary: ");
        double salary = sc.nextDouble();
        System.out.println("Enter department name: ");
        String dept = sc.next();
        Employee emp = new Employee(name, salary, dept);
        return emp;
    }

    private static void menu(){
        System.out.printf("1. create employee%n2. update employee by id%n3. find employee by id%n4. remove employee by id%n" +
                "5. display all emplyee%n6. create staff%n7. update staff by id%n8. remove staff by id%n9. display all staf%n" +
                "10. filtre employee by salary%n99. exit.");
    }
}
