<%@page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加书籍信息</title>
</head>
<body>
	<form action="addBook.do" method="post">
		${requetScope.errtip}
		<label>书名：<input name="name"></label>
		<label>描述：<input name="description"></label>
		<label>作者：<input name="author"></label>
		<label>出版社：<input name="press"></label>
		<label>出版日期：<input name="pressTimeString" type="date"></label>
		<label>定价：<input name="price" type="number"></label>
		所属类型：
		教育：
		<label>教程<input name="keyword" type="checkbox" value="1"></label>
		<label>教材<input name="keyword" type="checkbox" value="2"></label>
		<label>考试<input name="keyword" type="checkbox" value="3"></label>
		<label>工具书<input name="keyword" type="checkbox" value="4"></label>
		<label>中小学辅导<input name="keyword" type="checkbox" value="5"></label>
		小说：
		<label>古典小说<input name="keyword" type="checkbox" value="6"></label>
		<label>现代小说<input name="keyword" type="checkbox" value="7"></label>
		<label>网络小说<input name="keyword" type="checkbox" value="8"></label>
		<label>外国小说<input name="keyword" type="checkbox" value="9"></label>
		文艺：
		<label>文学<input name="keyword" type="checkbox" value="10"></label>
		<label>传记<input name="keyword" type="checkbox" value="11"></label>
		<label>艺术<input name="keyword" type="checkbox" value="12"></label>
		<label>摄影<input name="keyword" type="checkbox" value="13"></label>
		儿童：
		<label>科普<input name="keyword" type="checkbox" value="14"></label>
		<label>幼儿启蒙<input name="keyword" type="checkbox" value="15"></label>
		<label>益智<input name="keyword" type="checkbox" value="16"></label>
		生活：
		<label>美食<input name="keyword" type="checkbox" value="17"></label>
		<label>旅游<input name="keyword" type="checkbox" value="18"></label>
		<label>休闲<input name="keyword" type="checkbox" value="19"></label>
		<label>美妆<input name="keyword" type="checkbox" value="20"></label>
		科技：
		<label>建筑<input name="keyword" type="checkbox" value="21"></label>
		<label>医学<input name="keyword" type="checkbox" value="22"></label>
		<label>计算机<input name="keyword" type="checkbox" value="23"></label>
		<label>农林<input name="keyword" type="checkbox" value="24"></label>
		<label>自然<input name="keyword" type="checkbox" value="25"></label>
		<label>工业<input name="keyword" type="checkbox" value="26"></label>
		
		<button type="submit">确认添加</button>
	</form>
</body>
</html>