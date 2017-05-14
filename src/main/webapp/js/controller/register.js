myApp.controller('register-controller', function($scope, md5, myService){
	$scope.register = function(){
		var param = {};
		param = angular.copy($scope.user);
		param.password = md5.createHash(md5.createHash(param.password));
		myService.httpPost('register.do', param, function(resp){
			if (resp.data.code) {
				$scope.errortip = resp.data.message;
			} else {
				myService.showModal($scope, '提示', resp.data.message, function() {
					location.href = "login.html";
				}, true);
			}
		});
	};
	
	//清除提示
	$scope.cleartip = function(){
		$scope.errortip = "";
	};
});
	