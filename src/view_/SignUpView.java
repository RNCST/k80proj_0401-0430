package view_;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpView extends JDialog implements ActionListener, ItemListener {
	Container pane    = null;
	JButton jb_signUp = null;
	JButton jb_cancel = null;
	JTextField jtf_email = null;
	JLabel     jl_email  = null;
	JTextField jtf_id = null; 
	JPasswordField jtf_showpw = null;
	JTextField jtf_nickname = null;
	JComboBox jcb_question = null;
	JTextField jtf_answer = null;

	JLabel jl_id = null;
	JLabel jl_pw = null;
	JLabel jl_nickname = null;
	JLabel jl_question = null;
	JLabel jl_answer = null;
	JLabel jl_information = null;
	String[] questionbox   = {"1번", "2번", "3번", "4번"};
	String  getTextBox     = null;
	
	Font   font            = null;
	
	public SignUpView () {
		jb_signUp    = new JButton("가입하기");
		jb_cancel    = new JButton("취소하기");
		
		jb_signUp.addActionListener(this);
		jb_cancel.addActionListener(this);
		
		jtf_email    = new JTextField();
		jl_email     = new JLabel("Email");
		jl_email.setHorizontalAlignment(JLabel.RIGHT);
		jtf_id       = new JTextField();
		jl_id        = new JLabel("Id");
		jl_id.setHorizontalAlignment(JLabel.RIGHT);
		jtf_showpw       = new JPasswordField();
		jl_pw        = new JLabel("Password");
		jl_pw.setHorizontalAlignment(JLabel.RIGHT);
		jcb_question = new JComboBox(questionbox);
		jl_question  = new JLabel("Question");
		jl_question.setHorizontalAlignment(JLabel.RIGHT);
		jtf_answer   = new JTextField(); 
		jl_answer    = new JLabel("Answer");
		jl_answer.setHorizontalAlignment(JLabel.RIGHT);
		jl_information = new JLabel("<html>아이디 중복은 지원하지 않습니다.<br>이메일은 아이디와 비밀번호를 찾기위해 필요합니다<br>비밀번호찾기질문과 답변을 꼭 기억해주세요</html>");
		getTextBox   = jcb_question.getSelectedItem().toString();
		
		
		
		jtf_id.setDocument(new BoundDocument(10,jtf_id));
		jtf_showpw.setDocument(new BoundDocument(10,jtf_showpw));
		jtf_answer.setDocument(new BoundDocument(20,jtf_answer));
		
		font = new Font("휴먼모음T", Font.PLAIN, 11);
		
		System.out.println("===SignUpVIew 디폴트생성자 생성 성공");
		
	}

	public void initdisplay() {
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		add(jtf_email);
		add(jl_email);
		add(jb_signUp);
		add(jb_cancel);
		add(jtf_id);
		add(jl_id);
		add(jtf_showpw);
		add(jl_pw);
		add(jcb_question);
		add(jl_question);
		add(jtf_answer);
		add(jl_answer);
		add(jl_information);
		
		
		jl_email.setBounds(60, 20, 60, 30);
		jtf_email.setBounds(170, 25, 200, 20);
		jl_id.setBounds(60, 60, 60, 30);
		jtf_id.setBounds(170, 65, 90, 20);
		jl_pw.setBounds(60, 100, 60, 30);
		jtf_showpw.setBounds(170, 105, 90, 20);
		jl_question.setBounds(60, 140, 60, 30);
		jcb_question.setBounds(170, 145, 160, 20);
		jl_answer.setBounds(60, 180, 60, 30);
		jtf_answer.setBounds(170, 185, 90, 20);
		jl_information.setFont(font);
		jl_information.setBounds(30, 220, 320, 50);
		
		
//		jl_information.setBounds(210, 360, 90, 30);
	
//      Label 왼쪽 정렬 
		
		jb_signUp.setBounds(100, 310, 90, 30);
		jb_cancel.setBounds(210, 310, 90, 30);
		
		
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		System.out.println("===SignUpView initdisplay(); 실행성공");
	}
	public static void main(String[] args) {
		SignUpView suv =new SignUpView();
		suv.initdisplay();
	} 

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(jb_signUp == obj) {
			
			
		}else if(jb_cancel == obj) {
			this.dispose();
		}
	}

}
