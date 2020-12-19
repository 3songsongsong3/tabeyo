
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>


<%@ include file="../tabeStoryIncludes/header.jsp" %>
 
	<!-- Follow/Follower Lists-->
	<div style="display: flex; justify-content: center; margin-top: 50px;">
					<!-- Follow List Container-->
					
					    <div class="jumbotron" style="width: 450px;">
					    <h1 class="display-3">팔로잉</h1>
						<c:choose>
			     			<c:when test="${fn:length(following )}==0}">
					 	       팔로잉 유저가 없습니다.
					 	   </c:when>
			 						  <c:otherwise>
										        <c:forEach var="following" items="${following }"  >
									  				   <img alt="profile" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkYvq7zWeYgf2yDxPRExBk-l4hhCzk6FyhWA&usqp=CAU" style="margin-right: 20px ;width: 40px; height: 45px; border-radius: 70%;">
									                   <a href='/tabeStory/get?userId=<c:out value="${following.followingUserId }"/>'>
									                   	<span class="lead" style="margin-top: 10px;">${following.followingUser } <!-- 팔로워 닉네임 출력 --></span></a>
									   				   <svg style="margin-left: 40px;" width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-person-plus" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									     			   <path fill-rule="evenodd" d="M8 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10zM13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
										     </c:forEach>
								    </c:otherwise> 
				 </c:choose> 
				
					    
					    </div>
					<!-- End Follow List Container-->
			
					<!-- Follower List Container-->
			  <div class="jumbotron" style="width: 450px;">
				<h1 class="display-3">팔로워</h1>			     
			 	<c:choose>
			     			<c:when test="${fn:length(follower )}==0}">
					 	       팔로워 유저가 없습니다.
					 	   </c:when>
			 						  <c:otherwise>
										        <c:forEach var="follower" items="${follower }"  >
									  				   <img alt="profile" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkYvq7zWeYgf2yDxPRExBk-l4hhCzk6FyhWA&usqp=CAU" style="margin-right: 20px ;width: 40px; height: 45px; border-radius: 70%;">
									                   <a href="#"><span class="lead" style="margin-top: 10px;">${follower.followerUser } <!-- 팔로워 닉네임 출력 --></span></a>
									   				   <svg style="margin-left: 40px;" width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-person-plus" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									     			   <path fill-rule="evenodd" d="M8 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10zM13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
										     </c:forEach>
								    </c:otherwise> 
				 </c:choose> 
				
				    </svg>
	
			</div>
		<!-- End Follower List Container-->
		</div>
		<!-- End Follw/Follwer Lists-->
<%@ include file="../tabeStoryIncludes/footer.jsp" %>

