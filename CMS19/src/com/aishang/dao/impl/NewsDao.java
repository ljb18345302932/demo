package com.aishang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.DBUtil;

import com.aishang.dao.INewsDao;
import com.aishang.po.News;
import com.aishang.po.User;
import com.mysql.jdbc.StringUtils;

public class NewsDao implements INewsDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	/**
	 * 查询新闻列表
	 * @return 
	 */
	public List<News> newsList(int pageNow,int pageSize,News news,User checkUser) {
		// TODO Auto-generated method stub
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_news where 1=1");
		if(news!=null){
			if(!StringUtils.isNullOrEmpty(news.getTitle())){
				stringBuilder.append(" and title like concat('%',?,'%')");
			}
			if(news.getFlag()>=0){
				stringBuilder.append(" and flag=?");
			}
			if(news.getTypeid()!=0){
				stringBuilder.append(" and typeid=?");
			}
			if(checkUser.getUserPower()==0){
				stringBuilder.append(" and uid=?");
			}
		}
		stringBuilder.append(" order by id desc limit ?,?");
		conn = DBUtil.getConnection();
		try {
			List<News> n = new ArrayList<News>();
			ps = conn.prepareStatement(stringBuilder.toString());
			if(news!=null){
				int i=1;
				for(i=1;i<2;i++){
					if(!StringUtils.isNullOrEmpty(news.getTitle())){
						ps.setString(i, news.getTitle());
						i++;
					}
					if(news.getFlag()>=0){
						ps.setInt(i, news.getFlag());
						i++;
					}
					if(news.getTypeid()!=0){
						ps.setInt(i, news.getTypeid());
						i++;
					}
					if(checkUser.getUserPower()==0){
						ps.setLong(i, news.getUid());
						i++;
					}
					ps.setInt(i, (pageNow-1)*pageSize);
					ps.setInt((i+1), pageSize);
				}
			}
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				News ne = new News();
				ne.setId(rs.getInt("id"));
				ne.setTitle(rs.getString("title"));
				ne.setContent(rs.getString("content"));
				ne.setTypeid(rs.getInt("typeid"));
				ne.setFlag(rs.getInt("flag"));
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = fmt.parse(rs.getString("createtime"));//将数据库出的 timestamp 类型的时间转换为java的Date类型  
				String s = fmt.format(date); 
				ne.setCreatetime(s);
				ne.setUid(rs.getLong("uid"));
				n.add(ne);
			}
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}
	
	/**
	 * 查询总条数
	 */
	public int getRowCount(News news,User checkUser) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select count(*) rowCount from tb_news where 1=1");
		if(news!=null){
			if(!StringUtils.isNullOrEmpty(news.getTitle())){
				stringBuilder.append(" and title like concat('%',?,'%')");
			}
			if(news.getFlag()>=0){
				stringBuilder.append(" and flag=?");
			}
			if(news.getTypeid()!=0){
				stringBuilder.append(" and typeid=?");
			}
			if(checkUser.getUserPower()==0){
				stringBuilder.append(" and uid=?");
			}
		}
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			if(news!=null){
				int i=1;
				for(i=1;i<2;i++){
					if(!StringUtils.isNullOrEmpty(news.getTitle())){
						ps.setString(i, news.getTitle());
						i++;
					}
					if(news.getFlag()>=0){
						ps.setInt(i, news.getFlag());
						i++;
					}
					
					if(news.getTypeid()!=0){
						ps.setInt(i, news.getTypeid());
						i++;
					}
					if(checkUser.getUserPower()==0){
						ps.setLong(i, news.getUid());
					}
				}
			}
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt("rowCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}

	/* 添加新闻
	 * @see com.aishang.dao.INewsDao#addNews()
	 */
	@Override
	public int addNews(News news) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("insert into tb_news values(default,?,?,?,?,?,?) ");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, news.getTitle());//标题
			ps.setString(2, news.getContent());//内容
			ps.setInt(3, news.getTypeid());//类别
			ps.setInt(4, news.getFlag());//是否审核
			ps.setString(5, news.getCreatetime());//时间
			ps.setLong(6, news.getUid());
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * 修改回显
	 * @see com.aishang.dao.INewsDao#updateEcho()
	 */
	@Override
	public News updateEcho(int id) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_news where id=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				News ne = new News();
				ne.setId(rs.getInt("id"));
				ne.setTitle(rs.getString("title"));
				ne.setContent(rs.getString("content"));
				ne.setTypeid(rs.getInt("typeid"));
				ne.setFlag(rs.getInt("flag"));
				ne.setCreatetime(rs.getString("createtime"));
				ne.setUid(rs.getLong("uid"));
				return ne;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}

	/* (non-Javadoc)
	 * 执行修改
	 * @see com.aishang.dao.INewsDao#updateNews(int)
	 */
	@Override
	public int updateNews(int id,News news) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update tb_news set title=?,content=?,typeid=?,flag=?,createtime=? where id=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getContent());
			ps.setInt(3, news.getTypeid());
			ps.setInt(4, news.getFlag());
			ps.setString(5, news.getCreatetime());
			ps.setInt(6, id);
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * 删除新闻
	 * @see com.aishang.dao.INewsDao#delNews(java.lang.String)
	 */
	@Override
	public int delNews(int check) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from tb_news where id=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, check);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * 修改审核
	 * @see com.aishang.dao.INewsDao#updatePend(int)
	 */
	@Override
	public int updatePend(int upPend,int id) {
		// TODO Auto-generated method stub
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update tb_news set flag=? where id=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, upPend);
			ps.setInt(2, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * 查询uid
	 * @see com.aishang.dao.INewsDao#selUid(int)
	 */
	@Override
	public boolean selUid(int id) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select uid from tb_news where uid=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, id);
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

	/* (non-Javadoc)
	 * 用id查询内容
	 * @see com.aishang.dao.INewsDao#mainContent(int)
	 */
	@Override
	public List<News> mainContent(int id, int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_news where typeid=? and flag=? limit ?,?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, id);
			ps.setInt(2, 2);
			ps.setInt(3, (pageNow-1)*pageSize);
			ps.setInt(4, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setTypeid(rs.getInt("typeid"));
				news.setFlag(rs.getInt("flag"));
				news.setCreatetime(rs.getString("createtime"));
				news.setUid(rs.getLong("uid"));
				list.add(news);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}

	/* (non-Javadoc)
	 * 查询所有通过的新闻
	 * @see com.aishang.dao.INewsDao#selNews()
	 */
	@Override
	public List<News> selNews(int i) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_news where flag=? and typeid=? limit ?,?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, 2);
			ps.setInt(2, i);
			ps.setInt(3, 0);
			ps.setInt(4, 6);
			rs = ps.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setTypeid(rs.getInt("typeid"));
				news.setFlag(rs.getInt("flag"));
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = fmt.parse(rs.getString("createtime"));//将数据库出的 timestamp 类型的时间转换为java的Date类型  
				String s = fmt.format(date); 
				news.setCreatetime(s);
				news.setUid(rs.getLong("uid"));
				list.add(news);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}

	/* (non-Javadoc)
	 * 查询对应通过的新闻
	 * @see com.aishang.dao.INewsDao#selectNews(int)
	 */
	@Override
	public List<News> selectNews(int id) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_news where flag=? and id=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, 2);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setTypeid(rs.getInt("typeid"));
				news.setFlag(rs.getInt("flag"));
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = fmt.parse(rs.getString("createtime"));//将数据库出的 timestamp 类型的时间转换为java的Date类型  
				String s = fmt.format(date);
				news.setCreatetime(s);
				news.setUid(rs.getLong("uid"));
				list.add(news);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}

	/* (non-Javadoc)
	 * 总条数
	 * @see com.aishang.dao.INewsDao#mainRowCount()
	 */
	@Override
	public int mainRowCount(int typeid, int pageNow, int pageSize, int pageCount) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select count(*) rowCount from tb_news where flag=? and typeid=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, 2);
			ps.setInt(2, typeid);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt("rowCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * 查询类别中是否有新闻
	 * @see com.aishang.dao.INewsDao#selType(int)
	 */
	@Override
	public boolean selType(int typeID) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_news where typeid=?");
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, typeID);
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

}
