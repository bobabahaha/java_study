import java.util.ArrayList;
import java.util.Scanner;

public class ss {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Salary> salaries = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = inputInt("请输入操作编号：");

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    showAllEmployees();
                    break;
                case 3:
                    addSalary();
                    break;
                case 4:
                    findSalary();
                    break;
                case 5:
                    showAllSalaries();
                    break;
                case 6:
                    countTotalSalary();
                    break;
                case 0:
                    System.out.println("系统已退出。");
                    return;
                default:
                    System.out.println("输入错误，请重新选择。");
            }
        }
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("====== 简单薪资管理系统 ======");
        System.out.println("1. 添加员工");
        System.out.println("2. 查看所有员工");
        System.out.println("3. 录入薪资");
        System.out.println("4. 查询员工薪资");
        System.out.println("5. 查看所有薪资");
        System.out.println("6. 统计工资总支出");
        System.out.println("0. 退出系统");
    }

    public static void addEmployee() {
        System.out.println();
        System.out.println("====== 添加员工 ======");

        String id = inputString("请输入员工编号：");
        if (findEmployeeById(id) != null) {
            System.out.println("员工编号已存在，添加失败。");
            return;
        }

        String name = inputString("请输入员工姓名：");
        String department = inputString("请输入部门：");
        String position = inputString("请输入职位：");
        double baseSalary = inputDouble("请输入基本工资：");

        Employee employee = new Employee(id, name, department, position, baseSalary);
        employees.add(employee);
        System.out.println("员工添加成功。");
    }

    public static void showAllEmployees() {
        System.out.println();
        System.out.println("====== 所有员工 ======");

        if (employees.isEmpty()) {
            System.out.println("暂无员工信息。");
            return;
        }

        for (Employee employee : employees) {
            employee.showInfo();
        }
    }

    public static void addSalary() {
        System.out.println();
        System.out.println("====== 录入薪资 ======");

        String employeeId = inputString("请输入员工编号：");
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("员工不存在，请先添加员工。");
            return;
        }

        String month = inputString("请输入发薪月份，例如 2026-06：");
        double bonus = inputDouble("请输入奖金：");
        double allowance = inputDouble("请输入补贴：");
        double deduction = inputDouble("请输入扣款：");

        Salary salary = new Salary(employeeId, employee.name, month, employee.baseSalary, bonus, allowance, deduction);
        salaries.add(salary);
        System.out.println("薪资录入成功，实发工资：" + salary.actualSalary);
    }

    public static void findSalary() {
        System.out.println();
        System.out.println("====== 查询员工薪资 ======");

        String employeeId = inputString("请输入员工编号：");
        String month = inputString("请输入发薪月份，例如 2026-06：");

        for (Salary salary : salaries) {
            if (salary.employeeId.equals(employeeId) && salary.month.equals(month)) {
                salary.showInfo();
                return;
            }
        }

        System.out.println("未找到该员工该月份的薪资记录。");
    }

    public static void showAllSalaries() {
        System.out.println();
        System.out.println("====== 所有薪资 ======");

        if (salaries.isEmpty()) {
            System.out.println("暂无薪资信息。");
            return;
        }

        for (Salary salary : salaries) {
            salary.showInfo();
        }
    }

    public static void countTotalSalary() {
        System.out.println();
        System.out.println("====== 统计工资总支出 ======");

        String month = inputString("请输入要统计的月份，例如 2026-06：");
        double total = 0;

        for (Salary salary : salaries) {
            if (salary.month.equals(month)) {
                total += salary.actualSalary;
            }
        }

        System.out.println(month + " 工资总支出：" + total);
    }

    public static Employee findEmployeeById(String id) {
        for (Employee employee : employees) {
            if (employee.id.equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public static String inputString(String message) {
        System.out.print(message);
        return sc.next();
    }

    public static int inputInt(String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            System.out.println("请输入整数。");
            sc.next();
        }
    }

    public static double inputDouble(String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                double num = sc.nextDouble();
                if (num >= 0) {
                    return num;
                }
                System.out.println("金额不能小于 0。");
            } else {
                System.out.println("请输入数字。");
                sc.next();
            }
        }
    }
}

class Employee {
    String id;
    String name;
    String department;
    String position;
    double baseSalary;

    public Employee(String id, String name, String department, String position, double baseSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public void showInfo() {
        System.out.println("--------------------");
        System.out.println("员工编号：" + id);
        System.out.println("员工姓名：" + name);
        System.out.println("部门：" + department);
        System.out.println("职位：" + position);
        System.out.println("基本工资：" + baseSalary);
    }
}

class Salary {
    String employeeId;
    String employeeName;
    String month;
    double baseSalary;
    double bonus;
    double allowance;
    double deduction;
    double actualSalary;

    public Salary(String employeeId, String employeeName, String month, double baseSalary,
                  double bonus, double allowance, double deduction) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.month = month;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.allowance = allowance;
        this.deduction = deduction;
        this.actualSalary = baseSalary + bonus + allowance - deduction;
    }

    public void showInfo() {
        System.out.println("--------------------");
        System.out.println("员工编号：" + employeeId);
        System.out.println("员工姓名：" + employeeName);
        System.out.println("月份：" + month);
        System.out.println("基本工资：" + baseSalary);
        System.out.println("奖金：" + bonus);
        System.out.println("补贴：" + allowance);
        System.out.println("扣款：" + deduction);
        System.out.println("实发工资：" + actualSalary);
    }
}
