<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="wrapper">
		

    <div class="sidebar" data-color="grow" data-image="images/sidebar-5.jpg">
    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    <img src="images/logo.png">
                </a>
            </div>

            <ul class="nav">
                <li>
                    <a href="home.jsp">
                        <i class="pe-7s-graph"></i>
                        <p>管理员首页</p>
                    </a>
                </li>
                <li>
                    <a href="${requestContext.request.pageContext }userController?flag=updateUser&username=${cookie.username.value }&password=${cookie.password.value}">
                        <i class="pe-7s-user"></i>
                        <p>个人信息</p>
                    </a>
                </li>
               	<li>
                    <a href="${requestContext.request.pageContext }newsController?flag=newsList">
                        <i class="pe-7s-news-paper"></i>
                        <p>书籍列表</p>
                    </a>
                </li>
                
                <li>
                    <a href="${requestContext.request.pageContext }userController?flag=userList">
                        <i class="pe-7s-note2"></i>
                        <p>用户管理</p>
                    </a>
                </li>
                <c:if test="${sessionScope.checkUser.userPower == 1 }">
                <li>
                    <a href="${requestContext.request.pageContext }userController?flag=add">
                        <i class="pe-7s-map-marker"></i>
                        <p>添加用户</p>
                    </a>
                </li>
                
                	<li>
                    <a href="category.jsp">
                        <i class="pe-7s-bell"></i>
                        <p>类别管理</p>
                    </a>
                </li>
                
                <li>
                    <a href="departmentManage.jsp">
                        <i class="pe-7s-bell"></i>
                        <p>学院管理</p>
                    </a>
                </li>
                </c:if>
                
                <li>
                    <a href="${requestContext.request.pageContext }newsTypeController?flag=addNews">
                        <i class="pe-7s-science"></i>
                        <p>书籍上传</p>
                    </a>
                </li>
				<li class="active-pro">
                    <a href="#">
                        <i class="pe-7s-rocket"></i>
                        <p>购买专业版</p>
                    </a>
                </li>
            </ul>
    	</div>
    </div>
		

		<div class="main-panel">
			
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <!-- <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button> -->
                    <a class="navbar-brand" href="#">新闻管理系统</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="pe-7s-graph">消息</i>
                                    <b class="caret"></b>
                                    <span class="notification">5</span>
                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Notification 1</a></li>
                                <li><a href="#">Notification 2</a></li>
                                <li><a href="#">Notification 3</a></li>
                                <li><a href="#">Notification 4</a></li>
                                <li><a href="#">Another notification</a></li>
                              </ul>
                        </li>
                       
                       
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        
                        <li>
                            <a href="userController?flag=quit">
                            	安全退出
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>