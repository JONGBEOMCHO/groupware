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

public class LoginCheckFilter implements Filter {

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		if( session==null || session.getAttribute("AUTHUSER")==null) {//로그인을 하지 않았다면 로그인 화면으로 이동
			HttpServletResponse response = (HttpServletResponse)res;
					response.sendRedirect(request.getContextPath()+"/login.do");
		}else {//세션에 로그인한 유저의 정보가 존재한다면 Client의 요청을 처리
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

