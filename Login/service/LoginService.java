package Login.service;

import java.io.IOException;

import Login.Controller;
import Login.DAO.DatabaseService;
import Login.DAO.DatabaseServiceInter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginService implements LoginServiceInter {
	private DatabaseServiceInter dbsrv = new DatabaseService();
	
	
	public void LoginProc(Parent root) {
		// TODO Auto-generated method stub
		TextField txtID = (TextField)root.lookup("#userId");
		TextField txtPW = (TextField)root.lookup("#userPw");
		
		dbsrv.open();
		if(dbsrv.Select(txtID.getText(),txtPW.getText())) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
	}

	public void OpenMember() {
		MembershipServiceInter membershipService = new MembershipService();
		Stage opemMember = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Member.fxml"));
		
		Parent parent = null;
		
		try {
			parent = loader.load();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		opemMember.setScene(new Scene(parent));
		Controller cnt = loader.getController();
		cnt.setMembershipForm(parent);
		cnt.setRoot(parent);
		
		membershipService.AddComboBox(parent);
		opemMember.show();
	}
}
