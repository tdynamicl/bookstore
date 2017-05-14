var cartItems = new Array();

myApp.controller("cart-controller", function($scope, $rootScope, myService) {
	
	$scope.loadCartInfo = function() {
		var param = {};
		param.userId = $rootScope.user.id;
		myService.httpPost('loadCartInfo.do', param, function(resp) {
			if (resp.data.code) {
				
			} else {
				var datas = resp.data.data;
				for (var i = 0; i < datas.length; i++) {
					$scope.loadBookInfo(datas[i]);
				} 
			}
		});
	};
	
	$scope.loadBookInfo = function(cartItem){
		myService.httpPost('loadBookInfo.do', {id: cartItem.bookId}, function(resp) {
			if(resp.data.code){
				//获取失败
			}else{
				var bookInfo = resp.data.data;
				$scope.addBookEL(bookInfo, cartItem);
				cartItems.push({id: cartItem.id, bookId:bookInfo.id, price: parseFloat(bookInfo.price), checked: false});
			}	
		});
	};
	
	$scope.addBookEL = function(bookInfo, cartItem) {
		var cartEL = $('<tr><td><input onclick="checkOne()" type="checkbox" data-id="' + cartItem.id + '"></td><td><div class="bookDitail">'+
			'<img data-bookId=' + bookInfo.id + '><div>' +
			'<a href="book.html?id='+ bookInfo.id +'"><span class="bookName">' + bookInfo.name + '</span></a>'+
			'<span class="bookDesc">' + bookInfo.desc + '</span>' +
			'<span class="authorName">' + bookInfo.authorName + '</span>' +
			'<span class="pressName">' + bookInfo.pressName + '</span></div></div>' +
			'<td><span class="price">' + bookInfo.price + '</span></td>' +
			'<td><a onclick="removeCartItem(\'' + $rootScope.user.id + '\', \''+ cartItem.id + '\')" class="cursorPointer">移除</a></td></tr>'
		);
		$('#cartEL .containerEL').append(cartEL);
		$('#cartEL .noItem').remove();
		$('.cartOperation hr').remove();
		myService.loadCoverBase64(bookInfo.id, function(resp) {
			var els = $('img[data-bookId='+bookInfo.id+']');
			if (resp.data.data===null) {
				for(var i = 0; i<els.length; i++){
					els[i].src = "img/commonLib/nocover.png";
				}
			} else{
				for(var i = 0; i<els.length; i++){
					els[i].src = resp.data.data;
				}
			}
		});
	};
	
	$scope.removeBookFromCart = function(id) {
		alert(id);
		var param = {};
		param.id = id;
		param.userId = $rootScope.user.id; 
		console.log(param);
		httpPost('removeBookFromCart.do', param, function(resp) {
			myService.showModal($scope, '提示', resp.data.message, undefined, true);
		});
	};
	
	$scope.purchaseCart = function() {
		$rootScope.checkLoginUser();
		if ($rootScope.user === null) {
			alert("您还未登录，请登录后继续");
			sessionStorage.setItem("previousURL", document.URL);
			window.location = "login.html";
		} else {
			var checkedCartIds = "";
			for (var i = 0; i < cartItems.length; i++) {
				if (cartItems[i].checked) {
					if (i!==0) {
						checkedCartIds+="|";
					}
					checkedCartIds+=cartItems[i].id;
				}
			}
			var param = {};
			param.userId = $rootScope.user.id;
			param.addr = $scope.addr.province + "（省）" +
				$scope.addr.city +" （市）" +
				$scope.addr.street + "（街道）";
			param.receiverName = $scope.name;
			param.receiverTel = $scope.tel;
			param.cartIds = checkedCartIds;
			myService.httpPost('purchaseCart.do', param, function(resp){
				myService.showModal($scope, '购物车结算结果', resp.data.message, undefined, true);
			});
		}
	};
	
	$scope.entry = function() {
		if ($rootScope.user === null) {
			myService.showModal($scope, '提示', '未登录的用户无法查看购物车', undefined, true)
		} else {
			// 加载信息
			$scope.loadCartInfo();
			
		}
	};
	$scope.entry();
	
});

var checkOne = function() {
	updateChecked();
};

var checkedAll = false;
var checkAll = function() {
	checkedAll = !checkedAll;
	$('input:checkbox').each(function () {
        $(this).attr('checked', checkedAll);
	});
	updateChecked();
};

var updateChecked = function() {
	var totalPrice = 0;
	var checkedELs = $('td input:checkbox:checked');
	resetCheckModel();
	checkedELs.each(function() {
		for (var i = 0; i < cartItems.length; i++) {
			if (cartItems[i].id === $(this)[0].dataset.id) {
				cartItems[i].checked = true;
				totalPrice += cartItems[i].price;
			}
		}
	});
	$('.cartOperation .price')[0].innerHTML=totalPrice.toFixed(2);
};

var resetCheckModel = function() {
	for (var i = 0; i < cartItems.length; i++) {
		cartItems[i].checked=false;
	}
};

var removeCartItem = function(userId, cartId) {
	$.post('removeCartItem.do', {id: cartId, userId: userId},function(resp){
		if (resp.code) {
			alert(resp.message);
		} else {
			location.reload();
		}
	 });
};

$('#fillinAddressEL').on('show.bs.modal', function(){
	var $this = $(this);
	var $modal_dialog = $this.find('.modal-dialog');
	$this.css('display', 'block');
	$modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2) });
});
