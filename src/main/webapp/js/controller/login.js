myApp.controller('login-controller', function ($scope, md5, myService){
	$scope.user={'account':'', 'password':''};
	$scope.login = function(){
		var param = {};
		param.account = md5.createHash($scope.user.account);
		param.password = md5.createHash(md5.createHash($scope.user.password));
		myService.httpPost('login.do', param, function(resp) {
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
		});
	};
	
	//清除提示
	$scope.clearlogintip = function(){
		$scope.logintip = "";
	};
});