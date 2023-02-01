package work.model;

import java.sql.Timestamp;
import java.util.Date;

public class Work {
	
	//필드
	private Timestamp work_in_time;
	private Timestamp work_out_time;
	private String total_day;
	private String status;
	private int emp_no;
	private Date work_reg_date;
	private String late;
	private String overtime;
	
	
	
	public Work(Timestamp work_in_time, Timestamp work_out_time, String status, int emp_no, Date work_reg_date) {
		this.work_in_time = work_in_time;
		this.work_out_time = work_out_time;
		this.status = status;
		this.emp_no = emp_no;
		this.work_reg_date = work_reg_date;
	}
	
	public Work(String total_day, Timestamp work_in_time, Timestamp work_out_time, Date work_reg_date, String overtime) {
		this.total_day = total_day;
		this.work_in_time = work_in_time;
		this.work_out_time = work_out_time;
		this.work_reg_date = work_reg_date;
		this.overtime = overtime;
	}
	
	
	
	
	
	
	public Timestamp getWork_in_time() {
		return work_in_time;
	}
	public void setWork_in_time(Timestamp work_in_time) {
		this.work_in_time = work_in_time;
	}
	public Timestamp getWork_out_time() {
		return work_out_time;
	}
	public void setWork_out_time(Timestamp work_out_time) {
		this.work_out_time = work_out_time;
	}
	public String getTotal_day() {
		return total_day;
	}
	public void setTotal_day(String total_day) {
		this.total_day = total_day;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public Date getWork_reg_date() {
		return work_reg_date;
	}
	public void setWork_reg_date(Date work_reg_date) {
		this.work_reg_date = work_reg_date;
	}
	public String getLate() {
		return late;
	}
	
	public void setLate(String late) {
		this.late = late;
	}
	
	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}


	@Override
	public String toString() {
		return "Work [work_in_time=" + work_in_time + ", work_out_time=" + work_out_time + ", total_day=" + total_day
				+ ", status=" + status + ", emp_no=" + emp_no + ", work_reg_date=" + work_reg_date + ", late=" + late
				+ ", overtime=" + overtime + "]";
	}





	
	
	
	
	
}
