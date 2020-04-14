package util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getCitys")
public class GetCity extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//创建map集合，存储list集合
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		List<String> list1 = new ArrayList<String>();
		list1.add("哈尔滨");
		list1.add("牡丹江");
		list1.add("佳木斯");
		map.put("1", list1);
		
		List<String> list2 = new ArrayList<String>();
		list2.add("沈阳");
		list2.add("大连");
		list2.add("四平");
		map.put("2", list2);
		
		
		String provinceid = request.getParameter("provinceid");
		System.out.println(provinceid);
		List<String> plist = map.get(provinceid);
		
		StringBuffer sb = new StringBuffer();
		String str = "";
		for(String s : plist){
			str = sb.append(s).append(",").toString();
		}
		
		str = str.substring(0,str.length()-1);
		
		out.print(str);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
