myApp.controller("cart-controller", function($scope, $rootScope, myService) {
	
	
	
	
	$scope.loadCartInfo = function() {
		var param = {};
		param.userId = $rootScope.user.id;
		myService.httpPost('loadCartInfo.do', param, function(resp) {
			
		});
	};
	
	$scope.entry = function() {
		if ($rootScope.user === null) {
			myService.showModal($scope, '提示', '未登录的用户无法查看购物车', undefined, true)
		} else {
			// 加载信息
			$scope.loadCartInfo();
			
		}
	};
	$scope.entry();
	
});