myApp.controller("indent-controller", function($scope, $rootScope, $filter, myService) {
	$scope.bookInfo = null;
	$scope.indentInfo = null;
	$scope.modalDialog = {};
	$scope.modalDialogDefault = {title:"", content:"", hasCancel: false, hasConfirm: false};
	$scope.loadIndentInfo = function() {
		var param = {};
		param.id = myService.getQueryString("id");
		param.userId = $rootScope.user.id;
		myService.httpPost('loadIndentInfo.do', param, function(resp) {
			$scope.indentInfo = resp.data.data;
			$scope.indentInfo.generateTime = $filter('date')($scope.indentInfo.generateTime, "yyyy-MM-dd HH:mm:ss");
			$scope.loadBookInfo(resp.data.data.bookId);
		});
	};
	
	$scope.loadBookInfo = function(bookId){
		var param = {};
		param.id = bookId;
		myService.httpPost('loadBookInfo.do', param, function(resp) {
			$scope.bookInfo = resp.data.data;
			$scope.bookInfo.bookURL = "book.html?id=" + resp.data.data.id;
		});
	};
	
	$scope.purchase = function() {
		$scope.checkAndToLogin();
		var param = {};
		param.id = $scope.indentInfo.id;
		param.userId = $rootScope.user.id;
		myService.httpPost('purchaseIndent.do', param, function(resp) {
			location.reload();
		});
	};
	
	$scope.cancelIndent = function() {
		$scope.checkAndToLogin();
		var param = {};
		param.id = $scope.indentInfo.id;
		param.userId = $rootScope.user.id;
		myService.httpPost('cancelIndent.do', param, function(resp) {
			window.location = "index.html";
		});
	};
	
	$scope.received = function() {
		$scope.checkAndToLogin();
		var param = {};
		param.id = $scope.indentInfo.id;
		param.userId = $rootScope.user.id;
		myService.httpPost('receivedIndent.do', param, function(resp) {
			location.reload();
		});
	};
	
	$scope.rate = function() {
		$scope.checkAndToLogin();
		var param = {};
		param.id = $scope.indentInfo.id;
		param.userId = $rootScope.user.id;
		myService.httpPost('rateIndent.do', param, function(resp) {
			location.reload();
		});
	};
	
	$scope.deleteIndent = function() {
		$scope.checkAndToLogin();
		var param = {};
		param.id = $scope.indentInfo.id;
		param.userId = $rootScope.user.id;
		myService.httpPost('deleteIndent.do', param, function(resp) {
			window.location = "index.html";
		});
	};
	
	$scope.checkAndToLogin = function(){
		$rootScope.checkLoginUser();
		if ($rootScope.user===null) {
			$scope.modalDialog = angular.copy($scope.modalDialogDefault);
			$scope.modalDialog.title = "提示";
			$scope.modalDialog.content = "您还未登录，请登录后继续";
			$scope.modalDialog.hasConfirm = true;
			$scope.modalDialog.confirmFun = function() {
				sessionStorage.setItem("previousURL", document.URL);
				window.location = "login.html";
			};
			$scope.showModalDialog();
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
		$scope.modalDialog = null;
	});
	
	$scope.loadIndentInfo();
	
});
