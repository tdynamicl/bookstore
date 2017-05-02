/**
 * 定义app
 */
var myApp = angular.module('myApp', ['angular-md5']);

myApp.service('myService', function($http){
	
	/**
	 * post 一个请求，给出成功的回调
	 */
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
	
	/**
	 * 从URL获取参数
	 */
	this.getQueryString = function(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) 
			return decodeURI(r[2]); 
		return null; 
	};
	
	/**
	 * 加载图片
	 */
	this.loadCoverBase64 = function (bookId, callback) {
		this.httpPost('loadBookImage.do', {id: bookId}, function(resp){
			callback(resp);
		})
	};
	
});