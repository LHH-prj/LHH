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
		
		TableColumn<EventList,String> date01=new TableColumn<EventList,String>("날짜");
		TableColumn<EventList,String> event=new TableColumn<EventList,String>("행사");
		TableColumn<EventList,String> prog=new TableColumn<EventList,String>("프로그램");
		TableColumn<EventList,String> time=new TableColumn<EventList,String>("시간");
		TableColumn<EventList,String> instructor=new TableColumn<EventList,String>("강사");

		date01.setCellValueFactory(new PropertyValueFactory<EventList, String>("date"));
		event.setCellValueFactory(new PropertyValueFactory<EventList, String>("event"));
		prog.setCellValueFactory(new PropertyValueFactory<EventList, String>("program"));
		time.setCellValueFactory(new PropertyValueFactory<EventList, String>("time"));
		instructor.setCellValueFactory(new PropertyValueFactory<EventList, String>("instructor"));
		
		table.getColumns().setAll(date01,event,prog,time,instructor);
		
		
		EventList list01=new EventList("1월1일","신정","-","-","-");
		EventList list02=new EventList("1월2일","-","줌바","15시5분","이선미");
		EventList list04=new EventList("1월6일","정기휴일","-","-","");
		EventList list03=new EventList("1월7일","-","스피닝","19시5분","장재근");
		EventList list05=new EventList("1월9일","-","요가","15시5분","달라이민");
		EventList list06=new EventList("1월15일","-","Mob Train","19시5분","마샤");
		EventList list08=new EventList("1월17일","-","줌바","15시5분","이선미");
		EventList list07=new EventList("1월15일","-","Mob Train","19시5분","마샤");
		
		EventList list15=new EventList("1월20일","정기휴일","-","-","");
		EventList list16=new EventList("1월21일","-","스피닝","19시5분","장재근");
		EventList list17=new EventList("1월24일부터 27일까지","설날","","","");
		
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
				
				primaryStage.setTitle("1월 일정");
				primaryStage.setScene(scene);
				primaryStage.show();
			
	}

	public static void main(String[] args) {
		launch(args);
	}
}
