import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller extends Main {
	
	ObservableList<String> tasks = FXCollections.observableArrayList();
	@FXML ListView<String> list;
	@FXML Button refresh;
	ObservableList<String> taskData = FXCollections.observableArrayList();

	
	@FXML private void handleTastViewButton(ActionEvent event) throws IOException, GeneralSecurityException {
		Calendar service = CalendarAPI.build();
		tasks.clear();
		tasks.addAll(CalendarAPI.getNext10(service));
		list.setItems(tasks);
	}


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
	
	@FXML Button create;
	@FXML TextField title;
	@FXML TextArea desc;
	@FXML DatePicker date;
	@FXML TextField time;
	@FXML TextField end;
	private void GetData() {
		taskData.clear();
		taskData.add(title.getText());
		taskData.add(desc.getText());
//		DateTimeFormatter formatter = DateTimeFormatter
//	            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//	            .withZone(ZoneId.of("CST"));
		taskData.add(date.getValue().toString() + "T" + time.getText() + ":00.000-06:00");
		taskData.add(date.getValue().toString() + "T" + end.getText() + ":00.000-06:00");
		System.out.println(taskData.get(2));
	}
	
	@FXML private void saveButtonAction(ActionEvent event) throws IOException, GeneralSecurityException {
		//get fields from taskData
		GetData();
		String summary = taskData.get(0);
		String desc = taskData.get(1);
		DateTime startTime = DateTime.parseRfc3339(taskData.get(2));
		System.out.println(startTime);
		DateTime endTime = DateTime.parseRfc3339(taskData.get(3));
		String[] recur = null;
		String timezone = "America/Chicago";
		
		//push taskData to GCal
		Calendar service = CalendarAPI.build();
		CalendarAPI.addEvent(service, summary, desc, startTime, endTime, recur, timezone);
	}
	
}
