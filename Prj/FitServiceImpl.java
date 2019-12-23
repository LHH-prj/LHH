package Prj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class FitServiceImpl implements FitService {
		

	@Override
	public void FitCal(Parent root){

		TextField txtheight = (TextField) root.lookup("#height");
		TextField txtweight = (TextField) root.lookup("#weight");
		double calheight = Double.parseDouble(txtheight.getText()) / 100;
		double calweight = Double.parseDouble(txtweight.getText());
		
		double bmi = calweight / (calheight * calheight);

		if (calweight == 0) {
			System.out.println("체중은 값이 0이면 안됩니다");
		} else {
			if (bmi <= 18.5) {
				System.out.printf("비만도(bmi)는 %.2f 이며 저체중 입니다.\n", bmi);
			} else if (bmi <= 23) {
				System.out.printf("비만도(bmi)는 %.2f 이며 정상체중 입니다.\n", bmi);
			} else if (bmi <= 25) {
				System.out.printf("비만도(bmi)는 %.2f 이며 과체중 입니다.\n", bmi);
			} else {
				System.out.printf("비만도(bmi)는 %.2f 이며이며 비만 입니다.\n", bmi);
			}
		}
		
	}

	@Override
	public void WindowClose(ActionEvent event) {
		Parent parent = (Parent)event.getSource();
		Stage stage = (Stage)parent.getScene().getWindow();
		stage.close();
	}



	

}
