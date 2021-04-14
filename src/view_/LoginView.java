package view_;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

import DAO.DAO;
import pVO.MemberVO;
import run_._run;

/**
 * @author OSH
 * LoginView
 * 
 */
public class LoginView extends JFrame implements ActionListener, Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private static LoginView loginView = new LoginView() ;
//	Logger logger = LogManager.getLogger(LoginView.class);
	
	
	
	JButton        jb_login   = null;
	JButton        jb_cancel  = null;
	JButton        jb_signUp  = null;
	JButton        jb_search  = null; 

	JTextField     jtf_id     = null;
	JPasswordField jtf_pw     = null;
	File           file       = null;

	JLabel         jl_id      = null;
	JLabel         jl_pw      = null;
	JLabel         jl_version   = null;
	JLabel         jl_gif     = null;
	
	ImageIcon      icon       = null;
	ImageIcon      changei    = null;
	Image          sizedImg   = null;
	
	URL            url        = null;
	
	
	Font   font                 = null;
	private String       getID   = "123";
	private String       getPW	= "123";
	MemberVO[]     memberList   =null;

public String getGetID() {
		return getID;
	}

	public void setGetID(String getID) {
		this.getID = getID;
	}

	public String getGetPW() {
		return getPW;
	}

	public void setGetPW(String getPW) {
		this.getPW = getPW;
	}

	//	LoginVO         LVO          = null;
	DAO         dblogic      = new DAO();
//	prjDAO          pDAO         = null;		
	
	public static synchronized LoginView getInstance() {
		if(loginView == null) {
			loginView = new LoginView();
			
		}
		return loginView;
	}
	
	public LoginView() {
		/*
		 * DB 연동을 여기에서 할지 RUN에서할지
		 */
//		System.setProperty
//		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
		jb_login  = new JButton("LOGIN");
		jb_cancel = new JButton("CANCEL");
		jb_signUp = new JButton("회원가입");
		jb_search = new JButton("ID,PW찾기");
		
		jb_login.addActionListener(this);
		jb_cancel.addActionListener(this);
		jb_signUp.addActionListener(this);
		jb_search.addActionListener(this);
		
		jtf_id = new JTextField();
		jtf_pw = new JPasswordField();
		
//		File f = new File("coding_cat.gif");
//		URL img = f.toURL();
//		icon = new ImageIcon(img);
//		icon.setImage(icon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));

		jl_gif = new JLabel(icon);
		jl_id = new JLabel("ID :");
		jl_pw = new JLabel("PW :");
		jl_version = new JLabel(_run.version);
		

//		logger.info("===LoginView 디폴트생성자 생성 성공");
		System.out.println("===LoginView 디폴트생성자 생성 성공");
		

		font = new Font("휴먼모음T", Font.PLAIN, 11);
		
		
		
//=======================================================================================================================
//	    addWindowListener
//	    (new WindowAdapter() {   // 내부 무명클래스로서 
//
//			@Override
//
//			public void windowClosing(WindowEvent e) {  // 이벤트프로그램
//
//				System.exit(0);
//
//			}
//	    });
//=======================================================================================================================
	    
	}
	

	public void initdisplay() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);

		add(jb_login);
		add(jb_cancel);
		add(jb_signUp);
		add(jb_search);
		
		add(jl_version);
		add(jl_gif);
		
		add(jl_id);
		add(jtf_id);
		add(jl_pw);
		add(jtf_pw);

		
		jl_version.setBounds(0, 425, 200, 40);
		
		jl_id.setBounds(30,260 ,60 ,30 );
		jtf_id.setBounds(60,265 ,90 ,20 ); 	   
		jl_pw.setBounds(30,300 ,60 ,30 );
		jtf_pw.setBounds(60,305 ,90 ,20 );
		
		jb_login.setBounds(170, 265, 80, 60);
		jb_login.setFont(font);
		
//		jb_cancel.setBounds(200, 400, 70, 70);
		jb_signUp.setBounds(20, 380, 90, 30);
		jb_signUp.setFont(font);
		jb_search.setBounds(140, 380, 90, 30);
		jb_search.setFont(font);
		
//		jl_gif.setBounds(250, 0, 300, 300);

		setSize(270, 500);
		setLocationRelativeTo(null); 
		setVisible(true);
//		logger.info("===LoginView initdisplay(); 실행성공");
		System.out.println("===LoginView initdisplay(); 실행성공");
	}

	public static void main(String[] args){
		
		LoginView.getInstance().initdisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainLobbyView mainLobbyViewInstance = MainLobbyView.getInstance();
		MainLobbyViewWithClient mainLobbyViewWithClient = MainLobbyViewWithClient.getInstance();
		SignUpView signUpViewInstance = SignUpView.getInstance();
		SearchView searchViewInstacne = SearchView.getInstance();
		
		Object obj = e.getSource();
		if(jb_login == obj){
//========== DB 확인 =====================================//	
			System.out.println(jtf_id.getText());
			System.out.println(jtf_pw.getText());
			if(dblogic.runLogin(jtf_id.getText(),jtf_pw.getText())==true) {
				System.out.println("===loginServer Thread.start(run) ");
				setGetID(jtf_id.getText());
				setGetPW(jtf_pw.getText());
				mainLobbyViewWithClient.initdisplay();
				mainLobbyViewWithClient.init();
				System.out.println(mainLobbyViewWithClient.clientSocket);
				System.out.println(mainLobbyViewWithClient.oos);
				System.out.println(mainLobbyViewWithClient.ois);
				jtf_id.setText("");
				jtf_pw.setText("");
			this.dispose();
			
			} else {
				JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 확인해주세요.");
				jtf_id.setText("");
				jtf_pw.setText("");
				
			}
			
		
//========== DB 확인 =====================================//	
			
		}else if(jb_cancel == obj){
			this.dispose();
		}else if(jb_signUp == obj){
			signUpViewInstance.initdisplay();
		}else if(jb_search == obj){
			searchViewInstacne.initdisplay();
		}
	}
		

}
