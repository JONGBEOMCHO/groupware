package employee.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import employee.dao.EmployeeDAO;
import employee.model.Employee;


//p596
//�� Ŭ������ ����Ŭ�����μ� JoinHandler��Ʈ�ѷ��� ���ؼ� ȣ��Ǵ� 
//ȸ������ ���� ���� ����� ����
// 	��Ʈ�ѷ�   <->     ����    <-> 	DAO		<->  DB
// JoinHandler <->  Join Service <->  MemberDAO <->  DB
//����̹��ε� - conn��� - ��ü�غ� - �������� - �ݳ�

public class JoinService {
	
	
	//�ʵ�
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	//������
	
	
	public void join(JoinRequest joinReq) {
		System.out.println("JoinService-join() joinReq= "+joinReq);
		
		//id�ߺ��˻��ɰ��� DAO�޼���ȣ��-p596. 22���� 
		Connection conn = null;
		Employee employee = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit��� ����
			
			//user�� �Է��� id�� ����ϴ� ����member������ ���  Member��ü�޴´�
			employee = employeeDAO.selectById(joinReq.getEmp_id(),conn);
			System.out.println("JoinService-selectById()���Ϲ��� member="+employee);
			if(employee!=null){ //id�� �̹� ������� ����ȸ���� �����Ƿ� rollback()ó���� �ϰ� id�ߺ����� �߻�
				JdbcUtil.rollback(conn);
				throw new DuplicatedIdException();
			}
			
			Employee newEmployee = 
					new Employee(
							joinReq.getEmp_id(),
							joinReq.getEmp_pw(),
							joinReq.getEmp_kname(),
							joinReq.getEmp_ename(),
							joinReq.getEmp_postcode(),
							joinReq.getEmp_address(),
							joinReq.getEmp_birthday(),
							joinReq.getEmp_phonenumber(),
							joinReq.getEmp_email(),
							joinReq.getDept_name(),
							joinReq.getEmp_position()
							
							
							);
			employeeDAO.insert(newEmployee,conn); //(ȸ���ǰ���� ������ �Է��� ȸ������);
			
			conn.commit(); //DB commit()ó��
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);//DB rollback()ó��
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}

