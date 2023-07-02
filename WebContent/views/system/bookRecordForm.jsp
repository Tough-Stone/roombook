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
								title: {
					required : true
				},
				roomNo: {
					required : true
				},
				bookTime: {
					required : true
				},
				timeQuantum: {
					required : true
				},
				useRemark: {
					required : true
				},
				mainUser: {
					required : true
				},
			},
			messages : {
				title: {
					required : "主题必填"
				},
				roomNo: {
					required : "会议室编号必填"
				},
				bookTime: {
					required : "起始时间必填"
				},
				timeQuantum: {
					required : "结束时间必填"
				},
				useRemark: {
					required : "使用说明必填"
				},

				mainUser: {
					required : "主讲人必填"
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
			<li><a href="${adminPath}/bookRecord?method=list">预约记录列表</a></li>
			<li class="active"><a href="${adminPath}/bookRecord?method=form&id=${bookRecord.id}">预约记录添加</a></li>
		</ul>
		<br />
		<c:if test="${msg!=null}">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-horizontal" id="submitForm" role="form" action="${adminPath}/bookRecord?method=save" method="post">
			<input type="hidden" name="id" value="${bookRecord.id}">
			 
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">主题<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="title" name="title" placeholder="请输入主题" value="${bookRecord.title}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">主讲人<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="mainUser" name="mainUser" placeholder="请输入主讲人" value="${bookRecord.mainUser}">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">会议室编号<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
			 		<select class="form-control" id="roomNo" name="roomNo">
			 			<option value="1" <c:if test="${bookRecord.roomNo=='1'}">selected</c:if> >会议室1</option>
			 			<option value="2" <c:if test="${bookRecord.roomNo=='2'}">selected</c:if>>会议室2</option>
			 			<option value="3" <c:if test="${bookRecord.roomNo=='3'}">selected</c:if>>会议室3</option>
			 		</select>
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">开始时间<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" readonly="readonly" class="form-control" id="bookTime" name="bookTime" placeholder="请输入预约日期" 
		 			value="<fmt:formatDate value="${bookRecord.bookTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
		 			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">结束时间<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
			 		<input type="text" readonly="readonly" class="form-control" id="timeQuantum" name="timeQuantum" placeholder="请输入结束时间"
			 		 value="<fmt:formatDate value="${bookRecord.timeQuantum}" pattern="yyyy-MM-dd HH:mm:ss"/>"
		 			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});">
		    	</div>
			 </div>
			 <div class="form-group">
			 	<label for="name" class="col-sm-3 control-label">使用说明<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<textarea rows="3" class="form-control" id="useRemark" name="useRemark" required>${bookRecord.useRemark}</textarea>
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">审核状态</label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="auditType" name="auditType" placeholder="请输入审核状态" value="${bookRecord.auditType}">
		    	</div>
			 </div>
			 <div class="form-group" style="display: none;">
			 	<label for="name" class="col-sm-3 control-label">预约用户<b style="color: red;">*</b></label>
			 	<div class="col-sm-5">
		 			<input type="text" class="form-control" id="bookUser" name="bookUser" placeholder="请输入预约用户" value="${bookRecord.bookUser}">
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