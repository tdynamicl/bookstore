<%@page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>查看书籍页面</h1>
	${requestScope.addBookTip}
	<form action="deletexxx.do">
		
		<tr>
			<input name="checked" type="checkbox" value="书1的uuid">
			<td>书名1</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<input name="checked" type="checkbox" value="书2的uuid">
			<td>书名2</td>
			<td></td>
			<td></td>
		</tr>
		<button type="submit">提交</button>
	</form>
</body>
</html>