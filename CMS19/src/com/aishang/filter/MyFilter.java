package com.aishang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aishang.po.User;
@WebFilter("/*")
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 转换两个对象HttpServletRequest，HttpServletResponse
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		//查看地址
		String uri = req.getRequestURI();
		
		int images = uri.indexOf("images");
		int css = uri.indexOf("css");
		int js = uri.indexOf("js");
		int num = uri.indexOf("login");
		int main = uri.indexOf("main");
		String flag = req.getParameter("flag");
//		if(("/java1910/login.jsp".equals(uri) || "doLogin".equals(flag) || "/java1910/mainList".equals(uri))){
//			chain.doFilter(req, resp);
//			return;
//		}
		if(!(num>0 || "doLogin".equals(flag) || images>0 || css>0 || main>0)) {
			User user = (User) session.getAttribute("checkUser");
			if(user==null) {
				resp.sendRedirect("login.jsp");
				return;
			}
			
		}
		chain.doFilter(request, response);
		
//		User user = (User)session.getAttribute("checkUser");
		
//		if(user==null){
//			resp.sendRedirect("login.jsp");
//		}else {
//			chain.doFilter(req, resp);
//		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
