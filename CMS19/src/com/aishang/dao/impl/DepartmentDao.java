package com.aishang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import com.aishang.dao.IDepartmentDao;
import com.aishang.po.Department;

public class DepartmentDao implements IDepartmentDao {
	
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	/**
	 * 查询部门
	 */
	public List<Department> findDepartment() {
		// TODO Auto-generated method stub
		List<Department> departments = new ArrayList<Department>();
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_department order by sort");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				Department department = new Department();
				department.setId(rs.getInt("id"));
				department.setDepName(rs.getString("depName"));
				department.setSort(rs.getInt("sort"));
				departments.add(department);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.realse(conn, ps, rs);
		}
		return departments;
	}
	/**
	 * 添加部门
	 * @return 
	 */
	public int addDepartment(Department department) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("insert into tb_department values(default,?,?,?) ");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, department.getDepName());
			ps.setString(2, department.getDepCreateTime());
			ps.setInt(3, department.getSort());
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
	 * 查询数据库比较
	 * @return 
	 */
	public boolean ajaxTypeName(String typeName) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from tb_department where depName=?");
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
	 * 修改部门
	 * @return 
	 */
	public int updateDepartment(Department department) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update tb_department set depName=?,depCreateTime=?,sort=? where id=?");
		try {
			ps = conn.prepareStatement(stringBuilder.toString());
			ps.setString(1, department.getDepName());
			ps.setString(2, department.getDepCreateTime());
			ps.setInt(3, department.getSort());
			ps.setInt(4, department.getId());
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
	 * 查询是否有重复部门名称
	 * @param type 
	 * @return 
	 */
	public int selDepName(String type) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select depName from tb_department where depName=?");
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
	 * 删除部门
	 * @param typeID
	 * @return 
	 */
	public int delDepartment(int typeID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from tb_department where id=?");
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
