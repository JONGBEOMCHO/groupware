package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;


//p606
//이 컨트롤러 클래스는 로그인요청에 따라 호출되는 클래스이다
//요청주소   http://ip주소/컨패/login.do
public class LoginHandler implements CommandHandler {
	
	private static final String FORM_VIEW="/view/loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler.process()호출");
		//폼의 요청방식에 따라 회원가입폼보여줘 요청과  가입처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//회원가입폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//로그인처리요청
		}else {
			/* 참고. 
			 * 상태코드 => SC_OK
			 * 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 
			 * 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			 * 
			 * 상태코드 => SC_METHOD_NOT_ALLOWED
			 * 405(허용되지 않는 메소드): 
			 * 요청에 지정된 방법을 사용할 수 없다. 
			 * 예를 들어 POST 방식으로 요청을 받는 서버에 GET 요청을 보내는 경우, 
			 * 또는 읽기 전용 리소스에 PUT 요청을 보내는 경우에 이 코드를 제공한다.*/
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process끝
	
	//로그인폼으로 이동-p607 32라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

	//로그인처리-p607 36라인
	private String processSubmit(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		System.out.println("processSubmit()진입");
		//컨트롤러가 해야 하는 일
		//1.파라미터받기-p607 38라인
		String emp_id = request.getParameter("emp_id"); //아이디
		String emp_pw= request.getParameter("emp_pw"); //비밀번호
		
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors",errors);//p607 42라인

		//id와 비번필수입력-p607 44라인
		if(emp_id==null || emp_id.isEmpty()) {
			errors.put("emp_id", Boolean.TRUE);
		}
		if(emp_pw==null || emp_pw.isEmpty()) {
			errors.put("emp_pw", Boolean.TRUE);
		}
		
		//p607 49라인
		if(!errors.isEmpty()) {//에러가 존재하면
			return FORM_VIEW;
		}
		
		//2.비즈니스로직수행<->Service<->DAO<->DB-p607 53라인
		//3.Model&4.view
		try {//P607 53라인
			//로그인에 성공하면 로그인한 회원의 정보를 session담는다
			User user = loginService.login(emp_id, emp_pw);
			/*Model 
			  request객체.setAttribute("속성값",Object value)
			  session객체.setAttribute("속성값",Object value)*/
			HttpSession session = request.getSession();
			session.setAttribute("AUTHUSER",user);
			response.sendRedirect(request.getContextPath()+"/view/main.jsp");
			return null;
		}catch(LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
	
	//문자열의 좌우공백제거-p607 64라인
	private String trim(String str) {
		return  (str==null)? null:str.trim();
	}
}









