package mall.client.commons;

import java.sql.*;

public class DBUtil {
	//1.db연결 - 예외 던지는 대신 try-catch구문
	public Connection getConnection() {
		Connection conn = null;
		try {	//예외가 발생하면 catch절로 넘기고 예외 메시지를 출력하겠다.
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mall", "root", "java1004");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//2.db자원(connection, statement, resultset) 해제 - 역순해제 
	//garbage collector가 지우기 전에 먼저 지울 것
	//db 연결자 수는 한정되어 있는데, db 사용 후 아직 남아있으면 
	public void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
