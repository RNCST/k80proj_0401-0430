package view_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SearchView extends JDialog implements ActionListener {

	JButton jb_searchID = null;
	JButton jb_searchPW = null;
	JButton jb_cancel1 = null;
	JButton jb_cancel2 = null;
	JTextField jtf_email1 = null;
	JTextField jtf_email2 = null;
	JTextField jtf_id = null;
	JTextField jtf_pw = null;
	JComboBox jcb_question = null;
	JTextField jtf_answer = null;
	JPanel jp_north = null;
	JPanel jp_south = null;
	JLabel jl_email1 = null;
	JLabel jl_email2 = null;
	JLabel jl_id = null;
	JLabel jl_pw = null;
	JLabel jl_question = null;
	JLabel jl_answer = null;
	JLabel jl_information = null;
	JLabel jl_information2 = null;
	String[] questionbox = { "1번", "2번", "3번", "4번" };
	String getTextBox = null;

	TitledBorder tb_north = null;
	TitledBorder tb_south = null;

	JSplitPane jspp_1 = null;

	Font font = null;

	public SearchView() {
		jb_searchID = new JButton("searchID");
		jb_searchPW = new JButton("searchPW");
		jb_cancel1 = new JButton("cancel");
		jb_cancel2 = new JButton("cancel");
		
		jb_searchID.addActionListener(this);
		jb_searchPW.addActionListener(this);
		jb_cancel1 .addActionListener(this); 
		jb_cancel2 .addActionListener(this); 
		
		jtf_email1 = new JTextField();
		jtf_email2 = new JTextField();
		jtf_id = new JTextField();
		jtf_pw = new JTextField();
		jcb_question = new JComboBox(questionbox);
		jtf_answer = new JTextField();

		jspp_1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jp_north, jp_south);
		jspp_1.setDividerSize(0);

		jp_north = new JPanel();
		jp_south = new JPanel();

		jl_email1 = new JLabel("Email");
		jl_email2 = new JLabel("Email");
		jl_id = new JLabel("ID");
		jl_pw = new JLabel("PW");
		jl_question = new JLabel("QUESTION");
		jl_answer = new JLabel("ANSWER");
		jl_information = new JLabel("<html>가입한 이메일을 적으신후 버튼을 누르시면 이메일로<br>아이디를 보내드립니다.</html>");
		jl_information2 = new JLabel("<html>공란을 모두 기입한 후 버튼을 누르시면 이메일로<br>아이디와 비밀번호를 보내드립니다.</html>");

		tb_north = new TitledBorder(new LineBorder(Color.black));
		tb_south = new TitledBorder(new LineBorder(Color.black));
		getTextBox = jcb_question.getSelectedItem().toString();

		font = new Font("휴먼모음T", Font.PLAIN, 14);
		jspp_1.add(jp_north);
		jspp_1.add(jp_south);


		System.out.println("===SearchVIew 디폴트생성자 생성 성공");
	}

	public void initdisplay() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.white);

		add(jspp_1, BorderLayout.CENTER);
		jspp_1.setDividerLocation(300);


		jp_north.setBorder(tb_north);
		jp_south.setBorder(tb_south);

		jp_north.setBorder(tb_north);
		jp_north.setLayout(null);
		jp_north.add(jb_searchID);
		jp_north.add(jb_cancel1);
		jp_north.add(jl_email1);
		jp_north.add(jtf_email1);
		jp_north.add(jl_information);

		jl_email1.setBounds(60, 40, 60, 30);
		jtf_email1.setBounds(170, 45, 200, 20);
		jl_information.setFont(font);
		jl_information.setBounds(50, 130, 320, 50);

		jb_searchID.setBounds(100, 210, 95, 30);
		jb_cancel1.setBounds(210, 210, 95, 30);

		jp_south.setBorder(tb_south);
		jp_south.setLayout(null);
		jp_south.add(jl_email2);
		jp_south.add(jtf_email2);
		jp_south.add(jb_searchPW);
		jp_south.add(jb_cancel2);
		jp_south.add(jl_id);
		jp_south.add(jtf_id);
		jp_south.add(jl_pw);
		jp_south.add(jtf_pw);
		jp_south.add(jl_question);
		jp_south.add(jcb_question);
		jp_south.add(jl_answer);
		jp_south.add(jtf_answer);
		jp_south.add(jl_information2);

		jl_email2.setBounds(60, 40, 60, 30);
		jtf_email2.setBounds(170, 45, 200, 20);
		jl_id.setBounds(60, 80, 60, 30);
		jtf_id.setBounds(170, 85, 90, 20);
		jl_pw.setBounds(60, 120, 60, 30);
		jtf_pw.setBounds(170, 125, 90, 20);
		jl_question.setBounds(60, 160, 60, 30);
		jcb_question.setBounds(170, 165, 160, 20);
		jl_answer.setBounds(60, 200, 60, 30);
		jtf_answer.setBounds(170, 205, 90, 20);
		jl_information2.setFont(font);
		jl_information2.setBounds(50, 290, 320, 50);

		jb_searchPW.setBounds(100, 380, 95, 30);
		jb_cancel2.setBounds(210, 380, 95, 30);

		setSize(400, 800);
		setVisible(true);
		setLocationRelativeTo(null);

		System.out.println("===SearchView initdisplay(); 실행성공");
	}

	public static void main(String[] args) {
		SearchView spv = new SearchView();
		spv.initdisplay();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(jb_searchID == obj) {
			
		}else if(jb_cancel1==obj) {
			this.dispose();
		}else if(jb_searchPW==obj) {
			
		}else if(jb_cancel2==obj) {
			this.dispose();
		}

	}

}

/*
 * setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 * getContentPane().setBackground(Color.white); // this.add(jspp_1,
 * BorderLayout.CENTER); // jspp_1.setDividerLocation(20); add(jp_north,
 * BorderLayout.NORTH); add(jp_south, BorderLayout.SOUTH);
 * 
 * 
 * 
 */
