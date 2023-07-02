<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<%@ include file="/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	function page(n){
		$("#pageNo").val(n);
		$("#searchForm").submit();
       	return false;
       }
</script>
</head>

<body>
	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li><a href="${adminPath}/user?method=list">系统用户列表</a></li>
			<li class="active"><a href="${adminPath}/user?method=form&id=${user.id}">系统用户添加</a></li>
		</ul>
		<br />
		<form class="form-horizontal" role="form" action="${adminPath}/user?method=save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${user.id}">
			 <input type="hidden" name="role" value="${user.role}">
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">用户名</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" value="${user.username}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">密码</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="password" name="password" placeholder="请输入密码" value="${user.password}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">昵称</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="trueName" name="trueName" placeholder="请输入昵称" value="${user.name}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">性别</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="sex" name="sex" placeholder="请输入性别" value="${user.sex}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">联系方式</label>
			 	<div class="col-sm-5">
			 			<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入联系方式" value="${user.phone}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">个人简介</label>
			 	<div class="col-sm-5">
			 		<textarea rows="20" cols="" class="form-control"  id="content" name="content">${user.content}</textarea>
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