<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<style>

 .navbar-nav > li

 {

  padding-left:20px;

  padding-right:20px;
  

 }

</style>
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #505D93; opacity: 76%;">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img alt="logo" src="/images/logo.png" style="width: 100px; padding-left: 20px;"></a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav justify-content-center">
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">메인 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">공지사항</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">전자결재</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">장비예약</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="work.do">근태관리</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">메신저</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">마이페이지</a>
      </li>
    </ul>
  </div>
</nav>