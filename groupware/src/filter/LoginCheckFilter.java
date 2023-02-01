package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//로그인 여부 검사 필터역할을 하는 클래스 p612
/*로그인 여부를 검사한다.
 *로그인을 하지 않았으면 로그인 화면으로 이동시킨다
 *로그인을 했다면 요청한 기능을 실행한다*/
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("AUTHUSER")==null) {//로그인을 하지 않았으면
			//로그인 화면으로 이동
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(request.getContextPath()+"/login.do"); //P613 24라인
		}else {  //session에 로그인한 user의 정보가 존재한다면
			//로그인하였으니 client의 요청을 처리하라
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	
}
