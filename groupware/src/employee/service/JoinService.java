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
//이 클래스는 서비스클래스로서 JoinHandler컨트롤러에 의해서 호출되는 
//회원가입 관련 여러 기능을 제공
// 	컨트롤러   <->     서비스    <-> 	DAO		<->  DB
// JoinHandler <->  Join Service <->  MemberDAO <->  DB
//드라이버로드 - conn얻기 - 객체준비 - 쿼리실행 - 반납

public class JoinService {
	
	
	//필드
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	//생성자
	
	
	public void join(JoinRequest joinReq) {
		System.out.println("JoinService-join() joinReq= "+joinReq);
		
		//id중복검사기능가진 DAO메서드호출-p596. 22라인 
		Connection conn = null;
		Employee employee = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit기능 해제
			
			//user가 입력한 id를 사용하는 기존member정보가 담긴  Member객체받는다
			employee = employeeDAO.selectById(joinReq.getEmp_id(),conn);
			System.out.println("JoinService-selectById()리턴받은 member="+employee);
			if(employee!=null){ //id를 이미 사용중인 기존회원이 있으므로 rollback()처리를 하고 id중복에러 발생
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
			employeeDAO.insert(newEmployee,conn); //(회원되고싶은 유저가 입력한 회원정보);
			
			conn.commit(); //DB commit()처리
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);//DB rollback()처리
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}

