package Server_Client_Thread_old;

import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import Server_Client_Thread_Prac.LoginClient2;
import view_.MainLobbyViewWithClient;

public class ChattClientThread extends Thread {
	ChattClient chattClient = null;
	MainLobbyViewWithClient mainLobbyViewWithClient = null;
	Calendar cal = Calendar.getInstance();

	public ChattClientThread(ChattClient chattClient) {
		System.out.println(
				"===run LoginClientThread Constructor " + cal.get(Calendar.SECOND) + cal.get(Calendar.MILLISECOND));
		this.chattClient = chattClient;
	}

	public ChattClientThread(MainLobbyViewWithClient mainLobbyViewWithClient) {
		this.mainLobbyViewWithClient = mainLobbyViewWithClient;
	}

	@Override
	public void run() {
		System.out.println("===run LoginClientThread run()" + cal.get(Calendar.SECOND) + cal.get(Calendar.MILLISECOND));
		boolean isStop = false;
		while (!isStop) {
			try {
				String msg = "";
				msg = (String) mainLobbyViewWithClient.ois.readObject();
				System.out.println("===ois.readObject from client in thread= " + msg);
				StringTokenizer st = null;
				Calendar cal = Calendar.getInstance();
				int protocol = 0;
				if (msg != null) {
					st = new StringTokenizer(msg, "#");
					protocol = Integer.parseInt(st.nextToken());
				}
				switch (protocol) {
				case Protocol.ROOM_IN: {
					String nickName = st.nextToken();
					mainLobbyViewWithClient.jta_showtext1.append(nickName + "님이 입장하였습니다.\n");
					Vector<String> v = new Vector<>();
					v.add(nickName);
//					mainLobbyViewWithClient.dtm.addRow(v);
				}
					break;
				case 201: {
				}
					break;
				case Protocol.MESSAGE: {
					System.out.println(
							"LoginClientTread 201 " + cal.get(Calendar.SECOND) + cal.get(Calendar.MILLISECOND));
					String nickName = st.nextToken();
					String message = st.nextToken();

					mainLobbyViewWithClient.jta_showtext1.append("[" + nickName + "]" + message + "\n");
					mainLobbyViewWithClient.jta_showtext1
							.setCaretPosition(mainLobbyViewWithClient.jta_showtext1.getDocument().getLength());
				}
					break;

				case Protocol.CHANGE: {
					String nickName = st.nextToken();
					String afterName = st.nextToken();
					String message = st.nextToken();

//					for(int i =0; i<chattClient.dtm.getRowCount();i++) {
//						String imsi = (String)chattClient.dtm.getValueAt(i, 0);
//						if(nickName.contentEquals(imsi)) {
//							chattClient.dtm.setValueAt(afterName, i, 0);
//							break;
//						}
//					}

					if (nickName.equals(mainLobbyViewWithClient.nickName)) {
						mainLobbyViewWithClient.jta_showtext1.append(nickName + "님이 " + afterName + "으로 닉네임을 변경했습니다\n");
						mainLobbyViewWithClient.setTitle(afterName + "님의 대화창");
						mainLobbyViewWithClient.nickName = afterName;
					}
//					chattClient.jta_display.append(message +"\n");
				}
					break;
				case Protocol.ROOM_OUT: {
					String nickName = st.nextToken();
					mainLobbyViewWithClient.jta_showtext1.append(nickName + "님이 퇴장 하였습니다.\n");
					mainLobbyViewWithClient.jta_showtext1
							.setCaretPosition(mainLobbyViewWithClient.jta_showtext1.getDocument().getLength());
					for (int i = 0; i < chattClient.dtm.getRowCount(); i++) {
						String n = (String) chattClient.dtm.getValueAt(i, 0);
						if (n.equals(nickName)) {
							chattClient.dtm.removeRow(i);
						}
					}
				}
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
