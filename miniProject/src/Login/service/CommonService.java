package Login.service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonService implements CommonServiceInter{

	
	public void WindowClose(ActionEvent event) {
		// TODO Auto-generated method stub
		Parent parent = (Parent)event.getSource();
		Stage stage = (Stage)parent.getScene().getWindow();
		stage.close();
		
		
		
	}

	
	public void ErrorMsg(String title, String headerString, String contentText) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerString);
		alert.setContentText(contentText);
		alert.showAndWait();
		
	}

	
	public void ErrorMsg(String headerString, String contentText) {
		// TODO Auto-generated method stub
		ErrorMsg("error", headerString, contentText);
		
	}

	
	public void ErrorMsg(String contentText) {
		// TODO Auto-generated method stub
		ErrorMsg("error", "error Header", contentText);
		
	}

}
