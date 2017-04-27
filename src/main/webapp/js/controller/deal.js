var dealapp = angular.module("deal-app", []);

dealapp.controller("nav-controller", function($scope, $rootScope, $http) {
	$rootScope.user = null;
	$rootScope.checkLoginUser = function() {
		var userString = sessionStorage.getItem("user");
		if (userString !== null) {
			$rootScope.user = JSON.parse(userString);
			$scope.nickname = $scope.user.nickname;
		}
	};
	
	$scope.toPrivateCenter = function() {
		location.href = "user.html";
	};
	
	$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
		
	$rootScope.checkLoginUser();
	
});

dealapp.controller("deal-controller", function($scope, $rootScope, $http){
	$scope.bookInfo = null;
	$scope.submitIndent = function(){
		$rootScope.checkLoginUser();
		if ($rootScope.user === null) {
			alert("您还未登录，请登录后继续");
			sessionStorage.setItem("previousURL", document.URL);
			window.location = "login.html";
		} else {
			$scope.indentInfo = {};
			$scope.indentInfo.userId = $rootScope.user.id;
			$scope.indentInfo.bookId = getQueryString("bid");
			$scope.indentInfo.addr = $scope.addr.province + "（省）" +
				$scope.addr.city +" （市）" +
				$scope.addr.street + "（街道）";
			$scope.indentInfo.receiverName = $scope.name;
			$scope.indentInfo.receiverTel = $scope.tel;
			console.log($scope.indentInfo);
			$http({
				method: 'POST',
				url: 'submitIndent.do',
				data: $.param($scope.indentInfo),
				headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
			}).then(function(resp){
				if(resp.data.code){
					$scope.errorTip = resp.data.message;
				}else{
					window.location="indent.html?id=" + resp.data.data;
				}
			}, function(err){
				alert("网络异常");
			});
		}
	};
	
	$scope.loadBookInfo = function(){
		var param = {};
		param.id = getQueryString("bid");
		$http({
			method: 'POST',
			url: 'loadBookInfo.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				//获取失败
				alert("该书已下架，为您跳转到主页");
				window.location="index.html";
			}else{
				$scope.bookInfo = angular.copy(resp.data.data);
			}
		}, function(err){
			alert("网络异常");
			window.location="index.html";
		});

	};
	
	if ($rootScope.user===null) {
		window.location="index.html";
	}
	
	$scope.loadBookInfo();
	
	$scope.clearTip = function(){
		
	};
	
});