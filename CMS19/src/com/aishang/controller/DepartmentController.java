package com.aishang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aishang.po.Department;

import com.aishang.service.IUserService;
import com.aishang.service.impl.DepartmentService;
import com.aishang.service.impl.UserService;
import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class DepartmentController
 */
@WebServlet("/departmentController")
public class DepartmentController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String flag = request.getParameter("flag");
		if("findDepartment".equals(flag)){
			findDepartment(request,response);
		}else if("addDepartment".equals(flag)){
			addDepartment(request,response);
		}else if("ajaxTypeName".equals(flag)){
			ajaxTypeName(request,response);
		}else if("updateDepartment".equals(flag)){
			updateDepartment(request,response);
		}else if("updateEcho".equals(flag)){
			updateEcho(request,response);
		}else if("delDepartment".equals(flag)){
			delDepartment(request,response);
		}
	}

	
	/**
	 * 删除部门
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delDepartment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		DepartmentService departmentService = new DepartmentService();
	//调用userService
		IUserService userService = new UserService();
	//查询部门中是否有用户
		boolean b = userService.selDepartment(typeID);
	//将id传到后台进行删除
		if(con){
			if(b==false){
				int i = departmentService.delDepartment(typeID);
			}else{
				out.print("o");
			}
		}else{
			out.print("ok");
		}
		
		
	}


	/**
	 * ajax验证修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateEcho(HttpServletRequest request,
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
	//调用service
		DepartmentService departmentService = new DepartmentService();
	//判断
		boolean c = true;
		if(StringUtils.isNullOrEmpty(type)){
			out.print("nullTypeName");
			c = false;
		}
		if(sor<0){
			out.print("nullSort");
			c = false;
		}
	//查询判断名称是否重复
		int j = departmentService.selDepName(type);
		if(j==1){
			out.print("ok");
			c = false;
		}
		
	}


	/**
	 * 修改部门
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateDepartment(HttpServletRequest request,
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
	//获取时间
		SimpleDateFormat sdf = new SimpleDateFormat();//格式化时间
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();//获取当前时间
	//封装
		Department department = new Department();
		department.setId(id);
		department.setDepName(type);
		department.setSort(sor);
		department.setDepCreateTime(sdf.format(date));
	//调用service
		DepartmentService departmentService = new DepartmentService();
	//查一下库
		List<Department> findDepartment = departmentService.findDepartment();
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
		int j = departmentService.selDepName(type);
		if(p==1){
			if(j==1){
				out.print("type");
				c = 0;
			}
		}else if(p==2){
			for (Department department2 : findDepartment) {
				if(sor == department2.getSort() && department2.getSort()!=0){
					out.print("errSort");
					c = 0;
				}
			}
		}
		
		
		if(c==1){
			if(con){
			//执行修改
				int i = departmentService.updateDepartment(department);
			}
		}else{
			out.print("no");
		}
	
	}


	/**
	 * ajax验证部门名称
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
		DepartmentService departmentService = new DepartmentService();
	//获取页面名称
		String typeName = "";
		if(!StringUtils.isNullOrEmpty(request.getParameter("typeName"))){
			typeName = request.getParameter("typeName");
			typeName = new String(typeName.getBytes("iso-8859-1"),"UTF-8");
		}
	//查询数据库比较
		boolean ajaxTypeName = departmentService.ajaxTypeName(typeName);
	//判断
		if(ajaxTypeName){
			out.print("ok");
		}
	}


	/**
	 * 添加部门
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addDepartment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//获取页面信息
		String typeName = "";
		if(!StringUtils.isNullOrEmpty(request.getParameter("typeName"))){
			typeName = request.getParameter("typeName");//部门名称
		}
		int sort = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("sort"))){
			sort = Integer.parseInt(request.getParameter("sort"));//优先级
		}
	//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat();//格式化时间
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();//获取当前时间
	//调用工具类
		Department department = new Department();
	//存储
		department.setDepName(typeName);
		department.setSort(sort);
		department.setDepCreateTime(sdf.format(date));
	//调用service
		DepartmentService departmentService = new DepartmentService();
	//查一下库
		List<Department> findDepartment = departmentService.findDepartment();
	//验证
		boolean a = true;
		if(StringUtils.isNullOrEmpty(typeName)){
			request.setAttribute("errTypeName", "请输入部门名称");
			request.setAttribute("sort", sort);
			a=false;
		}
	//遍历
		for (Department department2 : findDepartment) {
			if(typeName == department2.getDepName()){
				request.setAttribute("errTypeName", "部门名称重复");
				request.setAttribute("sort", sort);
				a=false;
			}
			if(sort == department2.getSort()){
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
		departmentService.addDepartment(department);
	//成功跳转到
		request.getRequestDispatcher("departmentManage.jsp").forward(request, response);
		}else{
	//失败跳转到
		request.getRequestDispatcher("departmentManage.jsp").forward(request, response);
		}
	}


	/**
	 * 查询部门
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void findDepartment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	//调用departmentService进行查询,拿到值
		DepartmentService departmentService = new DepartmentService();
		List<Department> departments = departmentService.findDepartment();
	//转换成json格式
		String jsonString = JSON.toJSONString(departments);
		out.print(jsonString);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
