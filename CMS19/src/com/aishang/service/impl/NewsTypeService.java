package com.aishang.service.impl;

import java.util.List;

import com.aishang.dao.INewsTypeDao;
import com.aishang.dao.impl.NewsTypeDao;
import com.aishang.po.NewsType;
import com.aishang.service.INewsTypeService;

public class NewsTypeService implements INewsTypeService {
	INewsTypeDao newsTypeDao = new NewsTypeDao();

	/**
	 * 类别查询
	 * @return 
	 */
	public List<NewsType> newsTypeList() {
		// TODO Auto-generated method stub
		return newsTypeDao.newsTypeList();
	}

	/**
	 * 类别添加
	 * @param newsType 
	 */
	public int addNewsType(NewsType newsType) {
		// TODO Auto-generated method stub
		return newsTypeDao.addNewsType(newsType);
	}

	/**
	 * ajax验证类别重复
	 * @param typeName
	 */
	public boolean ajaxTypeName(String typeName) {
		// TODO Auto-generated method stub
		return newsTypeDao.ajaxTypeName(typeName);
	}

	/**
	 * 判断是否重复
	 * @param type
	 * @return
	 */
	public int selDepName(String type) {
		// TODO Auto-generated method stub
		return newsTypeDao.selDepName(type);
	}

	/**
	 * 执行修改
	 * @param newsType
	 * @return
	 */
	public int updateNewsType(NewsType newsType) {
		// TODO Auto-generated method stub
		return newsTypeDao.updateNewsType(newsType);
	}

	/**
	 * 执行删除
	 * @param typeID
	 * @return
	 */
	public int delNewsType(int typeID) {
		// TODO Auto-generated method stub
		return newsTypeDao.delNewsType(typeID);
	}
	
}
