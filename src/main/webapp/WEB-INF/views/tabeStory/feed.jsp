   
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../tabeStoryIncludes/header.jsp" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
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
     <!-- 1 TimeLine Container-->
<div style="display: flex; justify-content: center; margin-top: 50px;">
    <!-- Follow List Container-->
    
        <div class="jumbotron" style="width: 450px; padding-top: 15px; padding-bottom: 15px;">
        					<c:choose>
									<c:when test="${feed.updateDate eq feed.regDate }">
											<!--  수정하지 않은 피드일 때  -->
											<span style="height: 10px; color: rosybrown; padding-right: 70px; margin-left: 90px; font-size: 0.8em; margin-bottom: 10px; margin-top: 2px; " class="lead" style="margin-top: 2px;">
														등록일 : <fmt:formatDate value="${feed.regDate }"
                            									type="both" dateStyle="short" timeStyle="short"/></span>
									</c:when>		
									<c:otherwise>
											<span style="height: 10px; color: rosybrown;  padding-right: 70px; margin-left: 90px; font-size: 0.8em; margin-bottom: 10px; margin-top: 2px;" class="lead" >
													수정일 : <fmt:formatDate value="${feed.updateDate }"
                            									type="both" dateStyle="short" timeStyle="short"/></span>
									</c:otherwise>
							</c:choose>
    		<!-- 수정/삭제  -->
          <button data-oper='modify' class="btn btn-outline-primary" style=" border:0; outline:0;">
          					<svg  width="2em" height="1em" viewBox="0 0 16 16" class="bi bi-three-dots-vertical" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
           					 <path fill-rule="evenodd" d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>    </svg>
         </button>
          
          <form id='operForm' action="/tabeStory/modify" method="get">
          		<input type='hidden' id='fdNo' name='fdNo' value='${feed.fdNo }'>
 		</form>
          
          <br><br>
    
          <img alt="profile" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkYvq7zWeYgf2yDxPRExBk-l4hhCzk6FyhWA&usqp=CAU" 
          	 style="margin-right: 20px ;width: 60px; height: 65px; border-radius: 70%;">
          <span class="lead" style="margin-top: 10px;">${feed.writer }</span><br>
          
        <p class="lead" style="margin-top: 10px;">${feed.fdContent }</p>
    
        <p><img src="../images/toni K.jpg" alt="" width="400px" height="auto" style="margin-top: 15px;"></p>
    
        
        <!-- Thumb up -->
        <button type="button" class="btn btn-outline-primary">  
          <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-hand-thumbs-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
          <path fill-rule="evenodd" d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
        </svg>
      </button>
    
        <!-- report-->
        <button type="button" class="btn btn-outline-primary">  
          <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-headset" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M8 1a5 5 0 0 0-5 5v4.5H2V6a6 6 0 1 1 12 0v4.5h-1V6a5 5 0 0 0-5-5z"/>
            <path d="M11 8a1 1 0 0 1 1-1h2v4a1 1 0 0 1-1 1h-1a1 1 0 0 1-1-1V8zM5 8a1 1 0 0 0-1-1H2v4a1 1 0 0 0 1 1h1a1 1 0 0 0 1-1V8z"/>
            <path fill-rule="evenodd" d="M13.5 8.5a.5.5 0 0 1 .5.5v3a2.5 2.5 0 0 1-2.5 2.5H8a.5.5 0 0 1 0-1h3.5A1.5 1.5 0 0 0 13 12V9a.5.5 0 0 1 .5-.5z"/>
            <path d="M6.5 14a1 1 0 0 1 1-1h1a1 1 0 1 1 0 2h-1a1 1 0 0 1-1-1z"/>
          </svg>
        </button>

    
    <br>
      <!-- 댓글 목록 -->
      	<c:choose>
							<c:when test="${empty timeFeed.replyCnt }">
								  <hr class="my-4">
									      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									        <path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
									        <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
									      </svg>
									      <span class="lead" style="margin-top: 10px; margin-left: 10px;">댓글이 없습니다.</span>
							</c:when>		
							<c:otherwise>
									  <hr class="my-4">
									      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									        <path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
									        <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
									      </svg>
									      <span class="lead" style="margin-top: 10px; margin-left: 10px;"> 댓글이 ${timeFeed.replyCnt }개 있습니다.</span>
							</c:otherwise>
	</c:choose>
	<br>
	
	<!--  댓글 목록 -->
	   	<c:choose>
							<c:when test="${empty feed.replyCnt }">
							
							</c:when>		
							<c:otherwise>
												<img alt="profile" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkYvq7zWeYgf2yDxPRExBk-l4hhCzk6FyhWA&usqp=CAU" 
												     style="margin-right: 20px ;width: 50px; height: 55px; border-radius: 70%;">
												      <strong><span style="margin-right: 10px;">우주</span></strong> <span> 1등 </span>
												      <svg style="margin-left: 100px;" width="2em" height="1em" viewBox="0 0 16 16" class="bi bi-three-dots-vertical" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
													  <path fill-rule="evenodd" d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
												    </svg><br>
										      				<!--  댓글 작성 or 수정 시간 -->
										      				<c:choose>
																			<c:when test="${feed.updateDate eq feed.regDate }">
																				<!--  수정하지 않은 피드일 때  -->
																					<span style="height: 10px; color: rosybrown; margin-left: 90px; font-size: 0.8em; margin-bottom: 10px; margin-top: 2px; " class="lead" style="margin-top: 2px;">
																								등록일 : <fmt:formatDate value="${feed.regDate }"
										                            									type="both" dateStyle="short" timeStyle="short"/></span>
																			</c:when>		
																			<c:otherwise>
																					<span style="height: 10px; color: rosybrown; margin-left: 90px; font-size: 0.8em; margin-bottom: 10px; margin-top: 2px;" class="lead" >
																							수정일 : <fmt:formatDate value="${feed.updateDate }"
										                            									type="both" dateStyle="short" timeStyle="short"/></span>
																			</c:otherwise>
																	</c:choose>
										      <br> 
							</c:otherwise>
	</c:choose>
      
      <!-- END 댓글 목록-->    
      <hr>
      <!-- 댓글 쓰기-->
    
      <div style="display: flex;">
        <input type="text" class="form-control"  id="inputDefault" style="width: 300px;">
        <button type="button" class="btn btn-primary btn-lg " style="width: 70px; font-size: 0.5em;" >
          작성
         </button>
        </div>
        </div>
    <!-- End Follow List Container-->
    </div>
    <!-- 1 End TimeLine Container-->

<!-- 버튼 이벤트  -->
<script>
	$(document).ready(function() {
		
		var operForm = $("#operForm");
		
		$("button[data-oper='modify']").on("click",function(e){
			
		operForm.attr("action","/tabeStory/modify").submit();
		});
		
	});

</script>



<!-- Slide Images-->
<!--  <script>
  var slideIndex = 1;
  showDivs(slideIndex);
  
  function plusDivs(n) {
    showDivs(slideIndex += n);
  }
  
  function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("mySlides");
    if (n > x.length) {slideIndex = 1}    
    if (n < 1) {slideIndex = x.length} ;
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    x[slideIndex-1].style.display = "block";  
  }
  
  
  </script> -->
 <!-- Stars-->
<!--  <script>
        $(".star").on('click',function(){
   var idx = $(this).index();
   $(".star").removeClass("on");
     for(var i=0; i<=idx; i++){
        $(".star").eq(i).addClass("on");
   }
 });
  </script> -->
  <%@ include file="../tabeStoryIncludes/footer.jsp" %>
  