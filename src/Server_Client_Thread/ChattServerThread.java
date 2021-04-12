package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import Server_Client_Thread_Prac.LoginServer2;
import Server_Client_Thread_Prac.LoginServerThread2;

public class ChattServerThread extends Thread{
	public ChattServer   chattServer = null;
	Socket               clientSocket = null;
	ObjectOutputStream         oos = null;
	ObjectInputStream          ois = null;
	String                NickName = null;
	Calendar                   cal = Calendar.getInstance();
	
	public ChattServerThread(ChattServer chattServer) {
		System.out.println("===run ChatServerThread(chattServer)"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		this.chattServer  = chattServer;
		this.clientSocket = chattServer.acceptedSocket;
		System.out.println("===clientSocket is ="+this.clientSocket);
		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("===oos = "+oos);
			System.out.println("===ois = "+ois);
			String msg = (String) ois.readObject();
			System.out.println("===ois.readObject msg = "+msg);
			StringTokenizer st = new StringTokenizer(msg, "#");
			st.nextToken();   
			NickName = st.nextToken();
			System.out.println("===ChattServerThread 실행");
			for (ChattServerThread chattServerThread : chattServer.globalList) {
				//이전 입장한 사람들 정보 받기.
				
				this.send(100 + "#" + chattServerThread.NickName);
			}
			//현재 서버에 입장한 클라이언트 스레드 추가하기.
			chattServer.globalList.add(this);
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
		System.out.println("===chattServerThread showServerLog()");
		for (ChattServerThread chattServerThread : chattServer.globalList) {
			chattServerThread.send(msg);
		}
	}
	/**
	 * @param msg
	 * @apiNote 클라이언트에게 말하기 구현
	 */
	public void send(String msg) {
		System.out.println("===run chattServerThread send()");
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		System.out.println("===run chattServerThread run()");
		String msg = null;
		boolean isStop = false;
		try {
			run_start:     // run_start와 세트로 사용하며 통채로 빠져나갈 수 있다.
			while(!isStop) {
			msg = (String) ois.readObject();
			System.out.println("===ois.readObject from client= "+msg);
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
				System.out.println("===ChattServerThread protocol 200 "+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
				String nickName = st.nextToken();
				String message  = null;
				try {
					message  = st.nextToken();
				} catch (NoSuchElementException e) {
					message = " ";
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
				chattServer.globalList.remove(this);
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
