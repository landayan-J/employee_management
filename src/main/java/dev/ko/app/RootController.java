package dev.ko.app;


import dev.ko.App;
import dev.ko.models.Department;
import dev.ko.models.Employee;
import dev.ko.models.Job;
import dev.sol.core.application.FXController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RootController extends FXController {
    @FXML
    private TableView<Employee> employeetable;

    @FXML 
    private TableColumn<String, Employee> empIDColumn;

    @FXML
    private TableColumn<String, Employee> empFirstNameColumn;

    @FXML
    private TableColumn<String, Employee> empLastName;

    @FXML
    private TableColumn<Job, Employee> empJobColumn;

    @FXML
    private TableColumn<Employee, Employee> empManager;
    
    @FXML
    private TableColumn<Department, Employee> departmentColumn;

    @FXML
    private ComboBox<Department> departmentDropdown;

    private ObservableList<Department> department_masterlist;
    private ObservableList<Employee> employee_masterlist;


    @Override
    protected void load_fields() {
        employee_masterlist = FXCollections.observableArrayList();
        department_masterlist = App.COLLECTIONS_REGISTER.getList("DEPARTMENT");

        departmentDropdown.setButtonCell(new Department.LIST_CELL());
        departmentDropdown.setCellFactory(cell -> new Department.LIST_CELL());
        departmentDropdown.setItems(department_masterlist);

        departmentColumn.setCellFactory(cell -> new Employee.DEPARTEMENT_TABLECELL());
     
    }

    @Override
    protected void load_bindings() {
       
    }

 

    @Override
    protected void load_listeners() {
       departmentDropdown.getSelectionModel().selectFirst();
    }
    
}
