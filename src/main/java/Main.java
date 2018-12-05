import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
 
        initRootLayout();
 
        showView("TaskView.fxml");
        setRight("UserLogin.fxml");

        setLeft("CreateTaskWindow.fxml");

    }
 
    // Initializes the root layout.
    public void initRootLayout() {
        try {
            rootLayout = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
 
            Scene scene = new Scene(rootLayout, 1250, 700);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
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



	public static void main(String[] args) {
		launch(args);
	}
}
