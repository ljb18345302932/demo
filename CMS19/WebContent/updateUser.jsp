<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="images/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>修改用户</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="css/light-bootstrap-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="css/demo.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="css/pe-icon-7-stroke.css" rel="stylesheet" />
    
    <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	
</head>
<body>
<%@ include file="left.jsp" %>

		<div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">修改信息</h4>
                            </div>
                            <div class="content">
                                <form action="userController?flag=doUpdateUser&id=${checkUser.id }&power=${sessionScope.checkUser.userPower}" method="post">
                                
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>用户ID(不可更改)</label>
                                                <input type="text" class="form-control" name="id" readonly=true value="${checkUser.id }">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>权限</label>
													<c:choose>
														<c:when test="${checkUser.userPower eq 0 }">		
															<c:set var="r" value="selected"></c:set>
														</c:when>
														<c:otherwise>
															<c:set var="h" value="selected"></c:set>
														</c:otherwise>
													</c:choose>
                                                <select name="power" class="form-control" ${checkUser.userPower==0?"disabled='disabled'":"" }>
                                                <option value="1" ${h } >超级管理员</option>
                                                <option value="0" ${r } >普通管理员</option>
                                                </select>
                                              	<span><font color='red'>${errPower }</font></span>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">部门</label>
                                                <select name="depId" class="form-control" ${checkUser.userPower==0?"disabled='disabled'":"" }>
                                                	<c:forEach var="department" items="${requestScope.findDepartment }">
                                                		<c:choose>
															<c:when test="${checkUser.depID == department.id  }">		
															<c:set var="select" value="selected"></c:set>
															</c:when>
															<c:otherwise>
															<c:set var="select" value=""></c:set>
															</c:otherwise>
														</c:choose>
														<option class="form-control"  value="${department.id }" ${select }>${department.depName }</option>
													</c:forEach>
                                                
                                                </select>
                                                <span id="recMsg"><font color='red'>${errDep }</font></span>
                                                <!-- <input type="email" class="form-control" placeholder="Email"> -->
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>用户名</label>
                                                <input type="text" class="form-control" id="username" placeholder="真实姓名" readonly=true name="username" value="${checkUser.userName }">
                                            	<span id="recMsg"><font color='red'>${errName }</font></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>密码</label>
                                                <input type="password" class="form-control" placeholder="不能为汉字" name="password" value="${checkUser.userPwd }">
                                                <span><font color='red'>${errPass }</font></span>
                                            </div>
                                        </div>
                                    </div>
									<div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>确认密码</label>
                                                <input type="password" class="form-control" placeholder="不能为汉字" name="password1" value="${checkUser.userPwd }">
                                                <span><font color='red'>${errPass1 }</font></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>性别</label>
                                               <br>
													<c:choose>				
														<c:when test="${checkUser.userSex eq '男' }">		
															<c:set var="a" value="checked"></c:set>
														</c:when>
														<c:otherwise>
															<c:set var="b" value="checked"></c:set>
														</c:otherwise>
													</c:choose>
                                                <input type="radio"  name="sex"  value="男" ${a }>男&nbsp&nbsp&nbsp&nbsp
                                                <input type="radio"  name="sex" value="女" ${b }>女
                                                <span><font color='red'>${errSex }</font></span>
                                            </div>
                                        </div>
                                      
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>年龄</label>
                                                <input type="number" name="age" class="form-control" value="${checkUser.userAge }">
                                            	<span><font color='red'>${errAge }</font></span>
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
                                        </div>
                                    </div>

                                    

                                    <a href="${pageContext.request.contextPath}/userController?flag=userList" style="font-size:20px">返回</a>
                                    <button type="submit" class="btn btn-info btn-fill pull-right">修改</button>
                                    
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                

<div class="col-md-4">
    <div class="card card-user">
        <div class="image">
            <img src="images/userleft.bg.jpg" alt="/newsSystem."/>
        </div>
        <div class="content">
            <div class="author">
                 <a href="#">
                 
                  	<img class="avatar border-gray" src="images\faces\face-3.jpg" alt=""/>
                 
                  
               

                  <h4 class="title">${checkUser.userName }<br />
                     <small>${findDepartment[checkUser.depID].depName }</small>
                  </h4>
                </a>
            </div>
            <p class="description text-center"> ${checkUser.userName }是一位 <br>
                                ${checkUser.userAge }岁的${checkUser.userSex }孩, 在<br>
                               	 ${findDepartment[checkUser.depID-1].depName }努力工作
            </p>
        </div>
        <hr>
        <div class="text-center">
            <button href="#" class="btn btn-simple"><i class="fa fa-facebook-square"></i></button>
            <button href="#" class="btn btn-simple"><i class="fa fa-twitter"></i></button>
            <button href="#" class="btn btn-simple"><i class="fa fa-google-plus-square"></i></button>

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
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="js/bootstrap-checkbox-radio-switch.js"></script>

	<!--  Charts Plugin -->
	<script src="js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="js/bootstrap-notify.js"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="js/light-bootstrap-dashboard.js"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="js/demo.js"></script>

</html> 