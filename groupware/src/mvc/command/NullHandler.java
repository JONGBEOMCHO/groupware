package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//��ûó������ �۾� �� null�߻�ó���� ���� Ŭ����
//(���⿡����)��� ��Ʈ�ѷ��� �ݵ�� CommandHandler�������̽��� �����ؾ� �Ѵ�.
public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}

/*����
Ư�� ���ǿ��� HTTP �����ڵ�� �����ؾ� �ϴ� ��찡 �ִ�. 
���� ��� ����ڰ� �Ķ���͸� ���Ƿ� �Է������� 
���� �������� ��ó�� ���δٴ��� �ϴ� ����ε�, 
�� ���� HttpServletResponse�� �޼��带 �̿���
�����ڵ��� ���°��� ���Ƿ� �����ϴ� ����� ����.

sendError
����>void sendError( int code [, String message ] )
����>response.sendError(404, "�߸��� �����Դϴ�.");

setStatus
����>void setStatus( int code [, String message ] )
����>response.setStatus(404);
*/