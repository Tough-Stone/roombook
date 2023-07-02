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
			<li class="active"><a href="${adminPath}/roomSingIn?method=mine">我的出席</a></li>
		</ul>
		<br />
		<c:if test="">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> ${msg}
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/roomSingIn?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
		</form>
		<br />
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>会议室</th>
					<th>会议主题</th>
					<th>会议时间</th>
					<th>签到时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="roomSingIn" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						<td>
							会议室${fns:getFieldNameById(roomSingIn.roomBookId,'room_no', 'book_record')}
						</td>
						<td>
							${fns:getFieldNameById(roomSingIn.roomBookId,'title', 'book_record')}
						</td>
						<td>
							${fns:getFieldNameById(roomSingIn.roomBookId,'book_time', 'book_record')}
						</td>
						<td>
							${roomSingIn.signTime}
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
		<%@ include file="/views/include/pageinfo.jsp"%>
	</div>
</body>
</html>