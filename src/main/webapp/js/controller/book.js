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
		location.href = "user.html";
	};
	
	$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
		
	$rootScope.checkLoginUser();
	
});

bookapp.controller("book-controller", function($scope, $rootScope, $http) {
	$scope.modalDialog = {};
	$scope.isFavorited = null;
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
				alert(resp.data.message);
				window.location="index.html";
			}else{
				$scope.bookInfo = angular.copy(resp.data.data);
				document.title = $scope.bookInfo.name;
				$scope.loadBookImage(param.id);
			}
		}, function(err){
			alert("网络异常");
			//window.location="index.html";
		});

	};
	
	//获取封面图片
	$scope.loadBookImage = function(bookId) {
		var param = {};
		param.id = bookId;
		$http({
			method: 'POST',
			url: 'loadBookImage.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				alert(resp.data.message);
			}else{
				$('#coverEL')[0].src = resp.data.data;
			}
		}, function(err){
			alert("获取封面图片时网络异常");
		});
	};

	//检查是否已经收藏
	$scope.checkFavorite = function(bookId) {
		if ($rootScope.user===null) {
			return;
		}
		var param = {'bookId': getQueryString("id"), 'userId': $rootScope.user.id};
		param.id = bookId;
		$http({
			method: 'POST',
			url: 'checkFavorite.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				alert(resp.data.message);
			}else{
				$scope.isFavorited = resp.data.data;
			}
		}, function(err){
			alert("获取封面图片时网络异常");
		});
	};
	
	//收藏书籍
	$scope.favorite = function() {
		if ($rootScope.user===null) {
			return;
		}
		var param = {'bookId': getQueryString("id"), 'userId': $rootScope.user.id};
		$http({
			method: 'POST',
			url: 'addFavoriteBook.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				alert(resp.data.message);
			}else{
				$scope.isFavorited = true;
			}
		}, function(err){
			alert("收藏书籍时网络异常");
		});
	};
	
	//取消收藏
	$scope.unFavorite = function() {
		if ($rootScope.user===null) {
			return;
		}
		var param = {'bookId': getQueryString("id"), 'userId': $rootScope.user.id};
		$http({
			method: 'POST',
			url: 'delFavoriteBook.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				alert(resp.data.message);
			}else{
				$scope.isFavorited = false;
			}
		}, function(err){
			alert("取消收藏时网络异常");
		});
	};
	
	// 购买书籍（按钮事件）
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
	
	//------------------------------------
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
		$scope.modalDialog = null;
	});
	
	$scope.loadBookInfo();
	$scope.checkFavorite();
	
});