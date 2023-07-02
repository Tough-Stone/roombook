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
	
	var maxtime = 5; //一个小时，按秒计算，自己调整!   
    function CountDown() {
        if (maxtime >= 0) {
            minutes = Math.floor(maxtime / 60);
            seconds = Math.floor(maxtime % 60);
            msg = '<a href="${adminPath}/login.jsp">点击跳转到登录界面</a>'+seconds + "秒后将自动跳转";
            document.all["timer"].innerHTML = msg;
                --maxtime;
        } else{
            clearInterval(timer);
            window.location.href="${adminPath}/login.jsp";
        }
    }
</script>

<c:if test="${login==null }">
	<script type="text/javascript">
	timer = setInterval("CountDown()", 1000);
	</script>
</c:if>
</head>

<body ><!-- style="background-color: #BCD2EE;" -->
	<div class="container" style="text-align: center; margin-top: 200px;">
		<div class="row" style="text-align: center;">
			<h2>欢迎使用系统</h2>
			<c:if test="${login==null }">
			<div id="timer" style="color:red"></div>
			<div id="warring" style="color:red"></div>
			</c:if>
		</div>
	</div>
</body>
</html>