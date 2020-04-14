<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" language="javascript">
  window.onload = function(){
	  var province = document.getElementById("province");
	  var city = document.getElementById("city");
	  
	province.onchange = function(){
		
		  var provinceid = province.value;
		  city.options.length=0;
		  
		  //清除原内容
		  if(province.value==0){
			  city.add(new Option('==请选择==','0'));
			  return;
		  }
		  
		  
		  //创建xmlHttpRequest对象
		  var req = xmlHttpRequest()
		  
		  //处理响应结果，设置回调函数
		  req.onreadystatechange = function(){
			  if(req.status == 200 && req.readyState == 4){
				  var data = req.responseText;
				  var citys = data.split(",");
				  for(var i=0;i<citys.length;i++){
					  city.add(new Option(citys[i],citys[i]));
				  }
			  }
		  }
		  
		   //2.发送请求
		  req.open("get","${pageContext.request.contextPath}/getCitys?provinceid="+provinceid,"true");
		  
		  //3.发送请求
		  req.send(null)
		  
	  };
  };
  function xmlHttpRequest()
	{
		xmlhttp=null;
		if (window.XMLHttpRequest)
		  {// code for all new browsers
		  xmlhttp=new XMLHttpRequest();
		  }
		else if (window.ActiveXObject)
		  {// code for IE5 and IE6
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
	      return xmlhttp;
	}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/GetCity" method="post" >
 	<select id="province" name="province" >
	      <option value="0">==请选择==</option>
	      <option value="1">黑龙江</option>
	      <option value="2">吉林</option>
	    
        </select>
    <select id="city" name="city">
      	<option value="0">==请选择==</option>
      	
      </select>
      <input type="submit" value="提交">
</form>
</body>
</html>
