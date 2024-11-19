package dev.ko.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class Department extends FXModel {

    public static  class LIST_CELL extends ListCell<Department> {
        @Override
        protected void updateItem(Department department, boolean empty){
            super.updateItem(department, empty);

            if (department == null || empty) {
                setText(null);
                setGraphic(null);
                return;
                
            }

            setGraphic(new Label(department.getName()));
        }
    
        
    } 
    

    
        
    
    
    private FXStringProperty dep_id;
    private FXStringProperty name;
    private FXStringProperty location;
    

    public Department(String dep_id, String name, String location){
        this.dep_id = new FXStringProperty(dep_id);
        this.name = new FXStringProperty(name);
        this.location = new FXStringProperty(location);

        track_properties(this.dep_id, this.name, this.location);

    }

    public FXStringProperty depIDProperty(){
        return dep_id;
    }

    public String getDepId() {
        return depIDProperty().get();
    }

    public void setDepId(String DepId){
        depIDProperty().set(getDepId());
    }

    public FXStringProperty nameProperty(){
        return name;

    }

    public String getName(){
        return nameProperty().get();
    }
    public void setName(String Name){
        nameProperty().set(getName());
    }

    public FXStringProperty locationProperty(){
        return location;
    }

    public String getLocation(){
        return locationProperty().get();
    }

    public void setLocation(String Location){
        locationProperty().set(getLocation());
    }

    

    @Override
    public FXModel clone() {
       return new Department(getDepId(), getName(), getLocation());
    }

    @Override
    public void copy(FXModel arg0) {
      Department c = (Department) arg0;

      setDepId(c.getDepId());
      setName(c.getName());
      setLocation(c.getLocation());
    }
    
}
