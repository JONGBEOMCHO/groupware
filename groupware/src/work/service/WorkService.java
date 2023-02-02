package work.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import work.dao.WorkDAO;
import work.model.Work;

public class WorkService {
	
	private WorkDAO workOnDAO = new WorkDAO();
	
	/*public int count(int mbno) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			cnt = workOnDAO.count(conn, mbno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}*/
	
	/*public WorkBefore selectBefore(int mbno) {
		Connection conn = null;
		WorkBefore workBefore = null;
		try {
			conn = ConnectionProvider.getConnection();
			workBefore = workOnDAO.selectBefore(conn, mbno);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workBefore;
	}*/
	
	public Work selectIn(int emp_no) {
		Connection conn = null;
		Work work = null;
		try {
			conn = ConnectionProvider.getConnection();
			work = workOnDAO.selectIn(conn, emp_no);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return work;
	}
	
	public void workIn(int emp_no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			workOnDAO.workIn(conn, emp_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void workOut(int emp_no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			workOnDAO.workOut(conn, emp_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Work> selectMonth(int year, int month, int emp_no){
		Connection conn = null;
		List<Work> workList = null;
			try {
				conn = ConnectionProvider.getConnection();
				workList = workOnDAO.selectMonth(conn, year, month, emp_no);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return workList;
	}
	
	
	

}
