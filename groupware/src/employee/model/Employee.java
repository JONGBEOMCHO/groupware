package employee.model;

public class Employee {

	//(DB employee테이블)직원관련 데이터저장,제공 등의 기능
	
	private int emp_no;
	private String emp_id;
	private String emp_pw;
	private String emp_kname;
	private String emp_ename;
	private int emp_postcode;
	private String emp_address;
	private String emp_birthday;
	private String emp_phonenumber;
	private String emp_email;
	private String dept_name;
	private int emp_position;
	private int emp_extnumber;
	private int emp_grade;
	
	public Employee(int emp_no, String emp_id, String emp_pw, String emp_kname, String emp_ename, int emp_postcode,
			String emp_address, String emp_birthday, String emp_phonenumber, String emp_email,
			String dept_name, int emp_position, int emp_extnumber, int emp_grade) {
		this.emp_no = emp_no;
		this.emp_id = emp_id;
		this.emp_pw = emp_pw;
		this.emp_kname = emp_kname;
		this.emp_ename = emp_ename;
		this.emp_postcode = emp_postcode;
		this.emp_address = emp_address;
		
		this.emp_birthday = emp_birthday;
		this.emp_phonenumber = emp_phonenumber;
		this.emp_email = emp_email;
		this.dept_name = dept_name;
		this.emp_position = emp_position;
		this.emp_extnumber = emp_extnumber;
		this.emp_grade = emp_grade;
	}
	


	public Employee(String emp_id, String emp_pw, String emp_kname, String emp_ename, int emp_postcode,
			String emp_address, String emp_birthday, String emp_phonenumber, String emp_email, String dept_name,
			int emp_position) {
		super();
		
		this.emp_id = emp_id;
		this.emp_pw = emp_pw;
		this.emp_kname = emp_kname;
		this.emp_ename = emp_ename;
		this.emp_postcode = emp_postcode;
		this.emp_address = emp_address;
		this.emp_birthday = emp_birthday;
		this.emp_phonenumber = emp_phonenumber;
		this.emp_email = emp_email;
		this.dept_name = dept_name;
		this.emp_position = emp_position;
	}



	public int getEmp_no() {
		return emp_no;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public String getEmp_pw() {
		return emp_pw;
	}
	public String getEmp_kname() {
		return emp_kname;
	}
	public String getEmp_ename() {
		return emp_ename;
	}
	public int getEmp_postcode() {
		return emp_postcode;
	}
	public String getEmp_address() {
		return emp_address;
	}

	public String getEmp_birthday() {
		return emp_birthday;
	}
	public String getEmp_phonenumber() {
		return emp_phonenumber;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public String getDept_name() {
		return dept_name;
	}
	public int getEmp_position() {
		return emp_position;
	}
	public int getEmp_extnumber() {
		return emp_extnumber;
	}
	public int getEmp_grade() {
		return emp_grade;
	}
	
	//암호매치 기능
	public boolean matchPassword(String pwd) {
		return emp_pw.equals(pwd);
	}
	
	//새비번을 필드memberpwd의 값으로 설정-p619 20라인
	public void changePwd(String newPwd){
		this.emp_pw = newPwd;
	}
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Employee [emp_no=" + emp_no + ", emp_id=" + emp_id + ", emp_pw=" + emp_pw + ", emp_kname=" + emp_kname
				+ ", emp_ename=" + emp_ename + ", emp_postcode=" + emp_postcode + ", emp_address=" + emp_address
				+ ", emp_birthday=" + emp_birthday + ", emp_phonenumber="
				+ emp_phonenumber + ", emp_email=" + emp_email + ", dept_name=" + dept_name + ", emp_position="
				+ emp_position + ", emp_extnumber=" + emp_extnumber + ", emp_grade=" + emp_grade + "]";
	}
	
	
	
	
	
	
}
