package Prj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader
				(getClass().getResource("metabol.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		Controller ctrler = loader.getController();
		//ctrler.setFitForm(root);
		ctrler.setMetabolForm(root);
		
		primaryStage.setTitle("기초대사량 계산기");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}