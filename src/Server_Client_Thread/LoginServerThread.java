package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.StringTokenizer;

public class LoginServerThread extends Thread{
	public LoginServer loginServer = null;
	Socket                  client = null;
	ObjectOutputStream         oos = null;
	ObjectInputStream          ois = null;
	String                NickName = null;
	
	public LoginServerThread(LoginServer loginServer) {
		this.loginServer = loginServer;
		this.client      = loginServer.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			String msg = (String) ois.readObject();
			loginServer.jta_log.append(msg + "\n");
			StringTokenizer st = new StringTokenizer(msg, "#");
			st.nextToken();   
			NickName = st.nextToken();
			loginServer.jta_log.append(NickName + "님이 입장하였습니다. \n");
			System.out.println("===LoginServerThread 실행");
			for (LoginServerThread loginServerThread : loginServer.globalList) {
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
		for (LoginServerThread loginServerThread : loginServer.globalList) {
			loginServerThread.send(msg);
		}
	}
	/**
	 * @param msg
	 * @apiNote 클라이언트에게 말하기 구현
	 */
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
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
			case 200: {
				
			}
			break;
			case 201: {
				System.out.println("LoginServerThread protocol 201 "+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
				String nickName = st.nextToken();
				String message  = st.nextToken();
				if(message=="") {
					message=" ";
				}
				showServerLog(201 + "#" +nickName + "#" + message + "#");
			}
			break;
			case 202: {
				String nickName = st.nextToken();
				String afterName = st.nextToken();
				String message = st.nextToken();
				this.NickName = afterName;
				showServerLog(202 + "#" + nickName + "#" + afterName + "#" + message);
			}
			break;
			case 500: {
				String nickName = st.nextToken();
				loginServer.globalList.remove(this);
				showServerLog(500 + "#" + nickName);
			}
			break run_start;
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
