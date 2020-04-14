<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<title></title>

<script type="text/javascript">
function GetJson(){
	   $.ajax({
		url:"${pageContext.request.contextPath }/json/user.js",//请求路径，读取user.js文件
		dataType:"json",//数据格式
		type:"GET",//请求方式
		success:function(Data){		//成功处理函数   
			var dataobj = eval(Data);//能把字符串转成js的对象
	    	     $.each(dataobj,function(key,val){
	    	       console.log(dataobj),
	    		   alert(val);
	    		   var username = val.username;
	    		   var age = val.age;
	    		   $("#userinfo").append("<tr><td>"+username+"</td><td>"+age+"</td></tr>"); 
	    	});
	    }
     });  
}

</script>
</head>
<body>
<body>
        <input type="button" value="加载json数据" onclick="GetJson()" />
        <br />
        <br />
        <table id="userinfo" border=1>
            <tr>
                <th>姓名</th>
                <th>年龄</th>
            </tr>
        </table>
</body>
</body>
</html>
