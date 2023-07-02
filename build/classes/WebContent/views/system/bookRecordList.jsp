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
    </tbody>
</head>

<body  scroll="no" onbeforeunload="return CloseEvent();" οnunlοad="UnLoadEvent()" >
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${adminPath}/bookRecord?method=list">预约记录列表</a></li>
			<c:if test="${login.role!='3' }">
			<li><a href="${adminPath}/bookRecord?method=form">预约记录添加</a></li>
			</c:if>
		</ul>
		<br />
		<c:if test="${msg!=null}">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>提示!</strong> <c:if test="${msg=='1'}">签到成功！</c:if>
			  <c:if test="${msg=='2'}">签到失败！原因：您已签到过！</c:if>
			</div>
		</c:if>
		<form class="form-inline" id="searchForm" action="${adminPath}/bookRecord?method=list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }">
			
				<div class="form-group">
				<label for="title">主题</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="请输入主题" value="${title}">
				</div>
				<div class="form-group">
				<label for="roomNo">会议室编号</label>
					<select class="form-control" id="roomNo" name="roomNo">
						<option value="">-请选择-</option>
			 			<option value="1" <c:if test="${roomNo=='1'}">selected</c:if> >会议室1</option>
			 			<option value="2" <c:if test="${roomNo=='2'}">selected</c:if>>会议室2</option>
			 			<option value="3" <c:if test="${roomNo=='3'}">selected</c:if>>会议室3</option>
			 		</select>
				</div>
				
			
			<button type="submit" class="btn btn-primary btn-sm">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				查询
			</button>
            <div id="shuka" class="form-group;" style="margin-left: 5px;">
                <label for="title">刷卡卡号</label><input class="form-control" id="input">
                <span id="xuanze">请选择会议</span>
            </div>
		</form>
		<br />
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>主题</th>
					<th>主讲人</th>
					<th>会议室编号</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>使用说明</th>
					<th>预约状态</th>
					<th>预约人</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="bookRecord" varStatus="num">
					<tr>
						<td>${num.index+1+page.pageSize*(page.pageNo-1) }</td>
						
						<td>
							${bookRecord.title}
						</td>
						<td>
							${bookRecord.mainUser}
						</td>
						<td>
							会议室${bookRecord.roomNo}
						</td>
						<td>
							${bookRecord.bookTime}
						</td>
						<td>
							${bookRecord.timeQuantum}
						</td>
						<td>
							${bookRecord.useRemark}
						</td>
						<td>
							<c:if test="${bookRecord.auditType=='1'}">预约成功</c:if>
							<c:if test="${bookRecord.auditType=='2'}">已过期</c:if>
							<c:if test="${bookRecord.auditType=='3'}">已撤销</c:if>
						</td>
						<td>
							${fns:getFieldNameById(bookRecord.bookUser,'name', 'db_user')}
						</td>
						<td>
							<c:if test="${bookRecord.auditType!='3' }">
							<c:if test="${login.role=='3' && bookRecord.isOpen == '1' && bookRecord.auditType == '1' }">
							<a href="${adminPath }/roomSingIn?method=sign&id=${bookRecord.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									签到
								</button>
							</a> 
							</c:if>
							<c:if test="${login.role!='3' }">
							<a href="${adminPath }/roomSingIn?method=list&roomBookId=${bookRecord.id}">
								<button class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									签到记录
								</button>
							</a>
                            <c:if test="${bookRecord.auditType=='1'}">
                            <a href="javascript: void(0)" onclick="qiandao('${bookRecord.id}','${bookRecord.title}','${bookRecord.mainUser}')">
                                <button class="btn btn-primary btn-xs">
                                    <span class="glyphicon glyphicon-pencil"  ></span>
                                    刷卡签到
                                </button>
                            </a>
                            </c:if>
							<c:if test="${bookRecord.auditType=='1' }">
							<a href="${adminPath }/bookRecord?method=delete&id=${bookRecord.id}" onclick="return confirm('确认要撤销吗？', this.href)">
								<button class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									撤销
								</button>
							</a>
							</c:if>
							</c:if>
							</c:if>
                        </td>
					</tr>
				</c:forEach>
		</table>
		<%@ include file="/views/include/pageinfo.jsp"%>
	</div>
    <script>
        window.onload = function (ev) {
            document.getElementById("input").focus();
            // var title = localStorage.getItem('title');
            var title=document.cookie.split(",")[1];
            if(title){
                $("#xuanze").text(title);
            }
        }
        function qiandao(e,t,u) {
            // var txt = document.getElementById("shuka")
            // txt.style.display="block";
            //选中输入框
            $("#input").focus();
            //修改提示内容
            $("#xuanze").text(t+'-'+u)
            document.cookie = 'yuyue='+e+','+t+'-'+u;
            // alert(document.cookie)
        }
        //回车事件
        $('#input').bind('keydown',function(event){
            if (event.keyCode == "13") {
                //回车执行查询
                var v = document.getElementById("input").value;
                var len = document.getElementById("input").value.length;
                var cookie
                var arr,reg=new RegExp("(^| )"+'yuyue'+"=([^;]*)(;|$)"); //正则匹配
                if(arr=document.cookie.match(reg)){
                    cookie =  unescape(arr[2]);
                }
                //判断是否选中预约
                if(!cookie){
                    alert("请选择预约！")
                }else {
                    var recordId =  cookie.split(",")[0];
                    if (v) {
                        $.ajax({
                            type:'post',
                            url : '${adminPath }/roomSingIn?method=insert&id='+input.value+'&recordId='+recordId,
                            dataType:'json',
                            success:function(data){

                            },
                        });
                    }
                }
            }
        });
        <%--document.onkeydown = function(e){--%>
            <%--if (e.keyCode == "13") {--%>
                <%--//回车执行查询--%>
                <%--var v = document.getElementById("input").value;--%>
                <%--var len = document.getElementById("input").value.length;--%>
                <%--var cookie--%>
                <%--var arr,reg=new RegExp("(^| )"+'yuyue'+"=([^;]*)(;|$)"); //正则匹配--%>
                <%--if(arr=document.cookie.match(reg)){--%>
                    <%--cookie =  unescape(arr[2]);--%>
                <%--}--%>
                <%--//判断是否选中预约--%>
                <%--if(!cookie){--%>
                    <%--alert("请选择预约！")--%>
                <%--}else {--%>
                    <%--var recordId =  cookie.split(",")[0];--%>
                    <%--if (len >=10 & v) {--%>
                        <%--$.ajax({--%>
                            <%--type:'post',--%>
                            <%--url : '${adminPath }/roomSingIn?method=insert&id='+input.value+'&recordId='+recordId,--%>
                            <%--dataType:'json',--%>
                            <%--success:function(data){--%>

                            <%--},--%>
                        <%--});--%>
                    <%--}--%>
                <%--}--%>
            <%--}--%>
        <%--}--%>
    </script>
</body>
</html>