<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title></title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
     <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./assets/css/font-awesome.css">
    <link rel="stylesheet" href="./assets/css/templatemo-hexashop.css">
    <link rel="stylesheet" href="./assets/css/owl-carousel.css">
    <link rel="stylesheet" href="./assets/css/lightbox.css">
     </head>
    
    <body>
    <script src="./assets/js/jquery-2.1.0.min.js"></script>

    <!-- Bootstrap -->
    <script src="./assets/js/popper.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>

    <!-- Plugins -->
    <script src="./assets/js/owl-carousel.js"></script>
    <script src="./assets/js/accordions.js"></script>
    <script src="./assets/js/datepicker.js"></script>
    <script src="./assets/js/scrollreveal.min.js"></script>
    <script src="./assets/js/waypoints.min.js"></script>
    <script src="./assets/js/jquery.counterup.min.js"></script>
    <script src="./assets/js/imgfix.min.js"></script> 
    <script src="./assets/js/slick.js"></script> 
    <script src="./assets/js/lightbox.js"></script> 
    <script src="./assets/js/isotope.js"></script> 
    
    <!-- Global Init -->
    <script src="./assets/js/custom.js"></script>


</head>
<body>
<%@ include file="module/top00.jsp" %>
<!-- ***** Main Banner Area Start ***** -->
    <div class="main-banner" id="top">
        <div class="container-fluid">
            <div class="row">       
                <div class="col-lg-6">
                    <div class="right-content">
                        <div class="row">
                                 <!-- ***** 프로필 ***** -->
                            <div class="col-lg-4">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4><br>현재접속자</h4>
                                            <br> <br> <br> <br> <br>
                                            <span>~~~님 </span><br>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>마이페이지</h4>
                                                <p></p>
                                                <div class="main-border-button">
                                                    <a href="#">바로가기</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/00.png">
                                    </div>
                                </div>
                            </div>
                                 <!-- ***** 프로필 ***** -->
                                 <!-- ***** 메신저 ***** -->
                            <div class="col-lg-4">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>메신저</h4>
                                            <span>채팅방참여</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>그룹 메신저</h4>
                                                <div class="main-border-button">
                                                    <a href="#">바로가기</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/gray.jpg">
                                    </div>
                                </div>
                            </div>
                                 <!-- ***** 메신저 ***** -->
                           <!-- ***** 근태관리 ***** -->      
                            <div class="col-lg-4">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>근태관리</h4>
                                            <span>월별근태<br>출&결근처리</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>근태관리<br>통합페이지</h4>
                                                 <div class="main-border-button">
                                                    <a href="#">바로가기</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/gray.jpg">
                                    </div>
                                </div>
                            </div>
                                 <!-- ***** 근태관리 ***** -->
                                <!-- ***** 임시 ***** -->
                            <div class="col-lg-6">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>임시블럭</h4>
                                            <span>추가예정</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>공사중</h4>
                                                <p>ToDo리스트추가예정<br>실패시 삭제</p>
                                                <div class="main-border-button">
                                                    <a href="#">바로가기</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/gray.jpg">
                                    </div>
                                </div>
                            </div>
                                 <!-- ***** 임시 ***** -->
                                 <!-- ***** 전자결재 ***** -->
                            <div class="col-lg-6">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>전자결재</h4>
                                            <span>문서작성,결재대기,결재확인</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>전자결재<br>통합페이지</h4>
                                                <div class="main-border-button">
                                                    <a href="#">바로가기</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/gray.jpg">
                                    </div>
                                </div>
                            </div>
                                <!-- ***** 전자결재 ***** -->
                        </div>
                    </div>
                </div>
     <!-- ***** 공지사항 ***** -->
                <div class="col-lg-5">
                    <div class="left-content">
                        <div class="thumb">
                            <div class="inner-content">
                                <h4>공지사항</h4>
                                <span>사내이벤트 &amp; 경조사</span>
                                <div class="main-border-button">
                                    <a href="#">바로가기</a>
                                </div>
                            </div>
                            <img src="assets/images/gray.jpg">
                        </div>
                    </div>
                </div>
                   <!-- ***** 공지사항 ***** -->  
            </div>
        </div>
    </div>


     <!-- ***** 중고장터 ***** -->
    <section class="section" id="men" style="margin-top:-100px;">
        <div class="container">
            <div class="row">
                <div class="col-lg-5">
                    <div class="section-heading">
                        <h3><strong>사내 중고장터</strong></h3>
                     </div>
                </div>
            </div>
        </div>
        <div class="container" style="margin-top:-50px;">
            <div class="row">
                <div class="col-lg-10">
                    <div class="men-item-carousel">
                        <div class="owl-men-item owl-carousel">
                            <div class="item">
                                <div class="thumb">
                                    <img src="assets/images/sell.png" alt="">
                                </div>
                                <div class="down-content">
                                    <h4>중고</h4>
                                </div>
                            </div>
                            <div class="item">
                                <div class="thumb">
                                    <img src="assets/images/sell.png" alt="">
                                </div>
                                <div class="down-content">
                                    <h4>미사용</h4>
                                </div>
                            </div>
                            <div class="item">
                                <div class="thumb">
                                    <img src="assets/images/sell.png" alt="">
                                </div>
                                <div class="down-content">
                                    <h4>선물용</h4>
                                </div>
                            </div>
                            <div class="item">
                                <div class="thumb">
                                    <img src="assets/images/sell.png" alt="">
                                </div>
                                <div class="down-content">
                                    <h4>상품권</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
   
   
  <!-- jQuery -->
    <script src="assets/js/jquery-2.1.0.min.js"></script>

    <!-- Bootstrap -->
    <script src="assets/js/owl-carousel.js"></script>
    <script src="assets/js/waypoints.min.js"></script>
    <script src="assets/js/jquery.counterup.min.js"></script>
    <script src="assets/js/imgfix.min.js"></script> 
    <script src="assets/js/slick.js"></script> 
    <script src="assets/js/lightbox.js"></script> 
    <script src="assets/js/isotope.js"></script> 
    
        <!-- Global Init -->
    <script src="assets/js/custom.js"></script>
    
    <script>

        $(function() {
            var selectedClass = "";
            $("p").click(function(){
            selectedClass = $(this).attr("data-rel");
            $("#portfolio").fadeTo(50, 0.1);
                $("#portfolio div").not("."+selectedClass).fadeOut();
            setTimeout(function() {
              $("."+selectedClass).fadeIn();
              $("#portfolio").fadeTo(50, 1);
            }, 500);
                
            });
        });

    </script>






<%@ include file="module/bottom00.jsp" %>
</body>
</html>






