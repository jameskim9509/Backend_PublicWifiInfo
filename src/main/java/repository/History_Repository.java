package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class History_Repository {
	
	public static void createRepository()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("create table if not exists locHistory("
            		+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            		+ "X TEXT,"
            		+ "Y TEXT,"
            		+ "SEARCH_DATE TEXT)");
            pstmt.executeUpdate();
		}
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 finally
		 {
			 close(null, pstmt, connection);
		 }
	}
	
	public static int saveRepository(ArrayList<Map<String, String>> mapArr) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	        pstmt = connection.prepareStatement("insert into locHistory(X, Y, SEARCH_DATE) "
	        		+ "values(?, ?, datetime('now', 'localtime'))");
	        
	        for(int i = 0; i < mapArr.size(); i++)
            {
            	pstmt.setString(1, mapArr.get(i).get("LAT"));
            	pstmt.setString(2, mapArr.get(i).get("LNT"));
            	cnt += pstmt.executeUpdate();
            }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(null, pstmt, connection);
		}
		
		return cnt;
	}
	
	public static ArrayList<Map<String, String>> searchHistory()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Map<String, String>> rows = new ArrayList<>(); 
		
        try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("select * from locHistory order by id desc");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	Map<String, String> row = new HashMap<>();
            	row.put("ID", rs.getString("ID"));
            	row.put("X", rs.getString("X"));
            	row.put("Y", rs.getString("Y"));
            	row.put("SEARCH_DATE", rs.getString("SEARCH_DATE"));
            	
            	rows.add(row);
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		finally
		{
			close(rs, pstmt, connection);
		}
        
        return rows;
	}
	
	public static void deleteRowById(String id)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	        pstmt = connection.prepareStatement("delete from locHistory where id=?");
        	pstmt.setString(1, id);
            	
	        pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(null, pstmt, connection);
		}
	}
	
	private static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if(rs != null) rs.close();
		} catch(Exception e) {}
		
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {}
	
		try {
			if(con != null) con.close();
		} catch (SQLException e) {}
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ System.getProperty("user.home") + "/test.db");
		} catch (SQLException e) {}
		return connection;
	}
}
