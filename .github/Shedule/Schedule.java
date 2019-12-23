package Shedule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Schedule extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
				
		AnchorPane ap = new AnchorPane();
		DatePicker dp = new DatePicker();
		TableView<EventList> table=new TableView<EventList>();
		
		
		
//		ListView<EventList> listView = new ListView<EventList>();
//	
//		EventList event1=new EventList(2020/01/01,"신정");
//		EventList event2=new EventList(2020/01/01,"신정");
//		EventList event3=new EventList(2020/01/01,"신정");
//		EventList event4=new EventList(2020/01/01,"신정");
//		EventList event5=new EventList(2020/01/01,"신정");
		
		Parent intro = 
		FXMLLoader.load(getClass().getResource("Holidays.fxml"));
		
		
		ap.getChildren().addAll(dp);
		ap.setPrefSize(400, 220);
		
		
		Scene scene = new Scene(intro);
				
				primaryStage.setTitle("Holiday Schedule");
				primaryStage.setScene(scene);
				primaryStage.show();
			
	}

	public static void main(String[] args) {
		launch(args);
	}
}
