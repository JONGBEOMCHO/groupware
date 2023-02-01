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



	<c:if test="${! empty AUTHUSER}">
		${AUTHUSER.emp_kname}님, 안녕하세요 <br/>
		AUTHUSER = ${AUTHUSER}
		 
	</c:if>
	<hr/>
	<c:if test="${! empty AUTHUSER && (AUTHUSER.emp_grade eq 5)}">	 
 ${AUTHUSER.emp_kname}님 접속중 
<ul>          
 <li><a href="<%=request.getContextPath()%>/logout.do">로그아웃</a></li>
 <li><a href="<%=request.getContextPath()%>/changePwd.do">비밀번호 변경</a></li>
 <li><a href="#">회원관리</a></li>
 <li><a href="#">게시판관리</a></li>
 <li><a href="<%=request.getContextPath()%>/work.do">근태관리</a></li>
</ul>
</c:if>
<hr/>
<c:if test="${! empty AUTHUSER}">	 
 ${AUTHUSER.emp_kname}님 접속중 
<ul>          
 <li><a href="<%=request.getContextPath()%>/logout.do">로그아웃</a></li>
 <li><a href="<%=request.getContextPath()%>/changePwd.do">비밀번호 변경</a></li>
 <li><a href="<%=request.getContextPath()%>/work.do">근태관리</a></li>
 <li><a href="#">직원 근태현황</a></li>
</ul>
</c:if>
	
	
	
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
</body>
</html>