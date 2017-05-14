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
<title>添加书籍 | 书籍销售平台(管理端)</title>
<script src="../js/lib/jquery-3.1.1.min.js"></script>
<script src="../js/lib/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/lib/bootstrap.min.css" />
<link rel="stylesheet" href="../css/lib/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/common.css" />
<style type="text/css">
body{
	background: url(../img/manager/bg.jpg);
	font-family: "微软雅黑";
}
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
				<a href="index.jsp" class="navbar-brand">玄奘书籍销售平台(管理端)</a>
				<a href="<c:url value="/manager/queryBook.do?pageNo=1&pageSize=5"/>" class="navbar-brand">→查看书籍页面</a>
				<p class="navbar-text">→新增书籍</p>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="<c:url value="/manager/queryBook.do?pageNo=1&pageSize=5"/>"><span
						class="glyphicon glyphicon-book"></span> 管理书籍</a></li>
				<li><a href="indent/viewIndent.jsp"><span
						class="glyphicon glyphicon-envelope"></span> 处理订单</a></li>
				<li><a href="login.jsp"><span
						class="glyphicon glyphicon-log-in"></span> 注销</a></li>
			</ul>
		</div>
	</nav>
	<br>
	<div class="wrapper">
		<form action="addBook.do" method="POST">
		${requetScope.errtip}
		<div class="row">
			<div class="col-md-4">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<img id="cover" alt="书籍封面" height="260px" width="185px">
						<p>
							<input name="Cover" type="file" class="file" id="img" >
							<input id="bookCover" name="bookCover" type="text" hidden="hidden">
							<label class="filebtn" for="img" title="JPG,GIF,PNG">请选择图片</label>
						</p>
					</div>
					<div class="col-md-4"></div>
				</div>
			</div>
			<div class="col-md-8">
					<div class="row">
					<div class="col-md-5">
					<ul>
					<li><div class="input-group">
							<span class="input-group-addon">书名</span>
							<input name="name" type="text" class="form-control" placeholder="请输入书名" required>
						</div>
					</li>
					
					<li><div class="input-group">
							<span class="input-group-addon">作者</span>
							<input name="author" type="text" class="form-control" placeholder="请输入作者名" required>
						</div>
					</li>
					
					<li><div class="input-group">
							<span class="input-group-addon">出版社</span>
							<input name="press" type="text" class="form-control" placeholder="请输入出版社名" required>
						</div>
					</li>
					
					<li><div class="input-group">
							<span class="input-group-addon">出版日期</span>
							<input name="pressTimeString" type="date" class="form-control" required>
						</div>
					</li>
					
					<li><div class="input-group">
							<span class="input-group-addon">定价</span>
							<input name="price" type="number" class="form-control" placeholder="请输入价格（分）" required>
						</div>
					</li>
					</ul>
					</div>
					<div class="col-md-7">
					<div class="checkbox">
					<ul>
					<li>所属类型：</li>
					<li>教育：
						<label><input name="keyword" type="checkbox" value="1">教程</label> &nbsp;
						<label><input name="keyword" type="checkbox" value="2">教材</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="3">考试</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="4">工具书</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="5">中小学辅导</label>
					</li>
					<li>小说：
						<label><input name="keyword" type="checkbox" value="6">古典小说</label>  &nbsp;
						<label><input name="keyword" type="checkbox" value="7">现代小说</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="8">网络小说</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="9">外国小说</label>
					</li>
					<li>文艺：
						<label><input name="keyword" type="checkbox" value="10">文学</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="11">传记</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="12">艺术</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="13">摄影</label>
					</li>
					<li>儿童：
						<label><input name="keyword" type="checkbox" value="14">科普</label>  &nbsp;
						<label><input name="keyword" type="checkbox" value="15">幼儿启蒙</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="16">益智</label>
					</li>
					<li>生活：
						<label><input name="keyword" type="checkbox" value="17">美食</label>  &nbsp;
						<label><input name="keyword" type="checkbox" value="18">旅游</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="19">休闲</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="20">美妆</label>
					</li>
					<li>科技：
						<label><input name="keyword" type="checkbox" value="21">建筑</label>
						<label><input name="keyword" type="checkbox" value="22">医学</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="23">计算机</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="24">农林</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="25">自然</label>&nbsp;
						<label><input name="keyword" type="checkbox" value="26">工业</label>
					</li>
					</ul>
					</div>
					</div>
					</div>
					<ul>
					<li><label>描述：<textarea class="form-control" name="description" rows="3" cols="80px" placeholder="请输入对书籍的描述"></textarea></label></li>
					</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="btn-group">
					<button type="submit" class="btn btn-default">确认添加</button>
					<button type="button" class="btn btn-default" onclick="window.location='<c:url value="/manager/queryBook.do?pageNo=1&pageSize=5"/>'">返回</button>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</form>
	</div>
	<script type="text/javascript">
		window.onload = function() {
			// 选择图片  
			document.getElementById('img').onchange = function() {
				var img = event.target.files[0];
				// 判断是否图片  
				if (!img) {
					return;
				}
				// 判断图片格式  
				if (!(img.type.indexOf('image') == 0 && img.type && /\.(?:jpg|png|gif)$/
						.test(img.name))) {
					alert('图片只能是jpg,gif,png');
					return;
				}
				var reader = new FileReader();
				reader.readAsDataURL(img);
				reader.onload = function(e) { // reader onload start
					$("#cover").attr("src",e.target.result);
					$("#bookCover").val(e.target.result);
					// ajax 上传图片  
					//$.post("addBookCover.do", {
						//bookCover : e.target.result,
						//bookId : "${r.id}"
					//}, function(status) {
						//window.location.reload();
					//}, 'json');
				};// reader onload end  
			};
		};
	</script>
</body>
</html>