module dev.ko {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive core.fx;
    requires transitive core.db;
    requires java.base;
    requires core.util;

    opens dev.ko to javafx.fxml;
    opens dev.ko.app to javafx.fxml;
    
    
    
    exports dev.ko;
    exports dev.ko.app;
}
