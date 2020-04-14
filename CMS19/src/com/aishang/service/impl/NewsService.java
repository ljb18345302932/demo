package com.aishang.service.impl;

import java.util.List;

import com.aishang.dao.INewsDao;
import com.aishang.dao.impl.NewsDao;
import com.aishang.po.News;
import com.aishang.po.User;
import com.aishang.service.INewsService;


public class NewsService implements INewsService {
	INewsDao newsDao = new NewsDao();
	/**
	 * 查询新闻列表
	 * @return 
	 */
	public List<News> newsList(int pageNow,int pageSize,News news,User checkUser) {
		// TODO Auto-generated method stub
		return newsDao.newsList(pageNow,pageSize,news,checkUser);
	}
	
	/**
	 * 查询总页数
	 * @return
	 */
	public int getPageCount(int pageSize,int pageCount,News news,User checkUser) {
		int rowCount = getRowCount(news,checkUser);
		if(rowCount%pageSize==0){
			pageCount = rowCount/pageSize;
		}else{
			pageCount = (rowCount/pageSize) + 1;
		}
		return pageCount;
	}
	
	/**
	 * 查询总条数
	 * @return
	 */
	public int getRowCount(News news,User checkUser) {
		// TODO Auto-generated method stub
		return newsDao.getRowCount(news,checkUser);
	}

	@Override
	public int delUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * 添加新闻
	 * @see com.aishang.service.INewsService#addNews()
	 */
	@Override
	public int addNews(News news) {
		// TODO Auto-generated method stub
		return newsDao.addNews(news);
	}

	/* (non-Javadoc)
	 * 修改回显
	 * @see com.aishang.service.INewsService#updateEcho(int)
	 */
	@Override
	public News updateEcho(int id) {
		// TODO Auto-generated method stub
		return newsDao.updateEcho(id);
	}

	/* (non-Javadoc)
	 * 执行修改
	 * @see com.aishang.service.INewsService#updateNews(int)
	 */
	@Override
	public int updateNews(int id,News news) {
		// TODO Auto-generated method stub
		return newsDao.updateNews(id,news);
	}

	/* (non-Javadoc)
	 * 删除新闻
	 * @see com.aishang.service.INewsService#delNews(java.lang.String)
	 */
	@Override
	public int delNews(int check) {
		// TODO Auto-generated method stub
		return newsDao.delNews(check);
	}

	/**
	 * 修改审核
	 * @return 
	 */
	public int updatePend(int upPend,int id) {
		// TODO Auto-generated method stub
		return newsDao.updatePend(upPend,id);
	}

	/* (non-Javadoc)
	 * 查询uid
	 * @see com.aishang.service.INewsService#selUid(int)
	 */
	@Override
	public boolean selUid(int id) {
		// TODO Auto-generated method stub
		return newsDao.selUid(id);
	}

	/* (non-Javadoc)
	 * 用id查询内容
	 * @see com.aishang.service.INewsService#mainContent(int)
	 */
	@Override
	public List<News> mainContent(int id,int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		return newsDao.mainContent(id,pageNow,pageSize);
	}

	/* (non-Javadoc)
	 * 查询所有通过的新闻
	 * @see com.aishang.service.INewsService#selNews()
	 */
	@Override
	public List<News> selNews(int i) {
		// TODO Auto-generated method stub
		return newsDao.selNews(i);
	}

	/* (non-Javadoc)
	 * 查询对应的新闻
	 * @see com.aishang.service.INewsService#selectNews(int)
	 */
	@Override
	public List<News> selectNews(int id) {
		// TODO Auto-generated method stub
		return newsDao.selectNews(id);
	}

	/* (non-Javadoc)
	 * 总条数
	 * @see com.aishang.service.INewsService#mainRowCount()
	 */
	@Override
	public int mainRowCount(int typeid, int pageNow, int pageSize, int pageCount) {
		// TODO Auto-generated method stub
		return newsDao.mainRowCount(typeid,pageNow,pageSize,pageCount);
	}

	/* (non-Javadoc)
	 * 总页数
	 * @see com.aishang.service.INewsService#mainPageCount()
	 */
	@Override
	public int mainPageCount(int typeid,int pageNow,int pageSize,int pageCount) {
		int rowCount = mainRowCount(typeid,pageNow,pageSize,pageCount);
		if(rowCount%pageSize==0){
			pageCount = rowCount/pageSize;
		}else{
			pageCount = (rowCount/pageSize) + 1;
		}
		return pageCount;
	}

	/* (non-Javadoc)
	 * 查询类别中是否有新闻
	 * @see com.aishang.service.INewsService#selType(int)
	 */
	@Override
	public boolean selType(int typeID) {
		// TODO Auto-generated method stub
		return newsDao.selType(typeID);
	}

}
