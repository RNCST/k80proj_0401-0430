package view_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

import DAO.DAO;

/**
 * @author OSH Setting button action 받으면 jp_2 3 4 번과 SettingView panel jp_2 3 4
 *         치환
 * 
 */
public class SettingView extends JFrame implements ActionListener {
//	Logger logger = LogManager.getLogger(SettingView.class);
	private static SettingView settingView = new SettingView();
	
	JPanel jp_1 = null;
	JPanel jp_2s = null;
	JPanel jp_3s = null;
	JPanel jp_4 = null;
	JLabel jl_title = null;
	String verticalTitle = null;

	JButton jb_changePW = null;
	JButton jb_logOut = null;
	JButton jb_signOut = null;

	JButton jb_changeNickname = null;
	JButton jb_creator = null;
	JButton jb_ver = null;

	JButton jb_refresh = null;
	JButton jb_unde = null;
	JButton jb_goback = null;

	Font ft1 = null;

	TitledBorder tb_1 = null;
	TitledBorder tb_2 = null;
	TitledBorder tb_3 = null;
	TitledBorder tb_4 = null;

	JSplitPane jspp_1 = null;
	JSplitPane jspp_2 = null;
	JSplitPane jspp_3 = null;

	DAO        dblogic      = new DAO();
	
	public static SettingView getInstance() {
		if(settingView == null) {
			settingView = new SettingView();
		}
		return settingView;
	}
	public SettingView() {
		jp_1 = new JPanel();
		jp_2s = new JPanel();
		jp_3s = new JPanel();
		jp_4 = new JPanel();

		jp_1.setBackground(Color.white);
		jp_2s.setBackground(Color.white);
		jp_3s.setBackground(Color.white);
		jp_4.setBackground(Color.white);

		jspp_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp_1, jp_2s);
		jspp_2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_1, jp_3s);
		jspp_3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_2, jp_4);
		jspp_1.setDividerSize(0);
		jspp_2.setDividerSize(0);
		jspp_3.setDividerSize(0);

		jl_title = new JLabel();
		jl_title.setText("<html>p<br>o<br>p<br><br>c<br>h<br>a<br>t<br><br>v<br>e<br>r<br>.<br>0<br>.<br>0<br>0</html>");

		jb_changePW = new JButton("비밀번호변경");
		jb_logOut = new JButton("로그아웃");
		jb_signOut = new JButton("회원탈퇴");

		jb_changeNickname = new JButton("1");
		jb_creator = new JButton("만든자들");
		jb_ver = new JButton(" 버전   ");

		jb_refresh = new JButton("<html>닉네임<br>변  경</html>");
		jb_unde = new JButton("           ");
		jb_goback = new JButton("<html>돌아가기</html>");

		jb_changePW.addActionListener(this);
		jb_logOut.addActionListener(this);
		jb_signOut.addActionListener(this);

		jb_changeNickname.addActionListener(this);
		jb_creator.addActionListener(this);
		jb_ver.addActionListener(this);

		jb_refresh.addActionListener(this);
		jb_unde.addActionListener(this);
		jb_goback.addActionListener(this);

		tb_1 = new TitledBorder(new LineBorder(Color.black));

		ft1 = new Font("Ariel", Font.BOLD, 13);

		System.out.println("===SettingView디폴트생성자 생성 성공");
//		logger.info("===SettingView디폴트생성자 생성 성공");
		
	}

	public void initdisplay() {
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
		jp_4.setLayout(new GridLayout(3,1,0,0));
		jp_4.add(jb_refresh);
		jp_4.add(jb_unde);
		jb_unde.setEnabled(false);
		jp_4.add(jb_goback);

		setSize(1120, 400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		System.out.println("===SettingView initdisplay(); 실행성공");
//		logger.info("===SettingView initdisplay(); 실행성공");
	}

	public static void main(String[] args) {
//		System.setProperty
//		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
		SettingView sv = new SettingView();
//		System.out.println(sv.verticalTitle);
		sv.initdisplay();
		System.out.println(LoginView.getInstance().getGetID());
//		Logger logger = LogManager.getLogger(SettingView.class);
//		logger.info(LoginView.getInstance().getGetID());
		System.out.println("==="+"LoginView.getInstance().getGetID()");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainLobbyView mainLobbyViewInstance = MainLobbyView.getInstance();
		LoginView loginViewInstance = LoginView.getInstance();
		SignOutView signOutViewInstance = SignOutView.getInstacne();
		ChangePWView changePWViewInstance = ChangePWView.getInstance();
		Object obj = e.getSource();
		if (jb_changePW == obj) {
			changePWViewInstance.initDisplay();

		} else if (jb_logOut == obj) {
			dispose();
			mainLobbyViewInstance.dispose();
			loginViewInstance.initdisplay();
			
		} else if (jb_signOut == obj) {
			signOutViewInstance.initDisplay();

		} else if (jb_changeNickname == obj) {
			
		} else if (jb_creator == obj) {
			JOptionPane.showMessageDialog(this, "1111111");
		} else if (jb_ver == obj) {
			JOptionPane.showMessageDialog(this, "0.00000");
		} else if (jb_refresh == obj) {

		} else if (jb_unde == obj) {

		} else if (jb_goback == obj) {
			this.dispose();
		}

	}
}
