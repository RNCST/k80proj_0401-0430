package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StoredData throw IOExeption{
	Socket clientSocket = new Socket("127.0.0.1", 5550 );
	ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
	ObjectInputStream  ois = new ObjectInputStream(clientSocket.getInputStream());
}
