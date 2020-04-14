<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png"
	href="img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>新闻修改</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<!-- Bootstrap core CSS     -->
<link
	href="css/bootstrap.min.css"
	rel="stylesheet" />

<!-- Animation library for notifications   -->
<link
	href="css/animate.min.css"
	rel="stylesheet" />

<!--  Light Bootstrap Table core CSS    -->
<link
	href="css/light-bootstrap-dashboard.css"
	rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link
	href="css/demo.css"
	rel="stylesheet" />


<!--     Fonts and icons     -->
<style type="text/css">
.new-contentarea {
	width: 100%;
	overflow: hidden;
	margin: 0 auto;
	position: relative;
}

.new-contentarea label {
	width: 100%;
	height: 100%;
	display: block;
}

.new-contentarea input[type=file] {
	width: 188px;
	height: 60px;
	background: #333;
	margin: 0 auto;
	position: absolute;
	right: 50%;
	margin-right: -94px;
	top: 0;
	right /*\**/: 0px\9;
	margin-right /*\**/: 0px\9;
	width /*\**/: 10px\9;
	opacity: 0;
	filter: alpha(opacity = 0);
	z-index: 2;
}

a.upload-img {
	width: 165px;
	display: inline-block;
	margin-bottom: 10px;
	height: 57px;
	line-height: 57px;
	font-size: 20px;
	color: #FFFFFF;
	background-color: #f38e81;
	border-radius: 3px;
	text-decoration: none;
	cursor: pointer;
}

a.upload-img:hover {
	background-color: #ec7e70;
}

.tc {
	text-align: center;
}
</style>
<link
	href="css/pe-icon-7-stroke.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="js/XMLHttpRequest.js"></script>
<script type="text/javascript"
	src="js/jquery-1.10.2.js"></script>



<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/simditor-2.3.28/styles/simditor.css" />

<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/module.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/uploader.js"></script>
<c:if test="${sessionScope.checkUser.userPower==0 }">
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/lib/simditor.js"></script>
</c:if>
<script type="text/javascript">
$(function(){
	var editor = new Simditor({
		upload:true,
	  textarea: $('#editor'),
	  //optional options
	allowedTags:['span','div','link','table','tr','td','br', 'span', 'a', 'img', 'b', 'strong', 'i', 'strike', 'u', 'font', 'p', 'ul', 'ol', 'li', 'blockquote', 'pre', 'code', 'h1', 'h2', 'h3', 'h4', 'hr'],
    allowedAttributes:{
        div:['class','style'],
        link:['href','rel'],
        img: ['src', 'alt', 'width', 'height', 'data-non-image'],
        a: ['href', 'target','style'],
        font: ['color'],
        code: ['class'],
        span:['class','style'],
        i:['class','style']
    }
	});
})
</script>
<script type="text/javascript">
	function check(){
		var c = true;
		if($("#title").val()==""){
			$("#errTitle").html("<font color='red'>请输入标题</font>");
			c=false;
		}
		if($("#editor").val()==""){
			$("#errContent").html("<font color='red'>请输入内容</font>");
			c=false;
		}
		if($("#typeid").val()==""){
			$("#errTypeid").html("<font color='red'>请选择类别</font>");
			c=false;
		}
			return c;
	}
</script>

<c:if test="${fla==2}">
	<script type="text/javascript">
	$(document).ready(function() {

		demo.initChartist();

		$.notify({
			icon : 'pe-7s-gift',
			message : "该新闻已通过审核"

		}, {
			type : 'info',
			timer : 4000
		});

	});
</script>
</c:if>

<c:if test="${sessionScope.checkUser.userPower==1}">
	<script type="text/javascript">
	$(document).ready(function() {

		demo.initChartist();

		$.notify({
			icon : 'pe-7s-gift',
			message : "超级管理员不可修改新闻"

		}, {
			type : 'info',
			timer : 4000
		});

	});
</script>
</c:if>

</head>
<body>

	<%@ include file="left.jsp" %>

			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-8" style="width:1400px;">
							<div class="card">
								<div class="header">
									<h4 class="title">新闻修改</h4>
								</div>
								<div class="content">

									<!-- <div class="col-md-8">
										<div class="form-group">

											<label>图片文件</label>
											
												<div id="6">
													<div class="col-md-2">
														<input type="button" name="alreadyImage" value="6"
															class="form-control">
													</div>
													<div style="width: 80px">
														<img height="80px" width="80px"
															src="images\faces\face-7.jpg">
													</div>
													###.jpg
												</div>
											
												<div id="7">
													<div class="col-md-2">
														<input type="button" name="alreadyImage" value="7"
															class="form-control">
													</div>
													<div style="width: 80px">
														<img height="80px" width="80px"
															src="images\faces\face-7.jpg">
													</div>
													###.jpg
												</div> 
											
											<span id="innerFile"></span>
											<button type="button" class="form-control">
												上传文件</button>
										</div>
									</div>-->
									<form action="newsController?flag=updateNews" method="post" onsubmit='return check()'>
									<input type="hidden" name="id" value="${news.id }">
										<div>
											<input type="hidden" name="seltypeID" value="${n.typeid }">
											<input type="hidden" name="selPend" value="${n.flag }">
											<input type="hidden" name="selNewsName" value="${n.title }">				
											<input type="hidden" name="pageNow" value="${requestScope.pageNow }">
											
											<div class="col-md-2">
												<div class="form-group">
													<label for="exampleInputEmail1">新闻类别</label>
													<select class="form-control" name="typeid" id="typeid" style="width:200px" ${dis }>
															<c:forEach items="${newsTypeList }" var="newsTypeList">
																<option value="${newsTypeList.id }" ${newsTypeList.id eq news.typeid?"selected='selected'":"" }>${newsTypeList.typeName }</option>
															</c:forEach>
													</select>
													<span style='color:red' id="errTypeid">${errTypeid }</span>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<label>新闻ID(默认)</label> <input type="text" name="id"
														class="form-control" readonly="Company"
														value="4">
												</div>
											</div>
										</div>



										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>标题</label> <input type="text" name="title"
														id="title" class="form-control" ${dis } placeholder="新闻标题"
														value="${requestScope.news.title }">
														<span style='color:red' id="errTitle">${errTitle }</span>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>内容</label>
													<textarea ${dis } rows="15" name="content" id="editor" class="form-control" placeholder="新闻正文...." >${requestScope.news.content }</textarea>
													<span style='color:red' id="errContent">${errContent }</span>
												</div>
											</div>
										</div>

										<button type="submit" id="ssb"
											class="btn btn-info btn-fill pull-right" ${read }>${checkUser.userPower==0?"修改":"" }</button>
										<div class="clearfix"></div>
									</form>
								</div>
							</div>
						</div>
						

<!-- <div class="col-md-4">
    <div class="card card-user">
        <div class="image">
            <img src="images/userleft.bg.jpg" alt="#"/>
        </div>
        <div class="content">
            <div class="author">
                 <a href="#">
                 
                  	<img class="avatar border-gray" src="images\faces\face-3.jpg" alt="#"/>
                 
                  
               

                  <h4 class="title">admin<br />
                     <small>爱尚教育部</small>
                  </h4>
                </a>
            </div>
            <p class="description text-center"> admin是一位 <br>
                                18岁的女孩, 在<br>
                               	 爱尚教育部努力工作
            </p>
        </div>
        <hr>
        <div class="text-center">
            <button href="#" class="btn btn-simple"><i class="fa fa-facebook-square"></i></button>
            <button href="#" class="btn btn-simple"><i class="fa fa-twitter"></i></button>
            <button href="#" class="btn btn-simple"><i class="fa fa-google-plus-square"></i></button>

        </div>
    </div>
</div> -->
					</div>
				</div>
			</div>



			
<%@ include file="down.jsp" %>
		</div>
	</div>


</body>

<!--   Core JS Files   -->
<script
	src="js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script
	src="js/bootstrap.min.js"
	type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script
	src="js/bootstrap-checkbox-radio-switch.js"></script>

<!--  Charts Plugin -->
<script
	src="js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script
	src="js/bootstrap-notify.js"></script>


<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script
	src="js/light-bootstrap-dashboard.js"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script
	src="js/demo.js"></script>

</html>