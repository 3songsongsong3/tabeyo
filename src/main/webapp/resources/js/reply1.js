console.log("REPLY MODULE....");

var replyService =(function(){
	
	function add(reply, callback, error){
		$.ajax({
			type: 'post',
			url: '/tabeFR/register',
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
	
	return {add:add};
});