package Server_Client_Thread_Prac;

import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import Server_Client_Thread.Protocol;

/**
 * 이벤트 핸들러의 역할은 말하기 이고 클라이언트 스레드의 역할은 듣기이다.
 *
 */
public class LoginClientThread2 extends Thread {
	LoginClient2 loginClient = null;
	Calendar cal = Calendar.getInstance();

	public LoginClientThread2(LoginClient2 loginClient) {
		System.out.println("===run LoginClientThread Constructor "+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		this.loginClient = loginClient;
	}

	@Override
	public void run() {
		System.out.println("===run LoginClientThread run()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = "";
				msg = (String)loginClient.ois.readObject();
				StringTokenizer st = null;
				Calendar cal =Calendar.getInstance();
				int protocol = 0;
				if(msg !=null) {
					st= new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());
				}
				switch(protocol) {
				case 100:{
					String nickName = st.nextToken();
					loginClient.jta_display.append(nickName+"님이 입장하였습니다.\n");
					Vector<String> v = new Vector<>();
					v.add(nickName);
					loginClient.dtm.addRow(v);
				}break;
				case 201:{
				}break;
				case Protocol.MESSAGE:{
					System.out.println("LoginClientTread 201 "+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
					String nickName = st.nextToken();
					String message  = st.nextToken();
					
					loginClient.jta_display.append("["+nickName+"]"+message+"\n");
					loginClient.jta_display.setCaretPosition
					(loginClient.jta_display.getDocument().getLength());
				}break;
				case Protocol.CHANGE:{
					String nickName = st.nextToken();
					String afterName = st.nextToken();
					String message = st.nextToken();
					
					for(int i =0; i<loginClient.dtm.getRowCount();i++) {
						String imsi = (String)loginClient.dtm.getValueAt(i, 0);
						if(nickName.contentEquals(imsi)) {
							loginClient.dtm.setValueAt(afterName, i, 0);
							break;
						}
					}
					
					if(nickName.equals(loginClient.nickName)) {
						loginClient.setTitle(afterName+"님의 대화창");
						loginClient.nickName = afterName;
					}
					loginClient.jta_display.append(message +"\n");
				}break;
				case Protocol.ROOM_OUT:{
					String nickName = st.nextToken();
					loginClient.jta_display.append(nickName+"님이 퇴장 하였습니다.\n");
					loginClient.jta_display.setCaretPosition
					(loginClient.jta_display.getDocument().getLength());
					for(int i=0; i<loginClient.dtm.getRowCount();i++) {
						String n = (String)loginClient.dtm.getValueAt(i, 0);
						if(n.equals(nickName)) {
							loginClient.dtm.removeRow(i);
						}
					}
				}break;
				}	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
