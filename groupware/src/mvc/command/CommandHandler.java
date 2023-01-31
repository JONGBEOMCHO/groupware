package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//p531
//�� �������̽��� ��� ��Ʈ�ѷ�Ŭ���� (���翡���� ~~Handler)����
//������ �����ϰ� �����ν�
//��� ��Ʈ�ѷ�Ŭ�������� process()�� �ݵ��
//�������̵��ϵ��� ����ȭ ��Ű�� ������ �Ѵ�
public interface CommandHandler {

	public abstract String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	//String Ÿ���� view�� �������ִ� �޼���
}
	/* �������̽���?
	-��� �ʵ� ����
	:�������̽��� ��� �ʵ常 ���� ���� - �����͸� �������� ����
	-�������̽��� ����� �ʵ�� ��� public static final:�ڵ������� ������ �������� ����
	-������� �빮�ڷ� �ۼ� : ���� �ٸ� �ܾ�� �����Ǿ� ���� ��쿡�� ����� (_)�� ����
	
	-����� ���ÿ� �ʱⰪ ����
	static { } ��� �ۼ� �Ұ� - static {} ���� �ʱ�ȭ �Ұ�
	
	-�߻�޼���
	[public abstract] �������� �޼���� (�Ű�����);
	
	*/
	

