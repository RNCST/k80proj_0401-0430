package Server_Client_Thread_Prac;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view_.LoginView;

public class LoginClient extends JFrame implements ActionListener{

	Socket                socket  = null;
	ObjectOutputStream    oos     = null;
	ObjectInputStream     ois     = null;
	String                nickName= null;
	
	JPanel jp_second	  = new JPanel();
	JPanel jp_second_south= new JPanel();
	JButton jbtn_one	  = new JButton("1:1");
	JButton jbtn_change	  = new JButton("대화명변경");
	JButton jbtn_font	  = new JButton("글자색");
	JButton jbtn_exit	  = new JButton("나가기");
	
	String cols[] 		  = {"대화명"};
	String data[][] 	  = new String[0][1];
	DefaultTableModel dtm = new DefaultTableModel(data,cols);
	JTable			  jtb = new JTable(dtm);
	JScrollPane       jsp = new JScrollPane(jtb);
	
	JPanel jp_first 		= new JPanel();
	JPanel jp_first_south 	= new JPanel();
	JTextField jtf_msg = new JTextField(20);//south속지 center
	JButton jbtn_send  = new JButton("전송");//south속지 east
	JTextArea jta_display = null;
	JScrollPane jsp_display = null;
	//배경 이미지에 사용될 객체 선언-JTextArea에 페인팅
	
	Image back = null;
	
	public LoginClient() {
		jtf_msg.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_change.addActionListener(this);
	}
	
	public void initDisplay() {
		//사용자의 닉네임 받기
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		this.setLayout(new GridLayout(1,2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
		
		back = getToolkit().getImage("src\\athread\\talk2\\back.jpg");
		
		jta_display = new JTextArea() {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				g.drawImage(back, 0, 0, this);
				Point p = jsp_display.getViewport().getViewPosition();
				g.drawImage(back, p.x, p.y, null);
				paintComponent(g);
			}
		};
		jta_display.setLineWrap(true);
		jta_display.setOpaque(false);
		Font font = new Font("돋움",Font.BOLD,25);
		jta_display.setFont(font);
		jsp_display = new JScrollPane(jta_display);		
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName);
		this.setSize(800, 550);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		LoginClient loginClient = new LoginClient(); // 메인 스레드 우선원
		
		//소켓 생성 --> LoginServer ServerSocket감지 -> 소켓 전달 -> LoginServer run()에서 LoginServerThread 생성
		//생성자 호출(this) -> 듣기ㅏ 가능해짐 (oos(홀수), ois(짝수) 순으로 인스턴스화, 소켓객체가 있어야 가능)
		loginClient.init();
	}
	
	public void init() {
		try {
			socket = new Socket("127.0.0.1",4500); //서버측 ip주소 작성
			//LoginServer ServerSocket 감지 ==> Client = server.accept(); , Client Socket에 대한 정보를 가짐.
			oos = new ObjectOutputStream(socket.getOutputStream());//홀수 소켓에서 처리
			ois = new ObjectInputStream(socket.getInputStream());//짝수 소켓에서 처리
			//initDisplay()이 실행되어서 닉네임이 결정된 후 init 메소드가 호출되므로 서버에게 내가 입장한 사실을 알린다.(말하기)
			oos.writeObject(100+"#"+nickName); // 말하는 순간
			//LoginServerThread의 생성자가 듣기
			//서버에서 말을 한 후 들을 준비를 한다 == 대기를 하고난 후 듣는다 == 프로토콜을 비교해야한다.
			//프로토콜 설계하기 == ERD를 그린다. ==데이터 클래스를 설계 == List,Map 단위 테스트
			LoginClientThread loginClientThread = new LoginClientThread(this);
			System.out.println("===LoginClientThread start");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String msg  = jtf_msg.getText();
		if(jbtn_one == obj) {
			
		}
		else if(jtf_msg==obj) {
			try {
				oos.writeObject(201
						+"#"+nickName
						+"#"+msg);
				jtf_msg.setText("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
