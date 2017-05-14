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
<title>浏览订单 | 书籍销售平台(管理端)</title>
<script src="<c:url value="/js/lib/jquery-3.1.1.min.js"/>"></script>
<script src="<c:url value="/js/lib/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/lib/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/lib/bootstrap-theme.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/common.css"/>" />
<style type="text/css">
a{
	margin:0;
	padding:0;
}
table td,th{
	max-width: 200px;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
}
.actionBox{
	display: inline-block;
}
#leftAction{
	float:left;
}
#rightAction{
	max-width:300px;
	float:right;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation" novalidate
		ng-cloak style="border-radius: 0;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="<c:url value="/manager/index.jsp"/>" class="navbar-brand">玄奘书籍销售平台(管理端)</a>
				<p class="navbar-text">→查看订单页面</p>
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
	<div id="wrapper">
		<span style="color: black;">${requestScope.indentTip}&nbsp;</span>
		<div class="actionBox" id="leftAction">
			<c:set var="r" value="${requestScope.indents}"></c:set>
			<form name="QueryForm" method="post" action="queryIndent.do">
				<input type="text" name="pageNo" value="${r.pageNo}" hidden="hidden">
				<input type="text" name="pageSize" value="${r.pageSize}" hidden="hidden">
				<div class="input-group">
					<span class="input-group-addon">书名</span>
					<input type="text" name="bookName" class="form-control" placeholder="请输入书名关键字" value="${r.bookName=='null'?'':r.bookName}">
					<span class="input-group-addon">用户名</span>
					<input type="text" name="userName" class="form-control" placeholder="请输入用户名关键字" value="${r.userName=='null'?'':r.userName}">
					<span class="input-group-addon">下单时间</span>
					<input type="date" name="generateTimeStr" class="form-control" value="${r.generateTime}">
					<span class="input-group-btn">
						<input id="pageSearch" class="btn btn-default" type="submit" value="查询">
						<input id="pageSearch" class="btn btn-default" type="button" value="查询未处理订单" onclick="QueryForm.action='queryS2Indent.do';QueryForm.submit();">
					</span>
				</div>
			</form>
		</div>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>订单号</th>
					<th>操作</th>
					<th>书名</th>
					<th>用户名</th>
					<th>下单时间</th>
					<th>订单状态</th>
				</tr>
			</thead>

			<c:if test="${r.notFound}">
				<tr>
					<td colspan="99">对不起,未找到相关数据!</td>
				</tr>
			</c:if>
			<c:if test="${!r.notFound }">
				<c:forEach var="s" items="${r.indents}" varStatus="vs">
					<tr>
						<td><input name="id" value="${s.id}" hidden="hidden">${s.id}</td>
						<td><a href="<c:url value="/manager/checkIndent.do?id=${s.id}"/>">查看</a>
						<td>${s.bookName}</td>
						<td>${s.userName}</td>
						<td>${s.generateTimeStr}</td>
						<c:if test="${s.status==0}">
							<td>已注销</td>
						</c:if>
						<c:if test="${s.status==1}">
							<td>等待支付</td>
						</c:if>
						<c:if test="${s.status==2}">
							<td>尚未发货</td>
						</c:if>
						<c:if test="${s.status==3}">
							<td>待签收</td>
						</c:if>
						<c:if test="${s.status==4}">
							<td>用户尚未评论</td>
						</c:if>
						<c:if test="${s.status==5}">
							<td>已评论，订单完成</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div>
			共${r.totalRows}条记录,每页最多${r.pageSize}条,共${r.totalPages}页
			<c:if test="${r.pageNo==1}">
				<a>首页</a>
				<a>上一页</a>
			</c:if>
			<c:if test="${r.pageNo>1}">
				<a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=1&pageSize=10"/>">首页</a>
				<a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=${r.pageNo-1}&pageSize=10"/>">上一页</a>
			</c:if>
			<c:if test="${r.pageNo>2}">
				<b> <a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=${r.pageNo-2}&pageSize=10"/>">${r.pageNo-2}</a>
				</b>
			</c:if>
			<c:if test="${r.pageNo>1}">
				<b> <a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=${r.pageNo-1}&pageSize=10"/>">${r.pageNo-1}</a>
				</b>
			</c:if>
			<b>${r.pageNo}</b>
			<c:if test="${r.pageNo+1<=r.totalPages}">
				<b> <a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=${r.pageNo+1}&pageSize=10"/>">${r.pageNo+1}</a>
				</b>
			</c:if>
			<c:if test="${r.pageNo+2<=r.totalPages}">
				<b> <a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=${r.pageNo+2}&pageSize=10"/>">${r.pageNo+2}</a>
				</b>
			</c:if>
			<c:if test="${r.pageNo==r.totalPages}">
				<a>下一页</a>
				<a>末页</a>
			</c:if>
			<c:if test="${r.pageNo<r.totalPages}">
				<a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=${r.pageNo+1}&pageSize=10"/>">下一页</a>
				<a
					href="<c:url value="/manager/queryIndent.do?bookName=${r.bookName}&userName=${r.userName}&generateTimeStr=${r.generateTimeStr}&pageNo=${r.totalPages}&pageSize=10"/>">末页</a>

			</c:if>
		</div>
	</div>
</body>
</html>