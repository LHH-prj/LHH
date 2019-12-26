package Login;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.kerberos.KerberosKey;

import com.sun.javafx.scene.EnteredExitedHandler;

import Login.service.CommonService;
import Login.service.CommonServiceInter;
import Login.service.LoginService;
import Login.service.LoginServiceInter;
import Login.service.MembershipService;
import Login.service.MembershipServiceInter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller implements Initializable{
	private Parent root;
	private Parent membershipForm;
	@FXML TextField userPw;
	@FXML Button buttonOk;
	
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
//		userPw.setOnAction(e->{
//			buttonOk.requestFocus();
//			LoginProc();
//		});
//		userPw.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				buttonOk.requestFocus();
//				LoginProc();
//			}
//		});
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
