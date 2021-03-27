package run_;

import java.io.IOException;

import view_.LoginView;

public class _run {
	/**
	 * run() ==> Server, Client, LoginView
	 */
	
	public LoginView loginView = LoginView.getInstance();
	public static String version ="POPCHAT VER 0.0 입니다.";
	
	public _run() throws IOException {
		loginView 			= new LoginView();
	System.out.println("===_run디폴트생성자 생성 성공");
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		_run _run = new _run();
		_run.loginView.initdisplay();
		System.out.println("===시작 성공 ");
		
			/*
			 * Server Call method
			 * Cliend call method
			 * DBlogic call method
			 * LoginView Call method
			 */
		
   
	}

}
