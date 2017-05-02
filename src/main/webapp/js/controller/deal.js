myApp.controller("deal-controller", function($scope, $rootScope, myService){
	$scope.bookInfo = null;
	$scope.submitIndent = function(){
		$rootScope.checkLoginUser();
		if ($rootScope.user === null) {
			alert("您还未登录，请登录后继续");
			sessionStorage.setItem("previousURL", document.URL);
			window.location = "login.html";
		} else {
			var indentInfo = {};
			indentInfo.userId = $rootScope.user.id;
			indentInfo.bookId = myService.getQueryString("bid");
			indentInfo.addr = $scope.addr.province + "（省）" +
				$scope.addr.city +" （市）" +
				$scope.addr.street + "（街道）";
			indentInfo.receiverName = $scope.name;
			indentInfo.receiverTel = $scope.tel;
			myService.httpPost('submitIndent.do', indentInfo, function(resp){
				if(resp.data.code){
					$scope.errorTip = resp.data.message;
				}else{
					window.location="indent.html?id=" + resp.data.data;
				}
			});
		}
	};
	
	$scope.loadBookInfo = function(){
		var param = {};
		param.id = myService.getQueryString("bid");
		myService.httpPost('loadBookInfo.do', param, function(resp){
			if(resp.data.code){
				//获取失败
				alert("该书已下架，为您跳转到主页");
				window.location="index.html";
			}else{
				$scope.bookInfo = angular.copy(resp.data.data);
			}
		});
	};
	
	if ($rootScope.user===null) {
		window.location="index.html";
	}
	
	$scope.loadBookInfo();
	
	$scope.clearTip = function(){
		$scope.errorTip = '';
	};
	
});