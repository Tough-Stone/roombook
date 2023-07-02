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
<style>
.top {
	width: 100%;
	height: 80px;
	background-color: #31B0D5;
}
.centen {
	text-align: center;
}
</style>
</head>

<body style="">
	<div class="row">
		<div class="col-sm-12 top">
			<div style="margin-top: 20px; margin-left: 20px;">
				<span style="font-size: 26px; color: white;">会议考勤系统</span>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-2">
			<div class="panel-group" id="accordion">
			<c:if test="${login!=null && login.role=='1' }">
			</c:if>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"> 个人中心 </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
						<ul>
							<li><a href="${adminPath}/user?method=userInfo" target="mainframe">个人信息</a></li>
							<li><a href="${adminPath}/user?method=userPassFix" target="mainframe">修改密码</a></li>
						</ul>
					</div>
				</div>
			</div>
			
				
			<c:if test="${login!=null && login.role=='1' }">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo"> 用户管理 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse in">
						<div class="panel-body">
							<ul>
								<li><a href="${adminPath}/user?method=list" target="mainframe">用户列表</a></li>
								<li><a href="${adminPath}/student?method=list" target="mainframe">学生列表</a></li>
								<li><a href="${adminPath}/teacher?method=list" target="mainframe">教师列表</a></li>
								<li><a href="${adminPath}/bookRecord?method=list" target="mainframe">会议室预约</a></li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
				<c:if test="${login!=null && login.role=='3' }">
					<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo"> 会议记录 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse in">
						<div class="panel-body">
							<ul>
								<li><a href="${adminPath}/bookRecord?method=list" target="mainframe">会议签到</a></li>
								<li><a href="${adminPath}/roomSingIn?method=mine" target="mainframe">我的出席</a></li>
							</ul>
						</div>
					</div>
				</div>
				</c:if>
				
				<c:if test="${login!=null && login.role=='2' }">
					<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo"> 会议记录 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse in">
						<div class="panel-body">
							<ul>
								<li><a href="${adminPath}/bookRecord?method=list" target="mainframe">会议室预约</a></li>
							</ul>
						</div>
					</div>
				</div>
				</c:if>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse3"> 访客信息 </a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse in">
						<div class="panel-body" style="color: gray;">
							<c:if test="${login!=null }">
								欢迎使用系统，${login.name }<a href="${adminPath}/user?method=logout">退出</a>
							</c:if>
							<c:if test="${login==null }">
								欢迎使用系统，您的身份为游客。<a href="${adminPath}/login.jsp">登录</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="col-sm-10">
			<!-- 16:9 aspect ratio -->
			<div class="embed-responsive embed-responsive-16by9">
				<iframe class="embed-responsive-item" src="${adminPath}/main.jsp" name="mainframe"></iframe>
			</div>
		</div>
	</div>
</body>
</html>