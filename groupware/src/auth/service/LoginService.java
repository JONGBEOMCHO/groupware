package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeDAO;
import employee.model.Employee;
import jdbc.conn.ConnectionProvider;

//605
//이 클래스는 로그인처리를 위한 서비스클래스이다
public class LoginService {
	//필드
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	//생성자
	
	//메서드
	//로그인처리-p605 14라인
	//리턴타입 User : 로그인에 성공한 회원정보
	public User login(String id,String pwd) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		
			//1.user가 입력한 id를 사용하는 회원정보 조회
			Employee employee = employeeDAO.selectById(id, conn);
			if(employee==null) {//user가 입력한 id를 사용하는 회원이 없다면
				throw new LoginFailException();
			}
			
			//2.해당회원의 비밀번호와 유저가 입력한 비번일치 조회
			boolean result = employee.matchPassword(pwd);
			if(!result) { //user가 입력한 비번과  기존회원의 비밀번호가 일치x하면
				throw new LoginFailException();
			}
			return new User(
					employee.getEmp_no(),
					employee.getEmp_id(),
					employee.getEmp_kname(),
					employee.getEmp_ename(),
					employee.getEmp_birthday(),
					employee.getEmp_phonenumber(),
					employee.getEmp_email(),
					employee.getDept_name(),
					employee.getEmp_position(),
					employee.getEmp_extnumber()
					);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}







