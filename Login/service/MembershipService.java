package Login.service;

import java.util.HashMap;
import java.util.Map;

import Login.Member;
import Login.DAO.DatabaseServiceInter;
import Login.DAO.DatabaseService;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MembershipService implements MembershipServiceInter{
	final String[] txtFldIdArr = {"#userName","#txtID", "#txtPw", "#txtPwOk"};
	final boolean MAN = true;
	final boolean WOMAN = false;
	
	private Map<String, TextField> getTextFieldInfo(Parent membershipForm) {
		// TODO Auto-generated method stub
		Map<String, TextField> txtFldMap = new HashMap();
		
		for(String txtFldId:txtFldIdArr) {
			TextField txtFld = (TextField)membershipForm.lookup(txtFldId);
			txtFldMap.put(txtFldId, txtFld);
		}
		return txtFldMap;
	}
	
	private boolean IsTxtFld(Map<String, TextField> txtFldMap) {
		// TODO Auto-generated method stub
		CommonServiceInter commonService = new CommonService();
		for(String txtFldId:txtFldIdArr) {
			TextField txtFld = txtFldMap.get(txtFldId);
			if(txtFld.getText().isEmpty()) {
				commonService.ErrorMsg("Error Massage", "Text Field empty", "please, insert data");
				txtFld.requestFocus();
				return true;
			}
		}
		TextField pwTextField = txtFldMap.get(txtFldIdArr[2]);
		TextField pwOkTextField = txtFldMap.get(txtFldIdArr[3]);
		String pw = pwTextField.getText();
		String pwOk = pwOkTextField.getText();
		if(!pw.equals(pwOk)) {
			commonService.ErrorMsg("Different Password", "please, insert password try again");
			pwTextField.clear();
			pwOkTextField.clear();
			pwTextField.requestFocus();
			return true;
		}
		return false;
	}
	
	private Member getMember(Parent membershipForm) {
		// TODO Auto-generated method stub
		Map<String, TextField> txtFldMap = getTextFieldInfo(membershipForm);
		
		if(IsTxtFld(txtFldMap)) {
			return null;
		}
		
		String age = getComboBoxString(membershipForm);
		
		if (age.isEmpty()) {
			return null;
		}
		
		Member member = new Member();
		
		getComboBoxString(membershipForm);
		
		member.setName(((TextField)
			membershipForm.lookup(txtFldIdArr[0])).getText());
		member.setId(((TextField)
				membershipForm.lookup(txtFldIdArr[1])).getText());
		member.setPw(((TextField)
				membershipForm.lookup(txtFldIdArr[2])).getText());
		member.setAge(age);
		member.setGender(getGender(membershipForm));
		member.setCash(0);
		return member;
	}


	private String getGender(Parent membershipForm) {
		// TODO Auto-generated method stub
		RadioButton radioButton = (RadioButton)membershipForm.lookup("#radioMan");
		
		if(radioButton.isSelected()) {
			return "MAN";
		}
		
		return "WOMAN";
	}

	private String getComboBoxString(Parent membershipForm) {
		// TODO Auto-generated method stub
		CommonServiceInter commonService = new CommonService();
		ComboBox<String> comboBox = (ComboBox<String>)membershipForm.lookup("#comboAge");
		String age = "";
		
		if(comboBox == null) {
			commonService.ErrorMsg("ComboBox 문제입니다. 관리자에게 문의하세요.");
		}else if(comboBox.getValue() == null) {
			commonService.ErrorMsg("ComboBox를 선택하세요.");
		}else {
			age = comboBox.getValue().toString();
		}
		
		
		
		return age;
	}

	
	public void MembershipProc(Parent membershipForm) {
		// TODO Auto-generated method stub
		Member member = getMember(membershipForm);
		
		if (member==null) {
			return;
		}
		
		System.out.println(member.getName());
		System.out.println(member.getId());
		System.out.println(member.getPw());
		System.out.println(member.getAge());
		System.out.println(member.isGender());
		System.out.println(member.getCash());
		
		System.out.println("db 변수만들기전");
		
		DatabaseServiceInter dbServ = new DatabaseService();
		System.out.println("db 변수 만들었음");
		
		dbServ.open();
		
		System.out.println("db변수로 open() 실행했음");
		
		if(dbServ.Select(member.getId())) {
			Alert alert = new Alert(AlertType.ERROR);
			
			alert.setContentText("현재 사용중인 아이디 입니다.");
			alert.show();
			
			System.out.println("alert.show();");
			
			return;
			
		}else {
			dbServ.Insert(member);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("새로운 계정이 생성되었습니다.");
			alert.show();
			
			Stage stage = (Stage)membershipForm.getScene().getWindow();
			stage.close();
		}
		
		System.out.println("db에 멤버변수 입력했음 MembershipProc() 끝");
	}

	
	public void AddComboBox(Parent membershipForm) {
		// TODO Auto-generated method stub
		ComboBox<String> comboBoxAge = (ComboBox<String>)membershipForm.lookup("#comboAge");
		
		if(comboBoxAge != null) {
			comboBoxAge.getItems().addAll(
					"20대 미만", "20대", "30대", "40대", "50대", "60대 이상"
					);
		}
		
		
	}
}
