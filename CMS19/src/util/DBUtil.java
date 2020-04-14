package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;



public class DBUtil {
//	private static String url = "jdbc:mysql://localhost:3306/servle";
//	private static String dbuser = "root";
//	private static String dbpass = "";
//	static {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	// 创建链接
//	public static Connection getConnection() {
//		Connection connection = null;
//		try {
//			connection = (Connection) DriverManager.getConnection(url, dbuser,
//					dbpass);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return connection;
//	}
//	//关闭连接
//	public static void realse(Connection connection,PreparedStatement prepareStatement,ResultSet rs){
//		try {
//			if(rs!=null){
//				rs.close();
//				rs = null;
//			}
//			if(prepareStatement != null){
//				prepareStatement.close();
//				prepareStatement = null;
//			}
//			if(connection!=null){
//				connection.close();
//				connection = null;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	private static DataSource ds = null;
	static {
		Properties prop = new Properties();
		try {
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void realse(Connection connection,Statement prepareStatement,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
				rs = null;
			}
			if(prepareStatement != null){
				prepareStatement.close();
				prepareStatement = null;
			}
			if(connection!=null){
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
