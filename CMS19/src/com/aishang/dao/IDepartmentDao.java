package com.aishang.dao;

import java.util.List;

import com.aishang.po.Department;

public interface IDepartmentDao {
	/**
	 * 查询部门
	 */
	public List<Department> findDepartment();
	/**
	 * 添加部门
	 * @return 
	 */
	public int addDepartment(Department department);
	/**
	 * 查询数据库比较
	 * @return 
	 */
	public boolean ajaxTypeName(String typeName);
	/**
	 * 修改部门
	 * @return 
	 */
	public int updateDepartment(Department department);
	/**
	 * 查询是否有重复部门名称
	 * @param type 
	 * @return 
	 */
	public int selDepName(String type);
	/**
	 * 删除部门
	 * @param typeID
	 * @return 
	 */
	public int delDepartment(int typeID);
}
