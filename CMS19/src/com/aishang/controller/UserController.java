package com.aishang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.VisitedUtil;

import com.aishang.po.Department;
import com.aishang.po.News;
import com.aishang.po.User;
import com.aishang.service.INewsService;
import com.aishang.service.impl.DepartmentService;
import com.aishang.service.impl.NewsService;
import com.aishang.service.impl.UserService;
import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.StringUtils;


/**
 * @author yc950710
 *
 */
/**
 * @author yc950710
 *
 */
@WebServlet("/userController")
public class UserController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String flag = request.getParameter("flag");
		flag=flag.trim();
		if("doLogin".equals(flag)){
			doLogin(request,response);
		}else if("userList".equals(flag)){
			userList(request,response);
		}else if("quit".equals(flag)){
			quit(request,response);
		}else if("updateUser".equals(flag)){
			updateUser(request,response);
		}else if("doUpdateUser".equals(flag)){
			doUpdateUser(request,response);
		}else if("addUser".equals(flag)){
			addUser(request,response);
		}else if("add".equals(flag)){
			add(request,response);
		}else if("jump".equals(flag)){
			jump(request,response);
		}else if("delUser".equals(flag)){
			delUser(request,response);
		}else if("delBatch".equals(flag)){
			delBatch(request,response);
		}else if("checkUpdateUser".equals(flag)){
			checkUpdateUser(request,response);
		}else if("update".equals(flag)){
			update(request,response);
		}else if("ajaxAddVerify".equals(flag)){
			ajaxAddVerify(request,response);
		}
	}

	
	/**
	 * ajax添加验证
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void ajaxAddVerify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//拿到用户名
		String username = "";
		if(request.getParameter("username")!=null){
			username = request.getParameter("username");
		}
		username = new String(username.getBytes("iso-8859-1"),"UTF-8");
		int id = 0;
		if(request.getParameter("id")!=null){
			id = Integer.parseInt(request.getParameter("id"));
		}
		//查修改人
		UserService userService = new UserService();
		User checkUpdateUser = null;
		String chenkName = "";
		if(userService.checkUpdateUser(id)!=null){
			checkUpdateUser = userService.checkUpdateUser(id);
			chenkName = checkUpdateUser.getUserName();
		}
		//调用user
		User user = new User();
		user.setUserName(username);
		//查询数据库
		int i = 1;
		if((!"".equals(username))){
			i = userService.ajaxFinAllUser(user);
			if(username.equals(chenkName)){
				i=0;
			}
		}
	
		if(i>0){
			out.print("no");
		}else{
			out.print("ok");
		}
		
	}


	/**
	 * 修改选中人
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//获取表单信息
		
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		String[] depId = request.getParameterValues("depId");
		String[] sex = request.getParameterValues("sex");
		int age = 0;
		if(!"".equals(request.getParameter("age"))){
			age = Integer.parseInt(request.getParameter("age"));
		}
		String[] power = request.getParameterValues("power");
		//放到user里
		User user = new User();
		user.setId(id);
		user.setUserName(username);
		user.setUserPwd(password);
		for (String dep : depId) {
			user.setDepID(Integer.parseInt(dep));
		}
		for (String s : sex) {
			user.setUserSex(s);
		}
		user.setUserAge(age);
		
		for (String p : power) {
			user.setUserPower(Integer.parseInt(p));
		}
		
		int c = 1;
		if("".equals(username)){
			request.setAttribute("errName", "用户名不能为空");
			c=0;
		}
		if("".equals(password)){
			request.setAttribute("errPass", "密码不能为空");
			c=0;
		}
		if(user.getDepID()==0){
			request.setAttribute("errDep", "请选择部门");
			c=0;
		}
		if(user.getUserSex()==null){
			request.setAttribute("errSex", "请选择性别");
			c=0;
		}
		if(age==0){
			request.setAttribute("errAge", "请输入年龄");
			c=0;
		}
		if("".equals(user.getUserPower())){
			request.setAttribute("errPower", "请选择用户类型");
			c=0;
		}
		if(c==1){
			//执行修改
			UserService userService = new UserService();
			int i = userService.update(user);
			//修改成功跳到主页
			String searchName = "";
			if(request.getParameter("searchName")!=null){
				searchName = request.getParameter("searchName");
			}
			
			int searchDep = 0;
			if( !"".equals(request.getParameter("searchDep"))){
				searchDep = Integer.parseInt(request.getParameter("searchDep"));
			}
			
			int pageNow = 0;
			if( !"".equals(request.getParameter("pageNow"))){
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}
			
			response.sendRedirect("/CMS19/userController?flag=userList&pageNow="+pageNow+"&searchName="+searchName+"&searchDep="+searchDep+"");
			return;
		}else{
			//修改失败
			request.getRequestDispatcher("userController?flag=checkUpdateUser").forward(request, response);
		}
	}

	/**
	 * 修改前查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void checkUpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//拿到id
		int id = 0;
		if(request.getParameter("id")!=null){
			id = Integer.parseInt(request.getParameter("id"));
		}
		//拿到searchName searchDep pageNow
		String searchName = request.getParameter("searchName");
		
		int searchDep = 0;
		if(request.getParameter("searchDep")!=null){
			searchDep = Integer.parseInt(request.getParameter("searchDep"));
		}
		int pageNow = 0;
		if(request.getParameter("pageNow")!=null){
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		UserService userService = new UserService();
		User checkUpdateUser = userService.checkUpdateUser(id);
		
		DepartmentService departmentService = new DepartmentService();
		List<Department> findDepartment = departmentService.findDepartment();
		
		request.setAttribute("checkUpdateUser", checkUpdateUser);
		request.setAttribute("findDepartment", findDepartment);
		request.setAttribute("searchName", searchName);
		request.setAttribute("searchDep", searchDep);
		request.setAttribute("pageNow", pageNow);
		
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}


	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delBatch(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		UserService userService = new UserService();
		String[] ids = {};
		if(request.getParameterValues("ids")!=null){
			ids = request.getParameterValues("ids");
		}
		String pageNow = request.getParameter("pageNow");
		String searchName = request.getParameter("searchName");
		String searchDep = request.getParameter("searchDep");
	//调用新闻，查询
		INewsService newsService = new NewsService();
		List<Boolean> list= new ArrayList<>();
		boolean uid = false;
		for (String id : ids) {
			uid = newsService.selUid(Integer.parseInt(id));
			list.add(uid);
		}
		
	//获取登陆人的id
		HttpSession session = request.getSession();
		User checkUser = (User)session.getAttribute("checkUser");
		boolean a=false;
		
		
		
		int i = 0;
			for (String id : ids) {
				if(checkUser.getId()==Integer.parseInt(id)){
					a=true;
					list.add(a);
					
				}
				
				if(!list.contains(true)){
					i = userService.delUser(Integer.parseInt(id));
				}
			}
		
		if(i>0){
			response.sendRedirect("/CMS19/userController?flag=userList&pageNow="+pageNow+"&searchName="+URLEncoder.encode(searchName, "UTF-8")+"&searchDep="+searchDep+"");
		}else{
			String msg1 = "不能删除自己或已有用户已发表新闻";
			msg1 = URLEncoder.encode(msg1, "UTF-8");
			response.sendRedirect("/CMS19/userController?flag=userList&pageNow="+pageNow+"&searchName="+URLEncoder.encode(searchName, "UTF-8")+"&searchDep="+searchDep+"&msg1="+msg1+"");
		}
	}


	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		UserService userService = new UserService();
		//获取id
		int id = 0;
		if(request.getParameter("id")!=null){
			id = Integer.parseInt(request.getParameter("id"));
		}
		String pageNow = request.getParameter("pageNow");
		String searchName = request.getParameter("searchName");
		String searchDep = request.getParameter("searchDep");
		//获取登陆人的id
		HttpSession session = request.getSession();
		User checkUser = (User)session.getAttribute("checkUser");
		//调用新闻，查询
		INewsService newsService = new NewsService();
		boolean uid = newsService.selUid(id);
		int i = 0;
		if(id!=checkUser.getId()){
			if(uid==false){
				//执行删除
				i = userService.delUser(id);
			}
			
		}
		
		
		
		if(i>0){
			response.sendRedirect("/CMS19/userController?flag=userList&pageNow="+pageNow+"&searchName="+searchName+"&searchDep="+searchDep+"");
		}else{
			String msg = "不能删除自己或该用户已发表新闻";
			URLEncoder.encode(msg, "UTF-8");
			request.getRequestDispatcher("userController?flag=userList&msg="+msg+"").forward(request, response);
		}
		
	}


	/**
	 * 跳转页
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void jump(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int numb = 1;
		if(!"".equals(request.getParameter("numb"))){
			numb = Integer.parseInt(request.getParameter("numb"));
		}
		String searchName = "";
		if(!"".equals(request.getParameter("searchName"))){
			
			searchName = request.getParameter("searchName");
			searchName = URLEncoder.encode(searchName, "UTF-8");
		}
		
		int searchDep = 1;
		if(!"".equals(request.getParameter("searchDep"))){
			searchDep = Integer.parseInt(request.getParameter("searchDep"));
		}
		response.sendRedirect("/CMS19/userController?flag=userList&pageNow="+numb+"&searchName="+searchName+"&searchDep="+searchDep+"");
	}


	/**
	 * 添加回显
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//查询部门
		DepartmentService departmentService = new DepartmentService();
		List<Department> findDepartment = departmentService.findDepartment();
		request.setAttribute("findDepartment", findDepartment);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	/**
	 * 添加
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取添加页面的所有信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String userCode = request.getParameter("userCode");
		String[] sex = request.getParameterValues("sex");
		int age = 0;
		if(!"".equals(request.getParameter("age"))){
			if(request.getParameter("age")!=null){
				age = Integer.parseInt(request.getParameter("age"));
			}
		}
		int depId =0;
		if(request.getParameter("depId")!=null || "".equals(depId)){
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		String[] power = request.getParameterValues("power");
		//把所有信息存到user里
		User user = new User();
		user.setUserName(username);
		user.setUserPwd(password);
		user.setUserCode(userCode);
		for (String s : sex) {
			user.setUserSex(s);
		}
		user.setUserAge(age);
		user.setDepID(depId);
		for (String p : power) {
			if(p!=null){
				user.setUserPower(Integer.parseInt(p));
			}
		}
		
		//判断是否为空，不为空执行添加
		int c = 1;
		if(username==null || "".equals(username)){
			request.setAttribute("errName", "请输入用户名");
			c = 0;
		}
		if(password==null || "".equals(password)){
			request.setAttribute("errPass", "请输入密码");
			c = 0;
		}
		if(age==0){
			request.setAttribute("errAge", "请输入年龄");
			c = 0;
		}
		if(c==0){
			request.getRequestDispatcher("userController?flag=add").forward(request, response);
		}else{
			//把user里的信息传到数据库进行添加
			UserService userService = new UserService();
			int i = userService.addUser(user);
			response.sendRedirect("userController?flag=userList");
			return;
		}
		
	}


	/**
	 * 执行修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
	//拿到修改页面的信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		int id = 0;
		if(request.getParameter("id")!=null){
			id = Integer.parseInt(request.getParameter("id"));
		}
		String sex = request.getParameter("sex");
		int age = 0;
		if(!StringUtils.isNullOrEmpty(request.getParameter("age"))){
			age = Integer.parseInt(request.getParameter("age"));
		}
	//查询部门
		DepartmentService departmentService = new DepartmentService();
		List<Department> findDepartment = departmentService.findDepartment();
	//创建User
		User user= new User();
		user.setUserName(username);
		user.setUserPwd(password);
		user.setId(id);
		user.setUserAge(age);
		user.setUserSex(sex); 
		user.setUserPower(0);
		
		if(username==null || "".equals(username)){
			request.setAttribute("errName", "请输入用户名");
			request.setAttribute("password", password);
			request.setAttribute("age", age);
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		}else if(password==null || "".equals(password)){
			request.setAttribute("errPass", "请输入密码");
			request.setAttribute("username", username);
			request.setAttribute("age", age);
			request.setAttribute("findDepartment", findDepartment);
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		}else if(!password1.equals(password)){
			request.setAttribute("errPass1", "两次密码输入不一致");
			request.setAttribute("password", password);
			request.setAttribute("username", username);
			request.setAttribute("age", age);
			request.setAttribute("findDepartment", findDepartment);
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		}else if(age==0){
			request.setAttribute("password", password);
			request.setAttribute("username", username);
			request.setAttribute("findDepartment", findDepartment);
			request.setAttribute("errAge", "请输入年龄");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		}else if(age<=0){
			request.setAttribute("password", password);
			request.setAttribute("username", username);
			request.setAttribute("findDepartment", findDepartment);
			request.setAttribute("errAge", "必须是0以上的数字");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		}else{
			//执行修改
			UserService userService = new UserService();
			int i = userService.doUpdateUser(user);
			//判断是否修改成功
			if(i>0){
				//修改成功
				response.sendRedirect("/CMS19/userController?flag=userList");
			}else{
				//修改失败
				response.sendRedirect("updateUser.jsp");
			}
		}
	}


	/**
	 * 修改个人信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
	//拿到username password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	//调用user
		User user= new User();
		user.setUserName(username);
		user.setUserPwd(password);
	//查询登陆人信息
		UserService userService = new UserService();
		User checkUser = userService.checkUser(user);
	//查询部门
		DepartmentService departmentService = new DepartmentService();
		List<Department> findDepartment = departmentService.findDepartment();
	//存到request域里
		request.setAttribute("checkUser", checkUser);
		request.setAttribute("findDepartment", findDepartment);
		
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}


	/**
	 * 安全退出
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void quit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//销毁session
		session.invalidate();
		//删除cookie
		Cookie cookie1 = new Cookie("username", null);
		Cookie cookie2 = new Cookie("password", null);
		Cookie cookie3 = new Cookie("save", null);
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		cookie3.setMaxAge(0);
		cookie1.setPath("/");
		cookie2.setPath("/");
		cookie3.setPath("/");
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		response.sendRedirect("login.jsp");
	}


	/**
	 * 查询用户列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void userList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//获取searchName，searchDep
				String searchName = "";
				if(!StringUtils.isNullOrEmpty(request.getParameter("searchName"))){
					searchName = request.getParameter("searchName");
					if("GET".equals(request.getMethod())){
						searchName = URLDecoder.decode(searchName, "UTF-8");
						searchName = new String(searchName.getBytes("iso-8859-1"), "UTF-8");
						
					}
					
				}
				
				
				int searchDep = 0;
				if(request.getParameter("searchDep")!=null){
					if(!"".equals(request.getParameter("searchDep"))){
						searchDep = Integer.parseInt(request.getParameter("searchDep"));
					}
				}
				
		//调用service
		UserService userService = new UserService();
		int numb = 0;
		if(request.getParameter("numb")!=null){
			if(!"".equals(request.getParameter("numb"))){
				numb = Integer.parseInt(request.getParameter("numb"));
			}
		}
		int pageNow = 1; //当前页
		int pageSize = 10;//每页条数
		int pageCount = 0;
			pageCount = userService.getPageCount(pageSize,pageNow,searchName,searchDep);	//总页数
			
		int rowCount = userService.getRowCount(searchName,searchDep); 	//总条数
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
			//防止页数不正确
			if(pageNow<=0){
				pageNow=1;
			}
			if(pageNow>pageCount){
				pageNow=pageCount;
			}
		}
		
		//创建searchUser
		User searchUser = new User();
		searchUser.setUserName(searchName);
		searchUser.setDepID(searchDep);
		
		//查询用户，执行分页查询,拿到全部用户信息
		List<User> users = userService.findAllUser(pageNow,pageSize,searchUser);
		
		//查询部门名称
		DepartmentService departmentService = new DepartmentService();
		List<Department> findDepartment = departmentService.findDepartment();
		Map<Integer, String> map = new HashMap<>();
		for (Department department : findDepartment) {
			map.put(department.getId(), department.getDepName());
		}
		String msg = "";
		if(!StringUtils.isNullOrEmpty(request.getParameter("msg"))){
			msg = request.getParameter("msg");
		}
		String msg1 = "";
		if(!StringUtils.isNullOrEmpty(request.getParameter("msg1"))){
			msg1 = request.getParameter("msg1");
			msg1 = new String(msg1.getBytes("iso-8859-1"), "UTF-8");
		}
		//将信息存到request域里
		request.setAttribute("pageNow",pageNow);
		request.setAttribute("pageSize",pageSize);
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("rowCount",rowCount);
		request.setAttribute("numb",numb);
		request.setAttribute("users",users);
		request.setAttribute("searchUser",searchUser);
		request.setAttribute("findDepartment",findDepartment);
		request.setAttribute("msg",msg);
		request.setAttribute("msg1",msg1);
		request.setAttribute("map",map);
		
		
		//请求转发到users页面
		request.getRequestDispatcher("users.jsp").forward(request, response);
	}


	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//拿到用户名密码
		String username = request.getParameter("username").trim();
		
		String password = request.getParameter("password").trim();
		
		String save = request.getParameter("save");
		//调用user
		User user = new User();
		user.setUserName(username);
		user.setUserPwd(password);
		
		//调用service
		UserService userService = new UserService();
		
		//将用户名密码传过去,拿到登陆人全部信息
		User checkUser = userService.checkUser(user);
		
		//创建session	，cookie
		if(checkUser!=null){
			HttpSession session = request.getSession(); 	
			session.setAttribute("checkUser", checkUser);
			Cookie cookie1 = new Cookie("username",	URLEncoder.encode(username));
			Cookie cookie2 = new Cookie("password", password);
			Cookie cookie3 = new Cookie("save", "checked");
			if(save!=null){
				cookie1.setMaxAge(60 * 60 * 24 * 7);
				cookie2.setMaxAge(60 * 60 * 24 * 7);
				cookie3.setMaxAge(60 * 60 * 24 * 7);
			}else{
				cookie1.setMaxAge(0);
				cookie2.setMaxAge(0);
				cookie3.setMaxAge(0);
			}
			
			//设置cookie可在当前应用范围内共享
			cookie1.setPath("/");
			cookie2.setPath("/");
			cookie3.setPath("/");
			
			//将cookie发送至客户端
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			response.addCookie(cookie3);
		}
		
		//如果checkUser不为空
		if(checkUser!=null || "".equals(checkUser)){
			//登录成功
			ServletContext application = request.getServletContext();
			if(application.getAttribute("visited")==null){
				application.setAttribute("visited", 1);
			}else{
				int visited = (int)application.getAttribute("visited");
				visited++;
				application.setAttribute("visited", visited);
			}
			//跳转到主页
			response.sendRedirect("/CMS19/userController?flag=userList");
			return;
		}else{
			//登录失败
			String errLogin = "用户名或密码错误";
			request.setAttribute("errLogin", errLogin);
			request.getRequestDispatcher("login.jsp?username="+username+"&password="+password+"").forward(request, response);
//			response.sendRedirect("login.jsp?errLogin="+errLogin+"");
		}
		
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		VisitedUtil visitedUtil = new VisitedUtil();
		int visited  = visitedUtil.readVisited();
		//从数据库把访问量拿出来
		ServletContext application = this.getServletContext();
		application.setAttribute("visited", visited);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		VisitedUtil visitedUtil = new VisitedUtil();
		ServletContext application = this.getServletContext();
		int visited = (int)application.getAttribute("visited");
		//把访问量存进数据库
		visitedUtil.writeVisited(visited);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
