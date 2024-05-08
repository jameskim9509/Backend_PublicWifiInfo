package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Public_Wifi_Info_Repository{
	
	public static void createRepository()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
            Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	  
	        pstmt = connection.prepareStatement("create table if not exists TbPublicWifiInfo("
	            		+ "X_SWIFI_MGR_NO TEXT PRIMARY KEY,"
	            		+ "X_SWIFI_WRDOFC TEXT,"
	            		+ "X_SWIFI_MAIN_NM TEXT,"
	            		+ "X_SWIFI_ADRES1 TEXT,"
	            		+ "X_SWIFI_ADRES2 TEXT,"
	            		+ "X_SWIFI_INSTL_FLOOR TEXT,"
	            		+ "X_SWIFI_INSTL_TY TEXT,"
	            		+ "X_SWIFI_INSTL_MBY TEXT,"
	            		+ "X_SWIFI_SVC_SE TEXT,"
	            		+ "X_SWIFI_CMCWR TEXT,"
	            		+ "X_SWIFI_CNSTC_YEAR TEXT,"
	            		+ "X_SWIFI_INOUT_DOOR TEXT,"
	            		+ "X_SWIFI_REMARS3 TEXT,"
	            		+ "LAT TEXT,"
	            		+ "LNT TEXT,"
	            		+ "WORK_DTTM TEXT"
	            		+ "DISTANCE TEXT)");
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
	
	public static void deleteRepositoryRows()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
	        connection = getConnection();
	        
			pstmt = connection.prepareStatement("delete from TbPublicWifiInfo");
			pstmt.executeUpdate();
			pstmt.close();
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
			pstmt = connection.prepareStatement("insert into TbPublicWifiInfo "
					+ "(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2,"
					+ " X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR,"
					+ " X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM)"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        
            for(int i = 0; i < mapArr.size(); i++)
            {
            	pstmt.setString(1, mapArr.get(i).get("X_SWIFI_MGR_NO"));
            	pstmt.setString(2, mapArr.get(i).get("X_SWIFI_WRDOFC"));
            	pstmt.setString(3, mapArr.get(i).get("X_SWIFI_MAIN_NM"));
            	pstmt.setString(4, mapArr.get(i).get("X_SWIFI_ADRES1"));
            	pstmt.setString(5, mapArr.get(i).get("X_SWIFI_ADRES2"));
            	pstmt.setString(6, mapArr.get(i).get("X_SWIFI_INSTL_FLOOR"));
            	pstmt.setString(7, mapArr.get(i).get("X_SWIFI_INSTL_TY"));
            	pstmt.setString(8, mapArr.get(i).get("X_SWIFI_INSTL_MBY"));
            	pstmt.setString(9, mapArr.get(i).get("X_SWIFI_SVC_SE"));
            	pstmt.setString(10, mapArr.get(i).get("X_SWIFI_CMCWR"));
            	pstmt.setString(11, mapArr.get(i).get("X_SWIFI_CNSTC_YEAR"));
            	pstmt.setString(12, mapArr.get(i).get("X_SWIFI_INOUT_DOOR"));
            	pstmt.setString(13, mapArr.get(i).get("X_SWIFI_REMARS3"));
            	pstmt.setString(14, mapArr.get(i).get("LAT"));
            	pstmt.setString(15, mapArr.get(i).get("LNT"));
            	pstmt.setString(16, mapArr.get(i).get("WORK_DTTM"));
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
	
	
	
	public static Map<String, String> searchByMgrNo(String mgrNo)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> row = new HashMap<>(); 
		
        try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("select * from TbPublicWifiInfo where x_swifi_mgr_no=?");
            pstmt.setString(1, mgrNo);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	row.put("DISTANCE", rs.getString("DISTANCE"));
            	row.put("X_SWIFI_MGR_NO", rs.getString("X_SWIFI_MGR_NO"));
            	row.put("X_SWIFI_WRDOFC", rs.getString("X_SWIFI_WRDOFC"));
            	row.put("X_SWIFI_MAIN_NM", rs.getString("X_SWIFI_MAIN_NM"));
            	row.put("X_SWIFI_ADRES1", rs.getString("X_SWIFI_ADRES1"));
            	row.put("X_SWIFI_ADRES2", rs.getString("X_SWIFI_ADRES2"));
            	row.put("X_SWIFI_INSTL_FLOOR", rs.getString("X_SWIFI_INSTL_FLOOR"));
            	row.put("X_SWIFI_INSTL_TY", rs.getString("X_SWIFI_INSTL_TY"));
            	row.put("X_SWIFI_INSTL_MBY", rs.getString("X_SWIFI_INSTL_MBY"));
            	row.put("X_SWIFI_SVC_SE", rs.getString("X_SWIFI_SVC_SE"));
            	row.put("X_SWIFI_CMCWR", rs.getString("X_SWIFI_CMCWR"));
            	row.put("X_SWIFI_CNSTC_YEAR", rs.getString("X_SWIFI_CNSTC_YEAR"));
            	row.put("X_SWIFI_INOUT_DOOR", rs.getString("X_SWIFI_INOUT_DOOR"));
            	row.put("X_SWIFI_REMARS3", rs.getString("X_SWIFI_REMARS3"));
            	row.put("LAT", rs.getString("LAT"));
            	row.put("LNT", rs.getString("LNT"));
            	row.put("WORK_DTTM",rs.getString("WORK_DTTM"));
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
	
	public static void updateDistance(ArrayList<Map<String, String>> mapArr)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("update TbPublicWifiInfo set distance=? "
            		+ "where x_swifi_mgr_no=?");
            for(int i = 0; i < mapArr.size(); i++)
            {
            	pstmt.setString(1, mapArr.get(i).get("DISTANCE"));
            	pstmt.setString(2, mapArr.get(i).get("X_SWIFI_MGR_NO"));
            	pstmt.executeUpdate();
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		finally
		{
			close(null, pstmt, connection);
		}
	}
	
	public static void deleteAllDistanceData()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("update TbPublicWifiInfo set distance=null ");
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
	
	public static ArrayList<Map<String, String>> searchByDistance(String lat1, String lnt1)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Map<String, String>> rows = new ArrayList<>(); 
		PriorityQueue<Map<String, String>> pq = null;
		
        try {
            Class.forName("org.sqlite.JDBC");
            connection = getConnection();
            
            pstmt = connection.prepareStatement("select * from TbPublicWifiInfo");
            
            pq = new PriorityQueue<>((m1, m2)->{
            	double dist = Double.valueOf(m1.get("DISTANCE")) - Double.valueOf(m2.get("DISTANCE"));						
            	if(dist > 0) return 1;
            	else return -1;
            });
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	Map<String, String> row = new HashMap<>();
            	row.put("DISTANCE", String.format("%.4f", calculateDistance(lat1, lnt1, rs.getString("LAT"), rs.getString("LNT"))));
            	row.put("X_SWIFI_MGR_NO", rs.getString("X_SWIFI_MGR_NO"));
            	row.put("X_SWIFI_WRDOFC", rs.getString("X_SWIFI_WRDOFC"));
            	row.put("X_SWIFI_MAIN_NM", rs.getString("X_SWIFI_MAIN_NM"));
            	row.put("X_SWIFI_ADRES1", rs.getString("X_SWIFI_ADRES1"));
            	row.put("X_SWIFI_ADRES2", rs.getString("X_SWIFI_ADRES2"));
            	row.put("X_SWIFI_INSTL_FLOOR", rs.getString("X_SWIFI_INSTL_FLOOR"));
            	row.put("X_SWIFI_INSTL_TY", rs.getString("X_SWIFI_INSTL_TY"));
            	row.put("X_SWIFI_INSTL_MBY", rs.getString("X_SWIFI_INSTL_MBY"));
            	row.put("X_SWIFI_SVC_SE", rs.getString("X_SWIFI_SVC_SE"));
            	row.put("X_SWIFI_CMCWR", rs.getString("X_SWIFI_CMCWR"));
            	row.put("X_SWIFI_CNSTC_YEAR", rs.getString("X_SWIFI_CNSTC_YEAR"));
            	row.put("X_SWIFI_INOUT_DOOR", rs.getString("X_SWIFI_INOUT_DOOR"));
            	row.put("X_SWIFI_REMARS3", rs.getString("X_SWIFI_REMARS3"));
            	row.put("LAT", rs.getString("LAT"));
            	row.put("LNT", rs.getString("LNT"));
            	row.put("WORK_DTTM",rs.getString("WORK_DTTM"));
            	
            	pq.offer(row);
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		finally
		{
			close(rs, pstmt, connection);
		}
        
        while(rows.size() < 20)	rows.add(pq.poll()); // 거리가 작은 순으로 추출
        updateDistance(rows);
        
        return rows;
	}
	
	private static double calculateDistance(String lat1, String lnt1, String lat2, String lnt2)
	{
		double dlat1 = Double.valueOf(lat1);
		double dlnt1 = Double.valueOf(lnt1);
		double dlat2 = Double.valueOf(lat2);
		double dlnt2 = Double.valueOf(lnt2);
		
		double dif_lat = Math.toRadians(dlat1- dlat2);
		double dif_lnt = Math.toRadians(dlnt1- dlnt2);
		double a = Math.sin(dif_lat/2) * Math.sin(dif_lat/2) +
				Math.cos(Math.toRadians(dlat1)) * Math.cos(Math.toRadians(dlat2)) * Math.sin(dif_lnt/2) * Math.sin(dif_lnt/2);
		double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return 6378 * b;
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
