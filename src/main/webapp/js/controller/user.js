myApp.controller("user-controller", function($scope, $rootScope, $filter, myService) {
	$scope.indentStatus = ['已删除', '待支付', '待发货', '已发货', '待评价', '已完成'];
	
	$scope.loadProfile = function() {
		myService.loadUserIconBase64($rootScope.user.id, function(resp) {
			$('#profileEL .icon')[0].src = resp.data.data;
		});
		
	};
	
	$scope.loadAllIndentForUser = function() {
		$scope.loadUnfinishedIndent();
		$scope.loadFinishedIndent();
	};
	
	$scope.loadUnfinishedIndent = function() {
		var param = {};
		param.userId = $rootScope.user.id;
		myService.httpPost('loadUnfinishedIndent.do', param, function(resp) {
			var indentInfos = angular.copy(resp.data.data);
			for (var i = 0; i < indentInfos.length; i++) {
				$scope.addIndentEL(indentInfos[i], false);
			}
		});
	};
	
	$scope.loadFinishedIndent = function() {
		var param = {};
		param.userId = $rootScope.user.id;
		myService.httpPost('loadFinishedIndent.do', param, function(resp) {
			var indentInfos = angular.copy(resp.data.data);
			for (var i = 0; i < indentInfos.length; i++) {
				$scope.addIndentEL(indentInfos[i]);
			}
		});
	};
	
	$scope.addIndentEL = function(indentInfo, isFinished) {
		isFinished = !(isFinished===false);
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
	
	
	$scope.entry = function() {
		if ($rootScope.user === null) {
			location.href = "index.html";
		}
		$scope.loadAllIndentForUser();
		$scope.loadProfile();
	};
	$scope.entry();

});