package auth.service;

//�α����� user�� ���� ����

public class User {
	
	//�ʵ�
	private int emp_no;				//ȸ����ȣ
	private String emp_id;			//���̵�
	private String emp_kname;		//�ѱ��̸�
	private String emp_ename;		//�����̸�
	private String emp_birthday;	//�������
	private String emp_phonenumber;	//�ڵ�����ȣ
	private String emp_email;		//�̸���
	private String dept_name;		//�μ���
	private int emp_position;		//����. 1(�ִϾ�), 2(����), 3(å��), 4(����), 5(��ǥ)
	private int emp_extnumber;		//������ȣ
	private int emp_grade;			//1(�Ϲ�) 2(������)
	
	//������
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

	//�޼���
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

