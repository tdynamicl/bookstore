var bookapp = angular.module("book-app", []);

bookapp.controller("nav-controller", function($scope, $rootScope, $http) {
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

bookapp.controller("book-controller", function($scope, $rootScope, $http) {
	$scope.modalDialog = {};
	$scope.modalDialogDefault = {title:"", content:"", hasCancel: false, hasConfirm: false};
	$scope.loadBookInfo = function(){
		var param = {'id': getQueryString("id")};
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
				document.title = $scope.bookInfo.name;
			}
		}, function(err){
			alert("网络异常");
			//window.location="index.html";
		});

	};

	$scope.purchase = function(){
		$rootScope.checkLoginUser();
		if ($rootScope.user == null) {
			$scope.modalDialog = angular.copy($scope.modalDialogDefault);
			$scope.modalDialog.title = "提示";
			$scope.modalDialog.content = "您还未登录，请登录后继续";
			$scope.modalDialog.hasConfirm = true;
			$scope.modalDialog.confirmFun = function() {
				sessionStorage.setItem("previousURL", document.URL);
				window.location = "login.html";
			};
			$scope.showModalDialog();
		} else {
			window.location = "deal.html?bid=" + $scope.bookInfo.id;
		}
	};
	
	$scope.showModalDialog = function(){
		$scope.modalResult = {confirmed: false, canceled: false};
		$('#modalEL').modal({show: true});
	};
	
	$scope.confirm = function() {
		$scope.modalResult.confirmed = true;
	};
	
	$scope.cancel = function() {
		$scope.modalResult.canceled = true; 
	};
	
	$('#modalEL').on('hidden.bs.modal', function () {
		if ($scope.modalResult.confirmed && $scope.modalDialog.confirmFun!==undefined) {
			$scope.modalDialog.confirmFun();
		}
	});
	
	$scope.loadBookInfo();
	
});