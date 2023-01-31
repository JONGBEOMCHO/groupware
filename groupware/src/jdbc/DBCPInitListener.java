package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;
 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

//Ŀ�ؼǰ����ڵ�(p583)
//DBCP�� �̿��Ͽ� Ŀ�ؼ�Ǯ ���(P417����)
public class DBCPInitListener implements ServletContextListener {

	//poolConfig ���ؽ�Ʈ �ʱ�ȭ
	@Override 
	public void contextInitialized(ServletContextEvent sce) {
		/*web.xml��������
		  <context-param>
		   	<param-name>poolConfig</param-name>
		  </context-param> 	
		 */
		String poolConfig = 
				sce.getServletContext().getInitParameter("poolConfig");
		Properties prop = new Properties();//Properties��ü����
		//"Ű=��"������ ���ڿ��κ��� Properties�� �ε�
		//String Ű��=String ��
		try {
			prop.load(new StringReader(poolConfig));//prop.load() "Ű=��"������ ���ڿ��κ��� Properties�� �ε�
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}
	
	//�Ű����� Properties prop�� ��ܿ� ������ �̿��Ͽ� JDBCDriver�� �ε�
	private void loadJDBCDriver(Properties prop) {
		       //"Ű=��"����
		//jdbcdriver=com.mysql.jdbc.Driver
		String driverClass = prop.getProperty("jdbcdriver");
		//com.mysql.jdbc.Driver����  StringŸ���� driverClass������ ����
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	//ConnectionPool �ʱ�ȭ
	private void initConnectionPool(Properties prop) {
		try {//���� jdbc:mysql://ip�ּ� : port��ȣ/DB��Ű����?characterEncoding=UTF-8&serverTimezone=UTC
			/*     Ű��=��
			 *  jdbcUrl=jdbc:mysql://localhost:3306/board?characterEncoding=utf8&amp;serverTimezone=UTC
				dbUser=jspexam
				dbPass=jsppw
			 */
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");

			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(jdbcUrl, username, pw);

			PoolableConnectionFactory poolableConnFactory = 
					new PoolableConnectionFactory(connFactory, null);
			
			//Ǯ�� Ŀ�ؼ� (��ȿ��)�˻�		
			String validationQuery = prop.getProperty("validationQuery");
			if (validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			
			//�ּ� ���� Ŀ�ؼǼ���
			//�ּ� ���� Ŀ�ؼ�:������ �ʰ� Ǯ�� ����� �� �ִ� �ּ� Ŀ�ؼ� ����
			int minIdle = getIntProperty(prop, "minIdle", 5);
			poolConfig.setMinIdle(minIdle);
			
			//�ִ� ���� Ŀ�ؼǼ���
			int maxTotal = getIntProperty(prop, "maxTotal", 50);
			poolConfig.setMaxTotal(maxTotal);

			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			//org.apache.commons.dbcp2��Ű��.PoolingDriverŬ������ �ҷ�����
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)
				DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			//poolName�� �̿��ؼ� ������ board����
			//StringŸ����  poolName������ �����Ѵ�
			//���⿡���� ->String poolName="board";
			String poolName = prop.getProperty("poolName");//poolName�� ������ ������ ����
			driver.registerPool(poolName, connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		String value = prop.getProperty(propName);
		if (value == null) return defaultValue;
		return Integer.parseInt(value);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}

/* �� Ŀ�ؼ�Ǯ�� ����ϴ°�?  DBCP�� �����ΰ�?(P415����)
Ŭ���̾�Ʈ���� ��, ������, DB ���� �ѹ� �Դٰ����� ��
���� �ð��� ���� �ҿ�Ǵ� ���� ���������� DB������ ���ʷ� ����Ǿ�
Connection ��ü�� �����ϴ� �κ��̴�.

�� �ð��� �����ų �� �ִ� ���� DBCP �̴�. 
DB Connection Pool, 
�� �̸� DB�������� ������ �����س���, 
DB������ ����� �ʿ��� �� Connection ��ü�� �������� ���̴�.

DBCP�� ���ġ ������ �Ʒ��� ���� ������ ��ģ��.
1.DB ���� ������ ���� JDBC ����̹��� �ε��Ѵ�.
2.DB ���� ������ DriverManager.getConnection() Method�� ���� DB Connection ��ü�� ��´�.
3.Connection ��ü�� ���� ������ �����ϱ� ���� PreparedStatement ��ü�� �޴´�.
4.executeQuery�� �����Ͽ� �� ����� ResultSet ��ü�� �޾Ƽ� �����͸� ó���Ѵ�.
   ó���� �Ϸ�Ǹ� ó���� ���� ���ҽ����� close�Ͽ� ��ȯ�Ѵ�.


**������, DBCP �� ����ϸ�
1.WAS�� ����Ǹ鼭 �̸� �������� DB Connection ��ü�� �����ϰ� Pool �̶�� ������ ������ �д�.
2.HTTP ��û�� ���� �ʿ��� �� Pool���� Connection ��ü�� ������ ���� ��ȯ�Ѵ�.
3. �̿� ���� ������� HTTP ��û ���� DB Driver�� �ε��ϰ� �������� ���ῡ ���� Connection ��ü�� �����ϴ� ����� �پ��� �ȴ�.


 �� �� ���� ��� Connection ��ü�� ������ ���ΰ�,
�ּ� ��� ������ ���ΰ�,
�ִ� ��� ������ ���ΰ�,
�ִ� ���� ����ϴ� Connection �� � ���� ����� ���ΰ�
��� �� properties ������ ���� �����ϰ� �ȴ�.


jdbcUrl = � ��� ����Ǿ��ִ°� (jdbc:mysql://localhost:3306/board?characterEncoding=utf8&amp;serverTimezone=UTC)
initialSize : ���� ������ getConnection() �� ���� Ŀ�ؼ� Ǯ�� ä�� ���� Ŀ�ؼ� ���� (default = 0)
maxTotal (1.x������ maxActive): ���ÿ� ����� �� �ִ� �ִ� Ŀ�ؼ� ���� (default = 8)
maxIdle : Connection Pool�� �ݳ��� �� �ִ�� ������ �� �ִ� Ŀ�ؼ� ���� (default = 8)
minIdle : �ּ������� ������ Ŀ�ؼ� ���� (default = 0)
maxWaitMillis (1.x������ maxWait) : pool�� ���Ǿ��� ��� �ִ� ��� �ð� (ms����, default = -1 = ������)
testOnBorrow = Connection �� ������ �� validationQuery�� ���� Ȯ���غ���� ����
validationQuery = select 1 DBMS������ ���� �޶��� �� ������ ���� �̷��� ��� �Ѵ�.
*/
