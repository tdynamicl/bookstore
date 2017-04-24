var loginapp = angular.module('login-app', ['angular-md5']);
	loginapp.controller('login-controller', function ($scope, $http, md5){
		$scope.user={'account':'', 'password':''};
		$scope.login = function(){
			var param = {};
			param.account = md5.createHash($scope.user.account);
			param.password = md5.createHash(md5.createHash($scope.user.password));
			$http({
				method: 'POST',
				url: 'login.do',
				data: $.param(param),
				headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
			}).then(function(resp){
				if(resp.data.code){
					$scope.errorTip=resp.data.message;
				}else{
					var userString = resp.data.data;
					sessionStorage.setItem("user", JSON.stringify(userString));
					var prevURL = sessionStorage.getItem("previousURL");
					if (prevURL !== null) {
						sessionStorage.removeItem("previousURL");
						window.location = prevURL;
					}else {
						window.location="index.html";
					}
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