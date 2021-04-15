package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class ClientDao {
	private DBUtil dbUtil;
	
	//고객정보 삭제 메소드
	public int deleteClient(Client client) {
		int delCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM client WHERE client_mail=(?) AND client_pw=PASSWORD(?)";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			System.out.println("deleteClient stmt-> "+stmt);
			
			delCnt = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, null);
		}
		
		return delCnt;
	}
	
	//비밀번호 변경 메소드
	public void updateClientPw(Client client) {
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE client SET client_pw=PASSWORD(?) WHERE client_mail=?";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientPw());
			stmt.setString(2, client.getClientMail());
			System.out.println("updateClientPw stmt-> "+stmt);
			
			stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, null);
		}
		
		return;
	}
	
	
	//회원가입 메소드
	public void insertClient(Client client) {
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {	//추가 sql : insert into 테이블명(컬럼1) values(컬럼1의값)
			String sql = "INSERT INTO client(client_mail, client_pw, client_date) VALUES(?, PASSWORD(?), now())";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			System.out.println("회원가입 stmt-> "+stmt);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, null); //회원가입은 rs값 필요없기 때문에 null을 넣어준다.
		}
		
		return;
	}
	
	//이메일 중복 검사 메소드
	public String checkClientMail(String clientMail) {
		String returnClientMail = null;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT client_mail clientMail FROM client WHERE client_mail=?";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			System.out.println("메일중복확인 stmt-> "+stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClientMail = rs.getString("clientMail");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return returnClientMail;
	}
	
	//로그인 메소드
	public Client login(Client client) {
		this.dbUtil = new DBUtil();
		Client returnClient = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT client_mail clientMail, client_date clientDate, client_pw clientPw FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			System.out.println("로그인 stmt-> "+stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClient = new Client();
				returnClient.setClientMail(rs.getString("clientMail"));
				returnClient.setClientDate(rs.getString("clientDate"));
				returnClient.setClientPw(rs.getString("clientPw"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return returnClient;
	}
}
