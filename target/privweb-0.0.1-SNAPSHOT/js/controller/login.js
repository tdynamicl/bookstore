var loginapp = angular.module('login-app', ['angular-md5']);
	loginapp.controller('login-controller', function ($scope, $http, md5){
		$scope.user={'account':'','password':''};
		$scope.login = function(){
			$scope.encuser = angular.copy($scope.user);
			$scope.encuser.account = md5.createHash($scope.encuser.account);
			$scope.encuser.password = md5.createHash(md5.createHash($scope.encuser.password));
			$http({
				method: 'POST',
				url: 'login.do',
				data: $.param($scope.encuser),
				headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
			}).then(function(resp){
				if(resp.data.code){
					$scope.logintip=resp.data.message;
				}else{
				var loggeduser = resp.data.data;
				sessionStorage.setItem("user", JSON.stringify(loggeduser));
				window.location="index.html";
				}
			}, function(err){
				alert("网络异常");
			}); 
		};
		
		//清除提示
		$scope.clearlogintip = function(){
			$scope.logintip = "";
		};
	});