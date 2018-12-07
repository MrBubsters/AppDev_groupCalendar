

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class RootLayoutController {
	 
    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        // exit() causes the JavaFX application to terminate.
    	Platform.exit();
        
        // exit(n) terminates the currently running Java Virtual Machine.
        // Normal termination with n = 0
        // A nonzero status code indicates abnormal termination.
        //System.exit(0);
    }
 
    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This is a sample JAVAFX application");
        alert.setContentText("You can search, delete, update, " 
        					 + "insert a new employee with this program.");
        alert.show();
    }
}
