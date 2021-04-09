package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import DBConnection.DBConnectionMgr;
import pVO.MemberVO;
import view_.LoginView;

/**
 * @author OSH
 *
 */
public class DAO {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private StringBuffer sql = null;
	private ResultSet rs = null;
	private MemberVO[] MemverList = null;

	/**
	 * 로그인하는 Vector타입 생성자
	 * ID / PW만 비교
	 * @return boolean loginOk
	 */
	public boolean runLogin(String loginID , String loginPW) {
		boolean loginOk = false;
		try {
			sql = null;
			sql = new StringBuffer();
			sql.append("SELECT * FROM MEMBERLIST WHERE p80_ID ='"+loginID+"'");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));
			rs = pstmt.executeQuery();
			if( rs.next() == false || (loginID.isEmpty() == true)){
				loginOk = false;
				System.out.println("===ID부터 틀림");
			} else {
				sql = null;
				sql = new StringBuffer();
			    sql.append("SELECT P80_PW FROM (SELECT * FROM MEMBERLIST WHERE p80_ID ='" + loginID + "')");
			    rs = pstmt.executeQuery(String.valueOf(sql));
			    if (rs.next() == true) {
			    	if (rs.getString(1).equals(loginPW)) {
			    		loginOk = true;
			    	}else {
			    		loginOk = false;
			    		System.out.println("===PW가 틀림");
			    	}
			    }
			}
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginOk;
	}
	
	
	
	/**
	 * VO 담기.. 안쓸듯
	 * @param loginEmail
	 * @param loginID
	 * @param loginPW
	 * @param loginQuestion
	 * @param loginAnswer
	 * @return
	 */
	public Vector<MemberVO> setSignUpVO(String loginEmail, String loginID, String loginPW, String loginQuestion, String loginAnswer) {
		Vector<MemberVO> mvoVec = new Vector<MemberVO>();
		MemberVO mVO = null;
		mVO = new MemberVO();
		mVO.setMember_Email(loginEmail);
		mVO.setMember_ID(loginID);
		mVO.setMember_PW(loginPW);
		mVO.setMember_Question(loginQuestion);
		mVO.setMember_Answer(loginAnswer);
		mvoVec.add(mVO);
		return mvoVec;
	}
	/**
	 * 회원가입 하는 메소드 
	 * Email ID PW Quesion Answer 순으로 기입;
	 * 알고리즘에 필요한 SCORE는 일단 101점으로 넣은후  추가 작업으로 101점이면 점수를 계산하는 메소드 실행하도록...
	 * 회원가입하는 sql 문 안쪽에 ID중복검사를 넣던가 
	 * 이 메소드 실행하기전에 중복검사를 하는 메소드를 하나 더 실행 <<= 1안
	 * 중복검사를 무조건 거치게.
	 * 
	 */
	public Boolean runSignUp(String loginEmail, String loginID, String loginPW, String loginQuestion, String loginAnswer) {
		Boolean signupOk = false;
		MemberVO mvo     = null;
		int     RegNumber= this.runGetPKNum()+1;
		try {
			
			sql = null;
			sql = new StringBuffer();
			sql.append("INSERT INTO MEMBERLIST" 
					+ " (P80_REGNUMBER, P80_EMAIL, P80_ID, P80_PW, P80_QUESTION, P80_ANSWER, p80_SCORE)" 
					+ " values (?, ?, ?, ?, ?, ?)");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));			
			pstmt.setInt   (1, RegNumber);
			pstmt.setString(2, loginEmail);
			pstmt.setString(3, loginID);
			pstmt.setString(4, loginPW);
			pstmt.setString(5, loginQuestion);
			pstmt.setString(6, loginAnswer);
			pstmt.setInt   (7, 101);
			pstmt.executeUpdate();
			dbMgr.freeConnection(con, pstmt);
			signupOk = true;
		} catch (Exception e) {
			e.printStackTrace();
			signupOk = false;
		}
		System.out.println("===loginOk 결과는 "+ signupOk);
		return signupOk;
	}
	
	/**
	 * 아이디 중복 검사 
	 * @param loginID
	 * 
	 * @return Boolean duplicateID = true , false 반환
	 * true인 경우 중복이 안됨
	 * false인 경우 중복이 됨.
	 */
	public Boolean runDuplicateID(String loginID) {
		Boolean duplicateID = false;
		int duplicateNum = 0;
		try {
			sql = null;
			sql = new StringBuffer();
			sql.append("SELECT COUNT(p80_id) AS cnt FROM MEMBERLIST WHERE p80_id=?");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, loginID);
			rs    = pstmt.executeQuery();
			rs.next();
			duplicateNum = rs.getInt(1);
			if(duplicateNum == 1)
			{ duplicateID = false;
			System.out.println("중복 됨");}
			else 
			{ duplicateID = true;
			System.out.println("중복 안됨");}	
			System.out.println("중복 검사 결과는 "+duplicateID);
			dbMgr.freeConnection(con, pstmt, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
			duplicateID = false;
		} return duplicateID;
	}
	/**
	 * 
	 * @return P80_RegNumber 
	 * MEMBERLIST 테이블에서 pk의 마지막 값을 가져옵니다. 
	 */
	public int runGetPKNum() {
		int p80_RegNumber = 0;
		try {
			sql = null;
			sql = new StringBuffer();
			sql.append("SELECT MAX(P80_REGNUMBER) FROM MEMBERLIST");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));
			rs    = pstmt.executeQuery();
			rs.next();
			p80_RegNumber = rs.getInt(1);
			System.out.println("===p80회원 고유번호는 "+ p80_RegNumber);
			dbMgr.freeConnection(con, pstmt, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} return p80_RegNumber;
	}
	
	/**
	 * jtf_text안에 이메일이 존재하는지 체크하는 메소드
	 * @param String loginEmail ==> SearchView.jtf_email1
	 * 
	 * @return boolean isRealEmail 체크
	 */
	public boolean runSearchID(String loginEmail){
		boolean isRealEmail    = false;
		int     isRealEmailNum = 0;
		sql = null;
		sql = new StringBuffer();
		sql.append("SELECT COUNT(CASE WHEN p80_EMAIL=? THEN 1 ELSE NULL END) "
				+ "AS cnt FROM MEMBERLIST");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));
			pstmt.setString(1 , loginEmail);
			rs = pstmt.executeQuery();
			rs.next();
			isRealEmailNum = rs.getInt(1);
			if(isRealEmailNum == 1) {
				isRealEmail = true;
			}
			else if(isRealEmailNum == 0) {
				isRealEmail = false;
			}
			
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isRealEmail;
	}
	
	/**
	 * PW를 찾는 메소드
	 * Email ID PW Question Answer 모두 비교해서 있으면 email 발송
	 * EMAIL 안에 가져온 아이디 비밀번호 값을 넣어야함.
	 * 
	 * @return membervoVeC ==> Email ID PW Question Answer값이 들어있게.
	 */
	public boolean runSearchPW(String memberEmail, String memberID, String memberQuestion, String memberAnswer){
		boolean isRealinfor    = false;
		int     isRealinforNum = 0;
		sql = null;
		sql = new StringBuffer();
		sql.append("SELECT COUNT(CASE WHEN p80_EMAIL=? "
				+ "AND P80_ID =? "
				+ "AND P80_QUESTION =? "
				+ "AND P80_ANSWER = ? "
				+ "THEN 1 ELSE NULL END) "
				+ "AS cnt FROM MEMBERLIST");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberID);
			pstmt.setString(3, memberQuestion);
			pstmt.setString(4, memberAnswer);
			rs = pstmt.executeQuery();
			rs.next();
			isRealinforNum = rs.getInt(1);
			if(isRealinforNum == 1) {
				isRealinfor = true;
			}
			else if(isRealinforNum == 0) {
				isRealinfor = false;
			}
			
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isRealinfor;
	}
	
	/**
	 * @param Member_ID, Member_PW 
	 * 로그인 하는 순간 아이디를 저장하고 있음. 그 아이디를 파라미터에 넣어서 식별
	 * 또는 회원탈퇴 누르는순간 창을 하나 더 띄워서 ID PW값을 입력 받도록.
	 * 
	 */
	
	/**
	 * 지금 아이디 비밀번호 바꿀비밀번호를 입력받아 테이블 UPDATE해주는 SQL명령어.
	 * @param nowID
	 * @param nowPW
	 * @param changedPW
	 */
	public void runChangePW(String nowID, String nowPW, String changedPW) {
		sql = null;
		sql = new StringBuffer();
		sql.append("UPDATE MEMBERLIST SET p80_pw =? WHERE P80_ID = ? AND P80_PW = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, changedPW);
			pstmt.setString(2, nowID);
			pstmt.setString(3, nowPW);
			pstmt.executeUpdate();
			dbMgr.freeConnection(con, pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void runSignOut(String savedP80_ID, String savedP80_PW) {
		sql = null;
		sql = new StringBuffer();
		sql.append("DELETE FROM MEMBERLIST WHERE P80_ID = ? and P80_PW = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, savedP80_ID);
			pstmt.setString(2, savedP80_PW);
			pstmt.executeUpdate();
			dbMgr.freeConnection(con, pstmt);
			LoginView.getInstance().setGetID(null);
			LoginView.getInstance().setGetPW(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

