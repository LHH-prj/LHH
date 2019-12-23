package Intro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Introduction01 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
//		BorderPane border=new BorderPane();
//		
//		ImageView imageView=new ImageView();
//		Image image = new Image(getClass().getResource("/image/stepinonstairs").toString());
//        imageView.setImage(image);
//        imageView.setStyle("-fx-background-color: BLACK");
//        imageView.setFitHeight(primaryStage.getHeight());
//        imageView.setPreserveRatio(true);
//        imageView.setSmooth(true);
//        imageView.setCache(true);
//        
//        border.setCenter(imageView);
//		
        Parent intro = 
				FXMLLoader.load(getClass().getResource("intro01.fxml"));
				
				Scene scene = new Scene(intro);
				
				primaryStage.setTitle("Fitness");
				primaryStage.setScene(scene);
				primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
