package Server_Client_Thread;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import DAO.DAO;
import run_.clientRun;
import view_.LoginView;
import view_.MainLobbyView;
import view_.MainLobbyViewWithClient;
import view_.SearchView;
import view_.SettingView;
import view_.SignOutView;
import view_.SignUpView;

public class ClientThread extends Thread {
	clientRun client = null;
	LoginView loginView = null;
	MainLobbyViewWithClient mainLobbyViewWithClient = null;
	SearchView searchView = null;
	SignUpView signUpView = null;
	SignOutView signOutView = null;
	SettingView settingView = null;

	public ClientThread(clientRun client) {
		System.out.println("===ClientThread.java Constructor(client)");
		this.client = client;
	}

	public ClientThread(LoginView loginView) {
		this.loginView = loginView;
	}

	public ClientThread(MainLobbyViewWithClient mainLobbyViewWithClient) {
		this.mainLobbyViewWithClient = mainLobbyViewWithClient;
	}

	public ClientThread(SearchView searchView) {
		this.searchView = searchView;
	}

	public ClientThread(SignUpView signUpView) {
		this.signUpView = signUpView;
	}

	public ClientThread(SignOutView signOutView) {
		this.signOutView = signOutView;
	}

	public ClientThread(SettingView settingView) {
		this.settingView = settingView;
	}

	public boolean stringCheck(String receiveData) {
		boolean isNotNull = false;
		if (receiveData != null) {
			isNotNull = true;
		} else {
			isNotNull = false;
		}
		return isNotNull;
	}

	@Override
	public void run() {
		DAO dao = new DAO();
		boolean isStop = false;
		while (!isStop) {
			try {
				String loginViewReceiveData = null;
				String mainLobbyReceiveData = null;
				String SearchViewReceiveData = null;
				String SignUpViewReceiveData = null;
				String SignOutViewReceiveData = null;
				String settingViewReceiveData = null;

				loginViewReceiveData = (String) LoginView.getInstance().ois.readObject();
//		mainLobbyReceiveData   = (String)mainLobby.ois.readObject();
//		SearchViewReceiveData  = (String)SearchView.ois.readObject();
//		SignUpViewReceiveData  = (String)SignUpView.ois.readObject();
//		SignOutViewReceiveData = (String)SignOutView.ois.readObject();
//		settingViewReceiveData = (String)settingView.ois.readObject();

				String nullcheck[] = { loginViewReceiveData, mainLobbyReceiveData, SearchViewReceiveData,
						SignUpViewReceiveData, SignOutViewReceiveData, settingViewReceiveData };
				StringTokenizer st = null;
				int protocol = 0;
				for (int i = 0; i < nullcheck.length; i++) {
					if (nullcheck[i] == null) {
					} else {
						st = new StringTokenizer(nullcheck[i], "#");
						protocol = Integer.parseInt(st.nextToken());
					}
				}
				switch (protocol) {
				case ProjectProtocol.Login: {
					System.out.println("===ClientThread.java Protocol.Login");
					String nickName = st.nextToken();
					System.out.println(LoginView.getInstance().jtf_id.getText());
					System.out.println(LoginView.getInstance().jtf_pw.getText());
					if (dao.runLogin(LoginView.getInstance().jtf_id.getText(),
							LoginView.getInstance().jtf_pw.getText()) == true) {
						System.out.println("===loginServer Thread.start(run) ");
						LoginView.getInstance().setGetID(LoginView.getInstance().jtf_id.getText());
						LoginView.getInstance().setGetPW(LoginView.getInstance().jtf_pw.getText());
						MainLobbyView.getInstance().initdisplay();
						LoginView.getInstance().jtf_id.setText("");
						LoginView.getInstance().jtf_pw.setText("");
						LoginView.getInstance().dispose();
					} else {
						JOptionPane.showMessageDialog(LoginView.getInstance(), "아이디와 비밀번호를 확인해주세요.");
						LoginView.getInstance().jtf_id.setText("");
						LoginView.getInstance().jtf_pw.setText("");
					}
				}
					break;
				case ProjectProtocol.MESSAGE: {
					System.out.println("===ClientThread.java Protocol.MESSAGE");
					String nickName = st.nextToken();
					String message = st.nextToken();
					
					MainLobbyView.getInstance().jta_showtext.append("[" + nickName + "]" + message + "\n");
					MainLobbyView.getInstance().jta_showtext
							.setCaretPosition(MainLobbyView.getInstance().jta_showtext.getDocument().getLength());
				}
					break;
				case ProjectProtocol.NickNameChange: {
				}
					break;
				case ProjectProtocol.SignUp: {
					System.out.println("===ClientThread.java Protocol.SignUp");
					String nickName = st.nextToken();
					String message = st.nextToken();
					SignUpView.getInstance().initdisplay();
				}
					break;
				case ProjectProtocol.Search: {
					System.out.println("===ClientThread.java Protocol.Search");
					String nickName = st.nextToken();
					String message = st.nextToken();
					SearchView.getInstance().initdisplay();
				}
					break;
				case ProjectProtocol.SearchID: {
				}
					break;
				case ProjectProtocol.SearchPW: {
				}
					break;
				case ProjectProtocol.RoomChange: {
				}
					break;
				case ProjectProtocol.Logout: {
				}
					break;
				case ProjectProtocol.SignOut: {
				}
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
