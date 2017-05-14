<%@page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录 | 书籍销售平台(管理端)</title>
<script src="../js/lib/jquery-3.1.1.min.js"></script>
<script src="../js/lib/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/lib/bootstrap.min.css" />
<link rel="stylesheet" href="../css/lib/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/common.css" />
</head>
<style>
#wrapper {
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	width: 20em;
}
</style>
<body>
	<nav class="navbar navbar-default"
		role="navigation" novalidate ng-cloak style="border-radius: 0;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">玄奘书籍销售平台(管理端)</a>
			</div>
		</div>
	</nav>
	<div id="wrapper">
		<form action="login.do" method="post">
			<h2 class="form-signin-header">管理员登录</h2>
				<span style="color: red;">${requestScope.errtip}&nbsp;<br></span> 
			<span style="color:red">
				<span>
					<span hidden="hidden">请填写用户名</span>&nbsp;
				</span>&nbsp;
	 		</span>
			<div class="input-group input-group-md">
				<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span> 
				<input  name="account" class="form-control" placeholder="用户名" required autofocus>
			</div>
	 		<span style="color:red">
				<span>
					<span hidden="hidden">请填写密码</span>&nbsp;
				</span>&nbsp;
	 		</span>
			<div class="input-group input-group-md">
				<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span> 
				<input name="password" type="password" class="form-control" placeholder="密码" required>
			</div>
			<br>
			<button class="btn btn-md btn-primary btn-block" type="submit">登录</button>
		</form>
	</div>
</body>
</html>
