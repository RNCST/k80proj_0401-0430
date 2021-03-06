package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {
	private final static String _DRIVER  = "oracle.jdbc.driver.OracleDriver";
	private final static String _URL     = "jdbc:oracle:thin:@127.0.0.1:1521:orcl11";
	private final static String _USER    = "KO80PROJECT_2";
	private final static String _PW      = "admin1234";
	private static DBConnectionMgr dbMgr = null;
	private               Connection con = null;
	private DBConnectionMgr() {}
	
	
	public static DBConnectionMgr getInstance() {
		if(dbMgr==null) {
			dbMgr = new DBConnectionMgr();
		}
		System.out.println("===DBConnectionMgr getInstance()");
		return dbMgr;
	}
	
	
	public Connection getConnection() {
		try {
			System.out.println("123");
			Class.forName(_DRIVER);
			System.out.println("ㅇㅇ");
			con = DriverManager.getConnection(_URL, _USER, _PW);
			System.out.println("ㄴㄴ");
			/* 트랜잭션처리
			con.setAutoCommit(true);
			con.setAutoCommit(false);
			con.commit();
			con.rollback();
			*/
			System.out.println("===DBConnectionMgr getConnection");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을수가 없습니다");
		} catch (Exception e) {
			System.out.println("오라클 서버에 접속 불가");
		}
		System.out.println("오라클 연결");
		return con;
	}
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
		}
	}
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
		}
	}
	public void freeConnection(Connection con, CallableStatement cstmt) {
		try {
			if(cstmt !=null) cstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
		}
	}
	public void freeConnection(Connection con, CallableStatement cstmt, ResultSet rs) {
		try {
			if(rs !=null) rs.close();
			if(cstmt !=null) cstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
		}
	}
}
