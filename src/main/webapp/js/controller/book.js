myApp.controller("book-controller", function($scope, $rootScope, myService) {
	$scope.isFavorited = null;
	$scope.loadBookInfo = function(){
		var param = {};
		param.id = myService.getQueryString("id");
		myService.httpPost('loadBookInfo.do', param, function(resp){
			if(resp.data.code){
				//获取失败
				alert(resp.data.message);
				window.location="index.html";
			}else{
				$scope.bookInfo = angular.copy(resp.data.data);
				document.title = $scope.bookInfo.name;
				//获取封面图片
				myService.loadCoverBase64(param.id, function(resp) {
					if (resp.data.data===null) {
						$('#coverEL')[0].src = "img/commonLib/nocover.png";
					} else{
						$('#coverEL')[0].src = resp.data.data;
					}
				});
			}		
		});
	};
	
	//检查是否已经收藏
	$scope.checkFavorite = function(bookId) {
		if ($rootScope.user===null) {
			return;
		}
		var param = {};
		param.bookId = myService.getQueryString("id");
		param.userId = $rootScope.user.id;
		myService.httpPost('checkFavorite.do', param, function(resp){
			if(resp.data.code){
				alert(resp.data.message);
			}else{
				$scope.isFavorited = resp.data.data;
			}
		});
	};
	
	//收藏书籍
	$scope.favorite = function() {
		if ($rootScope.user===null) {
			return;
		}
		var param = {};
		param.bookId = myService.getQueryString("id");
		param.userId = $rootScope.user.id;
		myService.httpPost('addFavoriteBook.do', param, function(resp){
			if(resp.data.code){
				alert(resp.data.message);
			}else{
				$scope.isFavorited = true;
			}
		});
	};
	
	//取消收藏
	$scope.unFavorite = function() {
		if ($rootScope.user===null) {
			return;
		}
		myService.showModal($scope, '提示', '取消收藏此书？', function() {
			var param = {};
			param.bookId = myService.getQueryString("id");
			param.userId = $rootScope.user.id;
			myService.httpPost('delFavoriteBook.do', param, function(resp){
				if(resp.data.code){
					alert(resp.data.message);
				}else{
					$scope.isFavorited = false;
				}
			});
		});
	};
	
	// 购买书籍（按钮事件）
	$scope.purchase = function(){
		$rootScope.checkLoginUser();
		if ($rootScope.user == null) {
			myService.showModal($scope, '提示', '您还未登录，请登录后继续。', function() {
				sessionStorage.setItem("previousURL", document.URL);
				window.location = "login.html";
			});
		} else {
			window.location = "deal.html?bid=" + $scope.bookInfo.id;
		}
	};
	
	$scope.loadBookInfo();
	$scope.checkFavorite();
	
});