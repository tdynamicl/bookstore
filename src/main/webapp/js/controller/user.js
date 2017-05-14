var userId = null;
myApp.controller("user-controller", function($scope, $rootScope, $filter, myService) {
	userId = $scope.user.id;
	$scope.indentStatus = ['已删除', '待支付', '待发货', '已发货', '待评价', '已完成'];
	$scope.indexs = {favorite: 0};
	$scope.loadProfile = function() {
		myService.loadUserIconBase64($rootScope.user.id, function(resp) {
			if (resp.data.code) {
				$('#profileEL .icon')[0].src = "img/commonLib/noUserIcon.jpg";
			} else {
				$('#profileEL .icon')[0].src = resp.data.data;
			}
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
	
	$scope.loadFavoriteBook = function() {
		var param = {};
		param.userId = $rootScope.user.id;
		param.index = $scope.indexs.favorite;
		myService.httpPost('loadFavoritBook.do', param, function(resp) {
			if (resp.data.code) {
				//没有收藏的书籍
				console.log(resp.data.message);
			} else {
				var datas = resp.data.data;
				for (var i = 0; i < datas.length; i++) {
					$scope.addFavoriteBookEL(datas[i]);
				}
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
			$('#finishedIndentEL .noItem').remove();
			if ($scope.isFirstFinishedIndentEL===false) {
				$('#finishedIndentEL').append($('<hr>'));
			}
			$scope.isFirstFinishedIndentEL=false;
			$('#finishedIndentEL').append(indentEL);
		} else {
			$('#unFinishedIndentEL .noItem').remove();
			if ($scope.isFirstUnfinishedIndentEL===false) {
				$('#unFinishedIndentEL').append($('<hr>'));
			}
			$scope.isFirstUnfinishedIndentEL=false;
			$('#unFinishedIndentEL').append(indentEL);
		}
	};
	
	$scope.addFavoriteBookEL = function(bookInfo) {
		var favoriteBoolEL = $('<div class="favoriteBook">'+
	    	'<img data-bookId="'+bookInfo.id+'"><div class="detail">'+
	    	'<span class="bookName"><a href="book.html?id='+bookInfo.id+'">'+bookInfo.name+'</a></span>'+
	    	'<span class="bookDesc">'+bookInfo.desc+'</span>'+
	    	'<span class="authorName">'+bookInfo.authorName+'</span>'+
	    	'<span class="pressName">'+bookInfo.pressName+'</span>'+
	    	'</div><div class="operation">'+
	    	'<a onclick="unFavorite(\''+bookInfo.id+'\')" class="cursorPointer">取消收藏</a>'+
	    	'</div></div>'
		);
		if ($scope.isNotFirstFavoriteEL === false) {
			$('#favoriteBookEL').append($('<hr>'));
		}
		$scope.isNotFirstFavoriteEL = false;
		$('#favoriteBookEL').append(favoriteBoolEL);
		$('#favoriteBookEL .noItem').remove();
		myService.loadCoverBase64(bookInfo.id, function(resp) {
			if (resp.data.code) {
				
			} else {
				$('img[data-bookId='+bookInfo.id+']')[0].src = resp.data.data;
			}
		});
		
	};
	
	$scope.entry = function() {
		if ($rootScope.user === null) {
			location.href = "index.html";
		}
		$scope.loadAllIndentForUser();
		$scope.loadProfile();
		$scope.loadFavoriteBook();
	};
	$scope.entry();

});

var unFavorite = function(bookId) {
	if (userId) {
		$.post('delFavoriteBook.do', {userId: userId, bookId: bookId},function(resp){
			if (resp.code) {
				alert(resp.message);
			} else {
				location.reload();
			}
		});
	}
};
