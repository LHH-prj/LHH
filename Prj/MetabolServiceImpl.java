package Prj;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MetabolServiceImpl implements MetabolService {

	

	@Override
	public void WindowClose(ActionEvent event) {
		Parent parent = (Parent)event.getSource();
		Stage stage = (Stage)parent.getScene().getWindow();
		stage.close();
		
	}

	@Override
	public void ManmetabolCal(Parent root) {
		TextField txtage  = (TextField) root.lookup("#Years");
		TextField txtheight = (TextField) root.lookup("#Height");
		TextField txtweight = (TextField) root.lookup("#Weight");
		double calage = Double.parseDouble(txtage.getText());
		double calheight = Double.parseDouble(txtheight.getText());
		double calweight = Double.parseDouble(txtweight.getText());
		
		double meta = 66.47+(13.75*calweight)+(5*calheight)-(6.76*calage);
		System.out.printf("기초대사량은 %.2f kcal 입니다.\n", meta);
		
	}

	@Override
	public void WomanmetabolCal(Parent root) {
		TextField txtage  = (TextField) root.lookup("#Years");
		TextField txtheight = (TextField) root.lookup("#Height");
		TextField txtweight = (TextField) root.lookup("#Weight");
		double calage = Double.parseDouble(txtage.getText());
		double calheight = Double.parseDouble(txtheight.getText());
		double calweight = Double.parseDouble(txtweight.getText());
		
		double meta = 655.1+(9.56*calweight)+(1.85*calheight)-(4.68*calage);
		System.out.printf("기초대사량은 %.2f kcal 입니다.\n", meta);
		
	}
	
	


}
