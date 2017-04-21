<%@page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<form action="login.do" method="post">
			${requestScope.errtip}
			<div>
			<input name="account">
			</div>
			<div>
			<input name="password" type="password">
			</div>			
			<button>登录</button> 
		</form>
	
	</body>
</html>
