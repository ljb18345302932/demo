package com.aishang.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.aishang.dao.IUserDao;
import com.aishang.dao.impl.UserDao;
import com.aishang.po.User;
import com.aishang.service.IUserService;

@WebServlet("/userService")
public class UserService implements IUserService {
	//调用dao
	IUserDao userDao = new UserDao();

	/**
	 * 登录验证
	 * @param request
	 * @param response
	 */
	public User checkUser(User user) {
		// TODO Auto-generated method stub
		//查询数据库
		return userDao.checkUser(user);
	}

	/**
	 * 获取总条数
	 * @return
	 */
	public int getRowCount(String searchName, int searchDep) {
		// TODO Auto-generated method stub
		return userDao.getRowCount(searchName,searchDep);
	}

	/**
	 * 查询用户，执行分页查询
	 * @param pageNow
	 * @param pageSize
	 */
	public List<User> findAllUser(int pageNow, int pageSize,User searchUser) {
		// TODO Auto-generated method stub
		return userDao.findAllUser(pageNow,pageSize,searchUser);
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getPageCount(int pageSize,int pageCount,String searchName,int searchDep) {
		// TODO Auto-generated method stub
		int rowCount = getRowCount(searchName, searchDep);
		if(rowCount%pageSize==0){
			pageCount = rowCount/pageSize;
		}else{
			pageCount = (rowCount/pageSize) + 1;
		}
		
		return pageCount;
	}

	/**
	 * 执行修改
	 */
	public int doUpdateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.doUpdateUser(user);
	}

	/**
	 * 添加用户
	 */
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.adduser(user);
	}

	/**
	 * 删除用户
	 */
	public int delUser(int id) {
		// TODO Auto-generated method stub
		return userDao.delUser(id);
	}

	/**
	 * 修改前查询
	 */
	public User checkUpdateUser(int id) {
		// TODO Auto-generated method stub
		return userDao.checkUpdateUser(id);
	}

	/**
	 * 修改选中用户
	 * @param user
	 * @return
	 */
	public int update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}
	
	/**
	 * ajax查询全部
	 * @param request
	 * @param response
	 */
	public int ajaxFinAllUser(User user) {
		// TODO Auto-generated method stub
		return userDao.ajaxFinAllUser(user);
	}

	/**
	 * 查询全部信息
	 * @return 
	 */
	public Map<Long, String> findAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAllUser();
	}

	/* (non-Javadoc)
	 * 查询部门中是否有用户
	 * @see com.aishang.service.IUserService#selDepartment(int)
	 */
	@Override
	public boolean selDepartment(int typeID) {
		// TODO Auto-generated method stub
		return userDao.selDepartment(typeID);
	}

	
}
