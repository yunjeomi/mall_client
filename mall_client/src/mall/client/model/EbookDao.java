package mall.client.model;

import java.sql.*;
import java.util.*;

import mall.client.commons.DBUtil;
import mall.client.vo.Ebook;

public class EbookDao {
	//select 메소드에 필요한 것 : dbUtil
	private DBUtil dbUtil;
	
	//totalRow 메소드
	public int totalRow() {
		int totalCnt = 0;
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) cnt FROM ebook";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.println("totalRow stmt-> "+stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalCnt = rs.getInt("cnt");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return totalCnt;
	}
	
	//totalRow 메소드 +category
	public int totalRowOfCategory(String categoryName) {
		int totalCnt = 0;
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) cnt FROM ebook WHERE category_name=?";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoryName);
			System.out.println("totalRow stmt-> "+stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalCnt = rs.getInt("cnt");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return totalCnt;
	}
	
	
	//ebookOne출력 메소드
	public Ebook ebookOne(int ebookNo) {
		Ebook ebook = new Ebook();
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT ebook_no ebookNo, ebook_isbn ebookISBN, category_name categoryName, ebook_title ebookTitle, ebook_author ebookAuthor, ebook_company ebookCompany, ebook_page_count ebookPageCount, ebook_price ebookPrice, ebook_img ebookImg, ebook_summary ebookSummary, ebook_date ebookDate, ebook_state ebookState FROM ebook WHERE ebook_no = ?";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ebookNo);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				ebook.setEbookNo(rs.getInt("ebookNo"));
				ebook.setEbookISBN(rs.getString("ebookISBN"));
				ebook.setCategoryName(rs.getString("categoryName"));
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookAuthor(rs.getString("ebookAuthor"));
				ebook.setEbookCompany(rs.getString("ebookCompany"));
				ebook.setEbookPageCount(rs.getInt("ebookPageCount"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				ebook.setEbookImg(rs.getString("ebookImg"));
				ebook.setEbookSummary(rs.getString("ebookSummary"));
				ebook.setEbookDate(rs.getString("ebookDate"));
				ebook.setEbookState(rs.getString("ebookState"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return ebook;
	}
	
	//ebookList출력 메소드
	public List<Ebook> ebookList(int beginRow, int rowPerPage, String searchWord){
		List<Ebook> list = new ArrayList<>();
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE ebook_title LIKE ? ORDER BY ebook_date DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+searchWord+"%");
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			System.out.println("ebookList non category stmt-> "+stmt);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebookNo"));
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
	
	//ebookList 출력메소드 + 원하는 category
	public List<Ebook> ebookListByCategory(int beginRow, int rowPerPage, String searchWord, String categoryName){
		List<Ebook> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			if(!searchWord.equals("")) {	//검색어 없을 때
				String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE category_name = ? ORDER BY ebook_date DESC LIMIT ?, ?";
				conn = this.dbUtil.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categoryName);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
				System.out.println("ebookList by category non searchWord stmt-> "+stmt);
				rs = stmt.executeQuery();
				while(rs.next()) {
					Ebook ebook = new Ebook();
					ebook.setEbookNo(rs.getInt("ebookNo"));
					ebook.setEbookTitle(rs.getString("ebookTitle"));
					ebook.setEbookPrice(rs.getInt("ebookPrice"));
					//ebook.setEbookImg(rs.getNString("ebookImg"));
					list.add(ebook);
				}
			} else {	//검색어 있을 때
				String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE ebook_title LIKE ? AND category_name = ? ORDER BY ebook_date DESC LIMIT ?, ?";
				conn = this.dbUtil.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchWord+"%");
				stmt.setString(2, categoryName);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
				System.out.println("ebookList by category with searchWord stmt-> "+stmt);
				rs = stmt.executeQuery();
				while(rs.next()) {
					Ebook ebook = new Ebook();
					ebook.setEbookNo(rs.getInt("ebookNo"));
					ebook.setEbookTitle(rs.getString("ebookTitle"));
					ebook.setEbookPrice(rs.getInt("ebookPrice"));
					//ebook.setEbookImg(rs.getNString("ebookImg"));
					list.add(ebook);
				}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
