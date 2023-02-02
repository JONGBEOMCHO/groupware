<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>근태관리</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js" ></script>
 <!-- bootstrap css -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/assets/css/bootstrap.min.css">
 <style>
 .date {
 	color : gray;
 	margin-bottom: 0;
 }
 
 .time{
  font-size: 60px;
 }
 .inOut{
 border: 2px solid #c7c7c7;
 height:365px;
 padding-top: 8px;
 margin-right: 5px;
 }
 .total {
 border: 2px solid #c7c7c7;
 min-height:365px;
 padding-top: 8px;
 margin-bottom: 10px;
 }
 .find-btn{
	text-align: center;
	}
	.find-btn1{
	display :inline-block;
	margin-left: 5px;
	margin-right: 5px;
	}
	
	.page{
	text-align: center;
	border-bottom: 1px solid #c7c7c7;
	}
	.chevron{
	width : 25px;
	}
	.fmt_time{
		float: right;
	}
 
 </style>
 <script>
 $(document).ready(function(){
	  });
 
 	function confirmIn() {
 		var dd = new Date();
 		var dhur = dd.getHours();		// 시
		var dmin = dd.getMinutes();	//분
		var dsec = dd.getSeconds();	//초
		var dtime = +dhur+":"+dmin+":"+dsec
 		if(window.confirm("현재시간 : "+dtime+" 입니다. 출근하시겠습니까?")){
 			location.href = '/workIn.do';
 		}else{};
 	}
 	
 	function confirmOut() {
 		var dd = new Date();
 		var dhur = dd.getHours();		// 시
		var dmin = dd.getMinutes();	//분
		var dsec = dd.getSeconds();	//초
		var dtime = +dhur+":"+dmin+":"+dsec
 		if(window.confirm("현재시간 : "+dtime+" 입니다. 퇴근하시겠습니까?")){
 			location.href = '/workOut.do';
 		}else{};
 	}	
 	
 </script>
<script type="text/javascript" src="/view/assets/js/work/time.js?ver=<%=System.currentTimeMillis()%>"></script>
</head>
<body id="bd" onload="getTime()">
	
<div class="container" style="margin-top: 10px;">
  <div class="row">
  	<!-- 출퇴근 버튼, 정보 영역 -->
    <div class="col-md-3 inOut mr-2">
			<p>${AUTHUSER.emp_kname} (직급)님, 안녕하세요</p>
			<p id="WhatDateIsItNow" class="date"></p>
			<p id="WhatTimeIsItNow" class="time"></p>
			<p>현재 [${work.status}]상태입니다</p>
			<p><span>출근시간</span><span class="fmt_time"><fmt:formatDate type="date" value="${work.work_in_time}" pattern="kk:mm:ss"/></span></p>
			<p><span>퇴근시간</span><span class="fmt_time"><fmt:formatDate type="date" value="${work.work_out_time}" pattern="kk:mm:ss"/></span></p>
			<hr/>
			<div class="find-btn">
			<button type="button" class="btn btn-outline-success find-btn1" onclick="confirmIn()">출근</button>
			<button type="button" class="btn btn-outline-primary find-btn1" onclick="confirmOut()">퇴근</button>
			</div>
    </div>
    <!-- 월별 누적 근태현황 -->
    <div class="col-md-8 total">
      <h3>누적 근태현황</h3> 
      <div class="page">
      <h2>
      <a href="work.do?pageMon=${pageAtt.mon - 1}&pageYear=${pageAtt.year}"><img src="/icon/chevron-left.svg" class="chevron"></a>
      <span>${pageAtt.year}.${pageAtt.mon}</span>
      <a href="work.do?pageMon=${pageAtt.mon + 1}&pageYear=${pageAtt.year}"><img src="/icon/chevron-right.svg" class="chevron"></a>
      </h2>
      </div>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">일자</th>
      <th scope="col">업무시작</th>
      <th scope="col">업무종료</th>
      <th scope="col">근무시간</th>
      <th scope="col">초과시간</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="month" items="${monthList}">
  	<tr>
      <th scope="row"><fmt:formatDate type="date" value="${month.work_reg_date}" pattern="dd [E]" /></th>
      <td><fmt:formatDate type="date" value="${month.work_in_time}" pattern="kk:mm:ss"/></td>
      <td><fmt:formatDate type="date" value="${month.work_out_time}" pattern="kk:mm:ss"/></td>
      <td>${month.total_day}</td>
      <td>${month.overtime}</td>
    </tr>
  	</c:forEach>
  </tbody>
</table>

    </div>
  </div>
</div>
 
</body>
</html>






