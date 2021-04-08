package Server_Client_Thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

import view_.MainLobbyView;


public class LoginServer implements Runnable {
	Logger logger = LogManager.getLogger(LoginServer.class);
	
	LoginServerThread loginServerThread = null;
	public List<LoginServerThread>  globalList = null;
//	       List<Room>               roomList   = null;
	ServerSocket                  serverSocket = null;
	Socket                      acceptedSocket = null;
	

	
	public static void main(String[] args) {
		System.setProperty
		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
		LoginServer loginServer = new LoginServer();
		Thread thread = new Thread(loginServer);
		thread.start();
		Logger logger = LogManager.getLogger(LoginServer.class);
		logger.info("===run ");
		
	}

	@Override
	public void run() {
		logger.info("===run LoginServer run()");
		globalList      = new Vector<>();
		boolean isStop  = false;
		try {
			serverSocket = new ServerSocket(5085);
			while(!isStop) {
			acceptedSocket = serverSocket.accept();
			logger.info("AcceptedSocket");
			loginServerThread = new LoginServerThread(this);
			loginServerThread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
