  package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

//p540
//��û URI�� ��ɾ�� ����ϱ����� Ŭ����

public class ControllerUsingURI extends HttpServlet {
    //�ʵ�
    // <Ŀ�ǵ�, �ڵ鷯�ν��Ͻ�> ���� ���� ����
    private Map<String, CommandHandler> commandHandlerMap = 
    		new HashMap<>();

    //�޼���
    //init()�� ������ ó�� �޸𸮿� �ø��� ����Ǿ�, ������ �ʱ�ȭ�ϸ� ó���� �ѹ��� ����. 
    public void init() throws ServletException {
		// web.xml���� �����κп���   /WEB-INF/commandHandlerURI.properties�� ������
		// StringŸ��  configFile������ ����
        String configFile = getInitParameter("configFile");
        Properties prop = new Properties();//Properties��ü
        String configFilePath = getServletContext().getRealPath(configFile);
        
        //���ൿ���� �� �ִ� ���Ϸ� �����
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        //Key����� ��������
        Iterator keyIter = prop.keySet().iterator();
        
        //Key�� �ִ¸�ŭ �ݺ�
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();
            //������ Key�� client�� ��û����
            
            String handlerClassName = prop.getProperty(command);
            try {
            	//������ �� �ִ� ���Ͽ��� Ư�� ������ ã�´� -> ������ �� �ִ� ���ϸ���� �����
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = 
                        (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    //doGet()�� get������� ��û�� ȣ��Ǵ� �޼���
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    //doPost()�� post������� ��û�� ȣ��Ǵ� �޼���
    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /*URI�� ��ɾ�� ����Ϸ��� ��Ʈ�ѷ� ������ process()���� �ν��� �� �־�� �Ѵ�.*/
    private void process(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
		
		//��ûURI���� request.getContextPath()�κ��� �����Ͽ� ��ûURI�� ����ϱ�
		String command = request.getRequestURI();// /���ؽ�Ʈ�н�/~~~
		if (command.indexOf(request.getContextPath()) == 0) {//��ûURI���� request.getContextPath()�κ�ã��
			command = command.substring(request.getContextPath().length());
		}
		
		//�����Ʈ�ѷ��� ��������
		//������ �����ߴ� Map<String, CommandHandler> commandHandlerMap
		//Ű�� �ش��ϴ� command�� �̿��Ͽ�
		// ���� �ش��ϴ� CommandHandler�� Map���� ������
		
        CommandHandler handler = commandHandlerMap.get(command);
        if (handler == null) {
            handler = new NullHandler();
        }
        String viewPage = null;
        try {
        	//(������) �����Ʈ�ѷ��� process()�޼��带 ȣ���ض�
        	//(���⿡����)��� ��Ʈ�ѷ��� �ݵ�� CommandHandler�������̽��� �����ϰ� �ִ�.
        	//��� ��Ʈ�ѷ��� process() �����ϴ� StringŸ���� view(jsp����)��
        	//StringŸ�� viewPage�� ����
            viewPage = handler.process(request, response);
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        if (viewPage != null) {//viewPage�� �����ϸ�
        	//�ش�  viewPage�� �������� �̵��ض�-> �������� ����ض�
	        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	        dispatcher.forward(request, response);
        }
    }
}








