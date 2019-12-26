package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	// chat sever�� �Ѹ��� Ŭ���̾�Ʈ�� ����ϱ����� Ŭ����
	
	Socket socket; //��Ĺ���־�� ��Ʈ��ũ�󿡼� ��Ű���
	
	public Client(Socket socket) {
		this.socket = socket;
		receive();
	}

	// Ŭ���̾�Ʈ�κ��� �޽����� ���� �޴� �޼ҵ�
	public void receive() {
		Runnable thread = new Runnable() {

			@Override //�ϳ��� ������ ��� ������ �Ұ��� run�ȿ��� ����
			public void run() {
				try {
					while(true) {
						InputStream in = socket.getInputStream(); //��� ������ ���޹ޱ����� ��ǲ��Ʈ��
						byte[] buffer = new byte[512];
						int length = in.read(buffer); //Ŭ���̾�Ʈ�� ������� ���޹޾Ƽ� ���ۿ� ����ֵ���
						while(length == -1) throw new IOException(); // ������ ���޹����� �������߻��Ѱ� �˱����� �ڵ�
						System.out.println("[�޽����� �����߽��ϴ�]" //����Ȯ�� �� �ּҿ� ������ �̸� ����� ���� �ڵ�
								+ socket.getRemoteSocketAddress() 
								+ ": " + Thread.currentThread().getName());
						String message = new String(buffer, 0, length, "UTF-8");
						for(Client client : Main.clients) {
							client.send(message);
						} 
					}

				} catch(Exception e) {
					try {
						System.out.println("[�޽��� ���ſ���]"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						
					} catch (Exception e2) {
						e2.printStackTrace(); 
					} 

				}
			}
		};
		Main.threadPool.submit(thread); //������Ǯ�� �ϳ��� �����带 ����� ���ְڴٴ� �̾߱�
	}
		



	// Ŭ���̾�Ʈ�κ��� �޽����� �����ϴ� �޼ҵ�
	public void send(String message) {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush(); //flush���ϴ������� ������� ���� �Ǿ��ٴ� ����� �˸��� ���ؼ���
				} catch (Exception e) {
					try {
						System.out.println("�޼��� �۽� ����"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						Main.clients.remove(Client.this); //Ŭ���̾�Ʈ �������� ���������� Ŭ���̾�Ʈ�� ���� 
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


