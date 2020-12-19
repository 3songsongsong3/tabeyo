
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../tabeStoryIncludes/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>

<!--피드 작성 폼-->
<div style="display: flex; justify-content: center; margin-top: 50px;">

  <div class="jumbotron" style="width: 450px; padding-top: 15px; padding-bottom: 15px;">
    <form role="form" action="/tabeStory/register" method="post">
		    <p style="height: 10px; color: rosybrown; margin-left: 150px; font-size: 2em;" 
		      class="lead" style="margin-top: 2px;">피드 작성</p>
		      <br>
		      <div class="form-group">
								<label>작성자</label>
								<input class="form-control" name="writer" >									
			</div>
			    <div class="form-group">
								<label>작성자 아이디</label>
								<input class="form-control" name="userId" >									
			</div>
		
		    <span class="lead" style="margin-top: 10px; "></span><br>
		 
		    <div class="form-group" style="margin-top: 10px;">
		      <textarea class="form-control"  rows="6" 
		       placeholder="당신은 무슨 생각을 하고 있습니까? " name="fdContent"></textarea>
		    </div>
		
		    <div class="form-group">
		      <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp" >
		    </div>
		    <button type="submit" class="btn btn-primary">등록</button>
	</form>
  </div>
</div>
<!--END 피드 작성 폼-->
  <%@ include file="../tabeStoryIncludes/footer.jsp" %>

