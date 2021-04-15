package Server_Client_Thread_Prac;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.StringTokenizer;

import Server_Client_Thread_old.Protocol;

public class LoginServerThread2 extends Thread{
	public LoginServer2   loginServer = null;
	Socket               clientSocket = null;
	ObjectOutputStream         oos = null;
	ObjectInputStream          ois = null;
	String                NickName = null;
	Calendar                   cal = Calendar.getInstance();
	
	public LoginServerThread2(LoginServer2 loginServer) {
		System.out.println("===run LoginServerThread(LoginServer)"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		this.loginServer  = loginServer;
		this.clientSocket = loginServer.acceptedSocket;
		System.out.println(loginServer.acceptedSocket);
		System.out.println(this.clientSocket);
		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println(oos + "|"+ ois);
			String msg = (String) ois.readObject();
			System.out.println(msg);
			loginServer.jta_log.append(msg + "\n");
			StringTokenizer st = new StringTokenizer(msg, "#");
			st.nextToken();   
			NickName = st.nextToken();
			loginServer.jta_log.append(NickName + "님이 입장하였습니다. \n");
			System.out.println("===LoginServerThread 실행");
			for (LoginServerThread2 loginServerThread : loginServer.globalList) {
				//이전 입장한 사람들 정보 받기.
				
				this.send(100 + "#" + loginServerThread.NickName);
			}
			//현재 서버에 입장한 클라이언트 스레드 추가하기.
			loginServer.globalList.add(this);
			this.showServerLog(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @param msg
	 * @apiNote 현재 입장한 친구들 모두에게 msg 전송하기 
	 */
	public void showServerLog(String msg) {
		System.out.println("===LoginServerThread showServerLog()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		for (LoginServerThread2 loginServerThread : loginServer.globalList) {
			loginServerThread.send(msg);
		}
	}
	/**
	 * @param msg
	 * @apiNote 클라이언트에게 말하기 구현
	 */
	public void send(String msg) {
		System.out.println("===run LoginServerThread send()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		System.out.println("===run LoginServerThread run()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		String msg = null;
		boolean isStop = false;
		try {
			run_start:     // run_start와 세트로 사용하며 통채로 빠져나갈 수 있다.
			while(!isStop) {
			msg = (String) ois.readObject();
			loginServer.jta_log.append(msg + "\n");
			loginServer.jta_log.setCaretPosition(loginServer.jta_log.getDocument().getLength());
			StringTokenizer st = null;
			Calendar cal = Calendar.getInstance();
			int protocol = 0;
			if( msg != null) {
				st = new StringTokenizer(msg, "#");
				protocol = Integer.parseInt(st.nextToken());
			}
			switch (protocol) {
			case 201: {
				
			}
			break;
			case Protocol.MESSAGE: {
				System.out.println("LoginServerThread protocol 201 "+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
				String nickName = st.nextToken();
				String message  = st.nextToken();
				if(message=="") {
					message=" ";
				}
				showServerLog(Protocol.MESSAGE 
						+ Protocol.seperator 
						+ nickName 
						+ Protocol.seperator 
						+ message 
						+ Protocol.seperator);
			}
			break;
			case Protocol.CHANGE: {
				String nickName = st.nextToken();
				String afterName = st.nextToken();
				String message = st.nextToken();
				this.NickName = afterName;
				showServerLog(Protocol.CHANGE 
						+ Protocol.seperator 
						+ nickName 
						+ Protocol.seperator 
						+ afterName 
						+ Protocol.seperator 
						+ message);
			}
			break;
			case Protocol.ROOM_OUT: {
				String nickName = st.nextToken();
				loginServer.globalList.remove(this);
				showServerLog(Protocol.ROOM_OUT 
						+ Protocol.seperator 
						+ nickName);
			}
			break run_start;
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
