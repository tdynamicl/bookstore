var bookapp = angular.module("book-app", []);

bookapp.controller("nav-controller", function($scope, $rootScope, $http) {
	$scope.user = sessionStorage.getItem("user");
	if ($scope.user != null) {
		$scope.user = JSON.parse($scope.user);
		$scope.nickname = $scope.user.nickname;
		$rootScope.user = $scope.user;
	}
		$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
	
});

bookapp.controller("book-controller", function($scope, $rootScope, $http) {
	$scope.bookId = {'id': getQueryString("id")};
	//$scope.bookInfo = {'id':'', 'name':'', 'desc':'', 'authorName':'', 'pressName':'',
	//	'pressTime':'', 'rankTotal':'', 'rankLevel':'', 'price':''};
	$scope.getBookInfo = function(){
		$http({
			method: 'POST',
			url: 'loadBookInfo.do',
			data: $.param($scope.bookId),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				//获取失败
				alert("该书已下架，为您跳转到主页");
				window.location="index.html";
			}else{
				$scope.bookInfo = angular.copy(resp.data.data);
				document.title = $scope.bookInfo.name;
			}
		}, function(err){
			alert("网络异常");
			//window.location="index.html";
		});

	};

	$scope.purchase = function(){
		if ($scope.user == null) {
			alert("您还未登录，请登录后继续");
			sessionStorage.setItem("previousURL", document.URL);
			window.location = "login.html";
		} else {
			window.location = "deal.html?id=" + $scope.bookInfo.id;
		}
	};
	
	$scope.getBookInfo();
	
});