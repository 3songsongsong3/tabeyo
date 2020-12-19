 
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../tabeStoryIncludes/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
 <style>
      *{margin:0; padding:0;}
    .star{
      display:inline-block;
      width: 30px;height: 60px;
      cursor: pointer;
    }
    .star_left{
      background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat 0 0; 
      background-size: 60px; 
      margin-right: -3px;
    }
    .star_right{
      background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat -30px 0; 
      background-size: 60px; 
      margin-left: -3px;
    }
    .star.on{
      background-image: url(http://gahyun.wooga.kr/main/img/testImg/star_on.png);
    }
   </style>
   
  <form role="form" action="/tabeStory/modify" method="post">
			<!-- 1 TimeLine Container-->
			<div style="display: flex; justify-content: center; margin-top: 50px;">
			<!-- Follow List Container-->
			
			    <div class="jumbotron" style="width: 450px; padding-top: 15px; padding-bottom: 15px;">
			      <span 
			      style="height: 10px; color: rosybrown; margin-left: 80px; font-size: 2em;" class="lead" 
			      style="margin-top: 2px;"> 피드 수정,삭제 </span>
			      <hr>
			      <!-- 수정/삭제 버튼-->
				<input type="hidden" class="lead" 
			      style="margin-top: 10px;" name="fdNo" value="${feed.fdNo  }"  ><br>
			
			      <input type="text" class="lead" 
			      style="margin-top: 10px;" name="writer" value="${feed.writer  }" readonly ><br>
			 
					<div class="form-group">
										<label>작성자 아이디</label>
										<input class="form-control" name="userId" value="${feed.userId }" readonly>									
					</div>
			   
			    <div class="form-group" style="margin-top: 10px;">
			      <textarea class="form-control" id="exampleTextarea" rows="6" 
			       placeholder="" name="fdContent">${feed.fdContent  }</textarea>
			    </div>
			
			
			
			    <hr class="my-4">
			    
			    <div class="form-group">
			      <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp" >
			    </div>
			    <button type="button" data-oper='modify' class="btn btn-primary" >수정</button>
			    <button type="button" data-oper='remove'  class="btn btn-danger">삭제</button>
			
			
			    </div>
			<!-- End Follow List Container-->
			</div>
			<!-- 1 End TimeLine Container-->
</form>
<script type="text/javascript">
	$(document).ready(function(){
		
		var formObj = $("form");
		
		$('button').on("click",function(e){
			
			e.preventDefault();
			
			var operation =$(this).data("oper");
			
			console.log(operation);
			
			if(operation === 'remove'){
				formObj.attr("action","/tabeStory/remove");
			}

			if(operation === 'modify'){
				formObj.attr("action","/tabeStory/modify");
			}
			formObj.submit();
		});
		
	});
</script>
<%@ include file="../tabeStoryIncludes/footer.jsp" %>
