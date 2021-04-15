package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;

public class Client {
	
	Socket clientSocket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String nickName = null;
	
	public void init() {
		System.out.println("===run chattClient init()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		try {
			clientSocket = new Socket("127.0.0.1", 5500); // 서버측 ip주소 작성
			oos = new ObjectOutputStream(clientSocket.getOutputStream());// 홀수 소켓에서 처리
			ois = new ObjectInputStream(clientSocket.getInputStream());// 짝수 소켓에서 처리
			oos.writeObject(100 + "#" + nickName); // 말하는 순간
			ClientThread clientThread = new ClientThread(this);
			clientThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
