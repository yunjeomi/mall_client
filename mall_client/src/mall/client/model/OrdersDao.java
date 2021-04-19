package mall.client.model;

import java.sql.*;
import java.util.*;

import mall.client.vo.*;

import mall.client.commons.DBUtil;

public class OrdersDao {
	private DBUtil dbUtil;
	
	//인기상품 출력 메소드
	public List<Map<String, Object>> bestOrdersList(){
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		/*
		 * SELECT t.ebook_no ebookNo, t.cnt cnt, e.ebook_title ebookTitle, e.ebook_price ebookPrice
			FROM 
				(SELECT ebook_no, COUNT(ebook_no) cnt
				FROM orders
				WHERE orders_state = '주문완료'
				GROUP BY ebook_no
				HAVING COUNT(ebook_no) > 1
				ORDER BY COUNT(ebook_no) DESC
				LIMIT 5) t INNER JOIN ebook e
			ON t.ebook_no = e.ebook_no
		 */
		
		try {
			String sql = "SELECT t.ebook_no ebookNo, t.cnt cnt, e.ebook_title ebookTitle, e.ebook_price ebookPrice, e.ebook_author ebookAuthor FROM (SELECT ebook_no, COUNT(ebook_no) cnt FROM orders WHERE orders_state = '주문완료' GROUP BY ebook_no HAVING COUNT(ebook_no) > 1 LIMIT 5) t INNER JOIN ebook e ON t.ebook_no = e.ebook_no ORDER BY t.cnt DESC";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.println("bestOrdersList stmt-> "+stmt);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("cnt", rs.getInt("cnt"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("ebookAuthor", rs.getString("ebookAuthor"));
				map.put("ebookPrice", rs.getInt("ebookPrice"));
				list.add(map);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return list;
		
	}
	
	
	//주문리스트 출력 메소드
	public List<Map<String, Object>> ordersListByClient(int clientNo){
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		/*SELECT
			o.orders_no,
			o.ebook_no,
			o.orders_date,
			o.orders_state,
			e.ebook_title,
			e.ebook_price
		FROM orders o INNER JOIN ebook e
		ON o.ebook_no = e.ebook_no
		WHERE o.client_no = ?*/
		
		try {
			String sql = "SELECT o.orders_no ordersNo, o.ebook_no ebookNo, o.orders_date ordersDate, o.orders_state ordersState, e.ebook_title ebookTitle, e.ebook_price ebookPrice FROM orders o INNER JOIN ebook e ON o.ebook_no = e.ebook_no WHERE o.client_no = ?";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, clientNo);
			System.out.println("ordersList stmt-> "+stmt);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ordersNo",rs.getInt("ordersNo"));
				map.put("ebookNo",rs.getInt("ebookNo"));
				map.put("ordersDate",rs.getString("ordersDate"));
				map.put("ordersState",rs.getString("ordersState"));
				map.put("ebookTitle",rs.getString("ebookTitle"));
				map.put("ebookPrice",rs.getInt("ebookPrice"));
				list.add(map);
			}
			
		} catch(Exception e) {
			e.printStackTrace();//예외 발생 시 시스템을 멈추고 함수호출 스택구조를 콘솔 출력하겠다.
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return list;
	}
	
	
	//장바구니->주문리스트로 넣기 메소드
	public void insertOrders(Orders orders) {
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO orders(ebook_no, client_no, orders_date, orders_state) VALUES(?, ?, NOW(), '주문완료')";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orders.getEbookNo());
			stmt.setInt(2, orders.getClientNo());
			System.out.println("insertOrders stmt-> "+stmt);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();//예외 발생 시 시스템을 멈추고 함수호출 스택구조를 콘솔 출력하겠다.
		} finally {
			this.dbUtil.close(conn, stmt, null);
		}
		
		return;
	}
}
