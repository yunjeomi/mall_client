package mall.client.model;

import java.sql.*;

import mall.client.commons.DBUtil;
import mall.client.vo.Stats;

public class StatsDao {
	private DBUtil dbUtil;
	
	//오늘 방문자 수 구하는 메소드
	public Stats statsByToday() {
		Stats stats = null;
		
		//SELECT stats_day statsDay, stats_count statsCount FROM stats WHERE stats_day = DATE(NOW())
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT stats_day statsDay, stats_count statsCount FROM stats WHERE stats_day = DATE(NOW())";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.println("statsByToday stmt-> "+stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				stats = new Stats();
				stats.setStatsDay(rs.getString("statsDay"));
				stats.setStatsCount(rs.getInt("statsCount"));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		
		return stats;
	}
	
	//방문자 수 없으면 추가하는 메소드
	public void insertStats() {
		//INSERT INTO stats(stats_day, stats_count) VALUES (DATE(NOW()), 1)
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO stats(stats_day, stats_count) VALUES (DATE(NOW()), 1)";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.println("insertStats stmt-> "+stmt);
			stmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, null);
		}
	}
	
	//방문자 수 증가시키는 메소드
	public void updateStats() {
		//UPDATE stats SET stats_count = stats_count+1 WHERE stats_day=DATE(NOW())
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE stats SET stats_count = stats_count+1 WHERE stats_day=DATE(NOW())";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.println("updateStats stmt-> "+stmt);
			stmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, null);
		}
	}
	
	//토탈 방문자 수 구하는 메소드
	public long statsTotal() {
		long total = 0;
		//SELECT SUM(stats_count) total FROM stats
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT SUM(stats_count) total FROM stats";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.println("statsTotal stmt-> "+stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getLong("total");
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, stmt, rs);
		}
		return total;
	}
}
