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

//�α��� ���� �˻� ���Ϳ����� �ϴ� Ŭ���� p612
/*�α��� ���θ� �˻��Ѵ�.
 *�α����� ���� �ʾ����� �α��� ȭ������ �̵���Ų��
 *�α����� �ߴٸ� ��û�� ����� �����Ѵ�*/
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("AUTHUSER")==null) {//�α����� ���� �ʾ�����
			//�α��� ȭ������ �̵�
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(request.getContextPath()+"/login.do"); //P613 24����
		}else {  //session�� �α����� user�� ������ �����Ѵٸ�
			//�α����Ͽ����� client�� ��û�� ó���϶�
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
