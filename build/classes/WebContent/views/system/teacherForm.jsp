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
								teacherNo: {
					required : true
				},
				teacherName: {
					required : true
				},
				teaTitle: {
					required : true
				},
				sex: {
					required : true
				},
				tel: {
					required : true
				},
			},
			messages : {
				teacherNo: {
					required : "教师编号必填"
				},
				teacherName: {
					required : "教师姓名必填"
				},
				teaTitle: {
					required : "教师职称必填"
				},
				sex: {
					required : "性别必填"
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
			<li><a href="${adminPath}/teacher?method=list">教师管理列表</a></li>
			<li class="active"><a href="${adminPath}/teacher?method=form&id=${teacher.id}">教师管理添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" id="submitForm" role="form" action="${adminPath}/teacher?method=save" method="post">
			<input type="hidden" name="id" value="${teacher.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">教师编号<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="teacherNo" name="teacherNo" placeholder="请输入教师编号" value="${teacher.teacherNo}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">教师姓名<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="teacherName" name="teacherName" placeholder="请输入教师姓名" value="${teacher.teacherName}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">教师职称<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="teaTitle" name="teaTitle" placeholder="请输入教师职称" value="${teacher.teaTitle}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">性别<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="sex" name="sex" placeholder="请输入性别" value="${teacher.sex}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">联系电话<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="tel" name="tel" placeholder="请输入联系电话" value="${teacher.tel}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">简介</label>
			 	<div class="col-sm-5">
		 			<textarea rows="3" class="form-control" id="content" name="content" >${teacher.content}</textarea>
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