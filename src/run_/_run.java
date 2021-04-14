package run_;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import DBConnection.DBConnectionMgr;
import Server_Client_Thread.ClientThread;
import Server_Client_Thread.Server;
import view_.LoginView;

public class _run {
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
			oos.writeObject(100 + "#" + nickName);
			ClientThread clientThread = new ClientThread(this);
			clientThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static _run _run = new _run();
	
	public static synchronized _run getInstance() {
		if(_run == null) {
			_run = new _run();
		}
		return _run;
	}
	
	public LoginView loginView = null;
		
	public DBConnectionMgr dbMgr = DBConnectionMgr.getInstance(); 
	public static String version ="POPCHAT VER 0.0 입니다.";
	
	public _run(){
	loginView 			= LoginView.getInstance();
	System.out.println("===_run디폴트생성자 생성 성공");
		
	}
	
	
	
	
	public static void main(String[] args){
		Server server = new Server();
		System.out.println("===시작 성공 ");
		_run.loginView.initdisplay();
		server.run();
		_run.init();
		_run.loginView.getInfo(_run.clientSocket, _run.oos, _run.ois);
		
   
	}

}
