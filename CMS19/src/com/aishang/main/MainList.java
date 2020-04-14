package com.aishang.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aishang.po.News;
import com.aishang.po.NewsType;
import com.aishang.service.INewsService;
import com.aishang.service.impl.NewsService;
import com.aishang.service.impl.NewsTypeService;
import com.aishang.service.impl.UserService;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class MainList
 */
@WebServlet("/mainList")
public class MainList extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if("mainTypeName".equals(flag)){
			mainTypeName(request,response);
		}else if("mainContent".equals(flag)){
			mainContent(request,response);
		}else if("content".equals(flag)){
			content(request,response);
		}
		
	}
	
	
	/**
	 * 内容遍历
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void content(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	//接收id
		int id = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("id"))){
			id = Integer.parseInt(request.getParameter("id"));
		}
	//查询类别
		NewsTypeService newsTypeService = new NewsTypeService();
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
	//调用service遍历内容
		INewsService newsService = new NewsService();
	//查询对应通过的新闻
		List<News> selNews = newsService.selectNews(id);
	//类别map
		Map<Integer,String> typeName = new HashMap<Integer, String>();
		for (NewsType newsType : newsTypeList) {
			typeName.put(newsType.getId(), newsType.getTypeName());
		}
	//查询上传用户
		UserService userService = new UserService();
		Map<Long, String> users = userService.findAllUser();
		request.setAttribute("selNews", selNews);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("typeName", typeName);
		request.setAttribute("users", users);
		request.getRequestDispatcher("mainContent.jsp").forward(request, response);
	}


	/**
	 * 标题遍历
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void mainContent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//调用service遍历内容
		INewsService newsService = new NewsService();
	//接收值
		int id = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("id"))){
			id = Integer.parseInt(request.getParameter("id"));
		}
		
	//查询类别
		NewsTypeService newsTypeService = new NewsTypeService();
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
	
	//查询体育通过的新闻
		List<News> sports = newsService.selNews(1);
	//查询军事通过的新闻
		List<News> military = newsService.selNews(2);
	//查询娱乐通过的新闻
		List<News> recreation = newsService.selNews(3);
	//查询游戏通过的新闻
		List<News> game = newsService.selNews(4);
	//查询历史通过的新闻
		List<News> history = newsService.selNews(5);
	//查询热门通过的新闻
		List<News> hot = newsService.selNews(6);
	//分页
		int pageNow = 1; //当前页
		int pageSize = 5;//每页条数
		int pageCount = 0;
			pageCount = newsService.mainPageCount(id, pageNow,pageSize,pageCount);	//总页数
		int rowCount = newsService.mainRowCount(id,pageNow,pageSize,pageCount); 	//总条数
		if (!StringUtils.isNullOrEmpty(request.getParameter("pageNow"))) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
			//防止页数不正确
			if(pageNow<=0){
				pageNow=1;
			}
			if(pageNow>pageCount){
				pageNow=pageCount;
			}
		}
	//用id查询内容
		List<News> mainContent = newsService.mainContent(id,pageNow,pageSize);
	//存到request
		request.setAttribute("mainContent", mainContent);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("sports", sports);//体育
		request.setAttribute("military", military);//军事
		request.setAttribute("recreation", recreation);//娱乐
		request.setAttribute("game", game);//游戏
		request.setAttribute("history", history);//历史
		request.setAttribute("hot", hot);//热门
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("rowCount", rowCount);
		request.setAttribute("id", id);
	//跳转到二级页
		request.getRequestDispatcher("mainTitle.jsp").forward(request, response);
	}


	/**
	 * 类别遍历
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void mainTypeName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//查询类别
		NewsTypeService newsTypeService = new NewsTypeService();
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
	//调用service遍历内容
		INewsService newsService = new NewsService();
	//查询体育通过的新闻
		List<News> sports = newsService.selNews(1);
	//查询军事通过的新闻
		List<News> military = newsService.selNews(2);
	//查询娱乐通过的新闻
		List<News> recreation = newsService.selNews(3);
	//查询游戏通过的新闻
		List<News> game = newsService.selNews(4);
	//查询历史通过的新闻
		List<News> history = newsService.selNews(5);
	//查询热门通过的新闻
		List<News> hot = newsService.selNews(6);
	//存到request里
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("sports", sports);//体育
		request.setAttribute("military", military);//军事
		request.setAttribute("recreation", recreation);//娱乐
		request.setAttribute("game", game);//游戏
		request.setAttribute("history", history);//历史
		request.setAttribute("hot", hot);//热门
	//请求转发到主页
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
