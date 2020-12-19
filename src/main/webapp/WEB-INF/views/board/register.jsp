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
                <h1 class="page-header">Board Register</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
<!--                    DataTables Advanced Tables -->
						Board Register
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
						<form role="form"
							  action="/board/register"
							  method="post">
							<div class="form-group">
								<label>Title</label>
								<input class="form-control" name="title">
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea class="form-control" name="content"
										  rows="3"></textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input class="form-control" name="writer">									
							</div>
							
							<button type="submit" class="btn btn-default">
								Submit Button
							</button>
							<button type="reset" class="btn btn-default">
								Reset Button
							</button>
						</form>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->
        </div>
        <!-- /.row -->


		<!-- 첨부파일 추가  -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                	<div class="panel-heading">File Attach</div>
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
$(function(e){
	var formObj = $("form[role='form']");
	
	//submit 버튼 제출 방지
	$("button[type='submit']").on('click', function(e){
		e.preventDefault();
		console.log('submit clicked!!');
		
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
	});//END submit 버튼 제출 방지

	//확장자 제한 정규 표현식
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;		//최대 업로드 크기
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert('파일 사이즈(5MB) 초과');
			return false;
		}
		
		if(regex.test(fileName)){
			alert('해당 파일 업로드 불가');
			return false;
		}
		return true;
	}//END checkExtension()
	
	
	//첨부 파일 선택 이벤트 처리
	$("input[type='file']").change(function(){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		console.log(files);
		
		for(var i=0 ; i<files.length ; i++){	//formData 객체에 파일 추가
			//확장자 및 파일 크기 확인
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append('uploadFile', files[i]);
		}
		
		$.ajax({
			url			: '/uploadAjaxAction',
			processData : false,
			contentType : false,
			data		: formData,
			type		: 'POST',
			dataType	: 'json',
			success		: function(result){
				console.log(result);
 				showUploadResult(result);	//업로드 결과 출력 처리 함수 호출
// 				$('.uploadDiv').html(cloneObj.html());	//업로드 영역 초기화
			}
		});//END ajax()
	});//END $('#uploadBtn').on();
	//END 첨부 파일 선택 이벤트 처리
	
	//업로드 결과 출력 처리 ----------------------------
	function showUploadResult(uploadResultArr){
		//첨부 파일이 없는 경우 중단
		if(!uploadResultArr || uploadResultArr.length == 0 ){
			return;
		}

		var uploadUL = $('.uploadResult ul');
		
		var str = "";
		$(uploadResultArr).each(function(i, obj){
			if(obj.image) { //이미지이면 
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
						  " data-type='" + obj.image + "'>" +
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
						  " data-type='" + obj.image + "'>" +
							"<div><span>" + obj.fileName + "</span>" +
						        "<button type='button' " + 
					        	   	   " data-file='" + fileCallPath + "' " + 
						   	   	       " data-type='file' " +
					        	       " class='btn btn-warning btn-circle'>" + 
							         "<i class='fa fa-times'></i></button><br>" +
							   "<img src='/resources/img/attach.png'></div></li>"
				
			}
		});
		uploadUL.append(str);//<ul>에 <li> 추가
	}//END showUploadResult()
	

	//X 표시 클릭 이벤트 처리
	$('.uploadResult').on('click', 'button', function(){
		console.log('delete file');
		
		var targetFile 	= $(this).data('file');
		var type 		= $(this).data('type');
		var targetLi 	= $(this).closest('li');
		
		$.ajax({
			url		: '/deleteFile',
			data	: { fileName : targetFile, type : type },
			dataType: 'text',
			type	: 'POST',
			success	: function(result){
				alert(result);	
				targetLi.remove();	//성공하면 li 삭제
			}
		})//END ajax()
	});//END X 표시 클릭 이벤트 처리
});
</script>
<%@ include file="../includes/footer.jsp" %>


















