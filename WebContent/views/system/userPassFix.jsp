<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<%@ include file="/views/include/head.jsp"%>
<script
	src="${adminPath }/static/jquery-validation-1.14.0/lib/jquery.js"></script>
<script
	src="${adminPath }/static/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	
	$().ready(function() {
		// 在键盘按下并释放及提交后验证提交表单
		$("#submitForm").validate({
			rules : {
				oldpassword : {
					required : true,
					minlength :4,
					remote: {
					    url: "${adminPath}/user?method=checkPass",     //后台处理程序
					    type: "post",               //数据发送方式
					    dataType: "json",           //接受数据格式   
					    data: {                     //要传递的数据
					    	oldpassword: function() {
					            return $("#oldpassword").val();
					        }
					    }
					}
				},
				password : {
					required : true,
					minlength : 5
				},
				password_again : {
					required : true,
					minlength : 5,
					equalTo : "#password"
				}
			},
			messages : {
				oldpassword : {
					required : "请输入旧密码",
					minlength : "密码长度不能小于 5 个字母",
					remote : "旧密码验证不通过"
				},
				password : {
					required : "请输入新密码",
					minlength : "密码长度不能小于 5 个字母"
				},
				password_again : {
					required : "请输入确认密码",
					minlength : "密码长度不能小于 5 个字母",
					equalTo : "两次密码输入不一致"
				}
			}
		})
	});
</script>
<style type="text/css">
	.error{
		color:red
	}
</style>
</head>

<body>
	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li class="active"><a href="#">密码修改</a></li>
		</ul>
		<br />
		<form class="form-horizontal" id="submitForm" role="form" action="${adminPath}/user?method=fixPass" method="post">
			<input type="hidden" name="id" value="${login.id}">
			 <input type="hidden" name="role" value="${login.role}">
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">密码</label>
			 	<div class="col-sm-5">
			 			<input type="password" class="form-control" id="oldpassword" name="oldpassword" placeholder="请输入密码" >
		    	</div>
			 </div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">密码<b
					style="color: red;">*</b></label>
				<div class="col-sm-5">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="请输入密码" >
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">确认密码<b
					style="color: red;">*</b></label>
				<div class="col-sm-5">
					<input type="password" class="form-control" id="password_again"
						name="password_again" placeholder="请输入密码">
				</div>
			</div>
			  

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="保存">
			      	<input type="button" class="btn btn-default btn-sm" value="返回" onclick="history.go(-1)">
			      	<span style="color: green;">${msg }</span>
			    </div>
			 </div>
		</form>
	</div>
</body>
</html>