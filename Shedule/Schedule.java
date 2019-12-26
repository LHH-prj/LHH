package Shedule;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Schedule extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
				
		BorderPane border = new BorderPane();
		//DatePicker dp = new DatePicker();
		TableView<EventList> table=new TableView<EventList>();
		
		
		border.setCenter(table);
		
		TableColumn<EventList,String> date01=new TableColumn<EventList,String>("��¥");
		TableColumn<EventList,String> event=new TableColumn<EventList,String>("���");
		TableColumn<EventList,String> prog=new TableColumn<EventList,String>("���α׷�");
		TableColumn<EventList,String> time=new TableColumn<EventList,String>("�ð�");
		TableColumn<EventList,String> instructor=new TableColumn<EventList,String>("����");

		date01.setCellValueFactory(new PropertyValueFactory<EventList, String>("date"));
		event.setCellValueFactory(new PropertyValueFactory<EventList, String>("event"));
		prog.setCellValueFactory(new PropertyValueFactory<EventList, String>("program"));
		time.setCellValueFactory(new PropertyValueFactory<EventList, String>("time"));
		instructor.setCellValueFactory(new PropertyValueFactory<EventList, String>("instructor"));
		
		table.getColumns().setAll(date01,event,prog,time,instructor);
		
		
		EventList list01=new EventList("1��1��","����","-","-","-");
		EventList list02=new EventList("1��2��","-","�ܹ�","15��5��","�̼���");
		EventList list04=new EventList("1��6��","��������","-","-","");
		EventList list03=new EventList("1��7��","-","���Ǵ�","19��5��","�����");
		EventList list05=new EventList("1��9��","-","�䰡","15��5��","�޶��̹�");
		EventList list06=new EventList("1��15��","-","Mob Train","19��5��","����");
		EventList list08=new EventList("1��17��","-","�ܹ�","15��5��","�̼���");
		EventList list07=new EventList("1��15��","-","Mob Train","19��5��","����");
		
		EventList list15=new EventList("1��20��","��������","-","-","");
		EventList list16=new EventList("1��21��","-","���Ǵ�","19��5��","�����");
		EventList list17=new EventList("1��24�Ϻ��� 27�ϱ���","����","","","");
		
		ObservableList<EventList> list=
				FXCollections.observableArrayList(list01,list02,list03,
						list04,list05,list06,list07,list08,list15,list16,list17);
		table.setStyle("-fx-alignment:CENTER;-fx-selection-bar:lightsalmon;-fx-font-size:13pt;"
				+ "-fx-text-fill:blue;-fx-font-family:Arial;");
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.setItems(list);
		
		
//		Parent intro = 
//		FXMLLoader.load(getClass().getResource("Holidays.fxml"));
//		
//		
	
		
		Scene scene = new Scene(border);
				
				primaryStage.setTitle("1�� ����");
				primaryStage.setScene(scene);
				primaryStage.show();
			
	}

	public static void main(String[] args) {
		launch(args);
	}
}
