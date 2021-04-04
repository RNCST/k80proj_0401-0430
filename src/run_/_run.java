package run_;

import java.io.IOException;

import DBConnection.DBConnectionMgr;
import view_.LoginView;

public class _run {
	/**
	 * run() ==> Server, Client, LoginView
	 */
	private static _run _run = new _run();
	
	public static synchronized _run getInstance() {
		if(_run == null) {
			_run = new _run();
		}
		return _run;
	}
	
	public LoginView loginView = null;
		
	public DBConnectionMgr dbMgr = DBConnectionMgr.getInstance(); 
	public static String version ="POPCHAT VER 0.0 입니다.";
	
	public _run(){
	loginView 			= LoginView.getInstance();
	System.out.println("===_run디폴트생성자 생성 성공");
		
	}
	
	
	
	
	public static void main(String[] args){
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
