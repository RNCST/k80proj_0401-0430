package view_;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SearchPwView extends JDialog implements ActionListener {
	
	
	JButton jb_searchID      = null;
	JButton jb_searchPW      = null;
	JButton jb_cancel        = null;
	JTextField jtf_id        = null;
	JTextField jtf_id2       = null;
	JTextField jtf_pw        = null;
	JComboBox jcb_question   = null;
	JTextField jtf_answer    = null;
	JPanel jp_north          = null;
	JPanel jp_south          = null;
	JLabel jl_id             = null;
	JLabel jl_pw             = null;
	JLabel jl_question       = null;
	JLabel jl_answer         = null;
	JLabel jl_information    = null;
	JLabel jl_information2   = null;
	String[] questionbox   = {"1번", "2번", "3번", "4번"};
	String  getTextBox     = null;
	
	TitledBorder    tb_north  = null;	
	TitledBorder    tb_south  = null;	
	

	public SearchPwView() {
		jb_searchID = new JButton("searchID");
		jb_searchPW = new JButton("searchID");
		jb_cancel   = new JButton("cancel");
		jtf_id      = new JTextField();
		jtf_id2     = new JTextField();
		jtf_pw      = new JTextField();
		jcb_question = new JComboBox(questionbox);
		jtf_answer  =  new JTextField();

	 	jp_north     = new JPanel();
		jp_south     = new JPanel();
 
		jl_id        = new JLabel("ID");
		jl_pw        = new JLabel("PW");
		jl_question  = new JLabel("찾기 질문");
		jl_answer    = new JLabel("찾기 질문에 대한 답");
		jl_information = new JLabel("INFORMATION FOR SID");
		jl_information2 = new JLabel("INFORMATION FOR SPW");
		
		tb_north    = new TitledBorder(new LineBorder(Color.black));
		tb_south    = new TitledBorder(new LineBorder(Color.black));
		getTextBox   = jcb_question.getSelectedItem().toString();
	}
	
	
	
	public void initdisplay() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		add("North",jp_north);
		add("South",jp_south);
		
		jp_north.setBorder(tb_north);
		jp_north.setPreferredSize(new Dimension(190,390));
		jp_north.setLayout(null);
		
		jp_north.add(jb_searchID);
		jp_north.add(jb_cancel);
		jp_north.add(jl_id);
		jp_north.add(jtf_id);
		jp_north.add(jl_information);
		jl_id.setBounds(180, 50, 90, 20);
		jtf_id.setBounds(180, 50, 90, 20);
		jl_information.setBounds(180, 50, 90, 20);
		//=================================================================
		
		jp_south.setBorder(tb_south);
		jp_south.setPreferredSize(new Dimension(190,390));
		jp_south.setLayout(null);
		
		jp_south.add(jb_searchPW);
		jp_south.add(jb_cancel);
		jp_south.add(jl_id);
		jp_south.add(jtf_id2);
		jp_south.add(jl_pw);
		jp_south.add(jtf_pw);
		jp_south.add(jl_question);
		jp_south.add(jcb_question);
		jp_south.add(jl_answer);
		jp_south.add(jtf_answer);
		jp_south.add(jl_information2);
		
		jl_id.setBounds(180, 50, 90, 20);
		jtf_id2.setBounds(180, 50, 90, 20);
		jl_id.setBounds(180, 50, 90, 20);
		jtf_id2.setBounds(180, 50, 90, 20);
		jl_id.setBounds(180, 50, 90, 20);
		jtf_id2.setBounds(180, 50, 90, 20);
		jl_id.setBounds(180, 50, 90, 20);
		jtf_id2.setBounds(180, 50, 90, 20);
		jl_information2.setBounds(180, 50, 90, 20);
		//=================================================================
		
		setSize(400, 800);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		SearchPwView spv = new SearchPwView();
		spv.initdisplay();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj =ae.getSource();
		
	}
	
}
		
		
		
		
		
