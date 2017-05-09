var currStar = 3;
var onStar = function(index) {
	if (index===currStar) {
		return;
	}
	for(var i = 1; i <= 5; i++){
		var starEL = $("span[star='" + i + "']");
		if (i <= index) {
			starEL.removeClass("emptyStar");
			starEL.addClass("fullStar");
		} else {
			starEL.removeClass("fullStar");
			starEL.addClass("emptyStar");
		}
	}
	currStar = index;
};

myApp.controller("indent-controller", function($scope, $rootScope, $filter, myService) {
	$scope.bookInfo = null;
	$scope.indentInfo = null;
	$scope.loadIndentInfo = function() {
		var param = {};
		param.id = myService.getQueryString("id");
		if ($rootScope.user===null) {
			return;
		}
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
		param.level = currStar;
		param.content = $scope.commentContent;
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
			myService.showModal($scope, '找不到登录信息', '您可能还还未登录，请登录后继续', function() {
				sessionStorage.setItem("previousURL", document.URL);
				window.location = "login.html";
			});
		}
	};
	
	$scope.checkAndToLogin();
	$scope.loadIndentInfo();
	
});
