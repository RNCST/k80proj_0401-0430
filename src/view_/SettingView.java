package view_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * @author OSH Setting button action 받으면 jp_2 3 4 번과 SettingView panel jp_2 3 4
 *         치환
 * 
 */
public class SettingView extends JFrame implements ActionListener {

	JPanel jp_1 = null;
	JPanel jp_2 = null;
	JPanel jp_3 = null;
	JPanel jp_4 = null;
	JLabel jl_title = null;
	String verticalTitle = null;

	JButton jb_changeNickName = null;
	JButton jb_logOut = null;
	JButton jb_signOut = null;

	JButton jb_report = null;
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

	MainLobbyView mainLobbyView = null;
	LoginView   loginView       = null;

	public SettingView() {
		jp_1 = new JPanel();
		jp_2 = new JPanel();
		jp_3 = new JPanel();
		jp_4 = new JPanel();

		jp_1.setBackground(Color.white);
		jp_2.setBackground(Color.white);
		jp_3.setBackground(Color.white);
		jp_4.setBackground(Color.white);

		jspp_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp_1, jp_2);
		jspp_2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_1, jp_3);
		jspp_3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspp_2, jp_4);
		jspp_1.setDividerSize(0);
		jspp_2.setDividerSize(0);
		jspp_3.setDividerSize(0);

		jl_title = new JLabel();
		jl_title.setText("<html>p<br>o<br>p<br><br>c<br>h<br>a<br>t<br><br>v<br>e<br>r<br>.<br>0<br>.<br>0<br>0</html>");

		jb_changeNickName = new JButton("닉네임변경");
		jb_logOut = new JButton("로그아웃");
		jb_signOut = new JButton("회원탈퇴");

		jb_report = new JButton("신고하기");
		jb_creator = new JButton("만든자들");
		jb_ver = new JButton(" 버전   ");

		jb_refresh = new JButton("새로고침");
		jb_unde = new JButton(" 미정 ");
		jb_goback = new JButton("<html>채팅창으로<br>&nbsp돌아가기&nbsp</html>");

		jb_changeNickName.addActionListener(this);
		jb_logOut.addActionListener(this);
		jb_signOut.addActionListener(this);

		jb_report.addActionListener(this);
		jb_creator.addActionListener(this);
		jb_ver.addActionListener(this);

		jb_refresh.addActionListener(this);
		jb_unde.addActionListener(this);
		jb_goback.addActionListener(this);

		tb_1 = new TitledBorder(new LineBorder(Color.black));

		ft1 = new Font("Ariel", Font.BOLD, 13);

		System.out.println("===SettingView디폴트생성자 생성 성공");
		
	}

	public void initdisplay(LoginView loginView) {
		loginView = loginView;
		this.loginView =loginView;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		add(jspp_3, BorderLayout.CENTER);
		jspp_1.setDividerLocation(20);
		jspp_2.setDividerLocation(520);
		jspp_3.setDividerLocation(1020);
		jl_title.setFont(ft1);
		jp_1.setBorder(tb_1);

		jp_1.add(jl_title);

		jp_2.setBorder(tb_1);
		jp_2.setLayout(new FlowLayout());
		jp_2.add(jb_changeNickName);
		jp_2.add(jb_logOut);
		jp_2.add(jb_signOut);

		jp_3.setBorder(tb_1);
		jp_3.setLayout(new FlowLayout());
		jp_3.add(jb_report);
		jp_3.add(jb_creator);
		jp_3.add(jb_ver);

		jp_4.setBorder(tb_1);
		jp_4.setLayout(new FlowLayout());
		jp_4.add(jb_refresh);
		jp_4.add(jb_unde);
		jp_4.add(jb_goback);

		setSize(1120, 400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		System.out.println("===SettingView initdisplay(); 실행성공");
	}

	public static void main(String[] args) {
		SettingView sv = new SettingView();
//		System.out.println(sv.verticalTitle);
//		sv.initdisplay();
		;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (jb_changeNickName == obj) {

		} else if (jb_logOut == obj) {
			mainLobbyView = new MainLobbyView();
			loginView.closedMainDisplay();
			dispose();
			try {
				loginView = new LoginView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			loginView.initdisplay();
			
		} else if (jb_signOut == obj) {

		} else if (jb_report == obj) {

		} else if (jb_creator == obj) {

		} else if (jb_ver == obj) {

		} else if (jb_refresh == obj) {

		} else if (jb_unde == obj) {

		} else if (jb_goback == obj) {
			this.dispose();
		}

	}
}