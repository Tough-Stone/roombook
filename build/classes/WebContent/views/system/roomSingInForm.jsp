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
								studentId: {
					required : true
				},
				signTime: {
					required : true
				},
				roomBookId: {
					required : true
				},
			},
			messages : {
				studentId: {
					required : "学生编号必填"
				},
				signTime: {
					required : "签到时间必填"
				},
				roomBookId: {
					required : "会议ID必填"
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
			<li><a href="${adminPath}/roomSingIn?method=list">签到记录列表</a></li>
			<li class="active"><a href="${adminPath}/roomSingIn?method=form&id=${roomSingIn.id}">签到记录添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" id="submitForm" role="form" action="${adminPath}/roomSingIn?method=save" method="post">
			<input type="hidden" name="id" value="${roomSingIn.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">学生编号<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="studentId" name="studentId" placeholder="请输入学生编号" value="${roomSingIn.studentId}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">签到时间<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="signTime" name="signTime" placeholder="请输入签到时间" value="${roomSingIn.signTime}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">会议ID<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="roomBookId" name="roomBookId" placeholder="请输入会议ID" value="${roomSingIn.roomBookId}">
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