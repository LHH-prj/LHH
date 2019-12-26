package application;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;



public class Main extends Application {

	//하나의 서버프로그램은 하나의 서버모듈을 구동시킬수있게 구현

	//다양한 클라이언트들이 접속을했을때 스레드를 효율적으로 관리할수있도록 하는것
	//스레드관리를 하는 대표적 라이브러리 스레드풀로 처리를할시 기본적인 스레드 수에 제한을 두기떄문에
	//갑작스럽게 클라이언트수가 폭증해도 한정된 리소스로 안정적인 서버운영이 가능하다.
	public static ExecutorService threadPool;
	public static Vector<Client> clients = new Vector<Client>(); 

	ServerSocket serverSocket;

	//서버를 구동시켜서 클라이언트의 연결을 기다리는 메소드
	public void startServer(String IP, int port) {
		try {
			serverSocket = new ServerSocket();
			// bind는 서버컴퓨터가 자신의 아이피주소 포트주소로 클라이언트를 기다리게한다
			serverSocket.bind(new InetSocketAddress(IP, port));
		} catch (Exception e) {
			e.printStackTrace();
			if(!serverSocket.isClosed()) {
				stopServer();
			}
			return; //오류가 발생하지않고 서버가 소켓을 잘 열어서 접속을 기다리게하는 상태
		}
		//클라이언트가 접속할떄까지 기다리는 쓰레드
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				while(true) {
					try { 
						Socket socket = serverSocket.accept(); //새로운 클라이언트가 접속할수있도록 상태유지시키는코드
						clients.add(new Client(socket));
						System.out.println("클라이언트에 접속하였습니다"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
					} catch (Exception e) {
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		threadPool = Executors.newCachedThreadPool(); //쓰레드풀 초기화
		threadPool.submit(thread);
	}




	//서버를 중지시키는 메소드
	public void stopServer() { 
		try {
			Iterator<Client> iterator = clients.iterator();
			while(iterator.hasNext()) { 
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}//서버소켓닫기	
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}//쓰레드풀종료
			if(threadPool != null && threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		};
	} 



	
	public void stopButton(ActionEvent event) {
		String IP = "127.0.0.1";
		int port = 9876;
		
		stopServer();
		Platform.runLater(() ->{
			String message = String.format("서버를 종료합니다\n", IP, port);
		});
		
	}
	

	// UI를 생성하고, 실질적으로 프로그램을 동작시키는 메소드
	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		root.setCenter(textArea);
		
		Button toggleButton = new Button("서버시작하기");
		toggleButton.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setMargin(toggleButton, new Insets(1, 0, 0, 0));
		root.setBottom(toggleButton);
		
		String IP = "127.0.0.1";
		int port = 9876;
		
		toggleButton.setOnAction(event -> {
			if(toggleButton.getText().equals("서버시작하기")) {
				startServer(IP, port);
				Platform.runLater(() -> {
					String message = String.format("(서버시작)\n", IP, port);
					textArea.appendText(message);
					toggleButton.setText("종료하기");
				});
			} else {
				stopServer();
				Platform.runLater(() -> {
					String message = String.format("(서버종료)\n", IP, port);
					textArea.appendText(message);
					toggleButton.setText("서버시작하기");
				});
			}	
		});
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("채팅 서버 프로그램");
		primaryStage.setOnCloseRequest(event -> stopServer());
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	

	// 프로그램의 시작점 main메소드
	public static void main(String[] args) {
		launch(args);
	}
}
