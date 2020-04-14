package com.aishang.dao;

import java.text.ParseException;
import java.util.List;

import com.aishang.po.News;
import com.aishang.po.User;

/**
 * @author yc950710
 *
 */
/**
 * @author yc950710
 *
 */
public interface INewsDao {
	/**
	 * 查询新闻列表
	 * @return 
	 */
	public List<News> newsList(int pageNow,int pageSize,News news,User checkUser);

	/**
	 * 查询总条数
	 */
	public int getRowCount(News news,User checkUser);
	/**
	 * 添加新闻
	 * @return 
	 */
	public int addNews(News news);

	/**
	 * 修改回显
	 * @return 
	 */
	public News updateEcho(int id);

	/**
	 * 执行修改
	 * @param id
	 * @param news 
	 * @return 
	 */
	public int updateNews(int id, News news);

	/**
	 * 删除新闻
	 * @param check
	 * @return 
	 */
	public int delNews(int check);

	/**
	 * 修改审核
	 * @param upPend
	 * @param id 
	 * @param selId 
	 * @return 
	 */
	public int updatePend(int upPend, int id);

	/**
	 * 查询uid
	 * @param id
	 * @return 
	 */
	public boolean selUid(int id);

	/**
	 * 用id查询内容
	 * @param id
	 * @param pageNow2 
	 * @param pageNow 
	 * @return
	 */
	public List<News> mainContent(int id, int pageNow, int pageSize);

	/**
	 * 查询所有通过的新闻
	 * @param i 
	 * @return 
	 * @throws ParseException 
	 */
	public List<News> selNews(int i);

	/**
	 * 查询对应通过的新闻
	 * @param id
	 * @return
	 */
	public List<News> selectNews(int id);

	/**
	 * 总条数
	 * @param pageCount 
	 * @param pageSize 
	 * @param pageNow 
	 * @return
	 */
	public int mainRowCount(int typeid, int pageNow, int pageSize, int pageCount);

	/**
	 * 查询类别中是否有新闻
	 * @param typeID
	 * @return
	 */
	public boolean selType(int typeID);

}
