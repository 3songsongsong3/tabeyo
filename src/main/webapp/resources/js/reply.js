console.log("REPLY MODULE....");

var replyService = (function(){
	
	//댓글 등록 함수 
	function add(reply, callback, error){
		$.ajax({
			type: 'post',
			url: '/replies/new',
			data: JSON.stringify(reply),
			contentType : 'application/json; charset=UTF-8',
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});//END ajax()
	}//END add()
	
	
	//댓글 목록 가져오기
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data){
				if(callback){
//					callback(data);
					callback(data.replyCnt, data.list);
				}
			}
		).fail(function(xhr, status, er){
			if(error){
				error(er);
			}
		});//END getJSON() fail()
	}//END getList()	
	
	//댓글 삭제 함수 
	function remove(rno, callback, error){
		$.ajax({
			type: 'delete',
			url: '/replies/' + rno,
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});//END ajax()
	}//END remove()
	
	//댓글 수정 함수 
	function update(reply, callback, error){
		$.ajax({
			type: 'put',
			url: '/replies/' + reply.rno ,
			data: JSON.stringify(reply),
			contentType : 'application/json; charset=UTF-8',
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});//END ajax()
	}//END update()
	

	//댓글 하나 가져오기
	function get(rno, callback, error){
		$.get("/replies/" + rno + ".json",
			function(data){
				if(callback){
					callback(data);
				}
			}
		).fail(function(xhr, status, er){
			if(error){
				error(er);
			}
		});//END get() fail()
	}//END get()	
	
	//댓글 시간 표시 함수
	function displayTime(timeValue){
		var today 	= new Date();
		var gap  	= today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		
		//등록일과 오늘 날짜 체크 
		if(today.getDate() === dateObj.getDate()){
			//같으면 '시:분:초' 출력
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [ (hh > 9 ? '' : '0') + hh, ':', 
					 (mi > 9 ? '' : '0') + mi, ':',
					 (ss > 9 ? '' : '0') + ss       ].join('');
		} else { 	
			//다르면 '연/월/일' 출력
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return [  yy, '/', 
					 (mm > 9 ? '' : '0') + mm, '/',
					 (dd > 9 ? '' : '0') + dd       ].join('');
		}
	}//END displayTime()
	
	return { get:get, update:update, remove:remove, 
			 getList:getList, add:add,
			 displayTime:displayTime };
})();















