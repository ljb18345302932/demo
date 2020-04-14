package com.aishang.service;

import java.util.List;

import com.aishang.po.Department;

public interface IDepartmentService {
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
	 * @param typeName
	 * @return 
	 */
	public boolean ajaxTypeName(String typeName);
	/**
	 * 修改部门
	 * @param department 
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
