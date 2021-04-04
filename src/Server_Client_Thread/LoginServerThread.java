package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class LoginServerThread extends Thread{
	public LoginServer loginServer    = null;
	       Socket      clientSocket   = null;
	       ObjectOutputStream oos     = null;
	       ObjectInputStream  ois     = null;
	       
	       public LoginServerThread(LoginServer loginServer) {
	    	   this.loginServer  = loginServer;
	    	   this.clientSocket = loginServer.acceptSocket;
	    	   try {
	    		   oos = new ObjectOutputStream(clientSocket.getOutputStream());
	    		   ois = new ObjectInputStream(clientSocket.getInputStream());
	    		   String msg = (String) ois.readObject();
	    		   
	    		   StringTokenizer st = new StringTokenizer(msg, "#");
	    		   st.nextToken();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	   System.out.println("===run LoginServerThread(loginServer loginServer");
	       }
	       public void ShowServerLog(String msg) {
	    	   for(LoginServerThread loginServerThread : loginServer.globalList) {
	    		   loginServerThread.send(msg);
	    	   }
	    	   
	       }
	       
	       public void send(String msg) {
	    	   try {
				oos.writeObject(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
	       }
	       
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
						if(msg != null ) {
							st = new StringTokenizer(msg,"#");
							protocol = Integer.parseInt(st.nextToken());
						}
						switch (protocol) {
						
						case Protocol.MESSAGE: {
							System.out.println("===run LoginServerThread Protocol.Message");
							String nickName = st.nextToken();
							String message  = st.nextToken();
							if(message=="") {
								message= " ";
							}
							System.out.println("==="+ Protocol.MESSAGE
												    + "#"
												    + nickName +"#"
												    + message  +"#");
						}
						break;
						case Protocol.ROOM_IN: {
							System.out.println("===run LoginServerThread Protocol.Room_In");
							String nickName = st.nextToken();
							System.out.println("==="+ Protocol.ROOM_IN
													+ "#"
													+ nickName +"#");
							
						}
						break;
						case Protocol.ROOM_OUT:{
							System.out.println("===run LoginServerThread Protocol.Room_out");
							String nickName = st.nextToken();
							loginServer.globalList.remove(this);
							System.out.println("==="+ Protocol.ROOM_OUT
								    				+ "#"
								    				+ nickName +"#");
						}
						break run_start;
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
	       }
	       
}
