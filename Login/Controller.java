package Login;

import java.net.URL;
import java.util.ResourceBundle;

import Login.service.CommonService;
import Login.service.CommonServiceInter;
import Login.service.LoginService;
import Login.service.LoginServiceInter;
import Login.service.MembershipService;
import Login.service.MembershipServiceInter;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Controller implements Initializable{
	private Parent root;
	private Parent membershipForm;
	
	private LoginServiceInter loginServiceInter;
	private CommonServiceInter commonServiceInter;
	private MembershipServiceInter membershipServiceInter;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loginServiceInter = new LoginService();
		commonServiceInter = new CommonService();
		membershipServiceInter = new MembershipService();
		
		
	}
	public void LoginProc() {
		
		loginServiceInter.LoginProc(root);
		
	}
	public void CancelProc(ActionEvent event) {
		commonServiceInter.WindowClose(event);
		
	}
	public void OpenMember() {
		loginServiceInter.OpenMember();
		
		
	}
	public void setMembershipForm(Parent membershipForm) {
		this.membershipForm = membershipForm;
	}
	public void MembershipProc() {
		membershipServiceInter.MembershipProc(membershipForm);
		
	}
}
