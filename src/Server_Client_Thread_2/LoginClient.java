package Server_Client_Thread_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import Server_Client_Thread.Protocol;

public class LoginClient extends JFrame implements ActionListener {

    Socket clientSocket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream  ois = null;
    String nickName        = null;
    
    public void init() {
    	System.out.println("===run LoginClient init()");
    	try {
    		clientSocket = new Socket("127.0.0.1",2085);
    		oos = new ObjectOutputStream(clientSocket.getOutputStream());
    		ois = new ObjectInputStream (clientSocket.getInputStream());
    		oos.writeObject(Protocol.seperator + Protocol.seperator + nickName);
    		LoginClientThread loginClientThread = new LoginClientThread(this);
    		loginClientThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
	public static void main(String[] args) {

		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

}
