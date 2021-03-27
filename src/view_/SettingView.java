package view_;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author OSH
 * Setting button action 받으면 
 * jp_2 3 4 번과 SettingView panel jp_2 3 4 치환 
 * 
 */
public class SettingView{
	
	
	
	JPanel  jp_1              = null;
	JPanel  jp_2              = null;
	JPanel  jp_3              = null;
	
    JButton jb_changeNickName = null;
    JButton jb_logOut         = null;
    JButton jb_signOut        = null;
    
    JButton jb_report         = null;
    JButton jb_creator        = null;
    JButton jb_ver            = null;
    
	JButton jb_refresh        = null;
	JButton jb_unde           = null;
	JButton jb_goback         = null;

	
	public SettingView() {
		
		
		
		
		jp_1              = new JPanel();
		jp_2              = new JPanel();
		jp_3              = new JPanel();
		
		jb_changeNickName =new JButton("닉네임변경"); 
		jb_logOut         =new JButton("로그아웃"); 
		jb_signOut        =new JButton("회원탈퇴"); 
		
		jb_report         =new JButton("신고하기"); 
		jb_creator        =new JButton("만든자들"); 
		jb_ver            =new JButton(" 버전   "); 

		jb_refresh        =new JButton("새로고침"); 
		jb_unde           =new JButton(" 미정 "); 
		jb_goback         =new JButton("채팅창으로 돌아가기"); 
	}
	
	public void panel1() {
		JFrame  jf_test1          = new JFrame();
		jf_test1.add(jp_1);
		jp_1.add(jb_changeNickName);
		jp_1.add(jb_logOut);
		jp_1.add(jb_signOut);
		jf_test1.setSize(500, 400);
		jf_test1.setVisible(true);
	}
	public void panel2() {
		JFrame  jf_test1          = new JFrame();
		jf_test1.add(jp_2);
		jp_2.add(jb_report);
		jp_2.add(jb_creator);
		jp_2.add(jb_ver);
		jf_test1.setSize(500, 400);
		jf_test1.setVisible(true);
	}
	public void panel3() {
		JFrame  jf_test1          = new JFrame();
		jf_test1.add(jp_3);
		jp_3.add(jb_refresh);
		jp_3.add(jb_unde);
		jp_3.add(jb_goback);
		jf_test1.setSize(500, 400);
		jf_test1.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		SettingView sv = new SettingView();
		sv.panel1();
		sv.panel2();
		sv.panel3();
	}

}
