<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container" id="all">
				<form action="${pageContext.request.contextPath }/mainList?flag=mainTypeName" method="post">				
					<div class="framea">
						<ul>
						<c:forEach var="newsTypeList" items="${requestScope.newsTypeList }">
							<li><a href="${pageContext.request.contextPath }/mainList?flag=mainContent&id=${newsTypeList.id }">${newsTypeList.typeName }</a></li>
						</c:forEach>
						</ul>
					</div>
				</form>
				
				