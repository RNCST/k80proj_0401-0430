package Server_Client_Thread_Prac;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LoginServer2 extends JFrame implements Runnable {

	LoginServerThread2 loginServerThread = null;
	public List<LoginServerThread2> globalList = null;
	List<Room> roomList = null;
	ServerSocket serverSocket = null;
	Socket acceptedSocket = null;
	public JTextArea jta_log = new JTextArea(10, 30);
	JScrollPane jsp_log = new JScrollPane(jta_log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JPanel jp_north = new JPanel();
	JButton jb_log = new JButton("log save");
	String logPath = "src\\Server_Client_Thread\\talk\\";
	static Calendar cal = Calendar.getInstance();

	public void initDisplay() {
		System.out.println("===run LoginServer2 initDisplay()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		jb_log.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object obj = ae.getSource();
				if(obj==jb_log) {
					String fileName = "log_" +setTimer()+".txt";
					System.out.println(fileName);
					try {
						File f = new File(logPath+fileName);
						PrintWriter pw =
								new PrintWriter(
										new BufferedWriter( new FileWriter(f.getAbsolutePath())));
						pw.write(jta_log.getText());
						pw.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					}
				}
		});
	jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
	jta_log.setBackground(Color.yellow);
	jp_north.add(jb_log);
	add("North",jp_north);
	add("Center",jsp_log);
	setSize(500,400);
	setVisible(true);
	}

	/**
	 *서버 소켓 클라이언트 소켓 연결
	 *server = 4500 서버소켓
	 *socket = server.accept();
	 *loginServerThread = new LoginServerThread(LoginServer)
	 *loginServerThread.start() ==> loginServerThread.run();
	 */
	@Override 
	public void run() {
		System.out.println("===run LoginServer2 run()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		globalList = new Vector<>();
		Calendar cal = Calendar.getInstance();
		int second        = cal.get(Calendar.SECOND);
		int milliSecond   = cal.get(Calendar.MILLISECOND);
		String totalSecond= second+" "+milliSecond;
		boolean isStop    = false;
		try {
			serverSocket = new ServerSocket(4500);
			jta_log.append("===Sever Start Ready........\n");
			System.out.println("===LoginServer Start........\n");
			while(!isStop) {
				acceptedSocket = serverSocket.accept();
				jta_log.append("===Client info:"+acceptedSocket+"\n");
				jta_log.append("===TotalSecond"+totalSecond);
				loginServerThread = new LoginServerThread2(this);
				loginServerThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		System.out.println("===run LoginServer2 main()"+cal.get(Calendar.SECOND)+cal.get(Calendar.MILLISECOND));
		LoginServer2 loginServer = new LoginServer2();
		loginServer.initDisplay();
		Thread thread = new Thread(loginServer);
		thread.start();
	}
	
	
	
	
	/*******************************************************
	 * 시스템의 오늘 날짜 정보 가져오기
	 * @param 해당사항 없음.
	 * @return now time (yyyy-mm-ddd)
	 ******************************************************/
	public String setTimer() {
		System.out.println("===run setTimer()");
		Calendar cal = Calendar.getInstance();
		int yyyy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH)+1;
		int day =  cal.get(Calendar.DAY_OF_MONTH);
		return yyyy+"-"+
			   (mm < 10 ? "0"+mm:""+mm)+"-"+
			   (day < 10 ? "0"+day:""+day);
	}////////////////end of setTimer

}
