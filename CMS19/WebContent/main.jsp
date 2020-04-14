<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>爱尚教育</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/mystyle.css"/>
		<style>
		
           li{
            	margin-right:10px;
           		position: relative;
				left: 10px;
				top: 5px;
                width: 200px;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
            }
        </style>
        <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	</head>
	<body>
		<%@ include file="mainTop.jsp" %>
			<div class="frameadd">
					<div class="lefta" id="leftTop">
						<div class="top">
						
						体育<span>更多</span>
						
							
						</div>
						
						<div class="leftb">
							<img src="images/tongzhi.png" >
						</div>
						
						<div class="rightb">
							<ul>
							<c:forEach var="sports" items="${sports }">
								<li><a href="${pageContext.request.contextPath }/mainList?flag=content&id=${sports.id }">${sports.title }</a></li>
							</c:forEach>
							</ul>
						</div>
					</div>
					
					<div class="righta">
						<div class="topa">
							军事 <span>更多</span>
						</div>
						
						<div class="leftc">
							<img src="images/wenda.png" >
						</div>
						
						<div class="rightc">
							<ul>
							<c:forEach var="military" items="${military }">
								<li><a href="${pageContext.request.contextPath }/mainList?flag=content&id=${military.id }">${military.title }</a></li>
							</c:forEach>
							</ul>
						</div>
					</div>
					
				</div>
				
			<div class="frameadd">
				<div class="lefta">
					<div class="top">
						娱乐 <span>更多</span>
					</div>
					
					<div class="leftb">
						<img src="images/jishu.png" >
					</div>
					
					<div class="rightb">
						<ul>
							<c:forEach var="recreation" items="${recreation }">
								<li><a href="${pageContext.request.contextPath }/mainList?flag=content&id=${recreation.id }">${recreation.title }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				<div class="righta">
					<div class="topa">
						游戏 <span>更多</span>
					</div>
					
					<div class="leftc">
						<img src="images/ziyuan.png" >
					</div>
					
					<div class="rightc">
						<ul>
							<c:forEach var="game" items="${game }">
								<li><a href="${pageContext.request.contextPath }/mainList?flag=content&id=${game.id }">${game.title }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>


			<div class="frameadd">
				<div class="lefta">
					<div class="top">
						 历史<span>更多</span>
					</div>
					
					<div class="leftb">
						<img src="images/xuyuan.png" >
					</div>
					
					<div class="rightb">
						<ul>
							<c:forEach var="history" items="${history }">
								<li><a href="${pageContext.request.contextPath }/mainList?flag=content&id=${history.id }">${history.title }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				<div class="righta">
					<div class="topa">
						热点 <span>更多</span>
					</div>
					
					<div class="leftc">
						<img src="images/jiuye.png" >
					</div>
					
					<div class="rightc">
						<ul>
							<c:forEach var="hot" items="${hot }">
								<li><a href="${pageContext.request.contextPath }/mainList?flag=content&id=${hot.id }">${hot.title }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
	</body>
</html>
    