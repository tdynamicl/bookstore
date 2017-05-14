<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
if(request.getSession().getAttribute("manageruser")==null){
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页 | 书籍销售平台(管理端)</title>
<script src="../js/lib/jquery-3.1.1.min.js"></script>
<script src="../js/lib/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/lib/bootstrap.min.css" />
<link rel="stylesheet" href="../css/lib/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/common.css" />
<style>
#wrapper {
	margin-left: auto;
	margin-right: auto;
	margin-top: 50px;
	width: 30em;
	text-align: center;
}
button{
	margin-left:50px;
	margin-right: 50px;
}
#hintMessage{
	text-align:left; 
}
</style>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation" novalidate
		ng-cloak style="border-radius: 0;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="index.jsp" class="navbar-brand">玄奘书籍销售平台(管理端)</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/manager/queryBook.do?pageNo=1&pageSize=5"/>"><span class="glyphicon glyphicon-book"></span>
						管理书籍</a></li>
				<li><a href="<c:url value="/manager/queryIndent.do?pageNo=1&pageSize=10"/>"><span class="glyphicon glyphicon-envelope"></span>
						处理订单</a></li>
				<li><a href="loginOut.do"><span class="glyphicon glyphicon-log-in"></span>
						注销</a></li>
			</ul>
		</div>
	</nav>
	<div id="wrapper">
		<c:set var="h" value="${sessionScope.hint}"></c:set>
		<h1>Welcome：${sessionScope.manageruser.nickname}</h1>
		<br><span style="color: red;">${requestScope.errtip}&nbsp;<br></span><br>
		<div class="panel panel-default" id="hintMessage">
			<div class="panel-heading">
				<h3 class="panel-title">管理端信息</h3>
			</div>
			<div class="panel-body">
				<c:if test="${h.sTwo>0}">
					现在有 ${h.sTwo} 份订单尚未发货
				</c:if>
				<c:if test="${h.sTwo==0}">
					现在系统中没有未发货订单
				</c:if>
			</div>
			<div class="panel-body">
				系统现有 ${h.allBookNumber} 本书库存
			</div>
			<table class="table">
				<tr><th>分类</th><th>数量 </th></tr>
				<tr><td>教育类</td><td>${h.eduBookNumber} 本</td></tr>
				<tr><td>小说类</td><td>${h.novelBookNumber} 本</td></tr>
				<tr><td>文艺类</td><td>${h.artBookNumber} 本</td></tr>
				<tr><td>儿童类</td><td>${h.childBookNumber} 本</td></tr>
				<tr><td>生活类</td><td>${h.liveBookNumber} 本</td></tr>
				<tr><td>科技类</td><td>${h.scienceBookNumber} 本</td></tr>
			</table>
		</div>
		<!--
		<a href="indent/viewIndent.jsp"><button type="button" class="btn btn-primary">订单管理</button></a>
		<a href="<c:url value="/manager/queryBook.do?pageNo=1&pageSize=5"/>"><button type="button" class="btn btn-primary">书籍信息</button></a>
		-->
	</div>
</body>
</html>