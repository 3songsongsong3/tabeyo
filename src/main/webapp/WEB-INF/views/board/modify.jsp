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

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Board Modify Page</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
<!--                    DataTables Advanced Tables -->
						Board Modify Page
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
						<form role="form" action="/board/modify" method="post">
							<!-- 페이지 번호, 게시물 수 파라미터 추가  -->
							<input type="hidden" name="pageNum" value="${cri.pageNum }">
							<input type="hidden" name="amount"  value="${cri.amount }">
							<!-- 검색 조건, 키워드 파라미터 추가  -->
							<input type="hidden" name="type" value="${cri.type }">
							<input type="hidden" name="keyword"  value="${cri.keyword }">
									   
							<div class="form-group">
								<label>Board no.</label>
								<input class="form-control" name="bno"
								       value="${board.bno }" readonly>
							</div>
							<div class="form-group">
								<label>Title</label>
								<input class="form-control" name="title"
								       value="${board.title }">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea class="form-control" name="content"
										  rows="3">${board.content }</textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input class="form-control" name="writer"
								          value="${board.writer }" readonly>									
							</div>
							
							<!-- 등록일자와 수정일자 -->																						
							<input class="form-control" name="regdate" type="hidden"
							       value='<fmt:formatDate pattern="yyyy/MM/dd"
							       			              value="${board.regdate }"/>' readonly>									
							<input class="form-control" name="updateDate" type="hidden"
							       value='<fmt:formatDate pattern="yyyy/MM/dd"
								       			          value="${board.updateDate }"/>' readonly>									
														
							<button type="submit" data-oper="modify" class="btn btn-default">
								Modify	</button>			
							<button type="submit" data-oper="remove" class="btn btn-danger">
								Remove	</button>
							<button data-oper="list" class="btn btn-info">
								List	</button>
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
	          	<div class="form-group uploadDiv">
	          		<input type="file" name="uploadFile" multiple>
	          	</div>
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

<script>
$(function(){
	
	//즉시 실행함수 - 첨부 파일 목록 가져오기
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
//	 				var originPath = obj.uploadPath + "\\" + 
//	 								 obj.uuid + "_" +
//	 								 obj.fileName;
					
//	 				//\를 /로 모두 변경
//	 				originPath = originPath.replace(new RegExp(/\\/g), "/");
					
					str += "<li data-path='" + obj.uploadPath + "' " +
							  " data-uuid='" + obj.uuid + "'" +
							  " data-filename='" + obj.fileName + "'" + 
							  " data-type='" + obj.fileType + "'>" +
								"<div><span>" + obj.fileName + "</span>" +
							        "<button type='button'" + 
							        	   " data-file='" + fileCallPath + "' " + 
								   	   	   " data-type='image' " +
							        	   " class='btn btn-warning btn-circle'>" + 
									     "<i class='fa fa-times'></i></button><br>" +
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
							        "<button type='button' " + 
						        	   	   " data-file='" + fileCallPath + "' " + 
							   	   	       " data-type='file' " +
						        	       " class='btn btn-warning btn-circle'>" + 
								         "<i class='fa fa-times'></i></button><br>" +
								   "<img src='/resources/img/attach.png'></div></li>"
					
				}
			});
			$('.uploadResult ul').html(str);
		});//END $.getJSON()
	})();//END 즉시 실행함수 - 첨부 파일 목록 가져오기
	

	//X 표시 클릭 이벤트 처리
	$('.uploadResult').on('click', 'button', function(){
		console.log('delete file');
		
// 		var targetFile 	= $(this).data('file');
// 		var type 		= $(this).data('type');
		var targetLi 	= $(this).closest('li');
		
		if(confirm('파일을 삭제하시겠습니까?')) {
			targetLi.remove();	//성공하면 li 삭제
		}
		
// 		$.ajax({
// 			url		: '/deleteFile',
// 			data	: { fileName : targetFile, type : type },
// 			dataType: 'text',
// 			type	: 'POST',
// 			success	: function(result){
// 				alert(result);	
// 				targetLi.remove();	//성공하면 li 삭제
// 			}
// 		})//END ajax()
	});//END X 표시 클릭 이벤트 처리
	
	var formObj = $('form');
	
	$('button').on('click', function(e){
		e.preventDefault();	//기본 동작 막기
		
		var operation = $(this).data("oper");
		
		if(operation === 'remove') { 		//삭제 버튼인 경우
			formObj.attr('action', '/board/remove');
		} else if(operation === 'list'){	//리스트 버튼인 경우
// 			self.location = "/board/list";
// 			return;

			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var typeTag = $("input[name='type']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			
			formObj.attr('action', "/board/list")
				   .attr('method', 'get');
			formObj.empty();
			
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(typeTag);
			formObj.append(keywordTag);
		} else if(operation === 'modify'){	//수정 버튼인 경우
			var str = "";
			$('.uploadResult ul li').each(function(i, obj){
				var jobj = $(obj);
				str += "<input type='hidden' " +
					         " name='attachList[" + i + "].fileName' " +
					         " value='" + jobj.data("filename") + "'>";
					         
				str += "<input type='hidden' " +
					         " name='attachList[" + i + "].uuid' " +
					         " value='" + jobj.data("uuid") + "'>";
					         
				str += "<input type='hidden' " +
					         " name='attachList[" + i + "].uploadPath' " +
					         " value='" + jobj.data("path") + "'>";
					         
				str += "<input type='hidden' " +
					         " name='attachList[" + i + "].fileType' " +
					         " value='" + jobj.data("type") + "'>";
			});
			formObj.append(str).submit();
		}
		formObj.submit();	//전송
	});//END button 이벤트 처리
});
</script>
 
<%@ include file="../includes/footer.jsp" %>














