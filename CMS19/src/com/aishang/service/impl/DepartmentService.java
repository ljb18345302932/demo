package com.aishang.service.impl;

import java.util.List;

import com.aishang.dao.IDepartmentDao;
import com.aishang.dao.impl.DepartmentDao;
import com.aishang.po.Department;
import com.aishang.service.IDepartmentService;

public class DepartmentService implements IDepartmentService {
	IDepartmentDao departmentDao = new DepartmentDao();
	/**
	 * 查询部门
	 */
	public List<Department> findDepartment() {
		// TODO Auto-generated method stub
		return departmentDao.findDepartment();
	}
	/**
	 * 添加部门
	 * @return 
	 */
	public int addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.addDepartment(department);
	}
	/**
	 * 查询数据库比较
	 * @param typeName
	 * @return 
	 */
	public boolean ajaxTypeName(String typeName) {
		// TODO Auto-generated method stub
		return departmentDao.ajaxTypeName(typeName);
	}
	/**
	 * 修改部门
	 * @param department 
	 * @return 
	 */
	public int updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.updateDepartment(department);
	}
	/**
	 * 查询是否有重复部门名称
	 * @param type 
	 * @return 
	 */
	public int selDepName(String type) {
		// TODO Auto-generated method stub
		return departmentDao.selDepName(type);
	}
	/**
	 * 删除部门
	 * @param typeID 
	 * @return 
	 */
	public int delDepartment(int typeID) {
		// TODO Auto-generated method stub
		return departmentDao.delDepartment(typeID);
	}

}
