package dev.ko;

import dev.ko.app.RootLoader;
import dev.ko.data.DepartmentDAO;
import dev.sol.core.application.FXApplication;
import dev.sol.core.application.loader.FXLoaderFactory;
import dev.sol.core.registry.FXCollectionsRegister;
import dev.sol.core.registry.FXControllerRegister;
import dev.sol.core.registry.FXNodeRegister;
import dev.sol.core.scene.FXSkin;
import dev.sol.core.thread.FXThreadService;
import dev.sol.db.DBService;

/**
 * JavaFX App
 */
public class App extends FXApplication {

    public static final FXControllerRegister CONTROLLER_REGISTER = FXControllerRegister.INSTANCE;
    public static final FXCollectionsRegister COLLECTIONS_REGISTER = FXCollectionsRegister.INSTANCE;
    public static final FXNodeRegister NODE_REGISTER = FXNodeRegister.INSTANCE;

    public static final FXThreadService THREAD_SERVICE = FXThreadService.INSTANCE;

    public static final DBService DB_EMPLOYEE = DBService.INSTANCE.initialize("jdbc:mysql://localhost/employeepos?user=root&passswords=");

    @Override
    public void initialize() throws Exception {
        setTitle("Employee ni Landayan");
        setSkin(FXSkin.DRACULA);

        _initialize_dataset();
        _initialize_application();
    }

    private void  _initialize_dataset(){
        COLLECTIONS_REGISTER.register("DEPARTMENT", DepartmentDAO.getDepartmentList());
    }

    private void _initialize_application() {
        RootLoader rootLoader = (RootLoader) FXLoaderFactory
        .createInstance(
            RootLoader.class,
            App.class.getResource("/dev/ko/app/ROOT.fxml"))
            .addParameter("scene", applicationScene)
            .initialize();
        rootLoader.load();
                
       
    }
}