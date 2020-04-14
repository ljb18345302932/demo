package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class VisitedUtil {
		private static Connection conn;
		private static PreparedStatement ps;
		private static ResultSet rs;

		//从数据库中读取当前保存的访问量
		public static int readVisited(){
			int visited = 0;
			try {
				conn = DBUtil.getConnection();
				ps = conn.prepareStatement("select * from tb_visited");
				rs = ps.executeQuery();
				if(rs.next()){
					visited = rs.getInt("visited");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.realse(conn, ps, rs);
			}
			return visited;
		}
		
		//将当前访问量写入数据库中
		public static int writeVisited(int visited){
			int i = 0;
			try {
				conn = DBUtil.getConnection();
				ps = conn.prepareStatement("update tb_visited set visited = ?");
				ps.setInt(1,visited);
				i = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.realse(conn, ps, rs);
			}
			return i;
		}
		
		
	}

