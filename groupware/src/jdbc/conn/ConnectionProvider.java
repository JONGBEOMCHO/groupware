package jdbc.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//p586
//web.xml�������� ������  poolName=board���� ������ board��
//Ǯ �̸����� ����Ͽ�  connection�� �����ϴ� Ŭ�����̴�

//Connection�� �ʿ��ϴ� �ܺο���
// Connection �������� = ConnectionProvider.getConnection();
public class ConnectionProvider {
	   
	public static Connection getConnection() throws SQLException {
		return  DriverManager.getConnection("jdbc:apache:commons:dbcp:groupware");
	}
	
}




