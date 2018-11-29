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
 
        showView();
    }
 
    //Initializes the root layout.
    public void initRootLayout() {
        try {
            rootLayout = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
 
            Scene scene = new Scene(rootLayout, 700, 375);
            primaryStage.setScene(scene);

            primaryStage.show();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public void showView() {
        try {
        	AnchorPane View = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        	
            rootLayout.setCenter(View);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}
