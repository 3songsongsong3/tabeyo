 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../tabeStoryIncludes/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 --%>   <style>
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


<!-- 어사이드 툴킷-->
<div style="display: flex; justify-content: right; margin-top: 50px; position: fixed; margin-left: 1200px;" >
  <div class="jumbotron bg-white" style="width: 150px; height: 150px; border-radius: 80%; padding-bottom: 15px; text-align: center;">
    <!-- 페이지 업-->
    <a href="#navbarColor03">
      <svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-chevron-bar-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" d="M3.646 11.854a.5.5 0 0 0 .708 0L8 8.207l3.646 3.647a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 0 0 0 .708zM2.4 5.2c0 .22.18.4.4.4h10.4a.4.4 0 0 0 0-.8H2.8a.4.4 0 0 0-.4.4z"/>
      </svg>
    </a>
    <a href="/tabeStory/register">
		    <svg xmlns="http://www.w3.org/2000/svg" width="2em" height="2em" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
		  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
		  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
		</svg>
	</a>
  </div>
</div>
<!-- END 어사이드 툴킷-->

		<c:forEach var="timeFeed" items="${list  }">
			<!-- TimeFeedLine Container-->
				<div style="display: flex; justify-content: center; margin-top: 50px;">
					    <div class="jumbotron" style="width: 450px; padding-top: 15px; padding-bottom: 15px;">
							
							<c:choose>
									<c:when test="${timeFeed.updateDate eq timeFeed.regDate }">
											<!--  수정하지 않은 피드일 때  -->
											<p style="height: 10px; color: rosybrown; margin-left: 150px; font-size: 0.8em;" class="lead" style="margin-top: 2px;">
												등록일 : <fmt:formatDate value="${timeFeed.regDate }"
                            									pattern="yyyy-MM-dd"/></p> 
									</c:when>		
									<c:otherwise>
								  			<p style="height: 10px; color: rosybrown; margin-left: 150px; font-size: 0.8em;" class="lead" style="margin-top: 2px;">
								  						수정일 : <fmt:formatDate value="${timeFeed.updateDate }"
                            									pattern="yyyy-MM-dd"/></p> 
									</c:otherwise>
							</c:choose>
					<!--  프로필 이미지 -->
					 <img alt="profile" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkYvq7zWeYgf2yDxPRExBk-l4hhCzk6FyhWA&usqp=CAU" 
					 	style="margin-right: 20px ;width: 30px; height: 35px; border-radius: 70%;">
					 <b> <span class="lead" style="margin-top: 10px;">${timeFeed.writer }</span></b><br>
					  <p class="lead" style="margin-top: 10px;">${timeFeed.fdContent }</p>
							
					 <!--  첨부파일(이미지) 있을때랑 없을때 나눠서 해야함  -->
					 <p><img src="../images/toni K.jpg" alt="" width="400px" height="auto" style="margin-top: 15px;"></p>
					
					<!--  좋아요 기능  -->
						   <button type="button" class="btn btn-outline-primary">  
						        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-hand-thumbs-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						        <path fill-rule="evenodd" d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
						      </svg>
						    </button>
					    
					    <!-- 피드 신고 기능 -->
						      <button type="button" class="btn btn-outline-primary" >  
						        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-headset" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						          <path fill-rule="evenodd" d="M8 1a5 5 0 0 0-5 5v4.5H2V6a6 6 0 1 1 12 0v4.5h-1V6a5 5 0 0 0-5-5z"/>
						          <path d="M11 8a1 1 0 0 1 1-1h2v4a1 1 0 0 1-1 1h-1a1 1 0 0 1-1-1V8zM5 8a1 1 0 0 0-1-1H2v4a1 1 0 0 0 1 1h1a1 1 0 0 0 1-1V8z"/>
						          <path fill-rule="evenodd" d="M13.5 8.5a.5.5 0 0 1 .5.5v3a2.5 2.5 0 0 1-2.5 2.5H8a.5.5 0 0 1 0-1h3.5A1.5 1.5 0 0 0 13 12V9a.5.5 0 0 1 .5-.5z"/>
						          <path d="M6.5 14a1 1 0 0 1 1-1h1a1 1 0 1 1 0 2h-1a1 1 0 0 1-1-1z"/>
						        </svg>
						      </button>		
						     <!--  피드 상세보기  -->
						  <a href='/tabeStory/feed?fdNo=<c:out value="${timeFeed.fdNo }"/>'><button type="button" class="btn btn-outline-danger" style="margin-left:200px;">상세보기</button></a>
		    
					<c:choose>
							<c:when test="${timeFeed.replyCnt eq 0 }">
								  <hr class="my-4">
									      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									        <path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
									        <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
									      </svg>
									      <span class="lead" style="margin-top: 10px; margin-left: 10px;">댓글이 없습니다.</span>
									      <c:out value=" ${timeFeed.replyCnt }" />
							</c:when>		
							<c:otherwise>
							  <hr class="my-4">
							      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							        <path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
							        <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
							      </svg>
							      <span class="lead" style="margin-top: 10px; margin-left: 10px;"> 댓글이 <b><c:out value=" ${timeFeed.replyCnt }" /></b> 개 있습니다.</span>
							</c:otherwise>
					</c:choose>
						
						</div>
					  </div>
				<!-- END TimeFeedLine Container-->
		</c:forEach>

    <!-- paging -->
		<!-- 	  <div style="display: flex; justify-content: center;">
			    <ul class="pagination">
			      <li class="page-item disabled">
			        <a class="page-link" href="#">&laquo;</a>
			      </li>
			      <li class="page-item active">
			        <a class="page-link" href="#">1</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">2</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">3</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">4</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">5</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">6</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">7</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">8</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">9</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">10</a>
			      </li>
			      <li class="page-item">
			        <a class="page-link" href="#">&raquo;</a>
			      </li>
			    </ul>
			  </div> -->
  
 <!-- END paging -->
 
 <!-- Move to Bottom-->
<div id="end">

</div>
<br>
<br>
<br>
<br>

<!-- Slide Images-->
 <script>
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
  </script>
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
  
  
  
  
  