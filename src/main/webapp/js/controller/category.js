myApp.controller("category-controller", function($scope, $rootScope, myService) {
	document.title = myService.getQueryString("word");
	$scope.pageSetting = {pageSize: 7};
	$scope.currIndex = 0;
	$scope.loadMoreELText = "点击加载更多";
	$scope.isNoMore = false;
	$scope.BooksEL = $("#books");
	
	$scope.loadBookInfo = function(){
		var param = {};
		param.word = myService.getQueryString("word");
		param.index = $scope.currIndex;
		myService.httpPost('loadBookInfosByCategory.do', param, function(resp){
			if(resp.data.code){
				$scope.loadMoreELText = resp.data.message;
				$scope.isNoMore = true;
			} else {
				var bookInfos = resp.data.data;
				for (var i = 0; i < bookInfos.length; i++) {
					$scope.addBookEL(bookInfos[i]);
					$scope.currIndex++;
				}
				if (bookInfos.length < $scope.pageSetting.pageSize) {
					$scope.loadMoreELText = "暂无更多";
					$scope.isNoMore = true;
				}
			}
		});
	};
	
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
		/**
		 * 获取封面
		 */
		$scope.BooksEL.append(bookEL);
		myService.loadCoverBase64(bookInfo.id, function(resp) {
			if (resp.data.data===null) {
				$('#'+bookInfo.id)[0].src = "img/commonLib/nocover.png";
			} else{
				$('#'+bookInfo.id)[0].src = resp.data.data;
			}
		});
	};
	
	$scope.loadBookInfo();
	
});
