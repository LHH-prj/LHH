package Login.service;

import java.io.IOException;

import Login.Controller;
import Login.DAO.DatabaseService;
import Login.DAO.DatabaseServiceInter;
import cash.cashMon;
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
			cashMon login = new cashMon();
			login.loginId.add(txtID.getText());
			Stage stage = (Stage)root.getScene().getWindow();
			stage.close();
			
			Stage mainStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cash/cash.fxml"));
			Parent parent = null;
			
			System.out.println("load");
			
			try {
				parent = (Parent)loader.load();
//				login.rootStage.add(parent); 이건 여기서 바로 결제창으로 갈때 넣는거고 메인으로 가면 메인에서 다시 이렇게 만들어야한다.
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("캐치");
			}
			
			mainStage.setScene(new Scene(parent));
			
			mainStage.show();
			
		} else {
			System.out.println("로그인 실패");
			txtID.clear();
			txtPW.clear();
			txtID.requestFocus();
		}
	}

	public void OpenMember() {
		
		MembershipServiceInter membershipService = new MembershipService();
		Stage opemMember = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Member.fxml"));
		
		Parent parent = null;
		
		try {
			parent = (Parent)loader.load();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		opemMember.setScene(new Scene(parent));
		Controller cnt = loader.getController();
		cnt.setMembershipForm(parent);
		cnt.setRoot(parent);
		
		membershipService.AddComboBox(parent);
		opemMember.show();
	}
}
