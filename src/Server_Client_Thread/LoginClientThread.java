package Server_Client_Thread;

import java.util.StringTokenizer;

import view_.MainLobbyView;

public class LoginClientThread extends Thread {
	LoginClient loginClient = null;
	MainLobbyView mainLobbyView = MainLobbyView.getInstance();

	public LoginClientThread(LoginClient loginClient) {
		System.out.println("===run LoginClientThread(MainLobbyView)");
		this.loginClient = loginClient;
	}
	
	
	@Override
	public void run() {
		System.out.println("===run LoginClientThread run()");
		boolean isStop = false;
		while(!isStop){
			try {
				String msg = null;
				System.out.println("msg be");
				msg = (String)loginClient.ois.readObject();
				System.out.println("msg af");
				StringTokenizer st =null;
				int protocol = 0;
				if(msg !=null) {
					st = new StringTokenizer(msg, "#");
					protocol = Integer.parseInt(st.nextToken());
					System.out.println(msg);
				}
				switch(protocol) {
				case Protocol.MESSAGE:{
					System.out.println("LoginClientThread's protocol.message");
					String nickName = st.nextToken();
					String message  = st.nextToken();
					System.out.println(nickName);
					System.out.println(message);
					
					
					mainLobbyView.jtf_showtext.setText("["+nickName+"]"+message
					 									+"\n");
					mainLobbyView.jtf_showtext.setCaretPosition
					(mainLobbyView.jtf_showtext.getDocument().getLength());
					
				}break;
				case Protocol.ROOM_OUT:{
					String nickName = st.nextToken();
					
					
				}
				break;
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

