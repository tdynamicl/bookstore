<%@page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
if(request.getSession().getAttribute("manageruser")==null){
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单详情 | 书籍销售平台(管理端)</title>
<script src="../js/lib/jquery-3.1.1.min.js"></script>
<script src="../js/lib/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/lib/bootstrap.min.css" />
<link rel="stylesheet" href="../css/lib/bootstrap-theme.min.css" />
<link rel="stylesheet" href="../css/common.css" />
<style type="text/css">
li {
	list-style: none;
}

input #id {
	display: none;
}

#wrapper {
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	width: 20em;
}
#lname{
	display: inline-block;
	width: 70px;
}
textarea{
	vertical-align: top;
}
</style>

</head>
<body>
	<nav class="navbar navbar-default" style="border-radius: 0;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="<c:url value="/manager/index.jsp"/>" class="navbar-brand">玄奘书籍销售平台(管理端)</a>
				<a href="<c:url value="/manager/queryIndent.do?pageNo=1&pageSize=10"/>" class="navbar-brand">→查看订单页面</a>
				<p class="navbar-text">→处理订单</p>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/manager/queryBook.do?pageNo=1&pageSize=5"/>"><span class="glyphicon glyphicon-book"></span>
						管理书籍</a></li>
				<li><a href="<c:url value="/manager/queryIndent.do?pageNo=1&pageSize=10"/>"><span class="glyphicon glyphicon-envelope"></span>
						处理订单</a></li>
				<li><a href="<c:url value="/manager/login.jsp"/>"><span class="glyphicon glyphicon-log-in"></span>
						注销</a></li>
			</ul>
		</div>
	</nav>
	<br>
	<div class="wrapper">
		<c:set var="r" value="${requestScope.indent}"></c:set>
		<div class="row">
			<div class="col-md-2">
			</div>
			<div class="col-md-8">
				<ul class="list-group">
					<li class="list-group-item">订单号：${r.id}</li>
					<li class="list-group-item">书籍名：${r.bookName}</li>
					<li class="list-group-item">用户名：${r.userName}</li>
					<li class="list-group-item">收件人姓名：${r.receiverName}</li>
					<li class="list-group-item">收件人电话：${r.receiverTel}</li>
					<li class="list-group-item">收件人地址：${r.address}</li>
					<li class="list-group-item">评论分数：${r.commentLevel}</li>
					<li class="list-group-item">评论内容：${r.commentContent}</li>
					<li class="list-group-item">评论时间：${r.commentTimeStr}</li>
					<li class="list-group-item">下单时间：${r.generateTimeStr}</li>
					<li class="list-group-item">
					<c:if test="${r.status==0}">订单状态：已注销</c:if>
					<c:if test="${r.status==1}">订单状态：等待用户支付</c:if>
					<c:if test="${r.status==2}">订单状态：已支付，尚未发货</c:if>
					<c:if test="${r.status==3}">订单状态：已发货未签收</c:if>
					<c:if test="${r.status==4}">订单状态：客户已签收，等待评论</c:if>
					<c:if test="${r.status==5}">订单状态：客户已评论，订单已完成</c:if>
					</li>
				</ul>
				<div class="btn-group">
					<c:if test="${r.status!=0}">
						<button type="button" class="btn btn-danger" onclick="javascript:deleteIndent('${r.id}')">注销订单</button>
					</c:if>
					<c:if test="${r.status==2}">
						<button type="button" class="btn btn-primary" onclick="javascript:dealIndent('${r.id}')">确认发货</button>
					</c:if>
					<button type="button" class="btn btn-default" onclick="window.location='<c:url value="/manager/queryIndent.do?pageNo=1&pageSize=10"/>'">返回</button>
				</div>
			</div>
			<div class="col-md-2">
			</div>
		</div>
	</div>
	<script>
	var deleteIndent = function(id) {
		if (confirm("确认注销该用户的订单么?")) {
			var url = "changeIndent.do?id=" + id+"&status="+0;
			var xml = new XMLHttpRequest();
			xml.open("GET", url, false);
			xml.onreadystatechange = handleStateChange;
			xml.send();
			function handleStateChange() {
				if (xml.readyState === 4) {
					window.location.reload();
				}
			}
		}
	};
	var dealIndent = function(id) {
		if (confirm("确认发货么?")) {
			var url = "changeIndent.do?id=" + id+"&status="+3;
			var xml = new XMLHttpRequest();
			xml.open("GET", url, false);
			xml.onreadystatechange = handleStateChange;
			xml.send();
			function handleStateChange() {
				if (xml.readyState === 4) {
					window.location.reload();
				}
			}
		}
	};
	</script>
</body>
</html>