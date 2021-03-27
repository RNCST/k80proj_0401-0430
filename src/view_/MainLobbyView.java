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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainLobbyView extends JFrame implements ActionListener{

	JPanel         jp_1       = null;
	JPanel         jp_2       = null;
	JPanel         jp_3       = null;
	JPanel         jp_4       = null;
	JLabel         jl_title   = null;
	
	JButton        jb_1       = null;
	JButton        jb_2       = null;
	JButton        jb_3       = null;
	JButton        jb_4       = null;
	JButton        jb_5       = null;
	JButton        jb_6       = null;
	JButton        jb_7       = null;
	JButton        jb_8       = null;
	JButton        jb_9       = null;
	
	JButton        jb_refresh = null;
	JButton        jb_unde    = null;
	JButton        jb_setting = null;
	
	JTextField jtf_gettext    = null;
	JTextField jtf_showtext   = null;
	
	JScrollPane    jsp_scroll = null;   
	
	TitledBorder    tb_1      = null;	
	TitledBorder    tb_2      = null;	
	TitledBorder    tb_3      = null;	
	TitledBorder    tb_4      = null;	
	
	JSplitPane      jspp_1     = null;
	JSplitPane      jspp_2     = null;
	JSplitPane      jspp_3     = null;
	
	Font            ft1        = null;
	
	String          nick1      = null;
	String          nick2      = null;
	String          nick3      = null;
	String          nick4      = null;
	String          nick5      = null;
	String          nick6      = null;
	String          nick7      = null;
	String          nick8      = null;
	String          nick9      = null;
	
	                          
	
	public MainLobbyView() {
	
	    jp_1       = new 	JPanel     ();
	    jp_2       = new 	JPanel     ();
	    jp_3       = new 	JPanel     ();
	    jp_4       = new 	JPanel     ();
	    
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

	    
	    jl_title   = new 	JLabel     ("p\ro\rp\rc\rh\ra\rt\r \rv\re\rr\r \r0\r.\r0\n");
	    
	    jb_1       = new 	JButton    (nick1);
	    jb_2       = new 	JButton    (nick2);
	    jb_3       = new 	JButton    (nick3);
	    jb_4       = new 	JButton    (nick4);
	    jb_5       = new 	JButton    (nick5);
	    jb_6       = new 	JButton    (nick6);
	    jb_7       = new 	JButton    (nick7);
	    jb_8       = new 	JButton    (nick8);
	    jb_9       = new 	JButton    (nick9);
	    
	    jb_refresh = new 	JButton    ("새로고침");
	    jb_unde    = new 	JButton    ("미정");
	    jb_setting = new 	JButton    ("설 정");
	    
	    jtf_gettext = new 	JTextField ();
	    jtf_showtext= new 	JTextField ();
	
	    jsp_scroll = new 	JScrollPane();  
	    
	    tb_1     = new TitledBorder(new LineBorder(Color.black));
	    
	    ft1      = new Font("Ariel", Font.BOLD, 15);
	}
	public void initdisplay() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
//		setLayout(new FlowLayout());
		add(jspp_3, BorderLayout.CENTER);
	    jspp_1.setDividerLocation(20);
	    jspp_2.setDividerLocation(520);
	    jspp_3.setDividerLocation(1020);
		jl_title.setFont(ft1);		
		jp_1.setBorder(tb_1);
		
		jp_1.add(jl_title);
		
		
		jp_2.setBorder(tb_1);
		jp_2.setLayout(new BorderLayout());
		jp_2.add("Center",jtf_showtext);
		jp_2.add("South",jtf_gettext);
		// 사이즈 500 *400 , BorderLayout
		
		
		jp_3.setBorder(tb_1);
		jp_3.setLayout(new GridLayout(3, 3, 10, 10));
		jp_3.add(jb_1);
		jp_3.add(jb_2);
		jp_3.add(jb_3);
		jp_3.add(jb_4);
		jp_3.add(jb_5);
		jp_3.add(jb_6);
		jp_3.add(jb_7);
		jp_3.add(jb_8);
		jp_3.add(jb_9);
		jp_4.setBorder(tb_1);
		jp_4.setLayout(new FlowLayout());
		jp_4.add(jb_refresh);
		jp_4.add(jb_unde);
		jp_4.add(jb_setting);
		
		
		
		setSize(1120,400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		MainLobbyView mlv = new MainLobbyView();
		mlv.initdisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
