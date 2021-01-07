<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadAjax.jsp</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<style>
/*
.uploadResult { width:100%; background-color:lightgray; }
.uploadResult ul { 	display: flex;  			flex-flow:row; 
				   	justify-content: center;	align-items:center; }
.uploadResult ul li { list-style:none; 			
					  padding:10px; }
.uploadResult ul li img { width: 100px; }

.uploadResult ul li span { color: white; }
.bigPictureWrapper { position: absolute; 		display:none;
					 justify-content: center;	align-content: center; 
					 top: 0%;					width:100%;				
					 height:100%;			 	background-color: gray; 
					 z-index: 100;				background: rgba(255, 255, 255, 0.5 );}
.bigPicture	{ 	position: relative; 		display: flex;
				justify-content: center;	align-items: center; }
.bigPicture img { width: 600px; }
*/
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
</head>

<body>
	<h2>Spring file upload with AJAX</h2>
 	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	<button id="uploadBtn">Submit</button>	  
	
	<!-- 업로드 결과 출력 -->
	<div class="uploadResult">
		<ul>
		</ul>	
	</div>
	<!-- END 업로드 결과 출력 -->
	
	<!-- 섬네일 이미지 원본 표시 -->
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>
	</div>
	<!-- END섬네일 이미지 원본 표시 -->
 
<script>
$(function(){
	//확장자 제한 정규 표현식
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;		//최대 업로드 크기
	
	function checkExtension(fileName, fileSize){
		console.log("checkExtension");
		
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
	
	//업로드 후 <input type='file'> 부분 초기화를 위한 복제
	var cloneObj = $('.uploadDiv').clone();
	
	$('#uploadBtn').on('click', function(){
		
		console.log("uploadBtn clicked ");
		
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
				showUploadedFile(result);	//업로드 결과 출력 처리 함수 호출
				$('.uploadDiv').html(cloneObj.html());	//업로드 영역 초기화
			}
		});//END ajax()
	});//END $('#uploadBtn').on();
	
	//업로드 결과 출력 처리 ----------------------------
	var uploadResult = $('.uploadResult ul');
	function showUploadedFile(uploadResultArr){
		var str = "";
		$(uploadResultArr).each(function(i, obj){
			if(!obj.image) { //이미지가 아니면 
				//첨부파일 아이콘 표시
				var fileCallPath
					= encodeURIComponent(obj.uploadPath + "/" + 
										 obj.uuid + "_" +
										 obj.fileName);
			
				str += "<li><a href='/download?fileName=" + fileCallPath + "'>" + 
						   "<img src='/resources/img/attach.png'> "
							+ obj.fileName + "</a> " + 
						   "<span data-file='" + fileCallPath + "' " + 
						   		 "data-type='file'>X</span></li>";
			} else {		//그렇지 않으면 
				//섬네일 표시
				var fileCallPath
					= encodeURIComponent(obj.uploadPath + "/s_" + 
										 obj.uuid + "_" +
										 obj.fileName);
			
				//원본 이미지 표시
				var originPath = obj.uploadPath + "\\" + 
								 obj.uuid + "_" +
								 obj.fileName;
				
				//\를 /로 모두 변경
				originPath = originPath.replace(new RegExp(/\\/g), "/");
				
				str += "<li><a href=\"javascript:showImage('" + 
								originPath + "')\">" +
						   "<img src='/display?fileName=" + 
								fileCallPath + "'></a> " + 
						   "<span data-file='" + fileCallPath + "' " + 
					   		 "data-type='image'>X</span></li>";
			}
		});
		uploadResult.append(str);//<ul>에 <li> 추가
	}//END showUploadedFile()
	
	//X 표시 클릭 이벤트 처리
	$('.uploadResult').on('click', 'span', function(){
		var targetFile 	= $(this).data('file');
		var type 		= $(this).data('type');
		
		$.ajax({
			url		: '/deleteFile',
			data	: { fileName : targetFile, type : type },
			dataType: 'text',
			type	: 'POST',
			success	: function(result){
				alert(result);	
			}
		})//END ajax()
	});//END X 표시 클릭 이벤트 처리
	
	//원본 이미지 숨기기 처리
	$('.bigPictureWrapper').on('click', function(){
		$('.bigPicture').animate( { width:'0%', heigth:'0%'}, 1000);
		
		setTimeout(() => {
			$(this).hide();
		}, 1000);
	});//END 원본 이미지 숨기기 처리
});

//섬네일 이미지 원본 표시
function showImage(fileCallPath){
// 	alert(fileCallPath);
	
	//화면 가운데 표시
	$('.bigPictureWrapper').css('display', 'flex').show();
	
	//이미지 태그 추가
	$('.bigPicture').html("<img src='/display?fileName=" + 
							encodeURI(fileCallPath) + "'>")
				 	.animate( { width:'100%', heigth:'100%'}, 1000);
	
}//END showImage()
</script>
</body>
</html>













