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
		
		<%@ include file="mainTop.jsp" %>
			
				<h2>新闻标题</h2>
				<div class="lili">
				<ol>
					<c:forEach var="mainContent" items="${mainContent }">
						<li><a href="${pageContext.request.contextPath }/mainList?flag=content&id=${mainContent.id}" >${mainContent.title }</a></li>
					</c:forEach>
				</ol>
								<!-- 首页上一页 -->
									<c:if test="${requestScope.pageNow ne 1 }">
										<a
											href="mainList?flag=mainContent&pageNow=1&id=${id}">
											【首页】 </a>
										<a
											href="mainList?flag=mainContent&pageNow=${requestScope.pageNow-1 }&id=${id}">
											【上一页】 </a>
									</c:if>
									
									<!-- 总页数小于10 -->
									<c:if test="${pageCount lt 10}">
										<c:if test="${pageNow le 5}">
											<c:forEach var="i" begin="${1 }" end="${pageCount }" step="1">
												<a href="mainList?flag=mainContent&pageNow=${i }&id=${id}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
											</c:forEach>
										</c:if>
											
										<c:if test="${pageNow gt 5}">
											<c:forEach var="i" begin="${6 }" end="${pageCount }" step="1">
												<a href="mainList?flag=mainContent&pageNow=${i }&id=${id}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
											</c:forEach>
										</c:if>
									</c:if>
									
									<!-- 总页数大于等于10 -->
									<c:if test="${pageCount ge 10 }">
									<!-- 前五页 -->
									<c:if test="${pageNow le 5 }">
										<c:forEach var="i" begin="1" end="5" step="1">
											<a href="mainList?flag=mainContent&pageNow=${i }&id=${id}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>
									</c:if>
									
									<!-- 第六页到倒数五页 -->
									<c:if test="${pageNow gt 5 && pageNow le pageCount-5 }">
										<c:forEach var="i" begin="${pageNow-2 }" end="${pageNow }" step="1">
											<a href="mainList?flag=mainContent&pageNow=${i }&id=${id}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>
										
										<c:forEach var="i" begin="${pageNow+1 }" end="${pageNow+2 }" step="1">
											<a href="mainList?flag=mainContent&pageNow=${i }&id=${id}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>
									</c:if>
									
									<!-- 后五页 -->
									<c:if test="${pageNow gt pageCount-5 && pageNow le pageCount }">
										<c:forEach var="i" begin="${pageCount-4 }" end="${pageCount }" step="1">
											<a href="mainList?flag=mainContent&pageNow=${i }&id=${id}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>						
									</c:if>
									</c:if>
									<!-- 下一页末页 -->
									<c:if test="${requestScope.pageNow ne requestScope.pageCount}">
										<a
											href="mainList?flag=mainContent&pageNow=${requestScope.pageNow+1 }&id=${id}">
											【下一页】 </a>
										<a
											href="mainList?flag=mainContent&pageNow=${requestScope.pageCount }&id=${id}">
											【末页】 </a>
									</c:if>
				</div>
	</body>
</html>