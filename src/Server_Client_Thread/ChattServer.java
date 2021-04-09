package Server_Client_Thread;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Server_Client_Thread_Prac.LoginServer2;
import Server_Client_Thread_Prac.LoginServerThread2;
import Server_Client_Thread_Prac.Room;

public class ChattServer implements Runnable {

	ChattServerThread chattServerThread = null;
	public List<ChattServerThread> globalList = null;
	List<Room> roomList = null;
	ServerSocket serverSocket = null;
	Socket acceptedSocket = null;


	/**
	 *서버 소켓 클라이언트 소켓 연결
	 *server = 5500 서버소켓
	 *socket = server.accept();
	 *loginServerThread = new LoginServerThread(LoginServer)
	 *loginServerThread.start() ==> loginServerThread.run();
	 */
	@Override 
	public void run() {
		System.out.println("===run ChattServer run()");
		globalList = new Vector<>();
		Calendar cal = Calendar.getInstance();
		int second        = cal.get(Calendar.SECOND);
		int milliSecond   = cal.get(Calendar.MILLISECOND);
		String totalSecond= second+" "+milliSecond;
		boolean isStop    = false;
		try {
			serverSocket = new ServerSocket(5500);
			System.out.println("===ChattServer Start........\n");
			while(!isStop) {
				acceptedSocket = serverSocket.accept();
				System.out.println("===Client info:"+acceptedSocket+"\n");
				System.out.println("===TotalSecond"+totalSecond);
				chattServerThread = new ChattServerThread(this);
				chattServerThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		System.out.println("===run ChattServer main()");
		ChattServer chattServer = new ChattServer();
		Thread thread = new Thread(chattServer);
		thread.start();
	}
	
	
	
}
