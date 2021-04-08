package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

public class LoginServerThread extends Thread{
	Logger logger = LogManager.getLogger(LoginServerThread.class);
	public LoginServer loginServer  = null;
	Socket     clientSocket         = null;
	ObjectOutputStream oos          = null;
	ObjectInputStream  ois          = null;
	String             NickName     = null;

	
	public LoginServerThread(LoginServer loginServer) {
		System.setProperty
		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
		logger.info("===run LoginServerThread(LoginServer)");
		this.loginServer  = loginServer;
		this.clientSocket = loginServer.acceptedSocket;
		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream (clientSocket.getInputStream());
			String msg = (String) ois.readObject();
			logger.info(msg);
			logger.info(oos + "|"+ois);
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
		logger.info("===run LoginServerThread run()");
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
//						System.out.println(Protocol.MESSAGE
//								          +Protocol.seperator
//								          +nickName
//								          +Protocol.seperator
//								          +message
//								          +Protocol.seperator);
						logger.info(Protocol.MESSAGE
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
