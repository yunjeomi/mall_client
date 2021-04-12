package mall.client.model;

import java.sql.*;
import java.util.*;

import mall.client.commons.DBUtil;
import mall.client.vo.Ebook;

public class EbookDao {
	//select 메소드에 필요한 것 : dbUtil
	private DBUtil dbUtil;
	public List<Ebook> ebookList(int beginRow, int rowPerPage){
		List<Ebook> list = new ArrayList<>();
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT ebook_title ebookTitle, ebook_price ebookPrice FROM ebook ORDER BY ebook_date DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				//ebook.setEbookImg(rs.getNString("ebookImg"));
				list.add(ebook);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {	//try, catch 둘다 적어 코드 중복일 경우
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return list;
	}
}
