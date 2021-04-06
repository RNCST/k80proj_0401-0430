package Server_Client_Thread_2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;


public class LoginServer implements Runnable {
	
	LoginServerThread loginServerThread = null;
	public List<LoginServerThread>  globalList = null;
//	       List<Room>               roomList   = null;
	ServerSocket                  serverSocket = null;
	Socket                      acceptedSocket = null;
	

	
	public static void main(String[] args) {
		LoginServer loginServer = new LoginServer();
		Thread thread = new Thread(loginServer);
		thread.start();
		
	}

	@Override
	public void run() {
		System.out.println("===run LoginServer run()");
		globalList      = new Vector<>();
		boolean isStop  = false;
		try {
			serverSocket = new ServerSocket(2085);
			acceptedSocket = serverSocket.accept();
			loginServerThread = new LoginServerThread(this);
			loginServerThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
