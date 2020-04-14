package com.aishang.service;

import java.util.List;
import java.util.Map;

import com.aishang.po.User;

public interface IUserService {
	/**
	 * 登录验证
	 * @param request
	 * @param response
	 */
	public User checkUser(User user);
	/**
	 * 获取总条数
	 * @return
	 */
	public int getRowCount(String searchName, int searchDep);
	/**
	 * 查询用户，执行分页查询
	 * @param pageNow
	 * @param pageSize
	 */
	public List<User> findAllUser(int pageNow, int pageSize,User searchUser);
	/**
	 * 获取总页数
	 * @return
	 */
	public int getPageCount(int pageSize,int pageCount,String searchName,int searchDep);
	/**
	 * 执行修改
	 */
	public int doUpdateUser(User user);
	/**
	 * 添加用户
	 */
	public int addUser(User user);
	/**
	 * 删除用户
	 */
	public int delUser(int id);
	/**
	 * 修改前查询
	 */
	public User checkUpdateUser(int id);
	/**
	 * 修改选中用户
	 * @param user
	 * @return
	 */
	public int update(User user);
	/**
	 * ajax查询全部
	 * @param request
	 * @param response
	 */
	public int ajaxFinAllUser(User user);
	/**
	 * 查询全部信息
	 * @return 
	 */
	public Map<Long, String> findAllUser();
	/**
	 * 查询部门中是否有用户
	 * @param typeID
	 * @return 
	 */
	public boolean selDepartment(int typeID);
}
