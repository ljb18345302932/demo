package com.aishang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aishang.po.News;
import com.aishang.po.NewsType;
import com.aishang.po.User;
import com.aishang.service.INewsService;
import com.aishang.service.impl.NewsService;
import com.aishang.service.impl.NewsTypeService;
import com.aishang.service.impl.UserService;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class NewsController
 */
@WebServlet("/newsController")
public class NewsController extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset=utf-8");
    	String flag = request.getParameter("flag");
    	if("newsList".equals(flag)){
    		newsList(request,response);
    	}else if("jump".equals(flag)){
    		jump(request,response);
    	}else if("addNews".equals(flag)){
    		addNews(request,response);
    	}else if("updateEcho".equals(flag)){
    		updateEcho(request,response);
    	}else if("updateNews".equals(flag)){
    		updateNews(request,response);
    	}else if("delNews".equals(flag)){
    		delNews(request,response);
    	}else if("updatePend".equals(flag)){
    		updatePend(request,response);
    	}
    	
	}

	/**
	 * 审核修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//响应到浏览器
		PrintWriter out = response.getWriter();
	//获取下拉列表value
		int upPend = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("upPend"))){
			upPend = Integer.parseInt(request.getParameter("upPend"));
			
		}
	//获取id
		int id = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("id"))){
			id = Integer.parseInt(request.getParameter("id"));
		}	
	//调用service
		INewsService newsService = new NewsService();
		
	//获取确认修改
		boolean con = false;
		if(!StringUtils.isNullOrEmpty(request.getParameter("con"))){
			con = Boolean.parseBoolean(request.getParameter("con"));
		}
	//判断
		if(con){
		//用下拉列表的value+id取修改
			int i = newsService.updatePend(upPend,id);
		}
	}



	/**
	 * 删除新闻
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//获取页面信息
		String[] checkNews = {};
		if(request.getParameterValues("checkNews")!=null){
			checkNews = request.getParameterValues("checkNews");
		}
		int typeid = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("typeid"))){
			typeid = Integer.parseInt(request.getParameter("typeid"));//类别下拉
		}
		int pageNow = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("pageNow"))){
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int flag = -1;
		if(!StringUtils.isNullOrEmpty(request.getParameter("flags"))){
			flag = Integer.parseInt(request.getParameter("flags"));//审核下拉
		}
		String title = request.getParameter("title");//新闻名称
		boolean con = Boolean.parseBoolean(request.getParameter("con"));
	//调用news
		News news = new News();
		news.setTypeid(typeid);
		news.setFlag(flag);
		news.setTitle(title);
		if(con){
			//执行删除
			INewsService newsService = new NewsService();
			int i=0;
			for (String check : checkNews) {
				i = newsService.delNews(Integer.parseInt(check));
			}
			if(i>0){
			//存到request域对象
				request.setAttribute("news", news);
			//删除成功
				request.getRequestDispatcher("newsController?flag=newsList&pageNow="+pageNow+"").forward(request, response);
			}
		}else{
		//存到request域对象
			request.setAttribute("news", news);
			request.getRequestDispatcher("newsController?flag=newsList&pageNow="+pageNow+"").forward(request, response);
		}
	
	}

	/**
	 * 执行修改新闻
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//获取页面信息
		String title = request.getParameter("title");//标题
		int typeid = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("typeid"))){
			typeid = Integer.parseInt(request.getParameter("typeid"));//类别
		}
		String content = request.getParameter("content");//内容
	//获取id
		int id = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("id"))){
			id = Integer.parseInt(request.getParameter("id"));
		}
		
	//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat();//格式化时间
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();//获取当前时间
	//获取登陆人
		HttpSession session = request.getSession();
		User checkUser = (User)session.getAttribute("checkUser");
	//存到News类
		News news = new News();
		news.setTitle(title);
		news.setTypeid(typeid);
		news.setContent(content);
		news.setCreatetime(sdf.format(date));
		
		
		boolean c = true;
		//非空验证
		if(StringUtils.isNullOrEmpty(title)){
			request.setAttribute("errTitle", "标题不能为空");
			c = false;
		}
		
		if(typeid==0){
			request.setAttribute("errTypeid", "类别不能为空");
			c = false;
		}
	
		if(StringUtils.isNullOrEmpty(content)){
			request.setAttribute("errContent", "内容不能为空");
			c = false;
		}
		if(news.getFlag()==2){
			request.setAttribute("errFlag", "已通过新闻不可更改");
			c = false;
		}
		
		if(c){
			//news.setFlag(checkUser.getUserPower()==0?0:2);
			//执行修改
			INewsService newsService = new NewsService();
			int i = newsService.updateNews(id,news);
			int seltypeID = 0;
			if(!StringUtils.isNullOrEmpty(request.getParameter("seltypeID"))){
				seltypeID = Integer.parseInt(request.getParameter("seltypeID"));	//类别下拉
			}
			int selPend = -1;
			if(!StringUtils.isNullOrEmpty(request.getParameter("selPend"))){
				selPend = Integer.parseInt(request.getParameter("selPend"));	//审核下拉
			}
			int pageNow = 0;
			if(!StringUtils.isNullOrEmpty(request.getParameter("pageNow"))){
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}
			String selNewsName = "";
			if(!StringUtils.isNullOrEmpty(request.getParameter("selNewsName"))){
				selNewsName = request.getParameter("selNewsName");	//新闻名称
				if("GET".equals(request.getMethod())){
					selNewsName = new String(selNewsName.getBytes("iso-8859-1"),"UTF-8");
				}
			}
			News n = new News();
			n.setTypeid(seltypeID);
			n.setFlag(selPend);
			n.setTitle(selNewsName);
			request.setAttribute("n", n);
			//修改成功
			request.getRequestDispatcher("newsController?flag=newsList&pageNow="+pageNow+"").forward(request, response);
		}else{
			//修改失败
			request.getRequestDispatcher("newsController?flag=updateEcho").forward(request, response);
		}
	}

	/**
	 * 修改回显
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateEcho(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取id
		int id = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("id"))){
			id = Integer.parseInt(request.getParameter("id"));
		}
		int seltypeID = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("seltypeID"))){
			seltypeID = Integer.parseInt(request.getParameter("seltypeID"));	//类别下拉
		}
		int selPend = -1;
		if(!StringUtils.isNullOrEmpty(request.getParameter("selPend"))){
			selPend = Integer.parseInt(request.getParameter("selPend"));	//审核下拉
		}
		int pageNow = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("pageNow"))){
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		String selNewsName = "";
		if(!StringUtils.isNullOrEmpty(request.getParameter("selNewsName"))){
			selNewsName = request.getParameter("selNewsName");	//新闻名称
			if("GET".equals(request.getMethod())){
				selNewsName = new String(selNewsName.getBytes("iso-8859-1"),"UTF-8");
			}
		}
		int fla = -1;
		if(!StringUtils.isNullOrEmpty(request.getParameter("fla"))){
			fla = Integer.parseInt(request.getParameter("fla"));	//审核下拉
			
		}
		News n = new News();
		n.setTypeid(seltypeID);
		n.setFlag(selPend);
		n.setTitle(selNewsName);
		//查询该条新闻
		INewsService newsService = new NewsService();
		News news = newsService.updateEcho(id);
		//查询类别
		NewsTypeService newsTypeService = new NewsTypeService();
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		//放到reuqest域中
		request.setAttribute("news", news);
		request.setAttribute("fla", fla);
		request.setAttribute("n", n);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("pageNow", pageNow);
		//获取session
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("checkUser");
		if(fla==2 || user.getUserPower()==1){	
			if(user.getUserPower()==1){
				request.setAttribute("dis", "readonly='Company'");
			}
			if(fla==2){
				request.setAttribute("read", "disabled='disabled'");
			}
			request.getRequestDispatcher("updateNews.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("updateNews.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * 添加新闻
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取页面信息
		String title = request.getParameter("title");//标题
		int typeid = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("typeid"))){
			typeid = Integer.parseInt(request.getParameter("typeid"));//类别
		}
		String content = request.getParameter("content");//内容
		//获取上传人id
		HttpSession session = request.getSession();
		User checkUser = (User)session.getAttribute("checkUser");
		
		//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat();//格式化时间
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();//获取当前时间
		//存到News类
		News news = new News();
		news.setTitle(title);
		news.setTypeid(typeid);
		news.setContent(content);
		news.setCreatetime(sdf.format(date));
		news.setFlag(checkUser.getUserPower()==0?0:2);
		news.setUid(checkUser==null?0:checkUser.getId());
		boolean c = false;
		if(StringUtils.isNullOrEmpty(title)){
			request.setAttribute("errTitle", "标题不能为空");
			c = true;
		}
		if(StringUtils.isNullOrEmpty(content)){
			request.setAttribute("errContent", "内容不能为空");
			c = true;
		}
		if(typeid==0){
			request.setAttribute("errTypeid", "请选择类别");
			c = true;
		}
		if(c){
			request.getRequestDispatcher("newsTypeController?flag=addNews").forward(request, response);
		}
		if(!c){
			//添加
			INewsService newsService = new NewsService();
			int i = newsService.addNews(news);
			request.getRequestDispatcher("newsController?flag=newsList").forward(request, response);
		}
		}
		

	/**
	 * 跳转页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void jump(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//拿页面输入的值
		int numb = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("numb"))){
			numb = Integer.parseInt(request.getParameter("numb"));
		}
		request.getRequestDispatcher("newsController?flag=newsList&pageNow="+numb).forward(request, response);
	}

	/**
	 * 查询新闻列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void newsList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		INewsService newsService = new NewsService();
		//拿到查询条件
		int seltypeID = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("seltypeID"))){
			seltypeID = Integer.parseInt(request.getParameter("seltypeID"));	//类别下拉
		}
		
		int selPend = -1;
		if(!StringUtils.isNullOrEmpty(request.getParameter("selPend"))){
			selPend = Integer.parseInt(request.getParameter("selPend"));	//审核下拉
		}
		String selNewsName = "";
		if(!StringUtils.isNullOrEmpty(request.getParameter("selNewsName"))){
			selNewsName = request.getParameter("selNewsName");	//新闻名称
			if("GET".equals(request.getMethod())){
				selNewsName = new String(selNewsName.getBytes("iso-8859-1"),"UTF-8");
			}
		}
		News n = (News)request.getAttribute("news");
		if(request.getAttribute("news")!=null){
			seltypeID = n.getTypeid();
			selPend = n.getFlag();
			selNewsName = n.getTitle();
		}
		News ne = (News)request.getAttribute("n");
		if(request.getAttribute("n")!=null){
			seltypeID = ne.getTypeid();
			selPend = ne.getFlag();
			selNewsName = ne.getTitle();
		}
		//拿到上传人的权限
		HttpSession session = request.getSession();
		User checkUser = (User)session.getAttribute("checkUser");
		//调用news
		News news = new News();
		news.setTypeid(seltypeID);
		news.setFlag(selPend);
		news.setTitle(selNewsName);
		news.setUid(checkUser.getId());
		
		NewsTypeController newsTypeController = new NewsTypeController();
		List<NewsType> newsTypeList = newsTypeController.newsList(request, response);
		
		int pageNow = 1; //当前页
		int pageSize = 5;//每页条数
		int pageCount = 0;
			pageCount = newsService.getPageCount(pageSize,pageCount,news,checkUser);	//总页数
			
		int rowCount = newsService.getRowCount(news,checkUser); 	//总条数
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
		
		//查询新闻列表
		List<News> newsList = newsService.newsList(pageNow,pageSize,news,checkUser);
		//查询
		NewsTypeService newsTypeService = new NewsTypeService();
		List<NewsType> newsTypeList2 = newsTypeService.newsTypeList();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (NewsType newsType : newsTypeList2) {
			map.put(newsType.getId(), newsType.getTypeName());
		}
		//查询上传用户
		UserService userService = new UserService();
		Map<Long, String> users = userService.findAllUser();
		//存储
		request.setAttribute("newsList", newsList);
		request.setAttribute("users", users);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("rowCount", rowCount);
		request.setAttribute("news", news);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("map", map);
		request.getRequestDispatcher("news.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
