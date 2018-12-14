import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

	ObservableList<String> tasks = FXCollections.observableArrayList();
	@FXML ListView<String> list;
	@FXML Button refresh;

	// refreshes the ListView with next 10 events from google calendar
	@FXML private void handleTastViewButton(ActionEvent event) throws IOException, GeneralSecurityException {
		Calendar service = CalendarAPI.build();
		tasks.clear();
		tasks.addAll(CalendarAPI.getNext10(service));
		list.setItems(tasks);
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

	@FXML Button ChangeMe;
	@FXML Button SignMeUp;
	@FXML private javafx.scene.control.Button CalAPI;
	@FXML private javafx.scene.control.Button CloseMe;
	@FXML Button LogMeOut;
	
	@FXML private void LogOutAction(ActionEvent event) {
		CalendarAPI.DeleteCreds();
	}
	
	@FXML private void CalAPIevent(ActionEvent event)
	{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	
	@FXML private void nowCloseMe(ActionEvent event){
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}

	@FXML private void handleSubmitButtonAction(ActionEvent event)  throws IOException, GeneralSecurityException 
	{		
		CalendarAPI.build();
	}
	
	// controlling function for deleting event, based on what is selected from the list
	@FXML private void deleteSelection() throws GeneralSecurityException, IOException {
		int selectedIdx = list.getSelectionModel().getSelectedIndex();
		System.out.println(selectedIdx);
		ArrayList<String> idList = CalendarAPI.getList("id");
		CalendarAPI.DeleteEvent(idList.get(selectedIdx));
		handleTastViewButton(null);
	}
//	 text still does not update. No clue why
//	@FXML private void handleHoverTasks() {
//		int selectedIdx = list.getSelectionModel().getSelectedIndex();
//		System.out.println(selectedIdx);
//		ArrayList<String> descList = CalendarAPI.getList("desc");
//		System.out.println(descList);
//		String s = descList.get(selectedIdx);
//		StringProperty string = new SimpleStringProperty(s);
//		System.out.println(s);
//		description.textProperty().bind(string);
//		System.out.println(description.getText());
//	}

	
	
}
