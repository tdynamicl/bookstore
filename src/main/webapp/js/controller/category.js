var categoryapp = angular.module("category-app", []);

categoryapp.controller("nav-controller", function($scope, $rootScope, $http) {
	$rootScope.user = null;
	$rootScope.checkLoginUser = function() {
		var userString = sessionStorage.getItem("user");
		if (userString !== null) {
			$rootScope.user = JSON.parse(userString);
			$scope.nickname = $scope.user.nickname;
		}
	};
	
	$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
		
	$rootScope.checkLoginUser();

	
});

categoryapp.controller("books-controller", function($scope, $rootScope, $http) {
	$scope.BooksEL = $("#books");
	$scope.paramString = {"word":getQueryString("word"), "index": 0};
	
	$scope.loadBookInfo = function(){
		$http({
			method: 'POST',
			url: 'loadBookInfosByCategory.do',
			data: $.param($scope.paramString),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				//获取失败
				alert(resp.data.message);
			}else{
				$scope.bookInfos = angular.copy(resp.data.data);
			}
			if ($scope.bookInfos==null) {
				return;
			}
			//添加
			for (var i = 0; i < $scope.bookInfos.length; i++) {
				$scope.addBookEL($scope.bookInfos[i]);
			}
			$scope.paramString.index += $scope.bookInfos.length;
		}, function(err){
			alert("网络异常");
		});
	};
	
	$scope.addBookEL = function(bookInfo){
		var bookEL = $('<hr/><div class="book-item">'+
			'<div>'+
			'	<img src="" />'+
			'</div>'+
			'<div class="detail">'+
			'	<h3><a href="book.html?id=' + bookInfo.id + '">' + bookInfo.name + '</a></h3>'+
			'	<p>' + bookInfo.desc + '</p>'+
			'	<div class="bookDiscription">'+
			'		<span class="authorName">' + bookInfo.authorName + '</span>'+
			'		<span class="pressName">' + bookInfo.pressName + '</span>'+
			'		<span class="pressTime">' + bookInfo.pressTime + '</span>'+
			'		<span class="rankTotal">' + bookInfo.rankTotal + '</span>'+
			'		<span class="rankLevel">' + bookInfo.rankLevel + '</span>'+
			'</div></div></div>'
		); 
		$scope.BooksEL.append(bookEL);
	};
	
	$scope.loadBookInfo();
	
});
