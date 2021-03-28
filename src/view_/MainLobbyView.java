package view_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	private static MainLobbyView mainLobbyView = new MainLobbyView();

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
	
	String          room1      = null;
	String          room2      = null;
	String          room3      = null;
	String          room4      = null;
	String          room5      = null;
	String          room6      = null;
	String          room7      = null;
	String          room8      = null;
	String          room9      = null;
	
	LoginView       loginView   = null;
	SettingView     settingView = null;
	
	public static MainLobbyView getInstance() {
		if(mainLobbyView == null) {
			mainLobbyView = new MainLobbyView();
		}
		return mainLobbyView;	
	}
	
	
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

	    jl_title          = new JLabel();
	    jl_title.setText("<html>p<br>o<br>p<br><br>c<br>h<br>a<br>t<br><br>v<br>e<br>r<br>.<br>0<br>.<br>0<br>0</html>");
	    
	    jb_1       = new 	JButton    (room1);
	    jb_2       = new 	JButton    (room2);
	    jb_3       = new 	JButton    (room3);
	    jb_4       = new 	JButton    (room4);
	    jb_5       = new 	JButton    (room5);
	    jb_6       = new 	JButton    (room6);
	    jb_7       = new 	JButton    (room7);
	    jb_8       = new 	JButton    (room8);
	    jb_9       = new 	JButton    (room9);
	    
	    jb_refresh = new 	JButton    ("<html>새로<br>고침</html>");
	    jb_unde    = new 	JButton    ("미정");
	    jb_setting = new 	JButton    ("설 정");

	    jb_1.addActionListener(this);
	    jb_2.addActionListener(this);
	    jb_3.addActionListener(this);
	    jb_4.addActionListener(this);
	    jb_5.addActionListener(this);
	    jb_6.addActionListener(this);
	    jb_7.addActionListener(this);
	    jb_8.addActionListener(this);
	    jb_9.addActionListener(this);
	    jb_refresh.addActionListener(this);
	    jb_unde.addActionListener(this);
	    jb_setting.addActionListener(this);
	    
	    jtf_gettext = new 	JTextField ();
	    jtf_showtext= new 	JTextField ();
	
	    jsp_scroll = new 	JScrollPane();  
	    
	    tb_1     = new TitledBorder(new LineBorder(Color.black));
	    
	    ft1      = new Font("Ariel", Font.BOLD, 13);
	    
	    settingView = SettingView.getInstance();
	    
	    System.out.println("===MainLobby디폴트생성자 생성 성공");
	}
	public void initdisplay() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
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
		jp_4.setLayout(new GridLayout(3,1,0,0));
		jp_4.add(jb_refresh);
		jp_4.add(jb_unde);
		jp_4.add(jb_setting);
		
		setSize(1120,400);
//		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		System.out.println("===MainLobbyView initdisplay(); 실행성공");
	}
	
	
	
	
	public static void main(String[] args) {
		MainLobbyView mlv = new MainLobbyView();
		mlv.initdisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(jb_1 == obj) {
			
		}else if(jb_2 == obj) {
			
		}else if(jb_3 == obj) {
		
		}else if(jb_4 == obj) {
			
		}else if(jb_5 == obj) {
		
		}else if(jb_6 == obj) {
			
		}else if(jb_7 == obj) {	
		
		}else if(jb_8 == obj) {	
			
		}else if(jb_9 == obj) {	
			
		}else if(jb_refresh == obj) {
			
		}else if(jb_unde == obj) {
			
		}else if(jb_setting == obj) {
			settingView.initdisplay();
			
		}
		
	}

}
