package run_;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import DBConnection.DBConnectionMgr;
import Server_Client_Thread.ClientThread;
import Server_Client_Thread.Server;
import view_.LoginView;
import view_.MainLobbyView;

public class clientRun {
	/**
	 * run() ==> Server, Client, LoginView
	 */
	
	Socket clientSocket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String nickName = null;
	
	public void init() {
		System.out.println("===_run.java (client)init() 실행");
		try {
			clientSocket = new Socket("127.0.0.1", 5800);
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
			oos.writeObject(99999999 + "#" + nickName);
			ClientThread clientThread = new ClientThread(this);
			clientThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static clientRun _run = new clientRun();
	
	public static clientRun getInstance() {
		if(_run == null) {
			_run = new clientRun();
		}
		return _run;
	}
	
	public LoginView loginView = null;
	public MainLobbyView mainLobbyView = null;
		
	public DBConnectionMgr dbMgr = DBConnectionMgr.getInstance(); 
	public static String version ="POPCHAT VER 0.0 입니다.";
	
	public clientRun(){
	loginView 			= LoginView.getInstance();
	mainLobbyView       = MainLobbyView.getInstance();
	System.out.println("===_run디폴트생성자 생성 성공");
		this.loginView.initdisplay();
		this.init();
		this.loginView.getInfo(this.clientSocket, this.oos, this.ois);
		this.mainLobbyView.getInfo(this.clientSocket, this.oos, this.ois);
	}

	public static void main(String[] args){
		LoginView.getInstance().initdisplay();
	}

}
