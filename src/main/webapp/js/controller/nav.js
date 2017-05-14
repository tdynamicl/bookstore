/**
 * 导航栏控制器
 */
myApp.controller("nav-controller", function($scope, $rootScope, myService) {
	$rootScope.user = null;
	$rootScope.checkLoginUser = function() {
		var userString = sessionStorage.getItem("user");
		if (userString) {
			$rootScope.user = JSON.parse(userString);
			$scope.initializeUI();
		}
	};
	
	$scope.initializeUI = function() {
		$scope.nickname = $scope.user.nickname;
		myService.loadCartTotal($rootScope.user.id, function(resp) {
			if (resp.data.code) {
				$rootScope.cartTotal = 0;
			} else {
				$rootScope.cartTotal = resp.data.data.length;
			}
		});
	};
	
	$scope.toPrivateCenter = function() {
		location.href = "user.html";
	};
	
	$scope.logout = function() {
		sessionStorage.removeItem("user");
		$rootScope.user = null;
		location.reload();
	};
		
	$rootScope.checkLoginUser();
	
});
