var userapp = angular.module("user-app", []);
userapp.controller("nav-controller", function($scope, $rootScope, $http) {
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
	
	// must logged in
	if ($rootScope.user===null) {
		location.href = "index.html";
	}
	
});

userapp.controller("user-controller", function($scope, $rootScope, $http, $filter) {
	$scope.indentStatus = ['-', '待支付', '待发货', '已发货', '待评价', '已完成'];
	
	$scope.loadAllIndentForUser = function() {
		$scope.loadUnfinishedIndent();
		$scope.loadFinishedIndent();
	};
	
	$scope.loadUnfinishedIndent = function() {
		var param = {};
		$scope.sendRequest('loadUnfinishedIndent.do', param, function(resp) {
			var indentInfos = angular.copy(resp.data.data);
			for (var i = 0; i < indentInfos.length; i++) {
				$scope.addIndentEL(indentInfos[i], false);
			}
		});
	};
	
	$scope.loadFinishedIndent = function() {
		var param = {};
		$scope.sendRequest('loadFinishedIndent.do', param, function(resp) {
			var indentInfos = angular.copy(resp.data.data);
			for (var i = 0; i < indentInfos.length; i++) {
				$scope.addIndentEL(indentInfos[i]);
			}
		});
	};
	
	$scope.sendRequest = function(url, param, succFun, checkLogin) {
		checkLogin = !(checkLogin===false);
		$rootScope.checkLoginUser();
		if (checkLogin && $rootScope.user===null) {
			$scope.modalDialog = angular.copy($scope.modalDialogDefault);
			$scope.modalDialog.title = "提示";
			$scope.modalDialog.content = "您还未登录，请登录后继续";
			$scope.modalDialog.hasConfirm = true;
			$scope.modalDialog.confirmFun = function() {
				sessionStorage.setItem("previousURL", document.URL);
				window.location = "login.html";
			};
			$scope.showModalDialog();
		} else {
			if (checkLogin) {
				param.userId = $rootScope.user.id;
			} 
			$http({
				method: 'POST',
				url: url,
				data: $.param(param),
				headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
			}).then(function(resp){
				if(resp.data.code){
					//后台业务失败
					alert(resp.data.message);
				}else{
					succFun(resp);
				}
			}, function(err){
				alert("网络异常");
			});
		}
	};
	
	$scope.addIndentEL = function(indentInfo, isFinished) {
		isFinished = !(isFinished===false);
		console.log(indentInfo);
		var indentEL = $('<div class="indent">' + 
        	'<span class="label label-info">' + $scope.indentStatus[indentInfo.status] + '</span>' + 
        	'<span>订单编号：<a href="indent.html?id=' + indentInfo.id + '">' + indentInfo.id + '</a></span>' + 
        	'<span>下单时间：' + $filter('date')(indentInfo.generateTime, "yyyy-MM-dd HH:mm:ss") + '</span>' +
        	'<span>购买书籍：<span class="bookName"><a href="book.html?id=' + indentInfo.bookId + '">' + indentInfo.bookName + '</a></span></span>'+
        	'</div>');
		if (isFinished) {
			if ($scope.isFirstFinishedIndentEL===false) {
				$('#finishedIndentEL').append($('<hr>'));
			}
			$scope.isFirstFinishedIndentEL=false;
			$('#finishedIndentEL').append(indentEL);
		} else {
			if ($scope.isFirstUnfinishedIndentEL===false) {
				$('#unFinishedIndentEL').append($('<hr>'));
			}
			$scope.isFirstUnfinishedIndentEL=false;
			$('#unFinishedIndentEL').append(indentEL);
		}
	};
	
	$scope.loadAllIndentForUser();
	
});