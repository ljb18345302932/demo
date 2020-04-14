<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/png"
	href="images/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>学生信息</title>

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
<script type="text/javascript"
	src="js/jquery-1.10.2.js"></script>
	
	
	<script src='${pageContext.request.contextPath}/js/jquery.js' type='text/javascript'></script>
	<script src='${pageContext.request.contextPath}/js/add.js' type='text/javascript'></script>
	<script>
	$(function(){
		$("#username").blur(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/userController?flag=ajaxAddVerify",
				type:"GET",
				data:{
					username : $("#username").val(),
				},
				dataType:"text",
				success:function(data){
					if(data=="ok"){
						$("#errName").html("<font color='green'>用户名可以使用</font> "),
						$("#sb").attr("disabled",false);
					}else{
						$("#errName").html("<font color='red'>用户名不可使用</font> "),
						$("#sb").attr("disabled",true);
					}
				}
			});
		});
	});
	
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
<%@ include file="left.jsp" %>

			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-18">
							<div class="card">
								<div class="header">
									<h4 class="title">添加用户</h4>
								</div>
								<div class="content">
									<form action="userController?flag=addUser" method="post" onsubmit='return check()' >
										<div class="row">
											<div class="col-md-3">
												<div class="form-group">
													<label>权限</label> 
													  <select name="power" class="form-control">
             			                                   <option value="1" >学生</option>
                                          			      <option value="0" >管理员</option>
                                           		     </select>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<hidden name="depID" value="4"></hidden>
													<label for="exampleInputEmail1">学院</label>
													
													<select name="depId" class="form-control">
                                                	<c:forEach var="department" items="${requestScope.findDepartment }">
														<option class="form-control"  value="${department.id }">${department.depName }</option>
													</c:forEach>
                                                
                                              		 </select>
													
													<input type="hidden" name="depId" value="4">
													<!-- <input type="email" class="form-control" placeholder="Email"> -->
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>用户名</label> <input type="text" class="form-control"
														placeholder="真实姓名" id="username" name="username"
														value=""> <span id="errName" style='color:red'>${errName }</span>
												</div>
											</div>
											
										</div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>密码</label> <input type="password"
														class="form-control" placeholder="不能为汉字" name="password"
														value=""><span style='color:red'>${errPass }</span>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label>性别</label><br> <input type="radio"
														
														name="sex" value="男">男&nbsp&nbsp&nbsp&nbsp <input
														type="radio"
														checked="checked"
														name="sex" value="女">女
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group">
													<label>年龄</label> <input type="number" name="age"
														id="age" class="form-control" placeholder="请输入年龄"
														value="">
														<span style='color:red'>${errAge }</span>
												</div>
											</div>
										</div>


										<input type="hidden" name="icon" value="ICon\2018\09-25\e3b25415-a75c-49f1-9fcf-456091c4b2a7_001.gif">
										<input type="submit" id="sb" class="btn btn-info btn-fill pull-right" value='添加'>
										
										<div class="clearfix"></div>
									</form>
								</div>
							</div>
						</div>

					</div>
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
