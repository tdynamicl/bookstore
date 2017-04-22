var dealapp = angular.module("deal-app", []);

dealapp.controller("nav-controller", function($scope, $rootScope, $http) {
	$scope.user = sessionStorage.getItem("user");
	if ($scope.user !== null) {
		$scope.user = JSON.parse($scope.user);
		$scope.nickname = $scope.user.nickname;
		$rootScope.user = $scope.user;
	}
		$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
	
});

dealapp.controller("deal-controller", function($scope, $rootScope, $http){
	
});