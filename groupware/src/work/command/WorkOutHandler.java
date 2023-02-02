package work.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import work.model.Work;
import work.service.WorkService;

public class WorkOutHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/work.do";
	
	private WorkService workService = new WorkService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("workOutHandler Process()");
		User authUser = (User)request.getSession().getAttribute("AUTHUSER");
		int emp_no = authUser.getEmp_no();
		//Work work1 = null;
		
		
		Work work = workService.selectIn(emp_no);
		if(work == null) {
			request.setAttribute("msg", "아직 출근등록이 되지 않았습니다.");
			request.setAttribute("url", "/work.do");
			return "/view/worl/work_error.jsp";
		}
		if(work.getWork_out_time() !=null) {
			request.setAttribute("msg", "이미 퇴근처리되었습니다.");
			request.setAttribute("url", "/work.do");
			return "/view/error.jsp";
		}
		
		workService.workOut(emp_no);
		//List<Work> workList = workService.selectMonth(emp_no);
		//work1 = workService.selectIn(emp_no);
		
		//request.setAttribute("work", work1);
		//request.setAttribute("monthList", workList);
		return FORM_VIEW;
	}

}
