package employee.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.DuplicatedIdException;
import employee.service.JoinRequest;
import employee.service.JoinService;
import mvc.command.CommandHandler;


//p598
//이 클래스는 회원가입관련 요청시 호출되는 컨트롤러 클래스이다
//요청주소 http://localhost/groupware/join.do
public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW="view/joinForm.jsp";
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("JoinHandler의 process()호출 성공");
		
		//폼의 요청 방식에 따라 회원가입폼 요청과 가입처리요청을 처리
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//회원가입폼 보여줘
		} else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); //가입처리 요청
		} else {
			/*
			 * SC_METHOD_NOT_ALLOWED 405(허용되지 않는 메소드) 
			 * 요청에 지정된 방법을 사용할 수 없음 
			 * POST방식으로 요청을 받는 서버에 GET 요청을 보내는 경우
			 * 읽기 전용 리소스에 PUT 요청을 보내는 경우 등에 이 코드를 제공
			 */
			
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return FORM_VIEW;
		}
	} //process() 끝
	

	// 회원가입폼 보여줘 요청
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("processForm");
		return FORM_VIEW;
	}

	
	
	//가입요청 처리
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		 // 1 파라미터 얻기 
		
		String emp_id = request.getParameter("emp_id"); // 아이디
		String emp_pw = request.getParameter("emp_pw"); // 비밀번호
		String re_emp_pw = request.getParameter("re_emp_pw"); // 비밀번호 확인
		String emp_kname = request.getParameter("emp_kname"); // 한글이름
		String emp_ename = request.getParameter("emp_ename"); // 영문이름
		int emp_postcode = Integer.parseInt(request.getParameter("emp_postcode")); // 우편번호
		String sample6_address = request.getParameter("sample6_address");
		String sample6_detailAddress = request.getParameter("sample6_detailAddress");
		String sample6_extraAddress = request.getParameter("sample6_extraAddress");
		String emp_address = sample6_address+" "+sample6_detailAddress+" "+sample6_extraAddress;
		String emp_birthday = request.getParameter("emp_birthday"); // 생년월일
		String emp_phonenumber = request.getParameter("emp_phonenumber");
		String email_id = request.getParameter("emp_email_id"); // 이메일주소 앞
		String email_d = request.getParameter("emp_email_d"); // 이메일주소 뒤
		String emp_email = email_id+"@"+email_d; //이메일주소
		String dept_name = request.getParameter("dept_name"); // 부서
		int emp_position = Integer.parseInt(request.getParameter("emp_position")); // 직급
		
		//2 비즈니스 로직 실행 <-> service <-> DAO <-> DB 
		JoinService joinService = new JoinService();
		JoinRequest joinReq = new JoinRequest();
		joinReq.setEmp_id(emp_id);
		joinReq.setEmp_pw(emp_pw);
		joinReq.setRe_emp_pw(re_emp_pw);
		joinReq.setEmp_kname(emp_kname);
		joinReq.setEmp_ename(emp_ename);
		joinReq.setEmp_postcode(emp_postcode);
		joinReq.setEmp_address(emp_address);
		joinReq.setEmp_birthday(emp_birthday);
		joinReq.setEmp_phonenumber(emp_phonenumber);
		joinReq.setEmp_email(emp_email);
		joinReq.setDept_name(dept_name);
		joinReq.setEmp_position(emp_position);
		
		
		System.out.println("joinReq.setEmp_id="+joinReq.getEmp_id());
		

		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors",errors); //p598 43라인

		//3 Model처리 request, session 
		
		
		//4 View지정
		joinReq.validate(errors); //유효성검사(필수입력,비번 일치여부) 에러가 발생하면 참조변수 errors에 데이터 저장
		
		/*
		 * 에러가 있으면 아래 데이터가 error에 담긴다
		 * errors.put("memberid", Boolean.TRUE); 
		 * errors.put("memberpwd",Boolean.TRUE); 
		 * errors.put("re_memberpwd", Boolean.TRUE);
		 * errors.put("membername", Boolean.TRUE);
		 * errors.put("notMatch", Boolean.TRUE);
		 */
		
		if(!errors.isEmpty()) { //에러가 존재하면 돌아가라
			return FORM_VIEW;
		}
		
		
		try {
			joinService.join(joinReq);
			return "view/joinSuccess.jsp";
		}catch(DuplicatedIdException e){
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
		
		
		
		
	}

	
}
