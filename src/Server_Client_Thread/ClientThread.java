package Server_Client_Thread;

import java.util.StringTokenizer;

import run_._run;
import view_.LoginView;
import view_.MainLobbyViewWithClient;
import view_.SearchView;
import view_.SettingView;
import view_.SignOutView;
import view_.SignUpView;

public class ClientThread extends Thread{
	_run client = null;
	LoginView loginView = null;
	MainLobbyViewWithClient mainLobbyViewWithClient = null;
	SearchView searchView = null;
	SignUpView signUpView = null;
	SignOutView signOutView = null;
	SettingView settingView = null;
	
	public ClientThread(_run client) {
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
	public ClientThread(SettingView settingView ) {
		this.settingView = settingView;
	}
	
	public boolean stringCheck(String receiveData) {
			boolean isNotNull = false;
		if(receiveData != null) {
			isNotNull = true;
		} else {
			isNotNull = false;
		}
		return isNotNull;
	}
	
	@Override
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			try {
				String loginViewReceiveData   = null;
				String mainLobbyReceiveData   = null;
				String SearchViewReceiveData  = null;
				String SignUpViewReceiveData  = null;
				String SignOutViewReceiveData = null;
				String settingViewReceiveData = null;
				
//		loginViewReceiveData   = (String)loginView.ois.readObject();
//		mainLobbyReceiveData   = (String)mainLobby.ois.readObject();
//		SearchViewReceiveData  = (String)SearchView.ois.readObject();
//		SignUpViewReceiveData  = (String)SignUpView.ois.readObject();
//		SignOutViewReceiveData = (String)SignOutView.ois.readObject();
//		settingViewReceiveData = (String)settingView.ois.readObject();
				
				String nullcheck[] = {loginViewReceiveData,mainLobbyReceiveData,SearchViewReceiveData,
						SignUpViewReceiveData,SignOutViewReceiveData,settingViewReceiveData};
				StringTokenizer st = null;
				int protocol = 0;
				for(int i = 0 ; i < nullcheck.length ; i++) {
					if (nullcheck[i] == null) {
					} else {
						st = new StringTokenizer(nullcheck[i],"#");
						protocol = Integer.parseInt(st.nextToken());
					}
				}
				switch(protocol) {
				case ProjectProtocol.Login:{
					System.out.println("===ClientThread.java Protocol.Login");
					String nickName = st.nextToken();
				}break;
				case ProjectProtocol.MESSAGE:{
					System.out.println("===ClientThread.java Protocol.MESSAGE");
					String nickName = st.nextToken();
					String message  = st.nextToken();
					
					mainLobbyViewWithClient.jta_showtext1.append("["+nickName+"]"+message+"\n");
					mainLobbyViewWithClient.jta_showtext1.setCaretPosition
					(mainLobbyViewWithClient.jta_showtext1.getDocument().getLength());
					
				}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
