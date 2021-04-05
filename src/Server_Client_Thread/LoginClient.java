package Server_Client_Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Vector;

import view_.MainLobbyView;

public class LoginClient {
	MainLobbyView mainLobbyView = MainLobbyView.getInstance();
	Socket clientSocket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream  ois = null;
	String nickName = null;
	
	public LoginClient(MainLobbyView mainLobbyView2) {
		init();
	}
	public LoginClient() {
		
	}
	public void init() {
		try {
			clientSocket = new Socket("127.0.0.1", 5550 );
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("===run mlv.setLoginClient");
			
			
			oos.writeObject(Protocol.MESSAGE+Protocol.seperator+nickName);
			//말하는 순간
			LoginClientThread loginClientThread = new LoginClientThread(this);
			loginClientThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main (String[] args) {
		LoginClient loginClient = new LoginClient();
		loginClient.init();
	}
	public void message(String msg, String nickName) {
		try {
			oos.writeObject(Protocol.MESSAGE
						    + Protocol.seperator
						    + nickName
						    + Protocol.seperator
						    + msg);
			mainLobbyView.jtf_gettext.setText("");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
}
