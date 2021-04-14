package Server_Client_Thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server implements Runnable {
	ServerThread serverThread = null;
	public List<ServerThread> RunningThreadList = null;
	ServerSocket serverSocket = null;
	Socket acceptedSocket     = null;
	
	public static void main(String[] args) {
		System.out.println("===Server.java main 실행");
		Server server = new Server();
		Thread ServerThread = new Thread(server);
		ServerThread.start();
	}
	
	@Override
	public void run() {
		System.out.println("===Server.java run() 실행");
		RunningThreadList = new Vector<>();
		boolean isStop = false;
		try {
		serverSocket = new ServerSocket(5800);
		System.out.println("===Server start");
		while(!isStop) {
			acceptedSocket = serverSocket.accept();
			System.out.println("===acceptedSocket is "+acceptedSocket);
			serverThread = new ServerThread(this);
			serverThread.start();
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
