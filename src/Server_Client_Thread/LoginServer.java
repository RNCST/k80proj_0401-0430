package Server_Client_Thread;

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
		System.out.println("===run LoginServer main()");
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
			serverSocket = new ServerSocket(5085);
			while(!isStop) {
			acceptedSocket = serverSocket.accept();
			System.out.println("AcceptedSocket");
			loginServerThread = new LoginServerThread(this);
			loginServerThread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
