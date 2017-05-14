myApp.controller('comment-controller', function($scope, $rootScope, $filter, myService) {
	$scope.currIndex = 0;
	$scope.noComment = false;
	$scope.noMoreComment = false;
	$scope.commentsEL = $("#commentsEL");
	
	$scope.loadComment = function() {
		var param = {};
		param.bookId = myService.getQueryString("id");
		param.index = $scope.currIndex;
		myService.httpPost('loadComment.do', param, function(resp) {
			if (resp.data.code) {
				
			} else {
				var comments = resp.data.data;
				for (var i = 0; i < comments.length; i++) {
					$scope.addCommentEL(comments[i]);
					$scope.currIndex++;
				}
			}
		});
	};
	
	$scope.addCommentEL = function(comment) {
		$('#commentsEL .noItem').remove();
		if ($scope.isFirstEL===false) {
			$scope.commentsEL.append($('<hr>'));
		}
		$scope.isFirstEL=false;
		var commentELString = '<div class="comment"><img id="' + comment.userId + '"><div><div class="commentStars">';
		for(var i = 1; i<6; i++){
			if (i<=comment.level) {
				commentELString += '<span class="ratyStar fullStar"></span>'
			} else {
				commentELString += '<span class="ratyStar emptyStar"></span>'
			}
		}
		commentELString += '</div><p class="commentContent">' + comment.content +
		'</p><div class="right-bottom"><span class="commentUser">' + 
		comment.nickname + ' </span><span class="commentTime">' + $filter('date')(comment.time, "yyyy-MM-dd HH:mm:ss") + '</span></div></div></div>';
		var commentEL = $(commentELString);
		$scope.commentsEL.append(commentEL);
		myService.loadUserIconBase64(comment.userId, function(resp) {
			if (resp.data.data===null) {
				$('#'+comment.userId)[0].src = "img/commonLib/noUserIcon.jpg";
			} else{
				$('#'+comment.userId)[0].src = resp.data.data;
			}
		});
	};
	
	$scope.loadComment();
	
});