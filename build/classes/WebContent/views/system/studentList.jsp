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
			<li class="active"><a href="${adminPath}/student?method=list">学生管理列表</a></li>
			<li><a href="${adminPath}/student?method=form">学生管理添加</a></li>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/student?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group">
				<label for="studentNo">学号</label>
					<input type="text" class="form-control" name="studentNo" id="studentNo" placeholder="请输入学号" value="${studentNo}">
				</div>
				<div class="form-group">
				<label for="studentName">学生姓名</label>
					<input type="text" class="form-control" name="studentName" id="studentName" placeholder="请输入学生姓名" value="${studentName}">
				</div>
				<div class="form-group">
				<label for="sex">性别</label>
					<input type="text" class="form-control" name="sex" id="sex" placeholder="请输入性别" value="${sex}">
				</div>
				<div class="form-group">
				<label for="collegeRoom">学院</label>
					<input type="text" class="form-control" name="collegeRoom" id="collegeRoom" placeholder="请输入学院" value="${collegeRoom}">
				</div>
				<div class="form-group">
				<label for="major">专业</label>
					<input type="text" class="form-control" name="major" id="major" placeholder="请输入专业" value="${major}">
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
					<th>学号</th>
					<th>学生姓名</th>
					<th>性别</th>
					<th>学院</th>
					<th>专业</th>
					<th>邮箱</th>
					<th>联系电话</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="student" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td><a href="${adminPath}/student?method=form&id=${student.id}">
							${student.studentNo}
						</a></td>
						<td>
							${student.studentName}
						</td>
						<td>
							${student.sex}
						</td>
						<td>
							${student.collegeRoom}
						</td>
						<td>
							${student.major}
						</td>
						<td>
							${student.email}
						</td>
						<td>
							${student.tel}
						</td>
						
						
						<td>
							<a href="${adminPath }/student?method=form&id=${student.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
							</a> 
							<a href="${adminPath }/student?method=delete&id=${student.id}" onclick="return confirm('确认要删除吗？', this.href)">
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