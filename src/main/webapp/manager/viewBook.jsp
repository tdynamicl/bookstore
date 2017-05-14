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
<title>浏览书籍 | 书籍销售平台(管理端)</title>
<script src="../js/lib/jquery-3.1.1.min.js"></script>
<script src="../js/lib/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/lib/bootstrap.min.css" />
<link rel="stylesheet" href="../css/lib/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/common.css" />
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
				<a href="index.jsp" class="navbar-brand">玄奘书籍销售平台(管理端)</a>
				<p class="navbar-text">→查看书籍页面</p>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/manager/queryBook.do?pageNo=1&pageSize=5"/>"><span class="glyphicon glyphicon-book"></span>
						管理书籍</a></li>
				<li><a href="<c:url value="/manager/queryIndent.do?pageNo=1&pageSize=10"/>"><span class="glyphicon glyphicon-envelope"></span>
						处理订单</a></li>
				<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span>
						注销</a></li>
			</ul>
		</div>
	</nav>
	<br>
	<div id="wrapper">
		<span style="color: black;">${requestScope.addBookTip}&nbsp;</span>
		<div class="actionBox" id="leftAction">
			<div class="btn-group">
				<button type="button" id="exBookList" class="btn btn-default" data-toggle="modal" data-target="#myModal">推荐书籍</button>
				<button type="button" class="btn btn-default" onclick="window.location='addBook.jsp'">新增书籍</button>
				<button type="button" class="btn btn-default" onclick="deleteBatch()">批量删除</button>
			</div>
		</div>
		<div class="actionBox" id="rightAction">
			<c:set var="r" value="${requestScope.books}"></c:set>
			<form name="QueryForm" method="post" action="queryBooks.do">
				<input type="text" name="pageNo" value="${r.pageNo}" hidden="hidden">
				<input type="text" name="pageSize" value="${r.pageSize}" hidden="hidden">
					<div class="input-group">
						<input type="text" name="name" class="form-control" placeholder="请输入书名关键字" value="${r.name=='null'?'':r.name}">
						<span class="input-group-btn">
						<input id="pageSearch" class="btn btn-default" type="submit" value="查询">
						</span>
					</div>
			</form>
		</div>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							推荐书籍名单
						</h4>
					</div>
					<div class="modal-body" style="width: 600px;">
						<table class="table table-hover">
							<thead>
								<tr>
									<th></th>
									<th>书名</th>
									<th>推荐图</th>
									<th>操作</th>
								</tr>
								<c:if test="${r.exNotFound}">
									<tr>
										<td colspan="99">对不起,未找到相关数据!</td>
									</tr>
								</c:if>
								<c:if test="${!r.exNotFound}">
									<c:forEach var="s" items="${r.exBooks}" varStatus="vs">
										<tr>
											<td></td>
											<td>${s.name}</td>
											<td><img id="${s.id}" alt="书籍封面" src="${s.image}" height="60px" width="160px"></td>
											<td>
												<div class="btn-group">
													<span style="display: none;">
														<input name="Cover" type="file" class="file" id="img${vs.count}" onchange="javascript:updateExBookCover('${s.id}')">
													</span>
													<span>
														<button type="button" class="btn btn-info btn-sm" style="font-size: 5px" onclick="javascript:$('#img${vs.count}').click();">
															<span class="glyphicon glyphicon-picture">
															</span> 
														</button>
														<button type="button" class="btn btn-danger btn-sm" style="font-size: 5px" onclick="javascript:deleteExBook('${s.id}')">
															<span class="glyphicon glyphicon-remove"></span> 
														</button>
													</span>
												</div>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</thead>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<span style="display: none;">
							<button type="button" class="btn btn-primary">
								提交更改
							</button>
						</span>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th id="allCheck"><input onclick="checkAll(this)" type="checkbox">全选</th>
					<th>操作</th>
					<th>书名</th>
					<th>描述</th>
					<th>作者</th>
					<th>出版社</th>
					<th>出版时间</th>
					<th>价格</th>
					<th>上架时间</th>
				</tr>
			</thead>

			<c:if test="${r.notFound}">
				<tr>
					<td colspan="99">对不起,未找到相关数据!</td>
				</tr>
			</c:if>
			<c:if test="${!r.notFound }">
				<c:forEach var="s" items="${r.books}" varStatus="vs">
					<tr>
						<td><input name="id" value="${s.id}" type="checkbox">${vs.count}</td>
						<td><a href="<c:url value="checkBook.do?id=${s.id}"/>">查看/修改</a>
							<a href="" onclick="javascript:pyBook('${s.id}')" style="color: brown;">书籍推荐</a>
							<a id="deleteStudent" href="javascript:deleteStudent('${s.id}')" style="color: red">删除</a></td>
						<td>${s.name}</td>
						<td>${s.description}</td>
						<td>${s.author}</td>
						<td>${s.press}</td>
						<td>${s.pressTimeString}</td>
						<td>${s.priceStr}</td>
						<td>${s.addTimeString}</td>
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
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=1&pageSize=5"/>">首页</a>
				<a
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=${r.pageNo-1}&pageSize=5"/>">上一页</a>
			</c:if>
			<c:if test="${r.pageNo>2}">
				<b> <a
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=${r.pageNo-2}&pageSize=5"/>">${r.pageNo-2}</a>
				</b>
			</c:if>
			<c:if test="${r.pageNo>1}">
				<b> <a
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=${r.pageNo-1}&pageSize=5"/>">${r.pageNo-1}</a>
				</b>
			</c:if>
			<b>${r.pageNo}</b>
			<c:if test="${r.pageNo+1<=r.totalPages}">
				<b> <a
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=${r.pageNo+1}&pageSize=5"/>">${r.pageNo+1}</a>
				</b>
			</c:if>
			<c:if test="${r.pageNo+2<=r.totalPages}">
				<b> <a
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=${r.pageNo+2}&pageSize=5"/>">${r.pageNo+2}</a>
				</b>
			</c:if>
			<c:if test="${r.pageNo==r.totalPages}">
				<a>下一页</a>
				<a>末页</a>
			</c:if>
			<c:if test="${r.pageNo<r.totalPages}">
				<a
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=${r.pageNo+1}&pageSize=5"/>">下一页</a>
				<a
					href="<c:url value="queryBook.do?name=${r.name}&pageNo=${r.totalPages}&pageSize=5"/>">末页</a>

			</c:if>
		</div>
	</div>


	<script type="text/javascript">
		var gotoPage = function(no) {
			var qryForm = document.getElementById("QueryForm");
			qryForm.pageNo.value = String(no);
			qryForm.submit();
		};
		var deleteExBook = function(id) {
			if (confirm("确认删除?")) {
				var url = "delExBook.do?id=" + id;
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

		var deleteStudent = function(id) {
			if (confirm("确认删除?")) {
				var url = "delBook.do?strIds=" + id;
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

		var checkAll = function(ck) {
			//获取所有的checkbox
			var checkboxes = document.getElementsByName("id");
			for (var i = checkboxes.length; i-->0;) {
				checkboxes[i].checked = ck.checked;
			}
		};
		var deleteBatch = function() {
			if (confirm("确认删除么？")) {
				var ids = [];
				var checkboxes = document.getElementsByName("id");
				for (var i = checkboxes.length; i-->0;) {
					if (checkboxes[i].checked) {
						ids.push(checkboxes[i].value);
					}
				}
				if (ids.length === 0) {
					alert("请选择要删除的用户");
					return;
				}
				var url = "delBook.do?strIds=" + ids;
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
	<script type="text/javascript">
		var updateExBookCover=function(id){
			alert(id);
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
			reader.onload = function(e) {
				$("#"+id).attr("src",e.target.result);
				$.post("updateExBookCover.do", {
					bookCover : e.target.result,
					bookId : id
				}, function(status) {
					window.location.reload();
				}, 'json');
			};  
		};
		
		var pyBook=function(id){
			var size="${r.exSize}";
			if(size>=10){
				alert("推荐书籍已经有10本了,删两本吧！");
			}else if(size>=5){
				alert("推荐已经有5本了，稍微有点多了哦");
			}
			$.post("pyBook.do",{
				newId : id
			}, function(status){
				window.location.reload();
			});
		};
	</script>
</body>
</html>