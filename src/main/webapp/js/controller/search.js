myApp.controller("search-controller", function($scope, $rootScope, myService) {
	$scope.currIndex = 0;
	$scope.BooksEL = $("#books");
	
	$scope.loadSearchResult = function(){
		var param = {};
		param.keyword = myService.getQueryString("keyword");
		param.index = $scope.currIndex;
		myService.httpPost('search.do', param, function (resp) {
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
		myService.loadCoverBase64(bookInfo.id, function(resp) {
			if (resp.data.data===null) {
				$('#'+bookInfo.id)[0].src = "img/commonLib/nocover.png";
			} else {
				$('#'+bookInfo.id)[0].src = resp.data.data;
			}
		});
	};
	
	$scope.loadSearchResult();
	
});