<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 --%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>타베모노가타리</title>
      
      
		 <!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		 -->
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>		 
 <link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>

</head>
<body>


    <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.html"><img src="https://postfiles.pstatic.net/MjAyMDEyMTRfMTY1/MDAxNjA3OTEyMDI1Nzg5.5Abb4SQXkQxVg8VFEwRL_SUXX4HySUrSIPQl86NjoLog.4y7LsrCLQNC9U6WD98XQVY5GkPpTN0-xnKuDy5vrppog.PNG.tlahsh12/%ED%83%80%EB%B2%A0%EC%9A%94_%EB%A1%9C%EA%B7%B82.PNG?type=w773" alt="" style="height: 60px;"></a>
            </div>
            <!-- /.navbar-header -->
            <nav class="navbar navbar-expand-lg navbar-white bg-white">
                <div class="collapse navbar-collapse" id="navbarColor03">
                    <ul class="navbar-nav mr-auto">
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control" type="text" placeholder="아이디">
                            <input class="form-control" type="password" placeholder="패스워드">
                            <button class="btn btn-secondary">로그인</button>
                        </form>
                        <li class="nav-item"><a class="nav-link" href="#">ID찾기</a>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="#">PW찾기</a>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
                    </ul>
                </div>
            </nav>
            <!-- /.navbar-static-side -->
            </nav>
            <!-- /.navbar-top-links -->
    </div>
    
 <!-- Tabs-->
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav" style="text-align: center;">
            <li class="nav-item" style="width: 450px; font-size: 20px;"><a
                class="nav-link" href='/tabeStory/get?userId=<c:out value="${userVO.userId }"/>'>유저정보</a></li>
            <li class="nav-item" style="width: 450px; font-size: 20px;"><a
                class="nav-link" href='/tabeStory/followList?userId=<c:out value="${userVO.userId }"/>'>팔로잉/팔로워 목록</a></li>
            <li class="nav-item" style="width: 450px; font-size: 20px;"><a
                class="nav-link" href='/tabeStory/timelineList?userId=<c:out value="${userVO.userId }"/>'>타임라인 목록</a></li>
                    <svg style="color: blanchedalmond; font-size: 2em; margin-top:5px;" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-plus" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M8 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10zM13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                    </svg>
        </ul>
    </div>
  </nav>
 <!-- End Tabs-->
 
