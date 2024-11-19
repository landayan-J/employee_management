package dev.ko.data;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.ko.App;
import dev.ko.models.Department;
import dev.ko.models.Employee;
import dev.ko.models.Job;
import dev.sol.db.DBService;
import dev.sol.util.CoreLocalDateUtils;
import javafx.collections.ObservableList;

public class EmployeeDAO {
    public static final String TABLE = "employees";
    public static final DBService DB = App.DB_EMPLOYEE;

    private static final ObservableList<Department> departmentlist = App.COLLECTIONS_REGISTER.getList("DEPARTMENT");

    private static Employee data(CachedRowSet crs){
            try {
                String id = crs.getString("emp_id");
                String name = crs.getString("emp_name");
                Job job = Job.valueOf(crs.getString("job_name").toUpperCase());
                Employee manager = new Employee(crs.getString("manager"));
                LocalDate hireDate = CoreLocalDateUtils.parse(
                    crs.getString("hire_date"), "yyyy-MM-dd");
                long salary = crs.getLong("salary");
                long commission = crs.getLong("commission");
                Department department = departmentlist.stream()
                .filter(dept -> {
                    try {
                        return dept.getDepId().equals(crs.getString("dep_id"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return false;
                }).findFirst().get();
              
               return new Employee(id, name, job, manager, hireDate, salary, commission, department);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
    
        }
    
        public static List<Employee> getEmployeeList(){
            CachedRowSet crs = DB.select_all(TABLE);
            List<Employee> list = new LinkedList<>();
    
            try {
                while (crs.next()) {
                    Employee employee = data(crs);
                if (employee != null)
                list.add(employee);
                
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
                   list.forEach(employee -> {
                    String manager_id = employee.getManager().getEmpID();
                    employee.setManager(
                        list.stream()
                        .filter(e -> e.getEmpID().equals(manager_id))
                        .findFirst().get());
                    employee.rebaseline();
                    
                   });

                   return list;
    }
}
