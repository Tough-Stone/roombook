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
</script>
<style type="text/css">
	body{
	  background:#fff url("${adminPath }/static/images/bg.jpg") no-repeat left top;
	  background-size:100%;
	}
</style>
</head>

<body style="background-color: #BCD2EE;">
	<div class="container" style="text-align: center; margin-top: 200px;">
		<div class="row" style="text-align: center;">
			<div class="col-sm-4"></div>
			<div class="col-sm-4" style="background-color: #FFFAF0;opacity: 1;">
				<br />
				<div class="row" style="text-align: center; font-size: 26px;">用户登录</div>
				<br />
				<form action="${adminPath }/user?method=login" method="post">
					账&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" value=""
						name="username" /><br />
					<br /> 密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" value=""
						name="password" /><br />
					<br /> <input type="submit" class="btn btn-sm btn-info" value="登录" />
				</form>
				<br />
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
<script type="text/javascript">
	window.onload = function (ev) {
        var exp = new Date();
        exp.setTime (exp.getTime() - 1);
        document.cookie = 'yuyue'+"=;expires="+(new Date(0)).toGMTString();
    }
</script>
</body>
</html>