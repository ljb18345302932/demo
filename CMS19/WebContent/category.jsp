<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>书籍审核</title>

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
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/pe-icon-7-stroke.css" rel="stylesheet" />


	
	<script type="text/javascript" src="js/XMLhttpRequest.js"></script>
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<!-- 遍历类别 -->
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/newsTypeController?flag=newsLists",
				type:"get",
				dateType:"json",
				success:function(data){
					var mytable = $("#tab");
					var dataobj = eval(data);
					$.each(dataobj,function(key,val){
						var id = val["id"];
						var typeName = val["typeName"];
						var sort = val["sort"];
						mytable.append($("#tab").append("<tr id='"+id+"'><td><input type='text' class='form-control' readonly=true name='typeID' value='"+id+"'></td><td><input type='text' class='form-control' name='type' id='type' placeholder='类别名称' value='"+typeName+"' name='userName' onfocus='this.value = '';' onchange='return echo(this)'><span id='recMsg'></span></td><td><div style=' width:130px;'><input type='number' class='form-control' placeholder='类别优先级'   name='sor' id='sor'  value='"+sort+"' onchange='return sor(this)'></div><span></span></td><td class='td-actions text-right'><button type='button' rel='tooltip' title='修改' id='upd' name='upd' class='btn btn-info btn-simple btn-xs' onclick='return upd(this)' ><i class='fa fa-edit'></i></button><button type='submit' rel='tooltip' title='删除' name='del' value='22' class='btn btn-danger btn-simple btn-xs' onclick='return delDepartment("+id+")' ><i class='fa fa-times'></i></button></td></tr>)"));
					});
				},
			});
		});
	</script>
	<!-- 添加js验证 -->
	<script type="text/javascript">
	function check(){
		var c = true;
		if($("#typeName").val()==""){
			$("#errTypeName").html("<font color='red'>请输入类别名称</font>");
			c=false;
		}
		if($("#sort").val()==""){
			$("#errSort").html("<font color='red'>请输入优先级</font>");
			c=false;
		}
			return c;
	}
</script>
<!-- 验证添加用户重名问题 -->
<script type="text/javascript">
		$(function(){
			$("#typeName").blur(function(){
				$.ajax({
					url:"${pageContext.request.contextPath}/newsTypeController?flag=ajaxTypeName",
					type:"get",
					data:{
						typeName:$("#typeName").val(),
					},
					dataType:"text",
					success:function(data){
						console.log(data)
						if(data=="ok"){
							$("#errTypeName").html("<font color='red'>类别名称重复</font>");
							$("#sb").attr("disabled",true);
						}else{
							$("#errTypeName").html("<font color='red'></font>");
							$("#sb").attr("disabled",false);
						}
					}
				})
			})
		})
	
	</script>
	<!-- 类别修改 -->
	<script type="text/javascript">
	function upd(so){
		$.ajax({
			url:"${pageContext.request.contextPath}/newsTypeController?flag=updateNewsType",
			type:"get",
			data:{
				type:$(so).parent().prev().prev().find("input").val(),
				sor:$(so).parent().prev().find("input").val(),
				id:$(so).parent().prev().prev().prev().find("input").val(),
				
				con:confirm("确认修改?"),
			},
			dataType:"text",
		})
		window.location.href = window.location.href;
		return true;
	}
	
	function echo(ec){
		$.ajax({
			url:"${pageContext.request.contextPath}/newsTypeController?flag=updateNewsType",
			type:"get",
			data:{
				type:$(ec).val(),
				sor:$(ec).parent().next().find("input").val(),
				p:1,
			},
			dataType:"text",
			success:function(data){
				if(data == "no"){
					$($(ec).next()).html("<font color='red'>类别名称不可为空</font>");
					$($(ec).parent().next().next().find("button")).attr("disabled",true);
				}else if(data == "typeno"){
					$($(ec).next()).html("<font color='red'>类别重复</font>");
					$($(ec).parent().next().next().find("button")).attr("disabled",true);
				}else{
					$($(ec).next()).html("<font color='green'>可以使用</font>");
					$($(ec).parent().next().next().find("button")).attr("disabled",false);
				}
			}
		})
	}
	function sor(so){
		$.ajax({
			url:"${pageContext.request.contextPath}/newsTypeController?flag=updateNewsType",
			type:"get",
			data:{
				type:$(so).parent().parent().prev().find("input").val(),
				sor:$(so).val(),
				p:2,
			},
			dataType:"text",
			success:function(data){
				if(data == "no"){
					$($(so).parent().next()).html("<font color='red'>类别优先级不可为空</font>");
					$($(so).parent().next().parent().next().find("button")).attr("disabled",true);
				}else if(data == "errSortno"){
					$($(so).parent().next()).html("<font color='red'>类别优先级重复</font>");
					$($(so).parent().next().parent().next().find("button")).attr("disabled",true);
				}else{
					$($(so).parent().next()).html("<font color='green'>可以使用</font>");
					$($(so).parent().next().parent().next().find("button")).attr("disabled",false);
				}
			}
		})
	}
	</script>
	<!-- 删除类别 -->
<script type="text/javascript">
function delDepartment(del){
	$.ajax({
		url:"${pageContext.request.contextPath}/newsTypeController?flag=delNewsType",
		type:"get",
		data:{
			typeID:del,
			con:confirm("确认删除?"),
		},
		dataType:"text",
		success:function(data){
			if(data=="ok"){
				$.notify({
					icon: 'pe-7s-gift',
					message: " <b>删除失败</b>"

				},{
					type: 'danger',
					timer: 4000
				});
			}else if(data=="o"){
				$.notify({
					icon: 'pe-7s-gift',
					message: " <b>此类别有新闻不可删除</b>"

				},{
					type: 'danger',
					timer: 4000
				});
			}else{
				$(document).ready(function() {

					demo.initChartist();

					$.notify({
						icon : 'pe-7s-gift',
						message : "删除成功"
							
					}, {
						type : 'info',
						timer : 4000
					});
				});
				window.location.href = window.location.href;
			}
		}
	})
}
</script>
</head>
<body>
<%@ include file="left.jsp" %>
   
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                	<div class="col-md-6" style="width: 800px;">
                        <div class="card ">
                            <div class="header">
                                <h4 class="title">类别管理</h4>
                                <p class="category">Backend development</p>
                            </div>
                            <div style="float:right; width: 150px; height:50px;  margin-right: 10px; margin-top: -30px;">
                          	
		                           
		                          
		                          <!--  </div> -->
                          
                           </div>
                            <div class="content">
                                <div class="table-full-width">
                                    <table class="table" id="tab">
                                        <tbody>
                                           <tr>
                                            <td>类别ID</td>
                                            <td>类别名称</td>
                                            <td>类别排序</td>
                                            <td colspan=2 align="center">操作</td>
                                           </tr>
                                            
                                        </tbody>
                                    </table>
                                </div>

                                <div class="footer">
                                    <hr>
                                    <div class="stats">
                                        <i class="fa fa-history"></i>添加类别
                                        <form action="${pageContext.request.contextPath }/newsTypeController?flag=addNewsType" method="post" onsubmit="return check()">
                                        <table class="table">
                                            <tr>
                                        
                                                <td>      
					                            <input type="text" class="form-control" name="typeName" id="typeName" placeholder="类目名称" value="${typeName }" >
					                          	 <span id="errTypeName" style='color:red'>${errTypeName }</span>
					                            </td>
					                            <td>
					                            <div style=" width:130px;">
												<input type="text" class="form-control" placeholder="类目优先级"   name="sort" id="sort"  value="${sort }"  >
												<span style='color:red' id="errSort">${errSort }</span>
												</div>
					                         	</td>
					                         	
                                                <td class="td-actions text-right">
                                                
		                          				<button type="submit" id="sb" class="btn btn-info btn-fill pull-right" >添加类别</button>
                                                   
                                                </td>
                                            </tr>
                                            </table>
                                            </form>
                                    </div>
                                </div>
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