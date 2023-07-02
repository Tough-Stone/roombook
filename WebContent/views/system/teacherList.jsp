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
			<li class="active"><a href="${adminPath}/teacher?method=list">教师管理列表</a></li>
			<li><a href="${adminPath}/teacher?method=form">教师管理添加</a></li>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/teacher?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group">
				<label for="teacherNo">教师编号</label>
					<input type="text" class="form-control" name="teacherNo" id="teacherNo" placeholder="请输入教师编号" value="${teacherNo}">
				</div>
				<div class="form-group">
				<label for="teacherName">教师姓名</label>
					<input type="text" class="form-control" name="teacherName" id="teacherName" placeholder="请输入教师姓名" value="${teacherName}">
				</div>
			
			<button type="submit" class="btn btn-primary btn-sm">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				查询
			</button>
		</form>
		<br />
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>教师编号</th>
					<th>教师姓名</th>
					<th>教师职称</th>
					<th>性别</th>
					<th>联系电话</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="teacher" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath}/teacher?method=form&id=${teacher.id}">
							${teacher.teacherNo}
						</a></td>
						<td>
							${teacher.teacherName}
						</td>
						<td>
							${teacher.teaTitle}
						</td>
						<td>
							${teacher.sex}
						</td>
						<td>
							${teacher.tel}
						</td>
						
						
						<td>
							<a href="${adminPath }/teacher?method=form&id=${teacher.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
							</a> 
							<a href="${adminPath }/teacher?method=delete&id=${teacher.id}" onclick="return confirm('确认要删除吗？', this.href)">
								<button class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									删除
								</button>
							</a> 
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
		<%@ include file="/views/include/pageinfo.jsp"%>
	</div>
</body>
</html>