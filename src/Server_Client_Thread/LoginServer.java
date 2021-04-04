package Server_Client_Thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class LoginServer implements Runnable {
	LoginServerThread       loginServerThread    = null;
	public  List<LoginServerThread> globalList   = null;
//	public  List<LoginServerThread> 
	ServerSocket    serverSocket                 = null;
	Socket          acceptSocket                 = null;
	static Calendar cal                          = Calendar.getInstance();
	
	public static void main(String[] args) {
		System.out.println("===run LoginServer main()");
		LoginServer loginServer = new LoginServer();
		Thread thread = new Thread(loginServer);
		thread.start();
	}
	@Override
	public void run() {
		System.out.println("=== LoginServer main()");
		globalList = new Vector<>();
		Calendar cal = Calendar.getInstance();
		boolean isStop = false;
		try {
			serverSocket = new ServerSocket(5500);
			System.out.println("===ServerSocket Ready");
			while(!isStop) {
				acceptSocket = serverSocket.accept();
				System.out.println("===Client info:acceptSocket:"
						           +acceptSocket);
				loginServerThread = new LoginServerThread(this);
				loginServerThread.start();
				System.out.println("===new LoginServerThread(loginServer).start");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
