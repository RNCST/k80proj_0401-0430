package view_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Server_Client_Thread.ChattClientThread;
import Server_Client_Thread.Protocol;

public class MainLobbyViewWithClient extends JFrame implements ActionListener {
	private static MainLobbyViewWithClient mainLobbyView = new MainLobbyViewWithClient();

//	Logger logger = LogManager.getLogger(MainLobbyView.class);
//======================================================================
	Socket clientSocket = null;
	public ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;
	public String nickName = null;
	public String afterName = null;
//============================================================ ==========
	
	JPanel jp_1 = null;
	JPanel jp_2m = null;
	JPanel jp_2s = null;
	JPanel jp_3m = null;
	JPanel jp_3s = null;
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
	JButton jb_goback = null;

	JButton jb_changePW = null;
	JButton jb_logOut = null;
	JButton jb_signOut = null;

//	JButton jb_changeNickname = null;
	JButton jb_creator = null;
	JButton jb_ver = null;

	public JTextField jtf_gettext = null;
	public JTextArea jta_showtext1 = null;
	public JTextArea jta_showtext2 = null;
	public JTextArea jta_showtext3 = null;
	public JTextArea jta_showtext4 = null;
	public JTextArea jta_showtext5 = null;
	public JTextArea jta_showtext6 = null;
	public JTextArea jta_showtext7 = null;
	public JTextArea jta_showtext8 = null;
	public JTextArea jta_showtext9 = null;
	
	Vector<JTextArea> vjta        = new Vector<>();
	Vector<JScrollPane> vjsp      = new Vector<>(); 
	
	JScrollPane jsp_scroll1 = null;
	JScrollPane jsp_scroll2 = null;
	JScrollPane jsp_scroll3 = null;
	JScrollPane jsp_scroll4 = null;
	JScrollPane jsp_scroll5 = null;
	JScrollPane jsp_scroll6 = null;
	JScrollPane jsp_scroll7 = null;
	JScrollPane jsp_scroll8 = null;
	JScrollPane jsp_scroll9 = null;

	TitledBorder tb_1 = null;
	TitledBorder tb_2 = null;
	TitledBorder tb_3 = null;
	TitledBorder tb_4 = null;

	JSplitPane jspp_1 = null;
	JSplitPane jspp_2 = null;
	JSplitPane jspp_3 = null;

	Font ft1 = null;
	Font ft2 = null;

//======================================================================
	String r1Score = null;
	String room1 = "RoomScore"+r1Score;
	String r2Score = null;
	String room2 = "RoomScore"+r2Score;
	String r3Score = null;
	String room3 = "RoomScore"+r3Score;
	String r4Score = null;
	String room4 = "RoomScore"+r4Score;
	String r5Score = null;
	String room5 = "RoomScore"+r5Score;
	String r6Score = null;
	String room6 = "RoomScore"+r6Score;
	String r7Score = null;
	String room7 = "RoomScore"+r7Score;
	String r8Score = null;
	String room8 = "RoomScore"+r8Score;
	String r9Score = null;
	String room9 = "RoomScore"+r9Score;
	
	int r1_score = 10;
	int r2_score = 20;
	int r3_score = 30;
	int r4_score = 40;
	int r5_score = 50;
	int r6_score = 60;
	int r7_score = 70;
	int r8_score = 80;
	int r9_score = 90;

//======================================================================	
	public static MainLobbyViewWithClient getInstance() {
		if (mainLobbyView == null) {
			mainLobbyView = new MainLobbyViewWithClient();
		}
		return mainLobbyView;
	}

	public MainLobbyViewWithClient() {
		jp_1 = new JPanel();
		jp_2m = new JPanel();
		jp_3m = new JPanel();
		jp_4 = new JPanel();
		jp_message = new JPanel();

		jp_2s = new JPanel();
		jp_3s = new JPanel();

		jp_1.setBackground(Color.white);
		jp_2m.setBackground(Color.white);
		jp_3m.setBackground(Color.white);
		jp_4.setBackground(Color.white);

		jspp_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp_1, jp_2m);
		jspp_2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_1, jp_3m);
		jspp_3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_2, jp_4);
		jspp_1.setDividerSize(0);
		jspp_2.setDividerSize(0);
		jspp_3.setDividerSize(0);

		jl_title = new JLabel();
		jl_title.setText(
				"<html>p<br>o<br>p<br><br>c<br>h<br>a<br>t<br><br>v<br>e<br>r<br>.<br>0<br>.<br>0<br>0</html>");

		jb_r1 = new JButton(room1);
		jb_r2 = new JButton(room2);
		jb_r3 = new JButton(room3);
		jb_r4 = new JButton(room4);
		jb_r5 = new JButton(room5);
		jb_r6 = new JButton(room6);
		jb_r7 = new JButton(room7);
		jb_r8 = new JButton(room8);
		jb_r9 = new JButton(room9);

		jb_send = new JButton("전송");

		jb_changeNickname = new JButton("<html>닉네임<br>변  경</html>");
		jb_unde = new JButton("미정");
		jb_setting = new JButton("설 정");

		jb_changePW = new JButton("비밀번호변경");
		jb_logOut = new JButton("로그아웃");
		jb_signOut = new JButton("회원탈퇴");

//		jb_changeNickname = new JButton("1");
		jb_creator = new JButton("만든자들");
		jb_ver = new JButton(" 버전   ");

		jb_goback = new JButton("돌아가기");

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
		jb_goback.addActionListener(this);

		jb_changePW.addActionListener(this);
		jb_logOut.addActionListener(this);
		jb_signOut.addActionListener(this);

//		jb_changeNickname.addActionListener(this);
		jb_creator.addActionListener(this);
		jb_ver.addActionListener(this);

		jtf_gettext = new JTextField();
		jta_showtext1 = new JTextArea();
		jta_showtext2 = new JTextArea();
		jta_showtext3 = new JTextArea();
		jta_showtext4 = new JTextArea();
		jta_showtext5 = new JTextArea();
		jta_showtext6 = new JTextArea();
		jta_showtext7 = new JTextArea();
		jta_showtext8 = new JTextArea();
		jta_showtext9 = new JTextArea();
		
		jta_showtext1.setForeground(Color.BLACK);
		jta_showtext2.setForeground(Color.BLACK);
		jta_showtext3.setForeground(Color.BLACK);
		jta_showtext4.setForeground(Color.BLACK);
		jta_showtext5.setForeground(Color.BLACK);
		jta_showtext6.setForeground(Color.BLACK);
		jta_showtext7.setForeground(Color.BLACK);
		jta_showtext8.setForeground(Color.BLACK);
		jta_showtext9.setForeground(Color.BLACK);
		
		vjta.add(jta_showtext1);
		vjta.add(jta_showtext2);
		vjta.add(jta_showtext3);
		vjta.add(jta_showtext4);
		vjta.add(jta_showtext5);
		vjta.add(jta_showtext6);
		vjta.add(jta_showtext7);
		vjta.add(jta_showtext8);
		vjta.add(jta_showtext9);
		
		vjsp.add(jsp_scroll1);
		vjsp.add(jsp_scroll2);
		vjsp.add(jsp_scroll3);
		vjsp.add(jsp_scroll4);
		vjsp.add(jsp_scroll5);
		vjsp.add(jsp_scroll6);
		vjsp.add(jsp_scroll7);
		vjsp.add(jsp_scroll8);
		vjsp.add(jsp_scroll9);
		
		jtf_gettext.addActionListener(this);
		
		for(int i = 0 ; i < 9 ; i++) {
			vjsp.elementAt(i) = new JScrollPane(vjta.elementAt(i));
			vjsp.get(i) = new JScrollPane(vjta.elementAt(i));
			jsp_scroll1 = new JScrollPane(vjta.elementAt(i));
		}
		
		jsp_scroll1 = new JScrollPane(jta_showtext1);
		jsp_scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp_scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tb_1 = new TitledBorder(new LineBorder(Color.black));

		ft1 = new Font("Ariel", Font.BOLD, 13);
		ft2 = new Font("돋움", Font.BOLD, 12);

		System.out.println("===MainLobby디폴트생성자 생성 성공");
	}

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

		jp_2m.setBorder(tb_1);
		jp_2m.setLayout(new BorderLayout());
//==========점수따라서 jsp_scroll1~9 센터에 붙이게
		jp_2m.add("Center", jsp_scroll1);
		
		for(int i = 0 ; i<9 ; i++) {
			vjta.elementAt(i).setEnabled(false);
			vjta.elementAt(i).setLineWrap(true);
			vjta.elementAt(i).setOpaque(false);
			vjta.elementAt(i).setFont(ft2);
			vjta.elementAt(i).setForeground(Color.black);
		}
		
//		jta_showtext1.setEnabled(false);
//		jta_showtext1.setLineWrap(true);
//		jta_showtext1.setOpaque(false);
//		jta_showtext1.setFont(ft2);
//		jta_showtext1.setForeground(Color.black);
		jp_2m.add("South", jp_message);
		jp_message.setLayout(new BorderLayout());
		jp_message.add("Center", jtf_gettext);
		jp_message.add("East", jb_send);

		jp_3m.setBorder(tb_1);
		jp_3m.setLayout(new GridLayout(3, 3, 10, 10));
		jp_3m.add(jb_r1);
		jp_3m.add(jb_r2);
		jp_3m.add(jb_r3);
		jp_3m.add(jb_r4);
		jp_3m.add(jb_r5);
		jp_3m.add(jb_r6);
		jp_3m.add(jb_r7);
		jp_3m.add(jb_r8);
		jp_3m.add(jb_r9);

		jp_4.setBorder(tb_1);
		jp_4.setLayout(new GridLayout(3, 1, 0, 0));
		jp_4.add(jb_changeNickname);
		jp_4.add(jb_unde);
		jb_unde.setEnabled(false);
		jp_4.add(jb_setting);
		this.setTitle(nickName + "님의 채팅창");
		setSize(1120, 400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
//		setResizable(false);

//		logger.info("===MainLobbyView initdisplay(); 실행성공");
		System.out.println("===run MainLobbyView initdisplay();");
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		MainLobbyViewWithClient mainLobbyViewWithClient = new MainLobbyViewWithClient();
		mainLobbyViewWithClient.initdisplay();
		mainLobbyViewWithClient.init();
	}

	public void init() {
		System.out.println("===run MainLobbyView init()");
		try {
			clientSocket = new Socket("127.0.0.1", 5700);
			oos = new ObjectOutputStream(clientSocket.getOutputStream());// 홀수 소켓에서 처리
			ois = new ObjectInputStream(clientSocket.getInputStream());// 짝수 소켓에서 처리
			oos.writeObject(100 + "#" + nickName); // 말하는 순간
			ChattClientThread chattClientThread = new ChattClientThread(this);
			chattClientThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void setMainLobbyView() {
		System.out.println("===run setMainLobbyView()");
		getContentPane().removeAll();

		jp_4 = new JPanel();
		jp_4.setBackground(Color.white);

		jspp_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp_1, jp_2m);
		jspp_2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_1, jp_3m);
		jspp_3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_2, jp_4);
		jspp_1.setDividerSize(0);
		jspp_2.setDividerSize(0);
		jspp_3.setDividerSize(0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		add(jspp_3, BorderLayout.CENTER);
		jspp_1.setDividerLocation(20);
		jspp_2.setDividerLocation(520);
		jspp_3.setDividerLocation(1020);
		jl_title.setFont(ft1);
		jp_1.setBorder(tb_1);

		jp_1.add(jl_title);

		jp_2m.setBorder(tb_1);
		jp_2m.setLayout(new BorderLayout());
		jp_2m.add("Center", jsp_scroll1);
		
		jta_showtext1.setEnabled(false);
		jta_showtext1.setLineWrap(true);
		jta_showtext1.setOpaque(false);
		jta_showtext1.setFont(ft2);
		jta_showtext1.setForeground(Color.black);
		
		jp_2m.add("South", jp_message);
		jp_message.setLayout(new BorderLayout());
		jp_message.add("Center", jtf_gettext);
		jp_message.add("East", jb_send);

		jp_3m.setBorder(tb_1);
		jp_3m.setLayout(new GridLayout(3, 3, 10, 10));
		jp_3m.add(jb_r1);
		jp_3m.add(jb_r2);
		jp_3m.add(jb_r3);
		jp_3m.add(jb_r4);
		jp_3m.add(jb_r5);
		jp_3m.add(jb_r6);
		jp_3m.add(jb_r7);
		jp_3m.add(jb_r8);
		jp_3m.add(jb_r9);

		jp_4.setBorder(tb_1);
		jp_4.setLayout(new GridLayout(3, 1, 0, 0));
		jp_4.add(jb_changeNickname);
		jp_4.add(jb_unde);
		jb_unde.setEnabled(false);
		jp_4.add(jb_setting);
		this.setTitle(nickName + "님의 채팅창");
		setSize(1120, 400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
//		setResizable(false);

//		logger.info("===MainLobbyView initdisplay(); 실행성공");
	}

	public void setSettingView() {
		System.out.println("===run setSettingView()");
		getContentPane().removeAll();

		jp_4 = new JPanel();
		jp_4.setBackground(Color.white);

		jspp_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp_1, jp_2s);
		jspp_2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_1, jp_3s);
		jspp_3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_2, jp_4);
		jspp_1.setDividerSize(0);
		jspp_2.setDividerSize(0);
		jspp_3.setDividerSize(0);
		add(jspp_3, BorderLayout.CENTER);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		add(jspp_3, BorderLayout.CENTER);
		jspp_1.setDividerLocation(20);
		jspp_2.setDividerLocation(520);
		jspp_3.setDividerLocation(1020);
		jl_title.setFont(ft1);
		jp_1.setBorder(tb_1);

		jp_1.add(jl_title);

		jp_2s.setBorder(tb_1);
		jp_2s.setLayout(new FlowLayout());
		jp_2s.add(jb_changePW);
		jp_2s.add(jb_logOut);
		jp_2s.add(jb_signOut);

		jp_3s.setBorder(tb_1);
		jp_3s.setLayout(new FlowLayout());
		jp_3s.add(jb_changeNickname);
		jp_3s.add(jb_creator);
		jp_3s.add(jb_ver);

		jp_4.setBorder(tb_1);
		jp_4.setLayout(new GridLayout(3, 1, 0, 0));
		jp_4.add(jb_changeNickname);
		jp_4.add(jb_unde);
		jb_unde.setEnabled(false);
		jp_4.add(jb_goback);

		setSize(1120, 400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
//		setResizable(false);
		System.out.println("===SettingView initdisplay(); 실행성공");
//		logger.info("===SettingView initdisplay(); 실행성공");

	}

	public int setScore(int roomnum) {

		return roomnum;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		LoginClient loginClient = new LoginClient();
		String msg = jtf_gettext.getText();
		MainLobbyView mainLobbyViewInstance = MainLobbyView.getInstance();
		LoginView loginViewInstance = LoginView.getInstance();
		SignOutView signOutViewInstance = SignOutView.getInstacne();
		ChangePWView changePWViewInstance = ChangePWView.getInstance();
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
				oos.writeObject(Protocol.MESSAGE + Protocol.seperator + nickName + Protocol.seperator + msg);
				jtf_gettext.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (jb_send == obj) {
			System.out.println("jb_send action");
			try {
				oos.writeObject(Protocol.MESSAGE + Protocol.seperator + nickName + Protocol.seperator + msg);
				jtf_gettext.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (jb_changeNickname == obj) {
			afterName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
			try {
				oos.writeObject(Protocol.CHANGE + Protocol.seperator + nickName + Protocol.seperator + afterName
						+ Protocol.seperator + "닉네임변경");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (jb_unde == obj) {

		} else if (jb_setting == obj) {
			setSettingView();

		} else if (jb_goback == obj) {
			setMainLobbyView();
		} else if (jb_creator == obj) {
			JOptionPane.showMessageDialog(this, "1111111");
		} else if (jb_ver == obj) {
			JOptionPane.showMessageDialog(this, "0.00000");
		} else if (jb_changePW == obj) {
			changePWViewInstance.initDisplay();

		} else if (jb_logOut == obj) {
			dispose();
			mainLobbyViewInstance.dispose();
			loginViewInstance.initdisplay();

		} else if (jb_signOut == obj) {
			signOutViewInstance.initDisplay();
		}

	}

}
