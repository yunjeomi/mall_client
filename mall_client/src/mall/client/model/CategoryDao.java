package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class CategoryDao {
	private DBUtil dbUtil;
	//카테고리 출력 리스트
	public List<Category> categoryList(){
		List<Category> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT category_name categoryName FROM category ORDER BY category_weight DESC";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Category c = new Category();
				c.setCategoryName(rs.getString("categoryName"));
				list.add(c);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return list;
	}
}
