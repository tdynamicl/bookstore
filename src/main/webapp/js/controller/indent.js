var indentapp = angular.module("indent-app", []);
indentapp.controller("nav-controller", function($scope, $rootScope, $http) {
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

indentapp.controller("indent-controller", function($scope, $rootScope, $http, $filter) {
	$scope.bookInfo = null;
	$scope.indentInfo = null;
	$scope.modalDialog = {};
	$scope.modalDialogDefault = {title:"", content:"", hasCancel: false, hasConfirm: false};
	$scope.loadIndentInfo = function() {
		var param = {};
		param.id = getQueryString("id");
		$scope.sendRequest('loadIndentInfo.do', param, function(resp) {
			$scope.indentInfo = resp.data.data;
			$scope.indentInfo.generateTime = $filter('date')($scope.indentInfo.generateTime, "yyyy-MM-dd hh:mm:ss");
			$scope.loadBookInfo(resp.data.data.bookId);
			/////////////////////////////////////////////
			console.log($scope.indentInfo);
		});
	};
	
	$scope.loadBookInfo = function(bookId){
		var param = {};
		param.id = bookId;
		$scope.sendRequest('loadBookInfo.do', param, function(resp) {
			$scope.bookInfo = resp.data.data;
			$scope.bookInfo.bookURL = "book.html?id=" + resp.data.data.id;
			/////////////////////////////////
			console.log($scope.bookInfo);
		}, false);
	};
	
	$scope.purchase = function() {
		var param = {};
		param.id = $scope.indentInfo.id;
		$scope.sendRequest('purchaseIndent.do', param, function(resp) {
			location.reload();
		});
	};
	
	$scope.cancelIndent = function() {
		var param = {};
		param.id = $scope.indentInfo.id;
		$scope.sendRequest('cancelIndent.do', param, function(resp) {
			window.location = "index.html";
		});
	};
	
	$scope.received = function() {
		var param = {};
		param.id = $scope.indentInfo.id;
		$scope.sendRequest('receivedIndent.do', param, function(resp) {
			location.reload();
		});
	};
	
	$scope.rate = function() {
		var param = {};
		param.id = $scope.indentInfo.id;
		$scope.sendRequest('rateIndent.do', param, function(resp) {
			location.reload();
		});
	};
	
	$scope.deleteIndent = function() {
		var param = {};
		param.id = $scope.indentInfo.id;
		$scope.sendRequest('deleteIndent.do', param, function(resp) {
			window.location = "index.html";
		});
	};
	
	$scope.sendRequest = function(url, param, succFun, checkLogin) {
		checkLogin = !(checkLogin===false);
		$rootScope.checkLoginUser();
		if (checkLogin && $rootScope.user===null) {
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
			if (checkLogin) {
				param.userId = $rootScope.user.id;
			} 
			$http({
				method: 'POST',
				url: url,
				data: $.param(param),
				headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
			}).then(function(resp){
				if(resp.data.code){
					//后台业务失败
					alert(resp.data.message);
				}else{
					succFun(resp);
				}
			}, function(err){
				alert("网络异常");
			});
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
	
	$scope.loadIndentInfo();
	
});
