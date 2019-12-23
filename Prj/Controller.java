package Prj;

import Prj.LoginMain;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller implements Initializable{
	private Parent fitForm;
	private Parent metabolForm;

	private FitService fitServ;
	private MetabolServiceImpl metabolServ;

	
	public void setFitForm(Parent fitForm) {
		this.fitForm = fitForm;
	}
	
	public void setMetabolForm(Parent metabolForm) {
		this.metabolForm = metabolForm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fitServ = new FitServiceImpl();
		metabolServ = new MetabolServiceImpl();

		
	}
	
	public void FitnessProc(){
		fitServ.FitCal(fitForm);
	}
	
	public void MetabolProc(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader
				(getClass().getResource("metabol.fxml"));
		Parent root = loader.load();
		RadioButton radioman = (RadioButton) root.lookup("#Man");
		RadioButton radiowoman = (RadioButton) root.lookup("#Woman");
		if (radioman.isSelected()) {
			ManmetabolProc();
		}else if (radiowoman.isSelected()) {
			WomanmetabolProc();
		}
	}
	
	public void ManmetabolProc() {
	metabolServ.ManmetabolCal(metabolForm);
	}
	
	public void WomanmetabolProc() {
	metabolServ.WomanmetabolCal(metabolForm);
	}
	
	
	public void CancelProc(ActionEvent event) {
		fitServ.WindowClose(event);
	}
	
}