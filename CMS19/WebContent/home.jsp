<%@page import="com.aishang.po.User"%>
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

</head>
<body>

	<%@ include file="left.jsp" %>

			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="card">
							<div class="header">
								<h4 class="title">主页</h4>
								<p class="category">====================</p>
							</div>
							<div style="width: 800px; height: 400px;">
								
									
							
							</div>
							
						</div>
					</div>
					
				</div>
				
			</div>
<%@ include file="down.jsp" %>	
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

<script type="text/javascript">
	$(document).ready(function() {

		demo.initChartist();

		$.notify({
			icon : 'pe-7s-gift',
			message : "欢迎您<b>${sessionScope.checkUser.userPower == 0?'普通管理员':'超级管理员'}-${sessionScope.checkUser.userName}</b>"

		}, {
			type : 'info',
			timer : 4000
		});

	});
</script>

</html>