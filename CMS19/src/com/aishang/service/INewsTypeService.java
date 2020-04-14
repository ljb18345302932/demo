package com.aishang.service;

import java.util.List;

import com.aishang.po.NewsType;

public interface INewsTypeService {
	/**
	 * 类别查询
	 * @return 
	 */
	public List<NewsType> newsTypeList();
	/**
	 * 类别添加
	 * @param newsType 
	 */
	public int addNewsType(NewsType newsType);
	/**
	 * ajax验证类别重复
	 * @param typeName
	 */
	public boolean ajaxTypeName(String typeName);
	/**
	 * 判断是否重复
	 * @param type
	 * @return
	 */
	public int selDepName(String type);
	/**
	 * 执行修改
	 * @param newsType
	 * @return
	 */
	public int updateNewsType(NewsType newsType);
	/**
	 * 执行删除
	 * @param typeID
	 * @return
	 */
	public int delNewsType(int typeID);
}
