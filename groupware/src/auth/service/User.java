package auth.service;

//로그인한 user에 대한 정보

public class User {
	
	//필드
	private int emp_no;				//회원번호
	private String emp_id;			//아이디
	private String emp_kname;		//한글이름
	private String emp_ename;		//영문이름
	private String emp_birthday;	//생년월일
	private String emp_phonenumber;	//핸드폰번호
	private String emp_email;		//이메일
	private String dept_name;		//부서명
	private int emp_position;		//직급. 1(주니어), 2(선임), 3(책임), 4(수석), 5(대표)
	private int emp_extnumber;		//내선번호
	private int emp_grade;			//1(일반) 2(관리자)
	
	//생성자
	public User(int emp_no, String emp_id, String emp_kname, String emp_ename, String emp_birthday, String emp_phonenumber,
			String emp_email, String dept_name, int emp_position, int emp_extnumber ) {
		super();
		this.emp_no = emp_no;
		this.emp_id = emp_id;
		this.emp_kname = emp_kname;
		this.emp_ename = emp_ename;
		this.emp_birthday = emp_birthday;
		this.emp_phonenumber = emp_phonenumber;
		this.emp_email = emp_email;
		this.dept_name = dept_name;
		this.emp_position = emp_position;
		this.emp_extnumber = emp_extnumber;
	}

	//메서드
	public int getEmp_no() {
		return emp_no;
	}


	public String getEmp_id() {
		return emp_id;
	}


	public String getEmp_kname() {
		return emp_kname;
	}


	public String getEmp_ename() {
		return emp_ename;
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

	@Override
	public String toString() {
		return "User [emp_no=" + emp_no + ", emp_id=" + emp_id + ", emp_kname=" + emp_kname + ", emp_ename=" + emp_ename
				+ ", emp_birthday=" + emp_birthday + ", emp_phonenumber=" + emp_phonenumber + ", emp_email=" + emp_email
				+ ", dept_name=" + dept_name + ", emp_position=" + emp_position + ", emp_extnumber=" + emp_extnumber
				+ "]";
	}

	
	
	
}

