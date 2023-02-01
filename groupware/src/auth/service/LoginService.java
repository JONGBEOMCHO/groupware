package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeDAO;
import employee.model.Employee;
import jdbc.conn.ConnectionProvider;

//605
//�� Ŭ������ �α���ó���� ���� ����Ŭ�����̴�
public class LoginService {
	//�ʵ�
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	//������
	
	//�޼���
	//�α���ó��-p605 14����
	//����Ÿ�� User : �α��ο� ������ ȸ������
	public User login(String id,String pwd) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		
			//1.user�� �Է��� id�� ����ϴ� ȸ������ ��ȸ
			Employee employee = employeeDAO.selectById(id, conn);
			if(employee==null) {//user�� �Է��� id�� ����ϴ� ȸ���� ���ٸ�
				throw new LoginFailException();
			}
			System.out.println("aaaa");
			//2.�ش�ȸ���� ��й�ȣ�� ������ �Է��� �����ġ ��ȸ
			boolean result = employee.matchPassword(pwd);
			if(!result) { //user�� �Է��� �����  ����ȸ���� ��й�ȣ�� ��ġx�ϸ�
				throw new LoginFailException();
			}
			
			return new User(
					employee.getEmp_no(),
					employee.getEmp_id(),
					employee.getEmp_kname(),
					employee.getEmp_ename(),
					employee.getEmp_postcode(),
					employee.getEmp_address(),
					employee.getEmp_birthday(),
					employee.getEmp_phonenumber(),
					employee.getEmp_email(),
					employee.getDept_name(),
					employee.getEmp_position(),
					employee.getEmp_extnumber(),
					employee.getEmp_grade()
					);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}







