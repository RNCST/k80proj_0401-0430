package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
	public Server server = null;
	Socket clientSocket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String NickName = null;

	public ServerThread(Server server) {
		this.server = server;
		this.clientSocket = server.acceptedSocket;
		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
			String msg = (String) ois.readObject();
			System.out.println("===ois.readObject msg = " + msg);
			StringTokenizer st = new StringTokenizer(msg, "#");
			st.nextToken();
			NickName = st.nextToken();
			for (ServerThread serverThread : server.RunningThreadList) {
				sendMessage(100 + "#" + server.RunningThreadList);
			}
			server.RunningThreadList.add(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {
		System.out.println("===ServerThread.java SendMessage(msg) 실행");
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void speak(String msg) {
		System.out.println("===ServerThread.java Speak(msg) 실행");
		for (ServerThread serverThread : server.RunningThreadList) {
			serverThread.sendMessage(msg);
		}
	}

	public void run() {
		System.out.println("===ServerThread.java run() 실행");
		String recieveData = null;
		boolean isStop = false;
		try {
			run_start: while (!isStop) {
				recieveData = (String) ois.readObject();
				System.out.println("===ois.readObject from client= " + recieveData);
				StringTokenizer st = null;
				int protocol = 0;
				if (recieveData != null) {
					st = new StringTokenizer(recieveData, "#");
					protocol = Integer.parseInt(st.nextToken());
				}
				switch (protocol) {
				case ProjectProtocol.Login: {
					System.out.println("ServerThread.java Protocol.Login");
					String nickName = st.nextToken();
					String message = null;
					try {
						message = st.nextToken();
					} catch (NoSuchElementException e) {
						message = " ";
					}
					speak(ProjectProtocol.Login + ProjectProtocol.seperator + nickName + ProjectProtocol.seperator
							+ message + ProjectProtocol.seperator);
				}
					break;
				case ProjectProtocol.Logout: {

				}
					break;
				case ProjectProtocol.MESSAGE: {
					System.out.println("ServerThread.java Protocol.message");
					String nickName = st.nextToken();
					String message = null;
					try {
						message = st.nextToken();
					} catch (NoSuchElementException e) {
						message = " ";
					}
					speak(ProjectProtocol.MESSAGE + ProjectProtocol.seperator + nickName + ProjectProtocol.seperator
							+ message + ProjectProtocol.seperator);
				}
					break;
				case ProjectProtocol.NickNameChange: {

				}
					break;
				case ProjectProtocol.RoomChange: {

				}
					break;
				case ProjectProtocol.SearchID: {

				}
					break;
				case ProjectProtocol.SearchPW: {

				}
					break;
				case ProjectProtocol.SignOut: {

				}
					break;
				case ProjectProtocol.SignUp: {

				}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
