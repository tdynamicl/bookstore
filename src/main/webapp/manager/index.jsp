<%@page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>欢迎：${sessionScope.manageruser.nickname}</h1>
	<a href="addBook.jsp">添加书籍信息</a>
	<a href="viewBook.jsp">查看（修改、删除）书籍信息</a>
</body>
</html>