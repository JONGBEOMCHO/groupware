<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jdbc.conn.ConnectionProvider" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<style></style>
<script>
</script>
<title>연결테스트</title>
</head>
<body>
<%
	try (Connection conn = ConnectionProvider.getConnection()) {
		out.println("커넥션 연결 성공");
	} catch(SQLException ex){
		out.println("커넥션 연결 실패 :"+ex.getMessage());
		application.log("커넥션 연결 실패", ex);
	}

%>


</body>
</html>