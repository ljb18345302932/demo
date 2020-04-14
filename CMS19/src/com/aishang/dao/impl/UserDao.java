package com.aishang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import util.DBUtil;

import com.aishang.dao.IUserDao;
import com.aishang.po.User;
import com.mysql.jdbc.StringUtils;


public class UserDao implements IUserDao{

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	/**
	 * 登录验证
	 * @param request
	 * @param response
	 */
	public User checkUser(User user) {
		// TODO Auto-generated method stub
		//查询数据库
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_users where userName=? and userPwd=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			rs = ps.executeQuery();
			if(rs.next()){
				//登录成功
				user.setId(rs.getInt("id"));
				user.setDepID(rs.getInt("depID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserSex(rs.getString("userSex"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserPower(rs.getInt("userPower"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}
	
	/**
	 * 获取总条数
	 * @return
	 */
	public int getRowCount(String searchName, int searchDep) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select count(*) as rowCount from tb_users where 1=1");
		if(!"".equals(searchName)){
			stringBuilder.append(" and userName like concat('%',?,'%')");
		}
		if(searchDep!=0){
			stringBuilder.append(" and depID=?");
		}
		
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			if(!"".equals(searchName)){
				if(searchDep==0){
					ps.setString(1, searchName);
				}
			}
			if(searchDep!=0){
				if("".equals(searchName)){
					ps.setInt(1, searchDep);
				}
			}
			
			if(!"".equals(searchName) && searchDep!=0){
				ps.setString(1, searchName);
				ps.setInt(2, searchDep);
				
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

	/**
	 * 查询用户，执行分页查询
	 * @param pageNow
	 * @param pageSize
	 */
	public List<User> findAllUser(int pageNow, int pageSize, User searchUser) {
		// TODO Auto-generated method stub
		List<User> Users = new ArrayList<User>();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_users where 1=1");
		
		if(searchUser!=null){
			if(!StringUtils.isNullOrEmpty(searchUser.getUserName())){
				
				stringBuilder.append(" and userName like concat('%',?,'%')");
			}
			if(searchUser.getDepID()!=0){
				
				stringBuilder.append(" and depID=?");
			}
		}
		
		stringBuilder.append(" order by id desc limit ?,?");
		
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			int i=1;
			for(i=1;i<2;i++){
				if(searchUser!=null){
					if(!StringUtils.isNullOrEmpty(searchUser.getUserName())){
						ps.setString(i, searchUser.getUserName());
						i++;
					}
					if(searchUser.getDepID()!=0){
						ps.setInt(i, searchUser.getDepID());
						i++;
					}
				}
				
				ps.setInt(i, (pageNow-1)*pageSize);
				ps.setInt(i+1, pageSize);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setDepID(rs.getInt("depID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserSex(rs.getString("userSex"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserPower(rs.getInt("userPower"));
				//放到list集合里
				Users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return Users;
	}

	/**
	 * 执行修改
	 */
	public int doUpdateUser(User user) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update tb_users set userName=?,userPwd=?,userSex=?,userAge=?,userPower=? where id=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			ps.setString(3, user.getUserSex());
			ps.setInt(4, user.getUserAge());
			ps.setInt(5, user.getUserPower());
			ps.setInt(6, user.getId());
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

	/**
	 * 添加用户
	 */
	public int adduser(User user) {
		// TODO Auto-generated method stub
		
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("insert into tb_users values(default,?,?,?,?,?,?,?)");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, user.getDepID());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPwd());
			ps.setString(4, "");
			ps.setString(5, user.getUserSex());
			ps.setInt(6, user.getUserAge());
			ps.setInt(7, user.getUserPower());
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return -1;
	}

	/**
	 * 删除用户
	 */
	public int delUser(int id) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from tb_users where id=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, id);
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
	 * 修改前查询
	 * @return
	 */
	public User checkUpdateUser(int id) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_users where id=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setDepID(rs.getInt("depID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserSex(rs.getString("userSex"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserPower(rs.getInt("userPower"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}

	/**
	 * 修改选中用户
	 * @param user
	 * @return
	 */
	public int update(User user) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update tb_users set userName=?,userPwd=?,depID=?,userSex=?,userAge=?,userPower=? where id=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			ps.setInt(3, user.getDepID());
			ps.setString(4, user.getUserSex());
			ps.setInt(5, user.getUserAge());
			ps.setInt(6, user.getUserPower());
			ps.setInt(7, user.getId());
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
	 * ajax查询全部
	 * @param request
	 * @param response
	 */
	public int ajaxFinAllUser(User user) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_users where userName=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, user.getUserName());
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
	 * 查询全部信息
	 * @return 
	 */
	public Map<Long, String> findAllUser() {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_users");
		conn = DBUtil.getConnection();
		try {
			Map<Long, String> u = new HashMap<Long, String>();
			ps = conn.prepareStatement(stringBuilder.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setDepID(rs.getInt("depID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserSex(rs.getString("userSex"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserPower(rs.getInt("userPower"));
				u.put((long) user.getId(), user.getUserName());
			}
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return null;
	}

	/* (non-Javadoc)
	 * 查询部门中是否有用户
	 * @see com.aishang.dao.IUserDao#selDepartment(int)
	 */
	@Override
	public boolean selDepartment(int typeID) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_users where depID=?");
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
