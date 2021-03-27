package view_;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignUpView extends JDialog implements ActionListener, ItemListener {
	Container pane    = null;
	JButton jb_signUp = null;
	JButton jb_cancel = null;
	JTextField jtf_id = null; 
	JTextField jtf_pw = null;
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
	
	public SignUpView () {
		jb_signUp    = new JButton("가입하기");
		jb_cancel    = new JButton("취소하기");
		
		jtf_id       = new JTextField();
		jl_id        = new JLabel("id");
		jtf_pw       = new JTextField();
		jl_pw        = new JLabel("pw");
		jcb_question = new JComboBox(questionbox);
		jl_question  = new JLabel("question");
		jtf_answer   = new JTextField(); 
		jl_answer    = new JLabel("answer");
		jl_information = new JLabel();
		getTextBox   = jcb_question.getSelectedItem().toString();
		
	}

	public void initdisplay() {
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		
		add(jb_signUp);
		add(jb_cancel);
		add(jtf_id);
		add(jl_id);
		add(jtf_pw);
		add(jl_pw);
		add(jcb_question);
		add(jl_question);
		add(jtf_answer);
		add(jl_answer);
		add(jl_information);
		
//		jl_id.setBounds(90, 50, 50, 20);
//		jtf_id.setBounds(180, 50, 90, 20);
//		jl_pw.setBounds(40, 360, 90, 30);
//		jtf_pw.setBounds(40, 360, 90, 30);
//		jl_question.setBounds(40, 360, 90, 30);
//		jcb_question.setBounds(40, 360, 90, 30);
//		jl_answer.setBounds(40, 360, 90, 30);
//		jtf_answer.setBounds(40, 360, 90, 30);
		
//		jl_information.setBounds(210, 360, 90, 30);
	
//      Label 왼쪽 정렬 
		
		jb_signUp.setBounds(100, 360, 90, 30);
		jb_cancel.setBounds(210, 360, 90, 30);
		
		
		setSize(400, 450);
		setLocationRelativeTo(null);
		setVisible(true);
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
		// TODO Auto-generated method stub

	}

}
