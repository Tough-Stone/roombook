<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<%@ include file="/views/include/head.jsp"%>
<%@ include file="/views/include/validation.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitForm").validate({
			rules : {
				studentNo: {
					required : true,
					remote: {
					    url: "${adminPath}/user?method=checkUserName",     //后台处理程序
					    type: "post",               //数据发送方式
					    dataType: "json",           //接受数据格式   
					    data: {                     //要传递的数据
					        username: function() {
					            return $("#studentNo").val();
					        }
					    }
					}
				},
				studentName: {
					required : true
				},
				sex: {
					required : true
				},
				collegeRoom: {
					required : true
				},
				major: {
					required : true
				},
				tel: {
					required : true
				},
			},
			messages : {
				studentNo: {
					required : "学号必填",
					remote:"学号已经存在"
				},
				studentName: {
					required : "学生姓名必填"
				},
				sex: {
					required : "性别必填"
				},
				collegeRoom: {
					required : "学院必填"
				},
				major: {
					required : "专业必填"
				},
				tel: {
					required : "联系电话必填"
				},
			}
		})
	});
</script>

<style type="text/css">
	.error{  color:red; }
</style>
</head>

<body>
	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li><a href="${adminPath}/student?method=list">学生管理列表</a></li>
			<li class="active"><a href="${adminPath}/student?method=form&id=${student.id}">学生管理添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" id="submitForm" role="form" action="${adminPath}/student?method=save" method="post">
			<input type="hidden" name="id" value="${student.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学号<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="studentNo" name="studentNo" placeholder="请输入学号" value="${student.studentNo}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学生姓名<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="studentName" name="studentName" placeholder="请输入学生姓名" value="${student.studentName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">性别<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="sex" name="sex" placeholder="请输入性别" value="${student.sex}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学院<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="collegeRoom" name="collegeRoom" placeholder="请输入学院" value="${student.collegeRoom}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">专业<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="major" name="major" placeholder="请输入专业" value="${student.major}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">邮箱</label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱" value="${student.email}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">联系电话<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="tel" name="tel" placeholder="请输入联系电话" value="${student.tel}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">简介</label>
			 	<div class="col-sm-5">
		 			<textarea rows="3" class="form-control" id="content" name="content" >${student.content}</textarea>
		    	</div>
			 </div>
			  

			 <div class="form-group">
			    <label for="sort" class="col-sm-3 control-label"></label>
			    <div class="col-sm-5">
			      	<input type="submit" class="btn btn-success btn-sm" value="保存">
			      	<input type="button" class="btn btn-default btn-sm" value="返回" onclick="history.go(-1)">
			    </div>
			 </div>
		</form>
	</div>
</body>
</html>