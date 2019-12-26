package cash.service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Cencle implements CencleInter{

	
	public void cancleClose(ActionEvent event) {
		// TODO Auto-generated method stub
		Parent parent = (Parent)event.getSource();
		Stage stage = (Stage)parent.getScene().getWindow();
		stage.close();
		
		
	}
	
	
}
