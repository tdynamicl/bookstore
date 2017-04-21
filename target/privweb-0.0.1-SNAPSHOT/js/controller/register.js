
var registerapp = angular.module('register-app', ['angular-md5']);
	registerapp.controller('register-controller', function($scope, $http, md5){
		$scope.user={};
		$scope.regResult = {code: -1, message:''};
		$scope.register = function(){
			$scope.encuser = angular.copy($scope.user);
			$scope.encuser.account = md5.createHash($scope.encuser.account);
			$scope.encuser.password = md5.createHash(md5.createHash($scope.encuser.password));
			$http({
				method: 'POST',
				url: 'register.do',
				data: $.param($scope.encuser),
				headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
			}).then(function(resp){
				$scope.registertip = resp.data.message;
				$scope.regResult = angular.copy(resp.data);
				$('#regResultModal').modal({show:true});
			}, function(err){
				alert("网络异常");
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
	