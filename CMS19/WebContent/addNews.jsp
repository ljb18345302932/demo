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

<title>新书上传</title>

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

<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.js"></script>

<script>
	function check(){
		var a = false;
		var title = $("#title");
		var editor = $("#editor");
		if(title.val() == ""){
			$("#errTitle").html("<font color='red'>请输入书名</font>");
			a = true;
		}
		if(editor.val() == ""){
			$("#errContent").html("<font color='red'>请输入描述</font>");
			a = true;
		}
		if(a){
			return false;
		}
	}
</script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/simditor-2.3.28/styles/simditor.css" />

<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/module.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/site/assets/scripts/uploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/simditor-2.3.28/lib/simditor.js"></script>
<script type="text/javascript">
$(function(){
	var editor = new Simditor({
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

</head>
<body>

	<%@ include file="left.jsp" %>
	
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-8" style="width:1390px">
							<div class="card">
								<div class="header">
									<h4 class="title">新书上传</h4>
								</div>
								<div class="content">

									<!-- <div class="col-md-8">
										<div class="form-group">
											<span id="innerFile"></span>
											<button type="button" class="form-control">
												上传文件</button>
										</div> -->
									</div>
									<form action="${pageContext.request.contextPath }/newsController?flag=addNews" method="post" onsubmit='return check()'>
										<div>
											<div class="col-md-2">
												<div class="form-group">
												<div class="row">
													<label for="exampleInputEmail1">书籍类别</label> 
													<select class="form-control" name="typeid" style="width:200px">
															<c:forEach items="${newsTypeList }" var="newsTypeList">
																<option value="${newsTypeList.id }">${newsTypeList.typeName }</option>
															</c:forEach>
													</select>
													<span style='color:red'>${errTypeid }</span>
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<!-- <div class="form-group">
													<label>新闻ID(默认)</label> <input type="text" name="id"
														class="form-control" readonly="Company"
														value="4">
												</div> -->
											</div>
										</div>



										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>书名</label> <input type="text" name="title"
														id="title" class="form-control" placeholder="书籍名字"
														value="">
														<span id='errTitle' style='color:red'>${errTitle }</span>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>描述</label>
													<textarea rows="15" name="content" id="editor" class="form-control" placeholder="新闻正文...." ></textarea>
														<span id="errContent" style='color:red'>${errContent }</span>
												</div>
												
											</div>
										</div>

										<button type="submit" id="ssb" class="btn btn-info btn-fill pull-right">上传</button>
										<div class="clearfix"></div>
									</form>
								</div>
							</div>
						</div>
						



        <hr>
        <div class="text-center">
            <button href="#" class="btn btn-simple"><i class="fa fa-facebook-square"></i></button>
            <button href="#" class="btn btn-simple"><i class="fa fa-twitter"></i></button>
            <button href="#" class="btn btn-simple"><i class="fa fa-google-plus-square"></i></button>

        </div>
    </div>
</div>

<%@ include file="down.jsp" %>
					</div>
					
				</div>
				
			</div>



			

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