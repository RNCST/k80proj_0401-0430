package DBLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import dao_.DBConnectionMgr;
import pVO.MemberVO;

public class DBLogic {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private StringBuffer sql = null;
	private ResultSet rs = null;
	private MemberVO[] MemverList = null;

	/**
	 * 로그인하는 Vector타입 생성자
	 * ID / PW만 비교
	 * 
	 * @return membervoVec (ID , PW)
	 */
	public Vector<MemberVO> runLogin() {
		Vector<MemberVO> membervoVec = new Vector<MemberVO>();
		sql = null;
		sql.append(" SELECT " 
				+ "    FROM " 
				+ "   WHERE" 
				+ "   ORDER BY");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO mVO = null;
				mVO.setMember_ID(rs.getString("  sql 문  ID   "));
				mVO.setMember_PW(rs.getString("  sql 문 PW   "));
				membervoVec.add(mVO);
			}
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membervoVec;
	}

	/**
	 * 회원가입 하는 메소드 
	 * Email ID PW Quesion Answer 순으로 기입;
	 * 
	 * 회원가입하는 sql 문 안쪽에 ID중복검사를 넣던가 
	 * 이 메소드 실행하기전에 중복검사를 하는 메소드를 하나 더 실행 <<= 1안
	 * 
	 */
	public void runSignUp() {
		sql = null;
		MemberVO mVO = null;
		sql.append("INSERT INTO table" 
				+ " (EMAIL ID PW QUESION ANSWER )" 
				+ "values (?, ?, ?, ?, ?))");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mVO.getMember_Email());
			pstmt.setString(2, mVO.getMember_ID());
			pstmt.setString(3, mVO.getMember_PW());
			pstmt.setString(4, mVO.getMember_Question());
			pstmt.setString(5, mVO.getMember_Answer());
			pstmt.executeUpdate();
			dbMgr.freeConnection(con, pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ID를 찾는 생성자
	 * EMAIL만 비교해서 있으면 EMAIL 발송
	 * EMAIL안에 가져온 아이디값을 넣어야함
	 * 
	 * @return membervoVeC ==> ID값이 들어있게.
	 */
	public Vector<MemberVO> runSearchID(){
		Vector<MemberVO> membervoVec = new Vector<MemberVO>();
		sql = null;
		sql.append(" SELECT " 
				+ "    FROM " 
				+ "   WHERE" 
				+ "   ORDER BY");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO mVO = null;
				mVO.setMember_ID(rs.getString("  sql 문  ID   "));
				mVO.setMember_PW(rs.getString("  sql 문 PW   "));
				membervoVec.add(mVO);
			}
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membervoVec;
	}
	
	/**
	 * PW를 찾는 생성자
	 * Email ID PW Question Answer 모두 비교해서 있으면 email 발송
	 * EMAIL 안에 가져온 아이디 비밀번호 값을 넣어야함.
	 * 
	 * @return membervoVeC ==> Email ID PW Question Answer값이 들어있게.
	 */
	public Vector<MemberVO> runSearchPW(){
		Vector<MemberVO> membervoVec = new Vector<MemberVO>();
		sql = null;
		sql.append(" SELECT " 
				+ "    FROM " 
				+ "   WHERE" 
				+ "   ORDER BY");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO mVO = null;
				mVO.setMember_ID(rs.getString("  sql 문  ID   "));
				mVO.setMember_PW(rs.getString("  sql 문 PW   "));
				membervoVec.add(mVO);
			}
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membervoVec;
	}
	
	/**
	 * @param Member_ID, Member_PW 
	 * 로그인 하는 순간 아이디를 저장하고 있음. 그 아이디를 파라미터에 넣어서 식별
	 * 또는 회원탈퇴 누르는순간 창을 하나 더 띄워서 ID PW값을 입력 받도록.
	 * 
	 */
	public void runSignOut(String Member_ID, String Member_PW) {
		sql = null;
		sql = new StringBuffer();
		sql.append("DELETE FROM table WHERE Member_ID=?, Member_PW=?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, Member_ID);
			pstmt.setString(2, Member_PW);
			pstmt.executeUpdate();
			dbMgr.freeConnection(con, pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}