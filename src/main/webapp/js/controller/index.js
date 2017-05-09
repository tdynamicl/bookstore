myApp.controller("index-controller", function($scope, $rootScope, myService) {
	
	$scope.loadNewBooks = function() {
		myService.httpPost('loadNewBook.do', {}, function(resp) {
			if (resp.data.code) {
				
			} else {
				var bookInfos = angular.copy(resp.data.data);
				//添加
				for (var i = 0; i < bookInfos.length; i++) {
					$scope.addNewBookEL(bookInfos[i]);
				}
			}
		});
		
	};
	
	$scope.addNewBookEL = function(bookInfo) {
		var bookEL = $('<div class="newBookEL">'+
			'<a href=book.html?id=' + bookInfo.id + '><img id="' + bookInfo.id + '"/></a>'+
			'<a href=book.html?id=' + bookInfo.id + '><span>' + bookInfo.name + '</span></a>'+
			'<span class="price">' + bookInfo.price + '</span>'+
			'</div>')
		//获取封面
		$('#newBooksEL .containerEL').append(bookEL);
		myService.loadCoverBase64(bookInfo.id, function(resp) {
			if (resp.data.data===null) {
				$('#'+bookInfo.id)[0].src = "img/commonLib/nocover.png";
			} else{
				$('#'+bookInfo.id)[0].src = resp.data.data;
			}
		});
	
	};
	
	$scope.loadExihibitionBook = function() {
		myService.httpPost('loadExihibitionBook.do', {}, function(resp) {
			if (resp.data.code) {
				//没有要展示的轮播图片
			} else {
				var datas = resp.data.data;
				var indicatorsEL = $('#img-exhibition .carousel-indicators');
				var innerEL = $('#img-exhibition .carousel-inner');
				var li, item;
				for (var i = 0; i < datas.length; i++) {
					li = $('<li data-target="#img-exhibition" data-slide-to="' + i + '"></li>');
					item = $('<div class="item"><a href="book.html?id=' + datas[i].bookId + '"><img id="ex' + datas[i].bookId + '"></a></div>')
					if (i===0) {
						li.addClass('active');
						item.addClass('active');
					}
					indicatorsEL.append(li);
					innerEL.append(item);
					$('#ex' + datas[i].bookId)[0].src = datas[i].imageStream;
				}
				
				
			}
		});
	};
	
	
	$scope.entry = function() {
		$scope.loadExihibitionBook();
		$scope.loadNewBooks();
	};
	// 入口函数
	$scope.entry();
	
});