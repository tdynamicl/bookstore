var searchapp = angular.module("search-app", []);
searchapp.controller("nav-controller", function($scope, $rootScope, $http) {
	$rootScope.user = null;
	$rootScope.checkLoginUser = function() {
		var userString = sessionStorage.getItem("user");
		if (userString !== null) {
			$rootScope.user = JSON.parse(userString);
			$scope.nickname = $scope.user.nickname;
		}
	};
	
	$scope.toPrivateCenter = function() {
		location.href = "user.html";
	};
	
	$scope.logout = function() {
		sessionStorage.removeItem("user");
		location.reload();
	};
		
	$rootScope.checkLoginUser();
	
});

searchapp.controller("search-controller", function($scope, $rootScope, $http) {
	$scope.currIndex = 0;
	$scope.BooksEL = $("#books");
	
	$scope.loadSearchResult = function(){
		var param = {};
		param.keyword = getQueryString("keyword");
		param.index = $scope.currIndex;
		$http({
			method: 'POST',
			url: 'search.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				$scope.modalDialog = {};
				$scope.modalDialog.title = "提示";
				$scope.modalDialog.content = resp.data.message;
				$scope.modalDialog.hasConfirm = true;
				$scope.modalDialog.confirmFun = function() {
					return;
				};
				$scope.showModalDialog();
			}else{
				var bookInfos = angular.copy(resp.data.data);
				if (bookInfos == null) {
					return;
				}
				//添加
				for (var i = 0; i < bookInfos.length; i++) {
					$scope.addBookEL(bookInfos[i]);
				}
				$scope.currIndex += bookInfos.length;
			}
		}, function(err){
			alert("网络异常");
		});
	};
	
	//获取封面图片
	$scope.loadBookImage = function(bookId, succFun) {
		var param = {};
		param.id = bookId;
		$http({
			method: 'POST',
			url: 'loadBookImage.do',
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			if(resp.data.code){
				//alert(resp.data.message);
			}else{
				succFun(resp);
			}
		}, function(err){
			alert("获取封面图片时网络异常");
		});
	};
	
	$scope.showModalDialog = function(){
		$scope.modalResult = {confirmed: false, canceled: false};
		$('#modalEL').modal({show: true});
	};
	
	$scope.confirm = function() {
		$scope.modalResult.confirmed = true;
	};
	
	$('#modalEL').on('hidden.bs.modal', function () {
		if ($scope.modalResult.confirmed && $scope.modalDialog.confirmFun!==undefined) {
			$scope.modalDialog.confirmFun();
		}
		$scope.modalDialog = null;
	});
	
	$scope.addBookEL = function(bookInfo){
		var bookEL = $('<hr/><div class="book-item">'+
			'<div>'+
			'	<img id="' + bookInfo.id + '" />'+
			'</div>'+
			'<div class="detail">'+
			'	<h3><a href="book.html?id=' + bookInfo.id + '">' + bookInfo.name + '</a></h3>'+
			'	<p>' + bookInfo.desc + '</p>'+
			'	<div class="bookDiscription">'+
			'		<span class="authorName">' + bookInfo.authorName + '</span>'+
			'		<span class="pressName">' + bookInfo.pressName + '</span>'+
			'		<span class="pressTime">' + bookInfo.pressTime + '</span>'+
			'		<span class="rankTotal">' + bookInfo.rankTotal + '</span>'+
			'		<span class="rankLevel">' + bookInfo.rankLevel + '</span>'+
			'</div></div></div>'
		); 
		$scope.BooksEL.append(bookEL);
		$scope.loadBookImage(bookInfo.id, function(resp) {
			$('#'+bookInfo.id)[0].src = resp.data.data;
		});
	};
	
	$scope.loadSearchResult();
	
});