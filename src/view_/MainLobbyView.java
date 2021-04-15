package view_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

import Server_Client_Thread.ProjectProtocol;
import Server_Client_Thread_old.Protocol;

public class MainLobbyView extends JFrame implements ActionListener {
	private static MainLobbyView mainLobbyView = new MainLobbyView();

//	Logger logger = LogManager.getLogger(MainLobbyView.class);
//======================================================================
	Socket clientSocket = null;
	public ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;
//============================================================ ==========

	String nickName = null;
	JPanel jp_1 = null;
	JPanel jp_2 = null;
	JPanel jp_3 = null;
	JPanel jp_4 = null;
	JPanel jp_message = null;
	JLabel jl_title = null;

	JButton jb_r1 = null;
	JButton jb_r2 = null;
	JButton jb_r3 = null;
	JButton jb_r4 = null;
	JButton jb_r5 = null;
	JButton jb_r6 = null;
	JButton jb_r7 = null;
	JButton jb_r8 = null;
	JButton jb_r9 = null;

	JButton jb_send = null;

	JButton jb_changeNickname = null;
	JButton jb_unde = null;
	JButton jb_setting = null;

	public JTextField jtf_gettext = null;
	public JTextArea jta_showtext = null;

	JScrollPane jsp_scroll = null;

	TitledBorder tb_1 = null;
	TitledBorder tb_2 = null;
	TitledBorder tb_3 = null;
	TitledBorder tb_4 = null;

	JSplitPane jspp_1 = null;
	JSplitPane jspp_2 = null;
	JSplitPane jspp_3 = null;

	Font ft1 = null;

	String room1 = null;
	String room2 = null;
	String room3 = null;
	String room4 = null;
	String room5 = null;
	String room6 = null;
	String room7 = null;
	String room8 = null;
	String room9 = null;

	int r1_score = 0;
	int r2_score = 0;
	int r3_score = 0;
	int r4_score = 0;
	int r5_score = 0;
	int r6_score = 0;
	int r7_score = 0;
	int r8_score = 0;
	int r9_score = 0;

	public static MainLobbyView getInstance() {
		if (mainLobbyView == null) {
			mainLobbyView = new MainLobbyView();
		}
		return mainLobbyView;
	}

//	public void setLoginClient(Socket clientSocket, 
//							   ObjectOutputStream oos,
//							   ObjectInputStream  ois,
//							   String nickName) {
//		this.clientSocket =clientSocket;
//		this.oos = oos;
//		this.ois = ois;
//		this.nickName = nickName;
//		logger.info(this.clientSocket);
//		logger.info(this.oos);
//		logger.info(this.ois);
//		logger.info(this.nickName);
//	}

	public MainLobbyView() {
//		System.setProperty
//		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
	    jp_1       = new 	JPanel     ();
	    jp_2       = new 	JPanel     ();
	    jp_3       = new 	JPanel     ();
	    jp_4       = new 	JPanel     ();
	    jp_message = new    JPanel     ();
	    
	    jp_1.setBackground(Color.white);
	    jp_2.setBackground(Color.white);
	    jp_3.setBackground(Color.white);
	    jp_4.setBackground(Color.white);
	    
	    jspp_1     = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jp_1,jp_2);
	    jspp_2     = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspp_1,jp_3);
	    jspp_3     = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspp_2,jp_4);
	    jspp_1.setDividerSize(0);
	    jspp_2.setDividerSize(0);
	    jspp_3.setDividerSize(0);

	    jl_title          = new JLabel();
	    jl_title.setText("<html>p<br>o<br>p<br><br>c<br>h<br>a<br>t<br><br>v<br>e<br>r<br>.<br>0<br>.<br>0<br>0</html>");
	    
	    jb_r1       = new 	JButton    (room1);
	    jb_r2       = new 	JButton    (room2);
	    jb_r3       = new 	JButton    (room3);
	    jb_r4       = new 	JButton    (room4);
	    jb_r5       = new 	JButton    (room5);
	    jb_r6       = new 	JButton    (room6);
	    jb_r7       = new 	JButton    (room7);
	    jb_r8       = new 	JButton    (room8);
	    jb_r9       = new 	JButton    (room9);
	    
	    jb_send     = new   JButton    ("전송");
	    
	    jb_changeNickname = new 	JButton    ("<html>닉네임<br>변  경</html>");
	    jb_unde    = new 	JButton    ("미정");
	    jb_setting = new 	JButton    ("설 정");

	    jb_r1.addActionListener(this);
	    jb_r2.addActionListener(this);
	    jb_r3.addActionListener(this);
	    jb_r4.addActionListener(this);
	    jb_r5.addActionListener(this);
	    jb_r6.addActionListener(this);
	    jb_r7.addActionListener(this);
	    jb_r8.addActionListener(this);
	    jb_r9.addActionListener(this);
	    jb_send.addActionListener(this);
	    jb_changeNickname.addActionListener(this);
	    jb_unde.addActionListener(this);
	    jb_setting.addActionListener(this);
	    	    
	    jtf_gettext = new 	JTextField ();
	    jta_showtext= new 	JTextArea ();
	    jtf_gettext.addActionListener(this);
	
	    jsp_scroll = new 	JScrollPane(jta_showtext);  
	    
	    tb_1     = new TitledBorder(new LineBorder(Color.black));
	    
	    ft1      = new Font("Ariel", Font.BOLD, 13);
	    
//	    try {
//			if(clientSocket == null) {
//				clientSocket = new Socket("127.0.0.1",5085);
//	    		oos = new ObjectOutputStream(clientSocket.getOutputStream());
//	    		ois = new ObjectInputStream (clientSocket.getInputStream());
//	    		oos.writeObject(Protocol.seperator + Protocol.seperator + nickName);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	    	    
//	    logger.info("===MainLobby디폴트생성자 생성 성공");
	    System.out.println("===MainLobby디폴트생성자 생성 성공");
	}

//	public void init() {
////===============================================================================================
//		try {
//			if(clientSocket == null) {
//				clientSocket = new Socket("127.0.0.1",5085);
//				oos = new ObjectOutputStream(clientSocket.getOutputStream());
//				ois = new ObjectInputStream (clientSocket.getInputStream());
//				oos.writeObject(Protocol.seperator + Protocol.seperator + nickName);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////===============================================================================================
//		
//	}
	public void initdisplay() {
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		add(jspp_3, BorderLayout.CENTER);
		jspp_1.setDividerLocation(20);
		jspp_2.setDividerLocation(520);
		jspp_3.setDividerLocation(1020);
		jl_title.setFont(ft1);
		jp_1.setBorder(tb_1);

		jp_1.add(jl_title);

		jp_2.setBorder(tb_1);
		jp_2.setLayout(new BorderLayout());
		jp_2.add("Center", jta_showtext);
		jta_showtext.setEnabled(false);

		jta_showtext.setOpaque(false);
		jp_2.add("South", jp_message);
		jp_message.setLayout(new BorderLayout());
		jp_message.add("Center", jtf_gettext);
		jp_message.add("East", jb_send);

		jp_3.setBorder(tb_1);
		jp_3.setLayout(new GridLayout(3, 3, 10, 10));
		jp_3.add(jb_r1);
		jp_3.add(jb_r2);
		jp_3.add(jb_r3);
		jp_3.add(jb_r4);
		jp_3.add(jb_r5);
		jp_3.add(jb_r6);
		jp_3.add(jb_r7);
		jp_3.add(jb_r8);
		jp_3.add(jb_r9);

		jp_4.setBorder(tb_1);
		jp_4.setLayout(new GridLayout(3, 1, 0, 0));
		jp_4.add(jb_changeNickname);
		jp_4.add(jb_unde);
		jb_unde.setEnabled(false);
		jp_4.add(jb_setting);

		setSize(1120, 400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
//		logger.info("===MainLobbyView initdisplay(); 실행성공");
		System.out.println("===MainLobbyView initdisplay(); 실행성공");
	}

	public void getInfo(Socket s, ObjectOutputStream oos, ObjectInputStream ois) {
		this.clientSocket = s;
		this.oos = oos;
		this.ois = ois;
		System.out.println(this.clientSocket);
		System.out.println(this.oos);
		System.out.println(this.ois);
	}
	
	
	public void refreshButton() {
		jb_r1.setEnabled(true);
		jb_r2.setEnabled(true);
		jb_r3.setEnabled(true);
		jb_r4.setEnabled(true);
		jb_r5.setEnabled(true);
		jb_r6.setEnabled(true);
		jb_r7.setEnabled(true);
		jb_r8.setEnabled(true);
		jb_r9.setEnabled(true);
	}

//	public void init() {
////		logger.info("===run LoginClient init()");
//		System.out.println("===run LoginClient init()");
////		System.out.println(logger);
//		try {
//			clientSocket = new Socket("127.0.0.1", 5085);
//			oos = new ObjectOutputStream(clientSocket.getOutputStream());
//			ois = new ObjectInputStream(clientSocket.getInputStream());
//			oos.writeObject(Protocol.ROOM_IN + Protocol.seperator + nickName);
//			LoginClientThread loginClientThread = new LoginClientThread(this);
//			loginClientThread.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public int setScore(int roomnum) {

		return roomnum;
	}

	public static void main(String[] args) {
//		System.setProperty
//		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
		MainLobbyView mlv = new MainLobbyView();
		mlv.initdisplay();
//		mlv.init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		LoginClient loginClient = new LoginClient();
		String msg = jtf_gettext.getText();
		SettingView settingViewInstance = SettingView.getInstance();
		Object obj = e.getSource();

		if (jb_r1 == obj) {
			refreshButton();
			jb_r1.setEnabled(false);
		} else if (jb_r2 == obj) {
			refreshButton();
			jb_r2.setEnabled(false);
		} else if (jb_r3 == obj) {
			refreshButton();
			jb_r3.setEnabled(false);
		} else if (jb_r4 == obj) {
			refreshButton();
			jb_r4.setEnabled(false);
		} else if (jb_r5 == obj) {
			refreshButton();
			jb_r5.setEnabled(false);
		} else if (jb_r6 == obj) {
			refreshButton();
			jb_r6.setEnabled(false);
		} else if (jb_r7 == obj) {
			refreshButton();
			jb_r7.setEnabled(false);
		} else if (jb_r8 == obj) {
			refreshButton();
			jb_r8.setEnabled(false);
		} else if (jb_r9 == obj) {
			refreshButton();
			jb_r9.setEnabled(false);

		} else if (jtf_gettext == obj) {
			System.out.println("jtf_gettext action");
			try {
				oos.writeObject(ProjectProtocol.MESSAGE + ProjectProtocol.seperator + nickName + ProjectProtocol.seperator + msg);
				jtf_gettext.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (jb_send == obj) {
			System.out.println("jb_send action");
			try {
				oos.writeObject(ProjectProtocol.MESSAGE + ProjectProtocol.seperator + nickName + ProjectProtocol.seperator + msg);
				jtf_gettext.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (jb_changeNickname == obj) {

		} else if (jb_unde == obj) {

		} else if (jb_setting == obj) {
			settingViewInstance.initdisplay();

		}

	}

}
