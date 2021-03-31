package view_;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DBLogic.DBLogic;

public class SignOutView extends JDialog implements ActionListener {
	private static SignOutView signOutView = new SignOutView() ;
	
	JButton jb_signOut = null;
	JButton jb_cancel = null;

	JTextField jtf_id = null;
	JTextField jtf_pw = null;
	JTextField jtf_signOutCheck = null;

	JLabel jl_id = null;
	JLabel jl_pw = null;
	JLabel jl_signOutCheck = null;
	JLabel jl_information = null;

	Font font = null;

	DBLogic dblogic = new DBLogic();
	
	
	public static SignOutView getInstacne() {
		if(signOutView == null) {
			signOutView = new SignOutView();
		}
		return signOutView;
	}
	
	
	public SignOutView() {
		font = new Font("휴먼모음T", Font.PLAIN, 14);

		jb_signOut = new JButton("SignOut");
		jb_cancel = new JButton("Cancel");

		jtf_id = new JTextField();
		jtf_pw = new JTextField();
		jtf_signOutCheck = new JTextField();
		jl_id = new JLabel("ID");
		jl_pw = new JLabel("PW");
		jl_signOutCheck = new JLabel("<html>'회원 탈퇴'<br>를 정자로 써주세요</html>");
		jl_information = new JLabel("<html>로그인한 본인의 아이디와 비밀번호를 <br>확인을 위해 입력해주세요.</html>");

		System.out.println("===SignOutView 디폴트생성자 생성 성공");
	}

	public void initDisplay() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);

		add(jl_information);
		add(jl_id);
		add(jtf_id);
		add(jl_pw);
		add(jtf_pw);
		add(jl_signOutCheck);
		add(jtf_signOutCheck);
		add(jb_signOut);
		add(jb_cancel);
		
		jb_signOut.addActionListener(this);
		jb_cancel.addActionListener(this);
		
		jl_signOutCheck.setHorizontalAlignment(JLabel.CENTER);
		jl_information.setHorizontalAlignment(JLabel.CENTER);

		jl_information.setFont(font);
		jl_signOutCheck.setFont(font);
		jl_information.setBounds(60, 20, 200, 50);
		jl_id.setBounds(60, 80, 60, 30);
		jtf_id.setBounds(170, 85, 90, 20);
		jl_pw.setBounds(60, 120, 60, 30);
		jtf_pw.setBounds(170, 125, 90, 20);
		jl_signOutCheck.setBounds(60, 160, 200, 30);
		jtf_signOutCheck.setBounds(60, 200, 200, 20);

		jb_signOut.setBounds(60, 250, 100, 30);
		jb_cancel.setBounds(170, 250, 100, 30);

		setSize(350, 350);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		SignOutView signOutView = new SignOutView();
		signOutView.initDisplay();
	}
//	null.toString 일때 ==> 방어하는 목적으로 
	@Override
	public void actionPerformed(ActionEvent e) {
		LoginView loginViewInstance = LoginView.getInstance();
		MainLobbyView mainLobbyViewInstance = MainLobbyView.getInstance();
		SettingView settingViewInstance = SettingView.getInstance();
		Object obj = e.getSource();
		if (jb_signOut == obj) {
			System.out.println("버튼액션");
			boolean isOk    = (
					loginViewInstance.getGetID() == jtf_id.getText() && 
					loginViewInstance.getGetPW() == jtf_pw.getText() && 
					"회원 탈퇴".equals(jtf_signOutCheck.getText()));
			if (isOk = true) {
				System.out.println("회원 확인 true");
				dblogic.runSignOut(loginViewInstance.getGetID(), loginViewInstance.getGetPW());
				JOptionPane.showMessageDialog(this, "회원 탈퇴가 완료되었습니다.");
				loginViewInstance.setGetID("123");
				loginViewInstance.setGetPW("123");
				jtf_id.setText("");
				jtf_pw.setText("");
				jtf_signOutCheck.setText("");
				loginViewInstance.dispose();
				mainLobbyViewInstance.dispose();
				settingViewInstance.dispose();
				dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(this, "아이디 비밀번호 또는 확인문자를 확인해주세요!");
				System.out.println(jtf_signOutCheck.getText());
				System.out.println("회원 확인false");
			}
		}else if (jb_cancel == obj) {
			this.dispose();
		}

	}
}
