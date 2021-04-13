package mall.client.model;

import java.sql.*;
import java.util.*;

import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class CartDao {
	private DBUtil dbUtil;
	
	public List<Map<String, Object>> cartList(String clientMail){	//들어오는 값은 이것저것 다 가능. map은 키 값, 키 값, 키 값으로
		
		/* 쿼리 +)ebook가격
		SELECT c.cart_no, e.ebook_no, e.ebook_title, e.ebook_price, c.cart_date
		FROM cart c INNER JOIN ebook e
		ON c.ebook_no = e.ebook_no
		 */
		
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//where절 먼저 사용하고 출력 순서 정하기!!
			String sql = "SELECT c.cart_no cartNo, e.ebook_no ebookNo, e.ebook_title ebookTitle, e.ebook_price ebookPrice, c.cart_date cartDate FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no WHERE client_mail = ? ORDER BY cart_date DESC";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			System.out.println("cartList stmt-> "+stmt);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("cartNo", rs.getInt("cartNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("ebookPrice", rs.getInt("ebookPrice"));
				map.put("cartDate", rs.getString("cartDate"));
				list.add(map);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return list;
	}
	
}
