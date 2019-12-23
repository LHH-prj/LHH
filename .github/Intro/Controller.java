package Intro;

import java.net.URL;
import java.util.ResourceBundle;

import Intro.Service.Introduction;
import Intro.Service.IntroductionOp;
import exam1.Service.LoginService;
import exam1.Service.LoginServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Controller implements Initializable{

		private Parent root;
		private Introduction IntroS;
		
		public void setRoot(Parent root) {
			this.root = root;
		}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		IntroS = new IntroductionOp();
	
	}
		public void IntroProc() {
			
			IntroS.IntroProc(root);
		}
		
public void ScheduleProc() {
			
			IntroS.ScheduleProc(root);
		}
	

}
