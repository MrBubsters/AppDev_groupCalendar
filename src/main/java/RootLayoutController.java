
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;


public class RootLayoutController {
    
	//Exit the program
   public void handleExit(ActionEvent actionEvent) {
        // exit() causes the JavaFX application to terminate.
    	System.exit(0);
       
    }
 
    //Help Menu button behavior
   public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("For this Pin5ive Task Application");
        alert.setContentText("You can search, delete, update, " 
        					 + "any task that you may have");
        alert.show();
    }
}
