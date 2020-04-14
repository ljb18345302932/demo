package com.aishang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aishang.dao.INewsTypeDao;
import com.aishang.po.NewsType;

import util.DBUtil;

public class NewsTypeDao implements INewsTypeDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	/**
	 * 类别查询
	 * @return 
	 */
	public List<NewsType> newsTypeList() {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_newstype order by sort");
		conn = DBUtil.getConnection();
		try {
			List<NewsType> news = new ArrayList<NewsType>();
			ps = conn.prepareStatement(stringBuilder.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				NewsType newsType = new NewsType();
				newsType.setId(rs.getInt("id"));
				newsType.setTypeName(rs.getString("typeName"));
				newsType.setSort(rs.getInt("sort"));
				news.add(newsType);
			}
			return news;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}
	/**
	 * 类别添加
	 * @param newsType 
	 */
	public int addNewsType(NewsType newsType) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("insert into tb_newstype values(default,?,?) ");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, newsType.getTypeName());
			ps.setInt(2, newsType.getSort());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}
	/**
	 * ajax验证部门重复
	 * @param typeName
	 */
	public boolean ajaxTypeName(String typeName) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_newstype where typeName=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, typeName);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return false;
	}
	/**
	 * 判断是否重复
	 * @param type
	 * @return
	 */
	public int selDepName(String type) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select typeName from tb_newstype where typeName=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, type);
			rs = ps.executeQuery();
			if(rs.next()){
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}
	/**
	 * 执行修改
	 * @param newsType
	 * @return
	 */
	public int updateNewsType(NewsType newsType) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update tb_newstype set typeName=?,sort=? where id=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, newsType.getTypeName());
			ps.setInt(2, newsType.getSort());
			ps.setInt(3, newsType.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}
	/**
	 * 执行删除
	 * @param typeID
	 * @return
	 */
	public int delNewsType(int typeID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from tb_newstype where id=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, typeID);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}

}
