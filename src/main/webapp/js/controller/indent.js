var indentapp = angular.module("index-app", []);
indentapp.controller("nav-controller", function($scope, $rootScope, $http) {
	$rootScope.user = null;
	$rootScope.checkLoginUser = function() {
		var userString = sessionStorage.getItem("user");
		if (userString !== null) {
			$rootScope.user = JSON.parse(userString);
			$scope.nickname = $scope.user.nickname;
		}
	};
	
	$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
		
	$rootScope.checkLoginUser();
	
});