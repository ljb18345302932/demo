<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>	
<head>
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
    window.onload = function(){
        var username = document.getElementsByName("username")[0];        //获得用户名这个标签
        //document.cookie = "path=/";
        var name = decodeURIComponent("${cookie.username.value}");        //获取到名为username的cookie中转码后的值，若值不存在则为空串
        username.value = name;
       var password =  document.getElementById("password")
        password = decodeURIComponent("${cookie.password.value}"); 
        
        if(name.length >= 1){
            var memName = document.getElementsByName("memName")[0];
            memName.checked = true;
        }
    }
</script>
</head>
<body>
<!-- <script>
  $(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script> -->
 <!--SIGN UP-->
 <h1 style="font-family:'方正华隶简体'; color: #0e4277;">管理员登陆</h1>
<div class="login-form">
	<div class="close"> </div>
		<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="images/avtar.png" />
	</div>
			<form action="${pageContext.request.contextPath}/userController" method="post" onsubmit="return check()">
			<input type="hidden" name="flag" value="doLogin">
			<input type="text" class="text"
				value="${cookie.username.value}" name="username" id="username" >
			<div class="key">
					<input type="password" value="${cookie.password.value}" name="password" id="password">
				</div>
				
					<input type="checkbox" class="text" name="save" value="1" ${cookie.save.value} /><i style="color:#9199aa;">一周内免登陆</i>	
					
					<div class="signin">
					<span><font color='red'>${errLogin}</font></span>
						<input type="submit" value="Login" >
					</div>
			</form>
</div>
 <div class="copy-rights">
					<p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href=# target="_blank" title="#">#</a> - Collect from <a href=# title=# target="_blank">#</a></p>
</div>

</body>
</html>