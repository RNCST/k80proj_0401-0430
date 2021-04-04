package view_;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.DAO;

public class ChangePWView extends JDialog implements ActionListener{
	private static ChangePWView changePWView = new ChangePWView() ;
	JButton jb_changePW = null;
	JButton jb_cancel   = null;
	
	JTextField jtf_id = null;
	JTextField jtf_nowPW = null;
	JPasswordField jtf_changedPW = null;
	
	JLabel jl_id = null;
	JLabel jl_nowPW = null;
	JLabel jl_changedPW = null;
	JLabel jl_information = null;
	
	Font font = null;

	DAO dblogic = new DAO();
	
	public static ChangePWView getInstance() {
		if(changePWView == null) {
			changePWView = new ChangePWView();
		}
		return changePWView;
	}
	public ChangePWView() {
		font = new Font("휴먼모음T", Font.PLAIN, 14);

		jb_changePW = new JButton("changePW");
		jb_cancel = new JButton("Cancel");

		jtf_id = new JTextField();
		jtf_nowPW = new JTextField();
		jtf_changedPW = new JPasswordField();
		jl_id = new JLabel("ID");
		jl_nowPW = new JLabel("PW");
		jl_changedPW = new JLabel("<html>chagnedPW</html>");
		jl_information = new JLabel("<html>information<br>information<br>information</html>");

		System.out.println("===ChangePWView 디폴트생성자 생성 성공");
	}
	public void initDisplay() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		add(jl_information);
		add(jl_id);
		add(jtf_id);
		add(jl_nowPW);
		add(jtf_nowPW);
		add(jl_changedPW);
		add(jtf_changedPW);
		add(jb_changePW);
		add(jb_cancel);
		
		jb_changePW.addActionListener(this);
		jb_cancel.addActionListener(this);
		
		jl_information.setHorizontalAlignment(JLabel.CENTER);

		jl_information.setFont(font);
		jl_information.setBounds(60, 20, 200, 50);
		jl_id.setBounds(60, 80, 60, 30);
		jtf_id.setBounds(170, 85, 90, 20);
		jl_nowPW.setBounds(60, 120, 60, 30);
		jtf_nowPW.setBounds(170, 125, 90, 20);
		jl_changedPW.setBounds(60, 160, 80, 30);
		jtf_changedPW.setBounds(170, 165, 90, 20);

		jb_changePW.setBounds(60, 250, 100, 30);
		jb_cancel.setBounds(170, 250, 100, 30);

		setSize(350, 350);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		ChangePWView changePWView = ChangePWView.getInstance();
		changePWView.initDisplay();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		LoginView loginViewInstance = LoginView.getInstance();
		Object obj = e.getSource();
		if (jb_changePW == obj) {
			System.out.println("버튼 액션");
			boolean isOk = (
					loginViewInstance.getGetID() == jtf_id.getText() && 
					loginViewInstance.getGetPW() == jtf_nowPW.getText());
			System.out.println(loginViewInstance.getGetID()+" | "+loginViewInstance.getGetPW());
			System.out.println(jtf_id.getText()+ " | "+jtf_nowPW.getText());
			if (isOk = true) {
				System.out.println("현재 아이디 비밀번호 확인");
				dblogic.runChangePW(loginViewInstance.getGetID(), loginViewInstance.getGetPW(), jtf_changedPW.getText());
				JOptionPane.showMessageDialog(this, "비밀번호가 변경되었습니다.");
				loginViewInstance.setGetPW(jtf_changedPW.getText());
				jtf_id.setText("");
				jtf_nowPW.setText("");
				jtf_changedPW.setText("");
				dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 확인하세요");
				System.out.println("현재 아이디 비밀번호 확인X");
			}
		}else if (jb_cancel == obj) {
			this.dispose();
		}
		
	}

}
