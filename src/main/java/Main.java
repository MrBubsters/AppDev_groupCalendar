import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
    private Stage primaryStage;
    
    private BorderPane rootLayout;

	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
 
        this.primaryStage.setTitle("Pin5ive");
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("pinitlogo-fin.jpg")));

 
        initRootLayout("application.css");
 
        showView("TaskView.fxml");
        setRight("UserLogin.fxml");
        setLeft("CreateTaskWindow.fxml");

    }
 
    // Initializes the root layout.
    public void initRootLayout(String s) {
        try {
            rootLayout = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
            rootLayout.getStylesheets().add(getClass().getResource(s).toExternalForm());
            
            Scene scene = new Scene(rootLayout, 1250, 700);
            
            primaryStage.setScene(scene);

            primaryStage.show();
        }  
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	// Sets the center of root
    public void showView(String fxml) {
        try {
        	AnchorPane View = FXMLLoader.load(getClass().getResource(fxml));
        	
            rootLayout.setCenter(View);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Sets right justified AnchorPane
    public void setRight(String fxml) {
        try {
        	AnchorPane View = FXMLLoader.load(getClass().getResource(fxml));
        	
            rootLayout.setRight(View);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Sets left justified AnchorPane
    public void setLeft(String fxml) {
        try {
        	AnchorPane View = FXMLLoader.load(getClass().getResource(fxml));

            rootLayout.setLeft(View);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Sets bottom justified AnchorPane
    public void setBottom(String fxml) {
        try {
        	AnchorPane View = FXMLLoader.load(getClass().getResource(fxml));
        	
            rootLayout.setBottom(View);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

	//Exit the program
	@FXML
	public void handleExit(ActionEvent actionEvent) {
        // exit() causes the JavaFX application to terminate.
    	System.exit(0);
       
    }
 
    //Help Menu button behavior
	@FXML
	public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("For this Pin5ive Task Application");
        alert.setContentText("You can PinIt, which saves your task that was inputed  " 
        					 + "You can Update, Refresh, or Delete any completed task."+
        		" More information email info@Pin5ive.com");
        alert.show();
	}
 
	public static void main(String[] args) {
		launch(args);
	}
}
