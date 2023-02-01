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
//�� Ŭ������ ȸ�����԰��� ��û�� ȣ��Ǵ� ��Ʈ�ѷ� Ŭ�����̴�
//��û�ּ� http://localhost/groupware/join.do
public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW="view/joinForm.jsp";
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("JoinHandler�� process()ȣ�� ����");
		
		//���� ��û ��Ŀ� ���� ȸ�������� ��û�� ����ó����û�� ó��
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//ȸ�������� ������
		} else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); //����ó�� ��û
		} else {
			/*
			 * SC_METHOD_NOT_ALLOWED 405(������ �ʴ� �޼ҵ�) 
			 * ��û�� ������ ����� ����� �� ���� 
			 * POST������� ��û�� �޴� ������ GET ��û�� ������ ���
			 * �б� ���� ���ҽ��� PUT ��û�� ������ ��� � �� �ڵ带 ����
			 */
			
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return FORM_VIEW;
		}
	} //process() ��
	

	// ȸ�������� ������ ��û
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("processForm");
		return FORM_VIEW;
	}

	
	
	//���Կ�û ó��
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		 // 1 �Ķ���� ��� 
		
		String emp_id = request.getParameter("emp_id"); // ���̵�
		String emp_pw = request.getParameter("emp_pw"); // ��й�ȣ
		String re_emp_pw = request.getParameter("re_emp_pw"); // ��й�ȣ Ȯ��
		String emp_kname = request.getParameter("emp_kname"); // �ѱ��̸�
		String emp_ename = request.getParameter("emp_ename"); // �����̸�
		int emp_postcode = Integer.parseInt(request.getParameter("emp_postcode")); // �����ȣ
		String sample6_address = request.getParameter("sample6_address");
		String sample6_detailAddress = request.getParameter("sample6_detailAddress");
		String sample6_extraAddress = request.getParameter("sample6_extraAddress");
		String emp_address = sample6_address+" "+sample6_detailAddress+" "+sample6_extraAddress;
		String emp_birthday = request.getParameter("emp_birthday"); // �������
		String emp_phonenumber = request.getParameter("emp_phonenumber");
		String email_id = request.getParameter("emp_email_id"); // �̸����ּ� ��
		String email_d = request.getParameter("emp_email_d"); // �̸����ּ� ��
		String emp_email = email_id+"@"+email_d; //�̸����ּ�
		String dept_name = request.getParameter("dept_name"); // �μ�
		int emp_position = Integer.parseInt(request.getParameter("emp_position")); // ����
		
		//2 ����Ͻ� ���� ���� <-> service <-> DAO <-> DB 
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
		request.setAttribute("errors",errors); //p598 43����

		//3 Modeló�� request, session 
		
		
		//4 View����
		joinReq.validate(errors); //��ȿ���˻�(�ʼ��Է�,��� ��ġ����) ������ �߻��ϸ� �������� errors�� ������ ����
		
		/*
		 * ������ ������ �Ʒ� �����Ͱ� error�� ����
		 * errors.put("memberid", Boolean.TRUE); 
		 * errors.put("memberpwd",Boolean.TRUE); 
		 * errors.put("re_memberpwd", Boolean.TRUE);
		 * errors.put("membername", Boolean.TRUE);
		 * errors.put("notMatch", Boolean.TRUE);
		 */
		
		if(!errors.isEmpty()) { //������ �����ϸ� ���ư���
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
