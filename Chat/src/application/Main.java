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

	//�ϳ��� �������α׷��� �ϳ��� ��������� ������ų���ְ� ����

	//�پ��� Ŭ���̾�Ʈ���� ������������ �����带 ȿ�������� �����Ҽ��ֵ��� �ϴ°�
	//����������� �ϴ� ��ǥ�� ���̺귯�� ������Ǯ�� ó�����ҽ� �⺻���� ������ ���� ������ �α⋚����
	//���۽����� Ŭ���̾�Ʈ���� �����ص� ������ ���ҽ��� �������� ������� �����ϴ�.
	public static ExecutorService threadPool;
	public static Vector<Client> clients = new Vector<Client>(); 

	ServerSocket serverSocket;

	//������ �������Ѽ� Ŭ���̾�Ʈ�� ������ ��ٸ��� �޼ҵ�
	public void startServer(String IP, int port) {
		try {
			serverSocket = new ServerSocket();
			// bind�� ������ǻ�Ͱ� �ڽ��� �������ּ� ��Ʈ�ּҷ� Ŭ���̾�Ʈ�� ��ٸ����Ѵ�
			serverSocket.bind(new InetSocketAddress(IP, port));
		} catch (Exception e) {
			e.printStackTrace();
			if(!serverSocket.isClosed()) {
				stopServer();
			}
			return; //������ �߻������ʰ� ������ ������ �� ��� ������ ��ٸ����ϴ� ����
		}
		//Ŭ���̾�Ʈ�� �����ҋ����� ��ٸ��� ������
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				while(true) {
					try { 
						Socket socket = serverSocket.accept(); //���ο� Ŭ���̾�Ʈ�� �����Ҽ��ֵ��� ����������Ű���ڵ�
						clients.add(new Client(socket));
						System.out.println("Ŭ���̾�Ʈ�� �����Ͽ����ϴ�"
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
		threadPool = Executors.newCachedThreadPool(); //������Ǯ �ʱ�ȭ
		threadPool.submit(thread);
	}




	//������ ������Ű�� �޼ҵ�
	public void stopServer() { 
		try {
			Iterator<Client> iterator = clients.iterator();
			while(iterator.hasNext()) { 
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}//�������ϴݱ�	
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}//������Ǯ����
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
			String message = String.format("������ �����մϴ�\n", IP, port);
		});
		
	}
	

	// UI�� �����ϰ�, ���������� ���α׷��� ���۽�Ű�� �޼ҵ�
	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		root.setCenter(textArea);
		
		Button toggleButton = new Button("���������ϱ�");
		toggleButton.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setMargin(toggleButton, new Insets(1, 0, 0, 0));
		root.setBottom(toggleButton);
		
		String IP = "127.0.0.1";
		int port = 9876;
		
		toggleButton.setOnAction(event -> {
			if(toggleButton.getText().equals("���������ϱ�")) {
				startServer(IP, port);
				Platform.runLater(() -> {
					String message = String.format("(��������)\n", IP, port);
					textArea.appendText(message);
					toggleButton.setText("�����ϱ�");
				});
			} else {
				stopServer();
				Platform.runLater(() -> {
					String message = String.format("(��������)\n", IP, port);
					textArea.appendText(message);
					toggleButton.setText("���������ϱ�");
				});
			}	
		});
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("ä�� ���� ���α׷�");
		primaryStage.setOnCloseRequest(event -> stopServer());
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	

	// ���α׷��� ������ main�޼ҵ�
	public static void main(String[] args) {
		launch(args);
	}
}
