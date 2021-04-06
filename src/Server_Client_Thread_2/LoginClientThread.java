package Server_Client_Thread_2;

import java.util.StringTokenizer;
import java.util.Vector;

import Server_Client_Thread.Protocol;
import view_.MainLobbyView;

public class LoginClientThread extends Thread {
	MainLobbyView mainLobbyView = null;
	
	public LoginClientThread(MainLobbyView mainLobbyView) {
		System.out.println("===run LoginClientThread(LoginClient");
		this.mainLobbyView = mainLobbyView;
	}
	@Override
	public void run() {
		System.out.println("===run LoginClientThread run()");
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = "";
				msg = (String)mainLobbyView.ois.readObject();
				StringTokenizer st = null;
				int protocol = 0;
				if(msg !=null) {
					st = new StringTokenizer(msg, "#");
					protocol = Integer.parseInt(st.nextToken());
				}
				switch(protocol) {
				case Protocol.ROOM_IN:{
					String nickName = st.nextToken();
					Vector<String> v = new Vector<>();
					
					v.add(nickName);
					
				}
				break;
				case Protocol.MESSAGE:{
					String nickName = st.nextToken();
					String message  = st.nextToken();
					
					mainLobbyView.jta_showtext.append("["+nickName+"]"+message);
					mainLobbyView.jta_showtext.setCaretPosition(
					 mainLobbyView.jta_showtext.getDocument().getLength());
				}
				break;
				case Protocol.ROOM_OUT:{
					String nickName = st.nextToken();
					mainLobbyView.jta_showtext.append("["+nickName+"]"+"님이 퇴장 하였습니다.");
					mainLobbyView.jta_showtext.setCaretPosition(
							 mainLobbyView.jta_showtext.getDocument().getLength());
					
					
				}
				break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
