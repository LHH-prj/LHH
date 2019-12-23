package Intro;

import javafx.application.Application;
import javafx.stage.Stage;

public class DateCalendar extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	

		frame.add(datePicker);

	}
	public static void main(String[] args) {
		launch(args);
	}
	 }
