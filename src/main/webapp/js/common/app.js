//定义app
var myApp = angular.module('myApp', ['angular-md5']);

//放一些工具方法
myApp.service('myService', function($http){
	
	 //post一个请求，给出成功的回调
	this.httpPost = function(url, param, succFun){
		$http({
			method: 'POST',
			url: url,
			data: $.param(param),
			headers:{'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
		}).then(function(resp){
			succFun(resp);
		}, function(err){
			alert("网络异常: " + err);
		});
	};
	
	//从URL获取参数
	this.getQueryString = function(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) 
			return decodeURI(r[2]); 
		return null; 
	};
	
	//加载封面的base64编码
	this.loadCoverBase64 = function (bookId, callback) {
		this.httpPost('loadBookImage.do', {id: bookId}, function(resp){
			callback(resp);
		})
	};
	
	//加载头像的base64编码
	this.loadUserIconBase64 = function (userId, callback) {
		this.httpPost('loadUserIcon.do', {userId: userId}, function(resp){
			callback(resp);
		})
	};
	
	//显示模态框
	this.showModal = function($scope, title, content, confirmFun, _noCancel) {
		$scope.modal = {};
		$scope.modal.button = {noCancel: _noCancel===true};
		var result = {confirmed: false, canceled: false};
		$('#modalEL').on('hidden.bs.modal', function () {
			if (result.confirmed && confirmFun!==undefined) {
				confirmFun();
			}
			$scope.modal = null;
		});
		$('#modalEL').on('show.bs.modal', function(){
			var $this = $(this);
			var $modal_dialog = $this.find('.modal-dialog');
			$this.css('display', 'block');
			$modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2) });
		});
		$scope.modal.confirm = function() {
			result.confirmed = true;
		};
		$scope.modal.title = title;
		$scope.modal.content = content;
		$('#modalEL').modal({show: true});
	};
});