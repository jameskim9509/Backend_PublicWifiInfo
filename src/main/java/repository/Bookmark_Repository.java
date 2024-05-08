package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bookmark_Repository {
	
	public static void createRepository()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("create table if not exists Bookmark("
            		+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            		+ "NAME TEXT,"
            		+ "NUM TEXT,"
            		+ "GRP_REGISTER_DATE TEXT,"
            		+ "BMK_REGISTER_DATE TEXT,"
            		+ "GRP_MODIFY_DATE TEXT,"
            		+ "X_SWIFI_MGR_NO TEXT,"
            		+ "FOREIGN KEY (X_SWIFI_MGR_NO) "
            		+ "   REFERENCES TbPublicWifiInfo (X_SWIFI_MGR_NO)"
            		+ "      ON DELETE CASCADE"
            		+ "      ON UPDATE CASCADE)");
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
	        pstmt = connection.prepareStatement("insert into Bookmark(name, num, grp_register_date) "
	        		+ "values(?, ?, datetime('now', 'localtime'))");
	        
	        for(int i = 0; i < mapArr.size(); i++)
            {
            	pstmt.setString(1, mapArr.get(i).get("NAME"));
            	pstmt.setString(2, mapArr.get(i).get("NUM"));
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
	
	public static ArrayList<Map<String, String>> searchBookmarkGroupList()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Map<String, String>> rows = new ArrayList<>(); 
		
        try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("select * from Bookmark order by num");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	Map<String, String> row = new HashMap<>();
            	row.put("ID", rs.getString("ID"));
            	row.put("NAME", rs.getString("NAME"));
            	row.put("NUM", rs.getString("NUM"));
            	row.put("GRP_REGISTER_DATE", rs.getString("GRP_REGISTER_DATE"));
            	row.put("GRP_MODIFY_DATE", rs.getString("GRP_MODIFY_DATE"));
            	
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
	
	public static Map<String, String> searchRowById(String id)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> row = new HashMap<>();
		
        try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("select * from Bookmark where id=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	row.put("ID", rs.getString("ID"));
            	row.put("NAME", rs.getString("NAME"));
            	row.put("NUM", rs.getString("NUM"));
            	row.put("GRP_REGISTER_DATE", rs.getString("GRP_REGISTER_DATE"));
            	row.put("GRP_MODIFY_DATE", rs.getString("GRP_MODIFY_DATE"));
            	row.put("X_SWIFI_MGR_NO", rs.getString("X_SWIFI_MGR_NO"));
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		finally
		{
			close(rs, pstmt, connection);
		}
        
        return row;
	}
	
	public static Map<String, String> searchBookmarkById(String id)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> row = new HashMap<>();
		
        try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("select b.id, b.name, p.x_swifi_main_nm, b.bmk_register_date "
            		+ "from Bookmark b INNER JOIN TbPublicWifiInfo p ON b.x_swifi_mgr_no=p.x_swifi_mgr_no "
            		+ "where b.id = ?");
            
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	row.put("ID", rs.getString("ID"));
            	row.put("NAME", rs.getString("NAME"));
            	row.put("X_SWIFI_MAIN_NM", rs.getString("X_SWIFI_MAIN_NM"));
            	row.put("BMK_REGISTER_DATE", rs.getString("BMK_REGISTER_DATE"));
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		finally
		{
			close(rs, pstmt, connection);
		}
        
        return row;
	}
	
	public static ArrayList<Map<String, String>> searchBookmarkList()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Map<String, String>> rows = new ArrayList<>(); 
		
        try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement(""
            		+ "select b.x_swifi_mgr_no, b.id, b.name, p.x_swifi_main_nm, b.bmk_register_date "
            		+ "from Bookmark b INNER JOIN TbPublicWifiInfo p ON b.x_swifi_mgr_no=p.x_swifi_mgr_no "
            		+ "order by b.num");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	Map<String, String> row = new HashMap<>();
            	row.put("ID", rs.getString("ID"));
            	row.put("NAME", rs.getString("NAME"));
            	row.put("X_SWIFI_MAIN_NM", rs.getString("X_SWIFI_MAIN_NM"));
            	row.put("BMK_REGISTER_DATE", rs.getString("BMK_REGISTER_DATE"));
            	row.put("X_SWIFI_MGR_NO", rs.getString("X_SWIFI_MGR_NO"));
            	
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
	
	public static void updateMgrNo(String mgrNo, String id)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	        pstmt = connection.prepareStatement("update Bookmark "
	        		+ "set x_swifi_mgr_no=?, bmk_register_date=datetime('now', 'localtime') "
	        		+ "where id=?");
	        
        	pstmt.setString(1, mgrNo);
        	pstmt.setString(2, id);
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
	
	public static void editBookmarkGroupById(String name, String num, String id)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	        pstmt = connection.prepareStatement("update Bookmark set name=?, num=?, grp_modify_date=datetime('now', 'localtime') "
	        		+ "where id=?");
	        
        	pstmt.setString(1, name);
        	pstmt.setString(2, num);
        	pstmt.setString(3, id);
            	
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
	
	public static void deleteBookmarkById(String id)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	        pstmt = connection.prepareStatement("update Bookmark "
	        		+ "set x_swifi_mgr_no=null, bmk_register_date=null "
	        		+ "where id=?");
	        
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
	
	public static void deleteRowById(String id)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	        pstmt = connection.prepareStatement("delete from Bookmark "
	        		+ "where id=?");
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		try {
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ System.getProperty("user.home") + "/test.db");
		} catch (SQLException e) {}
		
		return connection;
	}
}
