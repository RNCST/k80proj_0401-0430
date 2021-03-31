package basic.mail;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DBLogic.DBLogic;
import dao_.DBConnectionMgr;
import pVO.MemberVO;


public class SendMail {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private StringBuffer sql = null;
	private ResultSet rs = null;
	private MemberVO[] mvoList = null;
	DBLogic dbLogic = new DBLogic();
	
	
	
		/**
		 * 파라미터로 받은 이메일로 아이디를 보내는 메소드
		 * @param memberEmail
		 * 
		 * @return int sendIDOk 성공하면 1 실패하면 0
		 * 
		 */
		public int sendID(String memberEmail) {
			int sendIDOk     = 0;
			String sendID    = null;
			try {
				sql = null;
				sql = new StringBuffer();
				sql.append("SELECT P80_ID FROM MEMBERLIST WHERE p80_email = ? ");
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, memberEmail);
				rs = pstmt.executeQuery();
				rs.next();
				sendID=rs.getString(1);
				
				dbMgr.freeConnection(con, pstmt, rs);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		
			String smtpServer = "smtp.naver.com";
			final String sendId = "rk51320";
			final String sendPass = "!rk349028";
			String sendEmailAddress = "rk51320@naver.com";
			int smtpPort=465;
			
			//받는 분
			String receiveEmailAddress=memberEmail;
			String subject="popchat id찾기를 누르셨나요?";
			String content="안녕하세요. "
					     + "회원님의 아이디는 "+sendID+"입니다.";
			
			
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.port", smtpPort);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", smtpServer);
			
			Session session5 = Session.getDefaultInstance(props, new Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(sendId, sendPass);
				}
			});
			//session2.setDebug(true);
			try{
				Message mimeMessage = new MimeMessage(session5);
				mimeMessage.setFrom(new InternetAddress(sendEmailAddress));
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmailAddress));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(content);
				Transport.send(mimeMessage);
				sendID = null;
				sendIDOk = 1;
				System.out.print("message sent successfully..."); 
			} catch (AddressException e) { 
				// TODO Auto-generated catch block
				sendIDOk = 0;
				e.printStackTrace(); 
			} catch (MessagingException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
				sendIDOk = 0;
			} 		
			return sendIDOk;
		}
		/**
		 * 파라미터로 받은 것으로 sql문 돌리고 pw를 찾아서 이메일로 보내는 메소드
		 * @param memberEmail
		 * @param memberID
		 * @param memberQuestion
		 * @param memberAnswer
		 * @return sendPWOk = 성공하면 1 실패하면 0
		 */
		public int sendPW(String memberEmail, String memberID, String memberQuestion, String memberAnswer ) {
			int sendPWOk  = 0;
			String sendID = null;
			String sendPW = null;
			System.out.println(memberEmail);
			System.out.println(memberID);
			System.out.println(memberQuestion);
			System.out.println(memberAnswer);
			try {
				sql = null;
				sql = new StringBuffer();
				sql.append("SELECT P80_ID, P80_PW FROM MEMBERLIST "
						+ "WHERE P80_EMAIL = ? "
						+ "AND P80_ID = ? "
						+ "AND P80_QUESTION = ? "
						+ "AND P80_ANSWER   = ? ");
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, memberEmail);
				pstmt.setString(2, memberID);
				pstmt.setString(3, memberQuestion);
				pstmt.setString(4, memberAnswer);
				rs = pstmt.executeQuery();
				rs.next();
				sendID=rs.getString(1);
				sendPW=rs.getString(2);
				
				
				dbMgr.freeConnection(con, pstmt, rs);
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			String smtpServer = "smtp.naver.com";
			final String sendId = "rk51320";
			final String sendPass = "!rk349028";
			String sendEmailAddress = "rk51320@naver.com";
			int smtpPort=465;
			
			//받는 분
			String receiveEmailAddress=memberEmail;
			String subject="popchat pw찾기를 누르셨나요?";
			String content="안녕하세요. "
				     + "회원님의 아이디는 "+sendID+"이고."
			         + "회원님의 비밀번호는 "+sendPW+"입니다.";
			
			
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.port", smtpPort);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", smtpServer);
			
			Session session5 = Session.getDefaultInstance(props, new Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(sendId, sendPass);
				}
			});
			//session2.setDebug(true);
			try{
				Message mimeMessage = new MimeMessage(session5);
				mimeMessage.setFrom(new InternetAddress(sendEmailAddress));
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmailAddress));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(content);
				Transport.send(mimeMessage);
				sendID = null;
				sendPW = null;
				sendPWOk = 0;
				System.out.print("message sent successfully..."); 
			} catch (AddressException e) { 
				// TODO Auto-generated catch block
				sendPWOk = 0;
				e.printStackTrace(); 
			} catch (MessagingException e) { 
				// TODO Auto-generated catch block 
				sendPWOk = 0;
				e.printStackTrace(); 
			} 			
		return sendPWOk;
		}
	public static void main(String[] args) {
		SendMail sendMail = new SendMail();
//		String smtpServer = "smtp.naver.com";
//		final String sendId = "보내는사람 아이디 입력합니다.";
//		final String sendPass = "보내는 사람 계정 비번이구요";
//		String sendEmailAddress = "보내는사람이메일계정@naver.com";
//		int smtpPort=465;
//		
//		//받는 분
//		String receiveEmailAddress="받는사람계정@gmail.com";
//		String subject="안녕하세요. 강감찬입니다.";
//		String content="안녕하세요. 학습용3으로 이메일 보내기3 연습 중입니다.";
//		
//		
//		Properties props = System.getProperties();
//		props.put("mail.smtp.host", smtpServer);
//		props.put("mail.smtp.port", smtpPort);
//		props.put("mail.smtp.auth", true);
//		props.put("mail.smtp.ssl.enable", true);
//		props.put("mail.smtp.ssl.trust", smtpServer);
//		
//		Session session5 = Session.getDefaultInstance(props, new Authenticator(){
//			protected PasswordAuthentication getPasswordAuthentication(){
//				return new PasswordAuthentication(sendId, sendPass);
//			}
//		});
//		//session2.setDebug(true);
//		try{
//			Message mimeMessage = new MimeMessage(session5);
//			mimeMessage.setFrom(new InternetAddress(sendEmailAddress));
//			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmailAddress));
//			mimeMessage.setSubject(subject);
//			mimeMessage.setText(content);
//			Transport.send(mimeMessage);
//			System.out.print("message sent successfully..."); 
//		} catch (AddressException e) { 
//			// TODO Auto-generated catch block
//			e.printStackTrace(); 
//		} catch (MessagingException e) { 
//			// TODO Auto-generated catch block 
//			e.printStackTrace(); 
//		} 			
	}

}