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
	
	$scope.toPrivateCenter = function() {
		location.href = "user.html?id=" + $rootScope.user.id;
	};
	
	$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
		
	$rootScope.checkLoginUser();

	
});

categoryapp.controller("books-controller", function($scope, $rootScope, $http) {
	document.title = getQueryString("word");
	$scope.currIndex = 0;
	$scope.BooksEL = $("#books");
	
	$scope.loadBookInfo = function(){
		var param = {"word": getQueryString("word"), "index": $scope.currIndex};
		$http({
			method: 'POST',
			url: 'loadBookInfosByCategory.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				//获取失败
				alert(resp.data.message);
			}else{
				var bookInfos = angular.copy(resp.data.data);
			}
			if (bookInfos == null) {
				return;
			}
			//添加
			for (var i = 0; i < bookInfos.length; i++) {
				$scope.addBookEL(bookInfos[i]);
			}
			$scope.currIndex += bookInfos.length;
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
