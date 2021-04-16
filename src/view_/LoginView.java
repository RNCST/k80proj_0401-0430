package view_;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import Server_Client_Thread.ProjectProtocol;
import Server_Client_Thread_old.Protocol;
import pVO.MemberVO;
import run_.clientRun;

/**
 * @author OSH LoginView
 * 
 */
public class LoginView extends JFrame implements ActionListener {
	private static LoginView loginView = new LoginView();

	Socket clientSocket = null;
	ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;

	JButton jb_login = null;
	JButton jb_cancel = null;
	JButton jb_signUp = null;
	JButton jb_search = null;

	public JTextField jtf_id = null;
	public JPasswordField jtf_pw = null;
	File file = null;

	JLabel jl_id = null;
	JLabel jl_pw = null;
	JLabel jl_version = null;
	JLabel jl_gif = null;

	ImageIcon icon = null;
	ImageIcon changei = null;
	Image sizedImg = null;

	URL url = null;

	Font font = null;
	private String getID = "123";
	private String getPW = "123";
	MemberVO[] memberList = null;

	public void getInfo(Socket s, ObjectOutputStream oos, ObjectInputStream ois) {
		this.clientSocket = s;
		this.oos = oos;
		this.ois = ois;
		System.out.println(this.clientSocket);
		System.out.println(this.oos);
		System.out.println(this.ois);
	}

	public Socket setSocket() {
		return this.clientSocket;
	}

	public ObjectOutputStream setOOS() {
		return this.oos;
	}

	public ObjectInputStream setOIS() {
		return this.ois;
	}

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

	DAO dblogic = new DAO();

	public static synchronized LoginView getInstance() {
		if (loginView == null) {
			loginView = new LoginView();

		}
		return loginView;
	}

	public LoginView() {
		jb_login = new JButton("LOGIN");
		jb_cancel = new JButton("CANCEL");
		jb_signUp = new JButton("회원가입");
		jb_search = new JButton("ID,PW찾기");

		jb_login.addActionListener(this);
		jb_cancel.addActionListener(this);
		jb_signUp.addActionListener(this);
		jb_search.addActionListener(this);

		jtf_id = new JTextField();
		jtf_pw = new JPasswordField();

		jl_gif = new JLabel(icon);
		jl_id = new JLabel("ID :");
		jl_pw = new JLabel("PW :");
		jl_version = new JLabel(clientRun.version);

		System.out.println("===LoginView 디폴트생성자 생성 성공");

		font = new Font("휴먼모음T", Font.PLAIN, 11);

	}

	public void nullCheck() {
		System.out.println(clientSocket);
		System.out.println(oos);
		System.out.println(ois);
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

		jl_id.setBounds(30, 260, 60, 30);
		jtf_id.setBounds(60, 265, 90, 20);
		jl_pw.setBounds(30, 300, 60, 30);
		jtf_pw.setBounds(60, 305, 90, 20);

		jb_login.setBounds(170, 265, 80, 60);
		jb_login.setFont(font);

		jb_signUp.setBounds(20, 380, 90, 30);
		jb_signUp.setFont(font);
		jb_search.setBounds(140, 380, 90, 30);
		jb_search.setFont(font);
//		jl_gif.setBounds(250, 0, 300, 300);

		setSize(270, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		System.out.println("===LoginView initdisplay(); 실행성공");
	}

	public static void main(String[] args) {

		LoginView.getInstance().initdisplay();
		LoginView.getInstance().nullCheck();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainLobbyView mainLobbyViewInstance = MainLobbyView.getInstance();
		SignUpView signUpViewInstance = SignUpView.getInstance();
		SearchView searchViewInstacne = SearchView.getInstance();

		Object obj = e.getSource();
		if (jb_login == obj) {
			try {
				oos.writeObject(ProjectProtocol.Login + ProjectProtocol.seperator + "beforeLogin"
						+ ProjectProtocol.seperator + "nomsg");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (jb_cancel == obj)
		{
			this.dispose();
		} else if (jb_signUp == obj) {
			try {
				oos.writeObject(ProjectProtocol.SignUp + ProjectProtocol.seperator + "beforeLogin"
						+ ProjectProtocol.seperator + "nomsg");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (jb_search == obj) {
			try {
				oos.writeObject(ProjectProtocol.Search + ProjectProtocol.seperator + "beforeLogin"
						+ ProjectProtocol.seperator + "nomsg");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
