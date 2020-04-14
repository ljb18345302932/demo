<%@page import="com.aishang.service.impl.NewsService"%>
<%@page import="com.aishang.po.User"%>
<%@page import="com.aishang.po.News"%>
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

<title>书籍审核</title>

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
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="css/pe-icon-7-stroke.css"
	rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script type="text/javascript">
	$(function(){
		var userPower = '${sessionScope.checkUser.userPower }';
		if(userPower == 0){
			$("[name='upPend']").attr("disabled",true);
		}
	})
</script>
<script type="text/javascript">
	$(function(){ 
		$("[name='upPend']").change(function(){
				$.ajax({
					url:"${pageContext.request.contextPath}/newsController?flag=updatePend",
					type:"GET",
					async:false,
					dataType:"text",
					data:{
						upPend:$(this).val(),
						id:$(this).parents().prev().prev().prev().prev().prev().html(),
						con:confirm("确认修改?"),
					},
					success:function(data){
						
					},
				});
		});
	}); 
	<%-- function upd(nid){
		alert($("#upPend").parent().$("#nid").text())
		/* $("#myform").attr("action", "${pageContext.request.contextPath}/newsController?flag=updatePend").submit(); */
	 $.ajax({
		url:"${pageContext.request.contextPath}/newsController?flag=updatePend",
		type:"GET",
		dataType:"text",
		data:{
			id:nid,
		},
	}); 
}--%>
</script>

<script type="text/javascript">
$(function(){
	$("#delUser").click(function(){
		 var con = confirm("确认删除");
		 $("#con").val(con);
	})
})
</script>
	<style>
		.demo {display: inline-block;*display: inline;*zoom: 1;width: 140px;height: px;line-height: 20px;font-size: 15px;overflow: hidden;-ms-text-overflow: ellipsis;text-overflow: ellipsis;white-space: nowrap;}
		.demo:hover {height: auto;white-space: normal;}
</style>
</head>
<body>
<%@ include file="left.jsp" %>

			<div class="content">

				<div class="container-fluid">

					<div class="row">
					
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">书籍管理</h4>
									<p class="category">Here is a subtitle for this table</p>
									
							<form action="newsController" method="post">
		         				   <input type="hidden" name="flag" value="newsList" >
		                           <div style="float:left; margin:0 2px; width: 130px;">
			                           <select name="seltypeID" class="form-control" style="width: 130px;">
			                           <option value="">所有类别</option>
			                           <c:forEach var="newsTypeList" items="${requestScope.newsTypeList }">
			                           		<option value="${newsTypeList.id }" ${newsTypeList.id eq news.typeid?"selected='selected'":"" }  >${newsTypeList.typeName }</option>
			                           </c:forEach>
			                           </select>
		                           </div>
		                           <div style="float:left; margin:0 2px; width: 100px;">
			                           <select name="selPend" class="form-control">
			                           <option  value="">全部</option>
			                           <option  value="0" ${news.flag eq 0?"selected='selected'":"" }>未借阅</option>
			                           <option  value="1" ${news.flag eq 1?"selected='selected'":"" }>已借阅</option>
			                           </select>
		                           </div>
		                         	 
		                           <div style="float:left; margin:0 2px; width: 130px;"> 
		                            <input type="text" class="form-control" placeholder="新闻名称" size=8  name="selNewsName"  value="${news.title }">
		                          </div>
		                          <div style="float:left; margin:0 2px; width: 65px;">
		                          <button type="submit" class="btn btn-info btn-fill pull-right">查询</button>
		                          </div>
                          	</form>
										
								</div>
								
								<div class="content table-responsive table-full-width">
									<form
										action="${pageContext.request.contextPath }/newsController?flag=delNews"
										method="post" id="myform">
										<input type="hidden" name="typeid" value="${news.typeid }">
										<input type="hidden" name="flags" value="${news.flag }">
										<input type="hidden" name="title" value="${news.title }">
										<input type="hidden" name="pageNow" value="${requestScope.pageNow }">
										<input type="hidden" name="con" id="con" value="">
										<table class="table table-hover table-striped">
											<thead>
												<th>序号</th>
												<th>标题</th>
												<th>类别</th>
												<th>日期</th>
												<th>上传人</th>
												<th>审核</th>
												<th>操作</th>
												<c:if test="${sessionScope.checkUser.userPower eq 0 }">
												<th><button type="submit" class="btn btn-info btn-fill pull-left" id="delUser">删除</button></th>
												</c:if>
											</thead>
											<tbody>
												<c:forEach var="newsList" items="${requestScope.newsList }" >
													<tr>
														<td>${newsList.id }</td>
														<td class="demo">${newsList.title }</td>
														<td>${map[newsList.typeid] }</td>
														<td>${newsList.createtime }</td>
														<td>${users[newsList.uid] }</td>
														
														<td>
															<select class="form-control" name="upPend" id="upPend" onchange="upd(${newsList.id })">
																	<option value="0" ${newsList.flag eq 0?"selected":"" } >未借阅</option>
																	<option value="1" ${newsList.flag eq 1?"selected":"" } >已借阅</option>
															</select>
														</td>
														
															<td>
															<button type="button" rel="tooltip" title="<c:if test="${sessionScope.checkUser.userPower==0}">修改</c:if>
															<c:if test="${sessionScope.checkUser.userPower==1}">查看</c:if>
															<c:if test="{newsList.flag==2}">查看</c:if>
															" class="btn btn-info btn-simple btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/newsController?flag=updateEcho&id=${newsList.id }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}&fla=${newsList.flag }&pageNow=${pageNow}'"> 
															<i class="fa fa-edit"></i>
															</button>
														</td>
														<c:if test="${sessionScope.checkUser.userPower eq 0 }">
														<td>
															<label class="checkbox"> 
															<input type="checkbox" value="${newsList.id }" name="checkNews"  data-toggle="checkbox" 
															<c:if test="${newsList.flag==2}">disabled="disabled"</c:if>
															>
															</label>
														</td>
														</c:if>
													</tr>
												</c:forEach>
												<!-- <tr>
													<td>1</td>
													<td>Dakota Rice</td>
													<td>$36,738</td>
													<td>Niger</td>
													<td>Oud-Turnhout</td>
												</tr> -->
											</tbody>
										</table>
									</form>
									
								</div>
								<div style=" margin: 0 0 0px 50px;padding:0 0 20px 0; width: 70%;">
									<!-- 首页上一页 -->
									<c:if test="${requestScope.pageNow ne 1 }">
										<a
											href="newsController?flag=newsList&pageNow=1&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}">
											【首页】 </a>
										<a
											href="newsController?flag=newsList&pageNow=${requestScope.pageNow-1 }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}">
											【上一页】 </a>
									</c:if>
									<c:if test="${requestScope.pageCount==0 }">
							     	<center><font color='red'><h2>无此新闻</h2></font></center>
							     </c:if>
									<!-- 总页数小于10 -->
									<c:if test="${pageCount lt 10}">
										<c:if test="${pageNow le 5}">
											<c:forEach var="i" begin="${1 }" end="${pageCount }" step="1">
												<a href="newsController?flag=newsList&pageNow=${i }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
											</c:forEach>
										</c:if>
											
										<c:if test="${pageNow gt 5}">
											<c:forEach var="i" begin="${6 }" end="${pageCount }" step="1">
												<a href="newsController?flag=newsList&pageNow=${i }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
											</c:forEach>
										</c:if>
									</c:if>
									
									<!-- 总页数大于等于10 -->
									<c:if test="${pageCount ge 10 }">
									<!-- 前五页 -->
									<c:if test="${pageNow le 5 }">
										<c:forEach var="i" begin="1" end="5" step="1">
											<a href="newsController?flag=newsList&pageNow=${i }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>
									</c:if>
									
									<!-- 第六页到倒数五页 -->
									<c:if test="${pageNow gt 5 && pageNow le pageCount-5 }">
										<c:forEach var="i" begin="${pageNow-2 }" end="${pageNow }" step="1">
											<a href="newsController?flag=newsList&pageNow=${i }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>
										
										<c:forEach var="i" begin="${pageNow+1 }" end="${pageNow+2 }" step="1">
											<a href="newsController?flag=newsList&pageNow=${i }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>
									</c:if>
									
									<!-- 后五页 -->
									<c:if test="${pageNow gt pageCount-5 && pageNow le pageCount }">
										<c:forEach var="i" begin="${pageCount-4 }" end="${pageCount }" step="1">
											<a href="newsController?flag=newsList&pageNow=${i }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}" ${pageNow eq i?"style='color:red'":"" }>【${i }】</font></a>
										</c:forEach>						
									</c:if>
									</c:if>
									<!-- 下一页末页 -->
									<c:if test="${requestScope.pageNow ne requestScope.pageCount}">
										<a
											href="newsController?flag=newsList&pageNow=${requestScope.pageNow+1 }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}">
											【下一页】 </a>
										<a
											href="newsController?flag=newsList&pageNow=${requestScope.pageCount }&seltypeID=${news.typeid}&selPend=${news.flag}&selNewsName=${news.title}">
											【末页】 </a>
									</c:if>
											
										
									</div>
									<form action="${pageContext.request.contextPath }/newsController?flag=jump" method="post">
									<!-- <input type="hidden" name="falg" value="jump"> -->
									<input type="hidden" name="seltypeID" value="${news.typeid}">
									<input type="hidden" name="selPend" value="${news.flag}">
									<input type="hidden" name="selNewsName" value="${news.title}">
										<div
											style="float: right; margin:-50px 10px 0 0; width: 60px; height: 40px">
											<button type="submit" id="skipBut"
												class="btn btn-info btn-fill pull-right">跳转</button>
										</div>
										<div
											style="float: right; margin:-50px 80px 0 0; width: 60px; height: 40px">
											<input type="number" id="numb" class="form-control" placeholder="页面" size=2  name="numb" value="">
										</div>
										
									</form>
								
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