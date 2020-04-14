<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png"
	href="img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>管理页面</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />

<!-- Bootstrap core CSS     -->
<link href="css/bootstrap.min.css"
	rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="css/animate.min.css"
	rel="stylesheet" />

<!--  Light Bootstrap Table core CSS    -->
<link
	href="css/light-bootstrap-dashboard.css"
	rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="css/demo.css"
	rel="stylesheet" />


<!--     Fonts and icons     -->

<link href="css/pe-icon-7-stroke.css"
	rel="stylesheet" />
	
	<%--<script type="text/javascript">
	window.onload = function(){
		var del = document.getElementById("del");
		del.onclick = function(){
			alert(123)
			var msg = document.getElementById("msg");
			msg.innerHTML = "删除失败";
		}
	
		
	}
	</script> --%>
	
</head>
<body>
<%@ include file="left.jsp" %>
	
			<div class="content">
				<div class="container-fluid">
					<div class="row">

						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">用户管理</h4>
									<p class="category">Here is a subtitle for this table</p>
								<form action="userController?flag=userList" method="post">
									
		                          	<div style="float:left;  width: 130px;">
		                           <select name="searchDep" class="form-control" style="width: 130px;">
		                           <option value="0">所有学院</option>
		                           <c:forEach items="${requestScope.findDepartment }" var="department" varStatus="vs">
		                           		<c:choose>
											<c:when test="${searchUser.depID == department.id  }">
												<c:set var="select" value="selected"></c:set>
											</c:when>
											<c:otherwise>
												<c:set var="select" value=""></c:set>
											</c:otherwise>
										</c:choose>
		                           		<option value="${department.id }" ${select } >${department.depName }</option>
		                           </c:forEach>
		                           </select>
		                           </div>
		                         	 
		                           <div style="float:left; margin:0 2px; width: 130px;"> 
		                            <input type="text" name="searchName" class="form-control" placeholder="查询姓名" size=8 value="${requestScope.searchUser.userName }">
		                          </div>
		                          <div style="float:left; margin:0 2px; width: 65px;">

		                          <button type="submit" class="btn btn-info btn-fill pull-right">查询</button>
		                          </div>
                          	</form>
								<form action='userController?flag=delBatch' method='post'>
									<input type='hidden' name='pageNow' value='${requestScope.pageNow }'>
									<input type='hidden' name='searchName' value='${requestScope.searchUser.userName  }'>
									<input type='hidden' name='searchDep' value='${requestScope.searchUser.depID  }'>
								</div>
								<div class="content table-responsive table-full-width">
									<table class="table table-hover table-striped">
										<thead>
											<th>用户名</th>
											
											<th>性别</th>
											<th>年龄</th>
											<th>学院名称</th>
											<th>管理等级</th>
											<c:if test="${sessionScope.checkUser.userPower == 1 }">
											<th>操作</th>
											<th>操作</th>
											
											<th><input type='submit' value='批量删除' class="btn btn-info btn-fill pull-left" ></th>
	                           				</c:if>
										</thead>
										<tbody>
									<c:forEach items="${requestScope.users }" var="u" varStatus="vs">
		                           		<tr  height="35px">
													
													<td>${u.userName }</td>
													
													<td>${u.userSex }</td>
													<td>${u.userAge }</td>
													<td>${map[u.depID] }</td>
													<td>${u.userPower==0?"学生":"管理员" }</td>
													
													<c:if test="${sessionScope.checkUser.userPower == 1 }">
													<td style="font-size: 12px"><a
														href="userController?flag=checkUpdateUser&id=${u.id }&pageNow=${requestScope.pageNow }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }">修改</a></td>
													<td style="font-size: 12px">
													<a id='del' href="userController?flag=delUser&id=${u.id }&pageNow=${requestScope.pageNow }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }">删除</a></td>
													<td width="20"><input type='checkbox' name='ids' value=${u.id } /></td>
													</c:if>
												</tr>
		                           </c:forEach>
										</tbody>
									</table>

								</div>
								 <div style=" margin: 0 0 0px 50px;padding:0 0 20px 0; width: 70%;">
	                           
							    <c:if test="${requestScope.pageNow!=1 }">
							    	<a  href='userController?flag=userList&pageNow=1&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }'>【首页】</a>
							    	<a  href='userController?flag=userList&pageNow=${requestScope.pageNow-1 }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }' >【上一页】</a>
							    </c:if>
							     <c:if test="${requestScope.pageCount==0 }">
							     	<center><font color='red'><h2>查无此人</h2></font></center>
							     </c:if>
							    <c:if test="${requestScope.pageCount!=0 }">
							    
								<c:if test="${requestScope.pageNow<=5 }">
							    	<c:forEach var="i" begin="1" end="5" step="1">
							    		<c:choose>
											<c:when test="${requestScope.pageNow == i }">		
												<c:set var="style" value="style='color:red'"></c:set>
											</c:when>
											<c:otherwise>
												<c:set var="style" value=""></c:set>
											</c:otherwise>
										</c:choose>
							    		<a href='userController?flag=userList&pageNow=${i }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }' ${style }> 【${i }】 </a>
							    	</c:forEach>
							    </c:if>
	     					   
								<c:if test="${requestScope.pageNow>5 && pageNow<=requestScope.pageCount}">									
									<c:forEach var="i" begin="${requestScope.pageNow-2 }" end="${requestScope.pageNow-1 }" step="1">
										<c:choose>
											<c:when test="${requestScope.pageNow == i }">		
												<c:set var="style" value="style='color:red'"></c:set>
											</c:when>
											<c:otherwise>
												<c:set var="style" value=""></c:set>
											</c:otherwise>
										</c:choose>
										<a href="userController?flag=userList&pageNow=${i }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }" ${style }> 【${i }】 </a>
									</c:forEach>
									<c:choose>
										<c:when test="${requestScope.pageNow<=requestScope.pageCount-2 }">		
											<c:forEach var="i" begin="${requestScope.pageNow }" end="${requestScope.pageNow+2 }" step="1">
												<c:choose>
													<c:when test="${requestScope.pageNow == i }">		
														<c:set var="style" value="style='color:red'"></c:set>
													</c:when>
													<c:otherwise>
														<c:set var="style" value=""></c:set>
													</c:otherwise>
												</c:choose>
												<a href="userController?flag=userList&pageNow=${i }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }" ${style }> 【${i }】 </a>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="i" begin="${requestScope.pageNow }" end="${requestScope.pageCount }" step="1">
												<c:choose>
													<c:when test="${requestScope.pageNow == i }">		
														<c:set var="style" value="style='color:red'"></c:set>
													</c:when>
													<c:otherwise>
														<c:set var="style" value=""></c:set>
													</c:otherwise>
												</c:choose>
												<a href="userController?flag=userList&pageNow=${i }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }" ${style }> 【${i }】 </a>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:if>
				    			<c:if test="${requestScope.pageNow != requestScope.pageCount }">
									<a href="userController?flag=userList&pageNow=${requestScope.pageNow+1 }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }"> 【下一页】 </a>
	                           		<a href="userController?flag=userList&pageNow=${requestScope.pageCount }&searchName=${requestScope.searchUser.userName }&searchDep=${requestScope.searchUser.depID }"> 【末页】 </a>
								</c:if>
	                           
	                           </c:if>
	                          </div>
	                          </form>
	                          <form action="userController" method="post">
	                          <input type='hidden' name="flag" value='jump'>
	                          <input type='hidden' name="searchName" value='${requestScope.searchUser.userName }'>
	                          <input type='hidden' name="searchDep" value='${requestScope.searchUser.depID }'>
	                          	<div style=" float: right; margin:-50px 80px 0 0; width: 60px; height: 40px">
	                          	 <input type="number" id="skipNum" class="form-control" placeholder="页面" size=2  name="numb" >
	                          	 
									<style>
									    input::-webkit-outer-spin-button,
									    input::-webkit-inner-spin-button {
									        -webkit-appearance: none;
									    }
									    input[type="number"]{
									        -moz-appearance: textfield;
									    }
									</style>
	                           </div>
	                           <div style="float: right; margin:-50px 10px 0 0; width: 60px; height: 40px">
	                          	<button type="submit" id="skipBut"  class="btn btn-info btn-fill pull-right">跳转</button>
	                           </div>
	                           </form>
	                          
							</div>
						</div>
					</div>
	
			<center><span><h2><font color='red'>${msg }${msg1 }</font></h2></span></center>
				</div>
				
			</div>
			<%@ include file="down.jsp" %>
		</div>
	</div>


</body>

<!--   Core JS Files   -->
<script src="js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script src="js/bootstrap.min.js"
	type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script
	src="js/bootstrap-checkbox-radio-switch.js"></script>

<!--  Charts Plugin -->
<script src="js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="js/bootstrap-notify.js"></script>


<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script
	src="js/light-bootstrap-dashboard.js"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="js/demo.js"></script>


</html>