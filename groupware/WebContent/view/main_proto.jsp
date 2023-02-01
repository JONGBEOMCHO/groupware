<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resource/css/bootstrap.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<style></style>
<script>
	$(document).ready(function(){
	});//
</script>
<title>Main페이지</title>
</head>
<body>

 <script type="text/javascript">
			location.href = 'login.do';
		</script>

	<c:if test="${! empty AUTHUSER}">
		${AUTHUSER.emp_kname}님, 안녕하세요 <br/>
		AUTHUSER = ${AUTHUSER} 
	</c:if>
	
	
	
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
</body>
</html>