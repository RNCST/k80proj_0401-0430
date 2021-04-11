package run_;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

import DBConnection.DBConnectionMgr;
import view_.LoginView;

public class _run {
//	Logger logger = LogManager.getLogger(_run.class);
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
//	logger.info("===_run디폴트생성자 생성 성공");
	System.out.println("===_run디폴트생성자 생성 성공");
		
	}
	
	
	
	
	public static void main(String[] args){
//		System.setProperty
//		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
//		Logger logger = LogManager.getLogger(_run.class);
		System.out.println("===시작 성공 ");
		System.out.println("===시작 성공 ");
//		LoginServer loginServer = new LoginServer();
//		Thread thread = new Thread(loginServer);
//		thread.start();
		_run.loginView.initdisplay();
		
			/*
			 * Server Call method
			 * Cliend call method
			 * DBlogic call method
			 * LoginView Call method
			 */
		
   
	}

}
