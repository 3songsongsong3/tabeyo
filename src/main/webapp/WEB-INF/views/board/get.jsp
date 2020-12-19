<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.uploadResult {width: 100%; background-color: gray;}
.uploadResult ul{display:flex; flex-flow: row; justify-content: center; 
				align-items: center;}
.uploadResult ul li{list-style: none; padding: 10px;}
.uploadResult ul li img{width: 100px;}
.uploadResult ul li span{color:white;}
.bigPictureWrapper{position: absolute;display: none;justify-content: center;
					align-content: center; top: 0%;width: 100%; height: 100%;background-color: gray;
					z-index: 100;background:rgba(255,255,255,0.5);}
.bigPicture{position: relative;display: flex;justify-content: center; align-items: center;}
.bicPicture img{width: 600px;}
</style>

<script src="/resources/js/reply.js"></script>
 
  <div class="row">
      <div class="col-lg-12">
          <h1 class="page-header">Board Read Page</h1>
      </div>
      <!-- /.col-lg-12 -->
  </div>
  <!-- /.row -->
  <div class="row">
      <div class="col-lg-12">
          <div class="panel panel-default">
              <div class="panel-heading">
<!--                    DataTables Advanced Tables -->
					Board Read Page
              </div>
              <!-- /.panel-heading -->
              <div class="panel-body">
				<div class="form-group">
					<label>Board no.</label>
					<input class="form-control" name="bno"
					       value="${board.bno }" readonly>
				</div>
				<div class="form-group">
					<label>Title</label>
					<input class="form-control" name="title"
					       value="${board.title }" readonly>
				</div>
				<div class="form-group">
					<label>Content</label>
					<textarea class="form-control" name="content"
							  rows="3" readonly>${board.content }</textarea>
				</div>
				<div class="form-group">
					<label>Writer</label>
					<input class="form-control" name="writer"
					          value="${board.writer }" readonly>									
				</div>
				
				<button data-oper="modify" class="btn btn-default">
					Modify </button>
				<button data-oper="list" class="btn btn-info">
					List </button>
				
				<!-- 폼 태그 추가 -->
				<form role="form" action="/board/modify" id="operForm">
					<input type="hidden" id="bno" name="bno" value="${board.bno }">
					<input type="hidden" name="pageNum" value="${cri.pageNum }">
					<input type="hidden" name="amount" value="${cri.amount }">
			                   	<input type="hidden" name="keyword" value="${cri.keyword }">
			                   	<input type="hidden" name="type"    value="${cri.type }">
				</form>
              </div>
              <!-- /.panel-body -->
          </div>
          <!-- /.panel -->
      </div>
      <!-- /.col-lg-6 -->
  </div>
  <!-- /.row -->
  
  <!-- 업로드 결과 출력 -->
  <div class="bigPictureWrapper">
	<div class='bigPicture'>
	</div>	
  </div>
  <!-- END 업로드 결과 출력 -->
  
  <!-- 첨부파일 추가  -->
  <div class="row">
      <div class="col-lg-12">
          <div class="panel panel-default">
          	<div class="panel-heading">Files</div>
          	<div class="panel-body">
<!--           	<div class="form-group uploadDiv"> -->
<!--           		<input type="file" name="uploadFile" multiple> -->
<!--           	</div> -->
          		<div class="uploadResult">
          			<ul>
          			</ul>
          		</div>
          	</div>
          </div>
      </div>
      <!-- /.col-lg-12 -->
  </div> <!-- /.row -->
  <!-- END 첨부파일 추가  -->
	
  <!-- 댓글 목록  -->
  <div class='row'>
  	<div class="col-lg-12">
  		<div class="panel panel-default">
  			<div class="panel-heading">
  				<i class="fa fa-comments fa-fw"></i>
  				Reply
  				<button id="addReplyBtn"
  						class="btn btn-primary btn-xs pull-right">
  					New Reply</button>
  			</div>
  			<div class="panel-body">
  				<ul class="chat">
  					<!-- start reply -->
 					<li class="left clearfix" data-rno="12">
 						<div>
 							<div class="header">
 								<strong class="primary-font">user00</strong>
 								<small class="pull-right text-muted">
 									2020-11-01 14:14
 								</small>
 							</div>
 						</div>
 						<p>Good job!</p>
 					</li> 				
  					<!-- END reply -->
  				</ul>
  			</div><!-- END /.panel-body -->
  			
  			<!-- 댓글 목록 페이징  -->
  			<div class="panel-footer">
  			
  			</div>	<!-- END 댓글 목록 페이징  -->
  		</div>	<!-- END /.panel -->
  	</div>		<!-- END /.col-lg-6 -->
  </div>		<!-- END 댓글 목록  -->

  <!-- 댓글 모달 창 -->
  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" 
                  		  data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4 class="modal-title" id="myModalLabel">
                  		REPLY MODAL</h4>
              </div>
              <div class="modal-body">
				<div class="form-group">
					<label>Reply</label>
					<input class="form-control" 
						   name="reply" value="new reply!!">
				</div>
				<div class="form-group">
					<label>Replyer</label>
					<input class="form-control" 
						   name="replyer" value="user00">
				</div>
				<div class="form-group">
					<label>Reply Date</label>
					<input class="form-control" 
						   name="replyDate" value="2020-11-03 14:41">
				</div>
              </div><!-- END modal-body -->
              
              <div class="modal-footer">
                  <button type="button" class="btn btn-warning" 
                  		  id='modalModBtn'>Modify</button>
                  <button type="button" class="btn btn-danger" 
                  		  id='modalRemoveBtn'>Remove</button>
                  <button type="button" class="btn btn-primary" 
                  		  id='modalRegisterBtn'>Register</button>
                  <button type="button" class="btn btn-default" 
                  		  id='modalCloseBtn' data-dismiss='modal'>
                  		  						Close</button>
              </div>
          </div>	<!-- /.modal-content -->
      </div>		<!-- /.modal-dialog -->
  </div>			<!-- /.modal -->
  <!-- END 댓글 모달 창 -->

<script>
$(function(){
	var formObj = $('form');
	
	$('button').on('click', function(e){
		e.preventDefault();	//기본 동작 막기
		
		var operation = $(this).data("oper");
		
		if(operation === 'list'){	//리스트 버튼인 경우
			formObj.find("#bno").remove();
			formObj.attr('action', "/board/list");
			formObj.submit();
		} else if(operation === 'modify') {
			formObj.submit();	//수정 버튼인 경우는 그냥 전송
		} else {
			return;
		}
	});//END button 이벤트 처리
 	
	var bnoValue = "${board.bno}";
	var replyUl = $(".chat");	//
 
 	showList(1);	//
 
 	function showList(page){	//댓글 목록 출력 함수 - <li>출력
 
		replyService.getList(	//replyService의 getList 함수 호출
		 	{ bno:bnoValue, page: page || 1 },
// 		 	function(list){
		 	function(replyCnt, list){
		 	
		 		if(page == -1) { //page 번호가 -1이면
			 		pageNum = Math.ceil(replyCnt/10.0); //마지막 페이지를 찾아서
			 		showList(pageNum);					//다시 목록 표시
			 		return;
		 		}
		 	
		 		//댓글 목록이 없는 경우
		 		if(list == null || list.length == 0){
 		 			replyUl.html("");
		 			return;
		 		}
		 		
		 		//댓글 목록이 있는 경우
		 		var str = "";
		 		for(var i=0 ; i<list.length ; i++){	//<li> 구성
		 			str += "<li class='left clearfix' data-rno='" + list[i].rno +"'>" +
						   "	<div><div class='header'><strong class='primary-font'>" + 
						  		         list[i].replyer + "</strong>" + 
						   "      <small class='pull-right text-muted'>" + 
						 		 	   /*  list[i].replyDate */
						 		 	   replyService.displayTime(list[i].replyDate) + "</small></div></div>" +
						   "      <p>" + list[i].reply + "</p></li>";
		 		}
	 			replyUl.html(str);	//<ul>에 <li> 추가
	 			
	 			showReplyPage(replyCnt);	//페이지 번호 표시 함수 호출
		 	} 
		);//END getList
 	}//END showList()
 	
 	//댓글 목록 페이징 -----------------------------
 	var pageNum = 1;
 	var replyPageFooter = $('.panel-footer');
 	
 	function showReplyPage(replyCnt){
		var endNum 		= Math.ceil(pageNum/10.0) * 10;	//끝 페이지 계산
		var startNum 	= endNum - 9;					//시작 페이지 계산
		
		var prev = startNum != 1;		//이전 페이지
		var next = false; 				//다음 페이지

		//전체 데이터 수를 반영하여 실제 끝 페이지 계산
		if(endNum * 10 >= replyCnt) {
			endNum = Math.ceil(replyCnt/10.0);
		}
		
		//다음 페이지 활성화 처리
		if(endNum * 10 < replyCnt) {
			next = true; 
		}
		
		//페이지 번호 표시 ul 작성
		var str = "<ul class='pagination pull-right'>";
		
		//이전 페이지 링크 처리
		if(prev) {
			str += "<li class='page-item'>" + 
				   "    <a class='page-link' href='" + (startNum - 1) + "'>"+
				   		"Previous</a></li>";	
		}
		
		//페이지 번호 출력 및 링크 처리
		for(var i=startNum; i<=endNum ; i++){
			var active = pageNum == i ? 'active' : '' ;
			str += "<li class='page-item " + active + "'>" +
				   "    <a class='page-link' href='" + i + "'>" + i + "</a></li>";
		}
		
		//다음 페이지 링크 처리
		if(next) {
			str += "<li class='page-item'>" + 
				   "    <a class='page-link' href='" + (endNum + 1) + "'>"+
				   		"Next</a></li>";	
		}
		str += "</ul>";
		replyPageFooter.html(str);	//페이지 번호 출력
 	}//END showReplyPage
 	
 	//댓글 페이지 번호 클릭 시 새로운 댓글 가져오기
 	replyPageFooter.on('click', 'li a', function(e){
 		e.preventDefault();
 		
 		var targetPageNum = $(this).attr('href');
 		pageNum = targetPageNum;
 		showList(pageNum);
	});//END 댓글 페이지 번호 클릭 시 새로운 댓글 가져오기
 	
 	
 	//모달 창 관련 부분-----------------------------
 	var modal = $('.modal');
 	var modalInputReply 	= modal.find("input[name='reply']");	//댓글
 	var modalInputReplyer 	= modal.find("input[name='replyer']");	//작성자
 	var modalInputReplyDate = modal.find("input[name='replyDate']");//작성일자
 	
 	var modalModBtn 	 = $('#modalModBtn'); 		//수정 버튼
 	var modalRemoveBtn 	 = $('#modalRemoveBtn'); 	//삭제 버튼
 	var modalRegisterBtn = $('#modalRegisterBtn'); 	//등록 버튼
 	
 	//신규 댓글 버튼 이벤트 처리
 	$('#addReplyBtn').on('click', function(){
 		modal.find('input').val('');	//입력 양식 값 지우기
 		
 		modalInputReplyDate.closest('div').hide();			//작성일자 숨기기
 		modal.find('button[id != modalCloseBtn]').hide();	//닫기 버튼 이외 숨기기
 		modalRegisterBtn.show();							//등록 버튼 보이기
 		
 		modal.modal('show');	//모달창 보이기
 	});//END 신규 댓글 버튼 이벤트 처리
 	
 	//댓글 등록 버튼 이벤트 처리
 	modalRegisterBtn.on('click', function(){
 		var reply = { 
 			bno		: bnoValue, 
 			reply	: modalInputReply.val(), 
 			replyer	: modalInputReplyer.val()
 		}
 		
 		//replyService의 add 함수 호출
		replyService.add(
		 	    reply,
		 	    function(result){
		 		    alert(result);

		 	 		modal.find('input').val('');	//입력 양식 값 지우기
		 	 		modal.modal('hide');			//모달창 숨기기
		 	 		
// 		 	 		showList(1);	//신규 댓글 목록 가져오기
		 	 		showList(-1);	//         "         - 마지막 페이지 표시
		 	    } 
	 	);//END add()
 	});//END 댓글 등록 버튼 이벤트 처리
 	
 	//댓글 클릭 이벤트 처리
 	$('.chat').on('click', 'li', function(){
 		
 		var rno = $(this).data('rno');	//댓글 번호 가져오기
 		
 		//replyService의 get 함수 호출
 		replyService.get(rno,  function(data){
		 	modalInputReply.val(data.reply);		//댓글 표시
		 	modalInputReplyer.val(data.replyer);	//작성자 표시
		 	modalInputReplyDate						//작성일 표시 및 읽기 전용 처리
		 		.val(replyService.displayTime(data.replyDate))
		 		.attr('readonly','readonly'); 

		 	modal.data('rno', data.rno);			//댓글 번호 저장
		 	
		 	modalInputReplyDate.closest('div').show();			//작성일자 보이기	
	 		modal.find('button[id != modalCloseBtn]').hide();	//닫기 버튼 이외 숨기기
	 		modalModBtn.show();			//수정 버튼 보이기
	 		modalRemoveBtn.show();		//삭제 버튼 보이기
	 		
	 		modal.modal('show');	//모달창 보이기
	 	});//END get
 	});//END 댓글 클릭 이벤트 처리
 	
 	//댓글 수정 버튼 이벤트 처리
 	modalModBtn.on('click', function(){
 		var reply = { 
 			rno		: modal.data('rno'), 
 			reply	: modalInputReply.val()
 		}
 		
 		//replyService의 update 함수 호출
	 	replyService.update(
	 			reply,
		 	    function(result){
		 		    alert(result);
		 		    modal.modal('hide');	//모달창 숨기기
// 		 	 		showList(1);			//수정 댓글 목록 가져오기
		 		    showList(pageNum);	
		 	    } 
	 	);//END update()
 	});//END 댓글 수정 버튼 이벤트 처리

 	//댓글 삭제 버튼 이벤트 처리
 	modalRemoveBtn.on('click', function(){
 		var rno	= modal.data('rno');
 		
 		//replyService의 remove 함수 호출
	 	 replyService.remove(rno, function(count){
		 		console.log("replyService.remove count : " + count);
		 		if(count == 'success'){
		 			alert('remove ok');
		 		    modal.modal('hide');	//모달창 숨기기
// 		 	 		showList(1);			//수정 댓글 목록 가져오기
		 		    showList(pageNum);	
		 		}
		 	}, function(er){
		 		alert('remove error');
		 	}
	 	 );//END remove
 	});//END 댓글 삭제 버튼 이벤트 처리
});//END $(function(){

//즉시 실행함수 - 첨부 파일 목록 가져오기---------------------------
(function(){
	var bno = '<c:out value="${board.bno}"/>';
	
	$.getJSON("/board/getAttachList", {bno : bno }, function(arr){
		console.log(arr);
		
		var str = "";
		$(arr).each(function(i, obj){
			if(obj.fileType) { //이미지이면 
				//섬네일 표시
				var fileCallPath
					= encodeURIComponent(obj.uploadPath + "/s_" + 
										 obj.uuid + "_" +
										 obj.fileName);
			
				//원본 이미지 표시
// 				var originPath = obj.uploadPath + "\\" + 
// 								 obj.uuid + "_" +
// 								 obj.fileName;
				
// 				//\를 /로 모두 변경
// 				originPath = originPath.replace(new RegExp(/\\/g), "/");
				
				str += "<li data-path='" + obj.uploadPath + "' " +
						  " data-uuid='" + obj.uuid + "'" +
						  " data-filename='" + obj.fileName + "'" + 
						  " data-type='" + obj.fileType + "'>" +
							"<div>" +
						   		"<img src='/display?fileName=" + fileCallPath + "'>" +
						   	"</div></li>";
			} else {		//그렇지 않으면 
				//첨부파일 아이콘 표시
				var fileCallPath
					= encodeURIComponent(obj.uploadPath + "/" + 
										 obj.uuid + "_" +
										 obj.fileName);
			
				var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
				
				str += "<li data-path='" + obj.uploadPath + "' " +
				  		  " data-uuid='" + obj.uuid + "'" +
						  " data-filename='" + obj.fileName + "'" + 
						  " data-type='" + obj.fileType + "'>" +
							"<div><span>" + obj.fileName + "</span>" +
							   "<img src='/resources/img/attach.png'></div></li>"
				
			}
		});
		$('.uploadResult ul').html(str);
	});//END $.getJSON()
})();//END 즉시 실행함수 - 첨부 파일 목록 가져오기

//첨부파일 클릭 이벤트 처리
$('.uploadResult').on('click', 'li', function(){
	var liObj = $(this);
	var path = encodeURIComponent(liObj.data('path') + '/' +
								  liObj.data('uuid') + '_' +
								  liObj.data('filename'));
	
	if(liObj.data('type')){	//이미지이면 화면 표시
		showImage(path.replace(new RegExp(/\\/g), '/'));
	} else {				//이미지가 아니면 다운로드
		self.location = '/download?fileName=' + path;
	}
});//END 첨부파일 클릭 이벤트 처리

//섬네일 이미지 원본 표시
function showImage(fileCallPath){
//	alert(fileCallPath);
	
	//화면 가운데 표시
	$('.bigPictureWrapper').css('display', 'flex').show();
	
	//이미지 태그 추가
	$('.bigPicture').html("<img src='/display?fileName=" + 
							   fileCallPath  + "'>")
				 	.animate( { width:'100%', heigth:'100%'}, 1000);
}//END showImage()

//원본 이미지 숨기기 처리
$('.bigPictureWrapper').on('click', function(){
	$('.bigPicture').animate( { width:'0%', heigth:'0%'}, 1000);
	
	setTimeout(() => {
		$(this).hide();
	}, 1000);
});//END 원본 이미지 숨기기 처리
 </script>
<%@ include file="../includes/footer.jsp" %>























