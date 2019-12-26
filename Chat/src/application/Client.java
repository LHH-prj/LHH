package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	// chat sever가 한명의 클라이언트와 통신하기위한 클래스
	
	Socket socket; //소캣이있어야 네트워크상에서 통신가능
	
	public Client(Socket socket) {
		this.socket = socket;
		receive();
	}

	// 클라이언트로부터 메시지를 전달 받는 메소드
	public void receive() {
		Runnable thread = new Runnable() {

			@Override //하나의 쓰레드 어떠한 동작을 할건지 run안에서 정의
			public void run() {
				try {
					while(true) {
						InputStream in = socket.getInputStream(); //어떠한 내용을 전달받기위한 인풋스트림
						byte[] buffer = new byte[512];
						int length = in.read(buffer); //클라이언트로 어떤내용을 전달받아서 버퍼에 담아주도록
						while(length == -1) throw new IOException(); // 내용을 전달받을떄 오류가발생한걸 알기위한 코드
						System.out.println("[메시지를 수신했습니다]" //수신확인 및 주소와 스레드 이름 출력을 위한 코드
								+ socket.getRemoteSocketAddress() 
								+ ": " + Thread.currentThread().getName());
						String message = new String(buffer, 0, length, "UTF-8");
						for(Client client : Main.clients) {
							client.send(message);
						} 
					}

				} catch(Exception e) {
					try {
						System.out.println("[메시지 수신오류]"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						
					} catch (Exception e2) {
						e2.printStackTrace(); 
					} 

				}
			}
		};
		Main.threadPool.submit(thread); //쓰레드풀에 하나의 쓰레드를 등록을 해주겠다는 이야기
	}
		



	// 클라이언트로부터 메시지를 전송하는 메소드
	public void send(String message) {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush(); //flush를하는이유는 여기까지 전달 되었다는 사실을 알리기 위해서다
				} catch (Exception e) {
					try {
						System.out.println("메세지 송신 오류"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						Main.clients.remove(Client.this); //클라이언트 서버에서 오류가생긴 클라이언트를 제거 
						socket.close();
					}  catch (Exception e2) {
						e2.printStackTrace(); 
					} 
				}

			}
		};
		Main.threadPool.submit(thread);
	}
}


