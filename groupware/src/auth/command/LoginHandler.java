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
//�� ��Ʈ�ѷ� Ŭ������ �α��ο�û�� ���� ȣ��Ǵ� Ŭ�����̴�
//��û�ּ�   http://ip�ּ�/����/login.do
public class LoginHandler implements CommandHandler {
	
	private static final String FORM_VIEW="/view/loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler.process()ȣ��");
		//���� ��û��Ŀ� ���� ȸ�������������� ��û��  ����ó����û�� ����
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//ȸ��������������
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//�α���ó����û
		}else {
			/* ����. 
			 * �����ڵ� => SC_OK
			 * 200(����): ������ ��û�� ����� ó���ߴٴ� ���̴�. 
			 * �̴� �ַ� ������ ��û�� �������� �����ߴٴ� �ǹ̷� ���δ�.
			 * 
			 * �����ڵ� => SC_METHOD_NOT_ALLOWED
			 * 405(������ �ʴ� �޼ҵ�): 
			 * ��û�� ������ ����� ����� �� ����. 
			 * ���� ��� POST ������� ��û�� �޴� ������ GET ��û�� ������ ���, 
			 * �Ǵ� �б� ���� ���ҽ��� PUT ��û�� ������ ��쿡 �� �ڵ带 �����Ѵ�.*/
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process��
	
	//�α��������� �̵�-p607 32����
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

	//�α���ó��-p607 36����
	private String processSubmit(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		System.out.println("processSubmit()����");
		//��Ʈ�ѷ��� �ؾ� �ϴ� ��
		//1.�Ķ���͹ޱ�-p607 38����
		String emp_id = request.getParameter("emp_id"); //���̵�
		String emp_pw= request.getParameter("emp_pw"); //��й�ȣ
		
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors",errors);//p607 42����

		//id�� ����ʼ��Է�-p607 44����
		if(emp_id==null || emp_id.isEmpty()) {
			errors.put("emp_id", Boolean.TRUE);
		}
		if(emp_pw==null || emp_pw.isEmpty()) {
			errors.put("emp_pw", Boolean.TRUE);
		}
		
		//p607 49����
		if(!errors.isEmpty()) {//������ �����ϸ�
			return FORM_VIEW;
		}
		
		//2.����Ͻ���������<->Service<->DAO<->DB-p607 53����
		//3.Model&4.view
		try {//P607 53����
			//�α��ο� �����ϸ� �α����� ȸ���� ������ session��´�
			User user = loginService.login(emp_id, emp_pw);
			/*Model 
			  request��ü.setAttribute("�Ӽ���",Object value)
			  session��ü.setAttribute("�Ӽ���",Object value)*/
			HttpSession session = request.getSession();
			session.setAttribute("AUTHUSER",user);
			response.sendRedirect(request.getContextPath()+"/view/main.jsp");
			return null;
		}catch(LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
	
	//���ڿ��� �¿��������-p607 64����
	private String trim(String str) {
		return  (str==null)? null:str.trim();
	}
}









