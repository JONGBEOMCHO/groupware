package work.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import work.model.Work;
import work.service.WorkService;

public class WorkInHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/work.do";
	
	private WorkService workService = new WorkService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("workInHandler Process()");
		User authUser = (User)request.getSession().getAttribute("AUTHUSER");
		int emp_no = authUser.getEmp_no();
		//Work work1 = null;
		
		
		Work work = workService.selectIn(emp_no);
		if(work != null) {
			
			request.setAttribute("msg", "이미 출근처리되었습니다.");
			request.setAttribute("url", "/work.do");
			return "/view/error.jsp";
		}
		
		workService.workIn(emp_no);
		//List<Work> workList = workService.selectMonth(emp_no);
		//work1 = workService.selectIn(emp_no);
		
		//Model
		//request.setAttribute("work", work1);
		//request.setAttribute("monthList", workList);
		return FORM_VIEW;
	}

}
