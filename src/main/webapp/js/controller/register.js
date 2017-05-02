myApp.controller('register-controller', function($scope, md5, myService){
	$scope.user={};
	$scope.regResult = {code: -1, message:''};
	$scope.register = function(){
		var param = {};
		param = angular.copy($scope.user);
		param.account = md5.createHash(param.account);
		param.password = md5.createHash(md5.createHash(param.password));
		myService.httpPost('register.do', param, function(resp){
			$scope.registertip = resp.data.message;
			$scope.regResult = angular.copy(resp.data);
			$('#regResultModal').modal({show:true});
		});
	};
	
	$('#regResultModal').on('hidden.bs.modal', function () {
		if($scope.regResult.code === 0) {
			window.location.href = "login.html";
		} else{
			$scope.regResult = {code: -1, message:''};
		}
	});
});
	