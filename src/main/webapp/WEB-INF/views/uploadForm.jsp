<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadForm.jsp</title>
</head>
<body>
	<h2>Spring file upload</h2>
	<form action="uploadFormAction" method="post"
		  enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple>
		<button>Submit</button>	  
	</form>
</body>
</html>













