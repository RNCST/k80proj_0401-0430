package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class LoginServerThread extends Thread{
	public LoginServer loginServer  = null;
	Socket     clientSocket         = null;
	ObjectOutputStream oos          = null;
	ObjectInputStream  ois          = null;
	String             NickName     = null;

	
	public LoginServerThread(LoginServer loginServer) {
		System.out.println("===run LoginServerThread(LoginServer)");
		this.loginServer  = loginServer;
		this.clientSocket = loginServer.acceptedSocket;
		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream (clientSocket.getInputStream());
			String msg = (String) ois.readObject();
			System.out.println(msg);
			System.out.println(oos + "|"+ois);
			StringTokenizer st = new StringTokenizer(msg, Protocol.seperator);
			st.nextToken();
			NickName = st.nextToken();
			
			for(LoginServerThread loginServerThread : loginServer.globalList) {
				
			}
			loginServer.globalList.add(this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		System.out.println("===run LoginServerThread run()");
		String msg = null;
		boolean isStop = false;
		try {
			run_start:
				while(!isStop) {
					msg = (String) ois.readObject();
					
					StringTokenizer st = null;
					int protocol = 0;
					if( msg != null) {
						st = new StringTokenizer(msg, Protocol.seperator);
						protocol = Integer.parseInt(st.nextToken());
					}
					switch (protocol) {
					case Protocol.MESSAGE: {
						String nickName = st.nextToken();
						String message  = st.nextToken();
						if(message=="") {
							message= " ";
						}
						System.out.println(Protocol.MESSAGE
								          +Protocol.seperator
								          +nickName
								          +Protocol.seperator
								          +message
								          +Protocol.seperator);
						
					}
					break;
					case Protocol.ROOM_OUT: {
						loginServer.globalList.remove(this);
						
					}
					break run_start;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
