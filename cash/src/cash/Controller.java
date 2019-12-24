package cash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cash.DAO.DatabaseService;
import cash.DAO.DatabaseServiceInter;
import cash.service.Cencle;
import cash.service.CencleInter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class Controller implements Initializable{
	private DatabaseServiceInter dbsrv = new DatabaseService();
	
	private Parent root;
	cashMon cashMon = new cashMon();
	
	@FXML private RadioButton checkOneMonth;
	@FXML private RadioButton checkThreeMonth;
	@FXML private RadioButton checkSixMonth;
	@FXML private RadioButton checkOneYear;
	@FXML private RadioButton checkTwoYear;
	
	private int n1;
	private int n2;
	private int n3;
	private int n4;
	private int n5;
	private int n6;
	
	private CencleInter cencleInter;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cencleInter = new Cencle();
		
	}
	
	public void cencle(ActionEvent event) {
		cencleInter.cancleClose(event);
		
	}
	
	public void identifyOk(ActionEvent event) throws IOException {
		Parent parent = (Parent)event.getSource();
		Stage stage = (Stage)parent.getScene().getWindow();
		stage.close();
		
		dbsrv.open();
		
//		FXMLLoader loader = new FXMLLoader.load(getClass().getResource("cash.fxml"));
//		Parent root = loader.load();
		
		for(String item : cashMon.cashCheak) {
        	System.out.println(item);
        	n6 += Integer.parseInt(item);
        }
		
		System.out.println("숫자 : " + n6);
		
		System.out.println("dbsrv.open(); 끝");
		
	}
	
	public void cashPayment(ActionEvent event) {
		checkValue();
		
		Stage opemIdentify = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CashIdentify.fxml"));
		
		Parent parent = null;
		
		try {
			parent = loader.load();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		opemIdentify.setScene(new Scene(parent));
		
		opemIdentify.show();
		
//		if(checkOneMonth.isSelected()) {
//			cashMon.setMonth(checkOneMonthAction());
//		}else if(checkThreeMonth.isSelected()) {
//			cashMon.setMonth(checkThreeMonthAction());
//		}else if(checkSixMonth.isSelected()) {
//			cashMon.setMonth(checkSixMonthAction());
//		}else if(checkOneYear.isSelected()) {
//			cashMon.setMonth(checkOneYearAction());
//		}else if(checkTwoYear.isSelected()) {
//			cashMon.setMonth(checkTwoYearAction());
//		}
//		
//		System.out.println(cashMon.getMonth());
		
		
	}
	
//	public int getCashMon(Parent root) {
//		RadioButton radioButton1 = (RadioButton)root.lookup("#checkOneMonth");
//		RadioButton radioButton2 = (RadioButton)root.lookup("#checkThreeMonth");
//		RadioButton radioButton3 = (RadioButton)root.lookup("#checkSixMonth");
//		RadioButton radioButton4 = (RadioButton)root.lookup("#checkOneYear");
//		RadioButton radioButton5 = (RadioButton)root.lookup("#checkTwoYear");
//		
//		if(radioButton1.isSelected()) {
//			cashMon.setMonth(checkOneMonthAction());
//		}else if(radioButton2.isSelected()) {
//			cashMon.setMonth(checkThreeMonthAction());
//		}else if(radioButton3.isSelected()) {
//			cashMon.setMonth(checkSixMonthAction());
//		}else if(radioButton4.isSelected()) {
//			cashMon.setMonth(checkOneYearAction());
//		}else if(radioButton5.isSelected()) {
//			cashMon.setMonth(checkTwoYearAction());
//		}
//		
//		return cashMon.getMonth();
//	}
	
	private void checkValue() {
        if(checkOneMonth.isSelected()) {
        	cashMon.cashCheak.add("28");
        }else if(checkThreeMonth.isSelected()) {
        	cashMon.cashCheak.add("84");
        }else if(checkSixMonth.isSelected()) {
        	cashMon.cashCheak.add("168");
        }else if(checkOneYear.isSelected()) {
        	cashMon.cashCheak.add("365");
        }else if(checkTwoYear.isSelected()) {
        	cashMon.cashCheak.add("730");
        }
        
        System.out.println(cashMon.cashCheak.size());
        
    }
	
	public int checkOneMonthAction() {
		n1 = 0;
		
		n1 += 28;
		
		return n1;
	}
	
	public int checkThreeMonthAction() {
		n2 = 0;
		
		n2 += 84;
		
		return n2;
	}
	
	public int checkSixMonthAction() {
		n3 = 0;
		
		n3 += 168;
		
		return n3;
	}
	
	public int checkOneYearAction() {
		n4 = 0;
		
		n4 += 365;
		
		return n4;
	}
	
	public int checkTwoYearAction() {
		n5 = 0;
		
		n5 += 730;
		
		return n5;
	}
	
	
}
