<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>爱尚教育</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/mystyle.css"/>
		
	</head>
	<body>
		<center>

				
				<div class="lili">
				<table border="1">
				<c:forEach var="selNews" items="${selNews }">
					
					<h4><font color="red">新闻标题：</font>${selNews.title }</h4>
					
					
					<h4><font color="red">新闻内容：</font>${selNews.content }</h4>
					
					<h4><font color="red">发表人:</font>${users[selNews.uid] }</h4>
					
					<h4><font color="red">发表时间:</font>${selNews.createtime }</h4>
					
					<h4><font color="red">新闻类别:</font>${typeName[selNews.typeid]}类</h4>
				</c:forEach>
					</table>
						
				
				
					
				</div>
				</center>
	</body>
</html>