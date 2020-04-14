package com.aishang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aishang.po.NewsType;
import com.aishang.service.INewsService;
import com.aishang.service.impl.NewsService;
import com.aishang.service.impl.NewsTypeService;
import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;


@WebServlet("/newsTypeController")
public class NewsTypeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String flag = request.getParameter("flag");
		if("newsList".equals(flag)){
			newsList(request,response);
		}else if("newsLists".equals(flag)){
			newsLists(request,response);
		}else if("addNews".equals(flag)){
			addNews(request,response);
		}else if("addNewsType".equals(flag)){
			addNewsType(request,response);
		}else if("ajaxTypeName".equals(flag)){
			ajaxTypeName(request,response);
		}else if("updateNewsType".equals(flag)){
			updateNewsType(request,response);
		}else if("delNewsType".equals(flag)){
			delNewsType(request,response);
		}
	}
	


	/**
	 * 删除类别
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delNewsType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//响应
			PrintWriter out = response.getWriter();
		//获取界面信息
			int typeID = 0;
			if(!StringUtils.isNullOrEmpty(request.getParameter("typeID"))){
				typeID = Integer.parseInt(request.getParameter("typeID"));
			}
			boolean con = false;
			if(!StringUtils.isNullOrEmpty(request.getParameter("con"))){
				con = Boolean.parseBoolean(request.getParameter("con"));
			}
		//调用service
			NewsTypeService newsTypeService = new NewsTypeService();
		//调用NewsService
			INewsService newsService = new NewsService();
		//查询类别中是否有新闻
			boolean b = newsService.selType(typeID);
		//将id传到后台进行删除
			if(con){
				if(b==false){
					int i = newsTypeService.delNewsType(typeID);
				}else{
					out.print("o");
				}
			}else{
				out.print("ok");
			}
	}



	/**
	 * 修改类别
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateNewsType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//响应到浏览器上
			PrintWriter out = response.getWriter();
		//获取ajax传过来的值
			String type = "";
			if(!StringUtils.isNullOrEmpty(request.getParameter("type"))){
				type = request.getParameter("type");
				type = new String(type.getBytes("iso-8859-1"),"UTF-8");
			}
			int sor = 0;
			if(!StringUtils.isNullOrEmpty(request.getParameter("sor"))){
				sor = Integer.parseInt(request.getParameter("sor"));
			}
			int id = 0;
			if(!StringUtils.isNullOrEmpty(request.getParameter("id"))){
				id = Integer.parseInt(request.getParameter("id"));
			}
			int p = 0;
			if(!StringUtils.isNullOrEmpty(request.getParameter("p"))){
				p = Integer.parseInt(request.getParameter("p"));
			}
			boolean con = false;
			if(!StringUtils.isNullOrEmpty(request.getParameter("con"))){
				con = Boolean.parseBoolean(request.getParameter("con"));
			}
		//封装
			NewsType newsType = new NewsType();
			newsType.setId(id);
			newsType.setTypeName(type);
			newsType.setSort(sor);
		//调用service
			NewsTypeService newsTypeService = new NewsTypeService();
		//查一下库
			List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		//判断
			int c = 1;
			
			if(p==1){
				if(StringUtils.isNullOrEmpty(type)){
					c = 0;
					
				}
			}else if(sor<=0){
				c = 0;
				
			}
			
		//查询判断名称是否重复
			int j = newsTypeService.selDepName(type);
			if(p==1){
				
				if(j==1){
					out.print("type");
					c = 0;
					
				}
			}else if(p==2){
				
				for (NewsType newsType2 : newsTypeList) {
					if(sor == newsType2.getSort() && newsType2.getSort()!=0){
						out.print("errSort");
						c = 0;
						
					}
				}
			}
			
			
			if(c==1){
				if(con){
				//执行修改
					int i = newsTypeService.updateNewsType(newsType);
				}
			}else{
				out.print("no");
			}
	}



	/**
	 * ajax验证类别名是否重复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void ajaxTypeName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//响应到浏览器
			PrintWriter out = response.getWriter();
		//调用service
			NewsTypeService newsTypeService = new NewsTypeService();
		//获取页面名称
			String typeName = "";
			if(!StringUtils.isNullOrEmpty(request.getParameter("typeName"))){
				typeName = request.getParameter("typeName");
				typeName = new String(typeName.getBytes("iso-8859-1"),"UTF-8");
			}
		//查询数据库比较
			boolean ajaxTypeName = newsTypeService.ajaxTypeName(typeName);
		//判断
			if(ajaxTypeName){
				out.print("ok");
			}
	}



	/**
	 * 类别添加
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addNewsType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//获取页面信息
		String typeName = "";
		if(!StringUtils.isNullOrEmpty(request.getParameter("typeName"))){
			typeName = request.getParameter("typeName");//类别名称
		}
		int sort = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("sort"))){
			sort = Integer.parseInt(request.getParameter("sort"));//优先级
		}
	//调用工具类
		NewsType newsType = new NewsType();
	//存储
		newsType.setTypeName(typeName);
		newsType.setSort(sort);
	//调用service
		NewsTypeService newsTypeService = new NewsTypeService();
	//查一下库
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
	//验证
		boolean a = true;
		if(StringUtils.isNullOrEmpty(typeName)){
			request.setAttribute("errTypeName", "请输入类别名称");
			request.setAttribute("sort", sort);
			a=false;
		}
	//遍历
		for (NewsType newsType2 : newsTypeList) {
			if(typeName.equals(newsType2.getTypeName())){
				request.setAttribute("errTypeName", "类别名称重复");
				request.setAttribute("sort", sort);
				a=false;
			}
			if(sort == newsType2.getSort()){
				request.setAttribute("typeName", typeName);
				request.setAttribute("errSort", "优先级不能相同");
				a=false;
			}
		}
		if(sort<=0){
			request.setAttribute("errSort", "请输入优先级");
			request.setAttribute("typeName", typeName);
			a=false;
		}
		if(a){
		//执行添加
			newsTypeService.addNewsType(newsType);
		//成功跳转到
			request.getRequestDispatcher("category.jsp").forward(request, response);
		}else{
		//失败跳转到
			request.getRequestDispatcher("category.jsp").forward(request, response);
		}
	}



	/**
	 * 类别管理回显
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void newsLists(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewsTypeService newsTypeService = new NewsTypeService();
		//查询类别
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		//转换为json格式
		String jsonString = JSON.toJSONString(newsTypeList);
		//整到浏览器上
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
	}
	
	/**
	 * 上传新闻回显
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	private void addNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewsTypeService newsTypeService = new NewsTypeService();
		//查询类别
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		request.setAttribute("newsTypeList", newsTypeList);
		request.getRequestDispatcher("addNews.jsp").forward(request, response);
	}

	/**
	 * 查询类别
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected List<NewsType> newsList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewsTypeService newsTypeService = new NewsTypeService();
		//查询类别
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		request.setAttribute("newsTypeList", newsTypeList);
		//request.getRequestDispatcher("category.jsp").forward(request, response);
		return newsTypeList;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
