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
	$scope.bookInfo = {'id':'', 'name':'', 'desc':'', 'authorName':'', 'pressName':'',
		'pressTime':'', 'rankTotal':'', 'rankLevel':'', 'price':''};
	$scope.getBookInfo = function(){
		$http({
			method: 'POST',
			url: 'loadBookInfo.do',
			data: $.param($scope.bookId),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				//获取失败
			}else{
				$scope.bookInfo = angular.copy(resp.data.data);
			}
		}, function(err){
			alert("网络异常");
		});

	};
		
	$scope.getBookInfo();
	document.title = $scope.bookInfo.name;
	
});