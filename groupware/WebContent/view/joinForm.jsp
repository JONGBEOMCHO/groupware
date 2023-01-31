<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="../css/joinStyle.css">
<style></style>
<script>
	$(document).ready(function(){
		$("#email_dd").change(function(){
			let val = $("select#email_dd option:selected").val();
			$("#emp_email_d").val(val);
		});
	});//
</script>
<title>직원등록</title>
</head>
<body>
	
	<div class="header">
		<span> 직원등록 </span>
	</div>
		<form action="/join.do" method="post">
		<div class="container">
			<div class="contant">
				<label>아이디* <input type="text" id="emp_id" name="emp_id" required="required"> </label> <br/>
				<label>비밀번호* <input type="password" id="emp_pw" name="emp_pw" required="required"> </label> <br/>
				<label>비밀번호 확인* <input type="password" id="re_emp_pw" name="re_emp_pw" required="required"> </label> <br/>
				<label>한글이름* <input type="text" id="emp_kname" name="emp_kname" required="required"> </label> <br/>
				<label>영문이름* <input type="text" id="emp_ename" name="emp_ename" placeholder="영문으로만 입력해주세요"  pattern="[a-zA-Z]+$" required="required"> </label> <br/>
				우편번호* <input type="text" id="sample6_postcode" pattern="[0-9]+">
									<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				주소* 		<input type="text" id="sample6_address" ><br>
				상세주소* <input type="text" id="sample6_detailAddress" ><br>
				참고항목* <input type="text" id="sample6_extraAddress" ><br>

				
				<!-- 
				<label>우편번호* <input type="text" id="emp_zipcode" name="emp_zipcode" pattern="[0-9]+$" required="required"> </label> <br/>
				<label>주소* <input type="text" id="emp_postcode" name="emp_postcode" required="required"> </label> <br/>
				<label>상세주소* <input type="text" id="emp_postcode_detail" name="emp_postcode_detail" required="required"> </label> <br/> -->
				<label>생년월일* <input type="text" id="emp_birthday" name="emp_birthday" placeholder="6자리 숫자로 입력해주세요" pattern="[0-9]+" maxlength="6" required="required"> </label> <br/>
				<label>연락처* <input type="text" id="emp_phonenumber" name="emp_phonenumber" placeholder="숫자만 입력해주세요" pattern="[0-9]+" required="required"> </label> <br/>
				<label>이메일주소* <input type="text" id="emp_email_id" name="emp_email_id" size="10" required="required">
				@
				<input type="text" name="emp_email_d" id="emp_email_d" required="required">
				<select name="email_dd" id="email_dd" required="required">
				    	<option value="">직접입력</option>
				    	<option value="naver.com">naver.com</option>
				    	<option value="daum.net">daum.net </option>
				    	<option value="gmail.com">gmail.com </option>
				    	<option value="nate.com">nate.com </option>
		   	</select>
				 </label> <br/>
				<label>부서선택* </label> 
				<select size="1" name ="dept_name" id="dept_name">
					<option value="management">경영지원실</option>
					<option value="development">기술개발부</option>
					<option value="qa">QA팀</option>
					<option value="business">사업부</option>
				</select>
				<label>직급선택* </label> 
				<select size="1" name="emp_position" id="emp_position">
					<option value="1">사원</option>
					<option value="2">선임</option>
					<option value="3">책임</option>
					<option value="4">수석</option>
					<option value="5">대표</option>
				</select>
				</div>
			</div>
			<input type="submit" value="등록">
			<input type="reset" value="취소">
		
		</form>
	
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
</body>
</html>