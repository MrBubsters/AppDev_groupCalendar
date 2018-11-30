import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller extends Main {

	@FXML Button ChangeMe;
	@FXML Button SignMeUp;

	@FXML private void handleSubmitButtonAction(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new 
		        FXMLLoader(getClass().getResource("RootLayout.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    //set what you want on your stage
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setTitle("Pin5ive Application");
		    stage.setScene(new Scene(root1));
		    stage.setResizable(false);
		    stage.show();
	}
	
	@FXML private void SignUpButtonAction(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new 
		        FXMLLoader(getClass().getResource("SignUpSheet.fxml"));
		    Parent root2 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    //set what you want on your stage
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setTitle("Pin5ive SignUp Sheet");
		    stage.setScene(new Scene(root2));
		    stage.setResizable(false);
		    stage.show();
	
	}
    }
