import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class taskController implements Initializable{
	
	ObservableList<String> comboCategoriesList = FXCollections.observableArrayList("Homework", "Work", "Exam");
	
	@FXML String colorId;
	@FXML ComboBox<String> comboCategories;
	ObservableList<String> taskData = FXCollections.observableArrayList();
	@FXML TextArea description;
	
	//Categories 
	
	@FXML Button create;
	@FXML TextField title;
	@FXML TextArea desc;
	@FXML DatePicker date;
	@FXML TextField time;
	@FXML TextField end;
	
	// pulls data from task and inserts it into temporary list taskData
	private void GetData() {
		taskData.clear();
		taskData.add(title.getText());
		taskData.add(desc.getText());
//			DateTimeFormatter formatter = DateTimeFormatter
//		            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//		            .withZone(ZoneId.of("CST"));
		taskData.add(date.getValue().toString() + "T" + time.getText() + ":00.000-07:00");
		if (end != null) {
			taskData.add(date.getValue().toString() + "T" + end.getText() + ":00.000-07:00");

		} else {taskData.add(date.getValue().toString() + "T" + time.getText() + ":00.000-07:00");}
		System.out.println(taskData.get(2));
	}
	
	private String getColor() {
		String category = comboCategories.getValue();
		System.out.println(category);
		switch (category) {
			case "Homework":
				colorId = "3";
				break;
			case "Work":
				colorId = "1";
				break;
			case "Exam":
				colorId = "4";
				break;
			default:
				colorId = "2";
				break;
		}
		return colorId;
	}
	// action handler for the save event option
	@FXML private void saveButtonAction(ActionEvent event) throws IOException, GeneralSecurityException {
		//get fields from taskData to store as local variables
		getColor();
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
		CalendarAPI.addEvent(service, summary, desc, startTime, endTime, recur, timezone, colorId);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboCategories.setItems(comboCategoriesList);
		comboCategories.setValue("Home");			
	}
}
