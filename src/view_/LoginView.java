package view_;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import run_._run;

/**
 * @author OSH
 * LoginView
 * 
 */
public class LoginView extends JFrame implements ActionListener {
	
	
	
	JButton        jb_login   = null;
	JButton        jb_cancel  = null;
	JButton        jb_signUp  = null;
	JButton        jb_search  = null; 

	JTextField     jtf_id     = null;
	JTextField     jtf_pw     = null;
	File           file       = null;

	JLabel         jl_id      = null;
	JLabel         jl_pw      = null;
	JLabel         jl_infor   = null;
	JLabel         jl_gif     = null;
	
	ImageIcon      icon       = null;
	ImageIcon      changei    = null;
	Image          sizedImg   = null;
	
	URL            url        = null;
	
	MainLobbyView mainLobbyView = null;
	SignUpView    signUpView    = null;
	SearchView    searchView    = null; 
	

//	LoginVO         LVO          = null;
//	DBLogic         dblogic      = null;
//	prjDAO          pDAO         = null;
	public LoginView(_run _run) throws IOException {
		this();
		
	}
	public LoginView() throws IOException {
		/*
		 * DB 연동을 여기에서 할지 RUN에서할지
		 */
		
		
		jb_login  = new JButton("LOGIN");
		jb_cancel = new JButton("CANCEL");
		jb_signUp = new JButton("회원가입");
		jb_search = new JButton("ID,PW찾기");
		
		jb_login.addActionListener(this);
		jb_cancel.addActionListener(this);
		jb_signUp.addActionListener(this);
		jb_search.addActionListener(this);

		jtf_pw = new JTextField();
		
		File f = new File("coding_cat.gif");
		URL img = f.toURL();
		icon = new ImageIcon(img);
		icon.setImage(icon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));

		jl_gif = new JLabel(icon);
		jl_id = new JLabel("ID :");
		jl_pw = new JLabel("PW :");
		jl_infor = new JLabel(_run.version);

		jb_login.setBounds(100, 400, 70, 70);
		jb_cancel.setBounds(200, 400, 70, 70);
		jb_signUp.setBounds(400, 400, 70, 70);
		jb_search.setBounds(500, 400, 70, 70);
		
		jl_gif.setBounds(250, 0, 300, 300);
		System.out.println("===LoginView 디폴트생성자 생성 성공");
		mainLobbyView = new MainLobbyView();
		signUpView    = new SignUpView();
		searchView    = new SearchView();
		
		
//=======================================================================================================================
	    addWindowListener
	    (new WindowAdapter() {   // 내부 무명클래스로서 

			@Override

			public void windowClosing(WindowEvent e) {  // 이벤트프로그램

				System.exit(0);

			}
	    });
//=======================================================================================================================
	    
	}
	public void closedMainDisplay() {
		mainLobbyView.dispose();
		
	}
	

	public void initdisplay() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.white);

		this.add(jb_login);
		this.add(jb_cancel);
		this.add(jb_signUp);
		this.add(jb_search);

		this.add(jl_gif);


		this.setSize(800, 600);
		this.setLocationRelativeTo(null); 
		this.setVisible(true);
		System.out.println("===LoginView initdisplay(); 실행성공");
	}

	public static void main(String[] args) throws IOException {
		LoginView lv = new LoginView();
		lv.initdisplay();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(jb_login == obj){
//========== DB 확인 =====================================//	
			mainLobbyView.initdisplay(this);
			this.dispose();
			
		
//========== DB 확인 =====================================//	
			
		}else if(jb_cancel == obj){
			this.dispose();
		}else if(jb_signUp == obj){
			signUpView.initdisplay();
		}else if(jb_search == obj){
			searchView.initdisplay();
		}
	}
		

}
